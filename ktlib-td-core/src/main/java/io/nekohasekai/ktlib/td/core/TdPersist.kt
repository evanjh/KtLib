package io.nekohasekai.ktlib.td.core

import cn.hutool.cache.impl.LFUCache
import cn.hutool.core.date.SystemClock
import io.nekohasekai.ktlib.core.anyFormByteArray
import io.nekohasekai.ktlib.core.defaultLog
import io.nekohasekai.ktlib.core.shift
import io.nekohasekai.ktlib.core.toByteArray
import io.nekohasekai.ktlib.db.DatabaseDispatcher
import io.nekohasekai.ktlib.db.upsert
import io.nekohasekai.ktlib.td.core.extensions.mkData
import io.nekohasekai.ktlib.td.core.extensions.readData
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import java.math.BigInteger

class TdPersist(

        var userId: Int,
        var persistId: Int,
        var subId: Long,
        var data: Array<Any?>,
        var allowFunction: Boolean,
        var allowCancel: Boolean,
        var createAt: Int = (SystemClock.now() / 1000L).toInt(),
        var dontSave: Boolean = false

) {

    constructor() : this(0, 0, 0L, arrayOf(), false, true, 0)

    interface Impl {

        fun read(userIdToRead: Int): TdPersist?

        fun save(persist: TdPersist)

        fun remove(userIdToRemove: Int)

        fun gc(onTimeout: (TdPersist) -> Unit)

    }

    class Map(val thisRef: TdClient) : LFUCache<Int, Map.Cache>(64) {

        inner class Cache(
                var userId: Int,
                var tdPersist: TdPersist?,
                var changed: Boolean
        ) {

            fun save() {

                if (changed) {

                    val tdPersist = tdPersist

                    if (tdPersist == null) {

                        thisRef.persistImpl.remove(userId)

                    } else if (!tdPersist.dontSave) {

                        thisRef.persistImpl.save(tdPersist)

                    }

                    changed = false

                }

            }

        }

        fun fetch(userId: Int): Cache {

            return get(userId) { Cache(userId, thisRef.persistImpl.read(userId), false) }

        }

        override fun onRemove(userId: Int, cachedObject: Cache) {

            cachedObject.save()

        }

    }

    object NoImpl : Impl {

        override fun read(userIdToRead: Int): TdPersist? = null

        override fun save(persist: TdPersist) = Unit

        override fun remove(userIdToRemove: Int) = Unit

        override fun gc(onTimeout: (TdPersist) -> Unit) = Unit
    }

    class DatabaseImpl(val database: DatabaseDispatcher, val table: String) : Table(table), Impl {

        val userId = integer("user_id").uniqueIndex()
        val persistId = integer("persist_id")
        val data = blob("data")
        val allowFunction = bool("allow_function")
        val allowCancel = bool("allow_cancel")
        val createAt = integer("create_at").index()

        override val primaryKey by lazy { PrimaryKey(userId) }

        init {

            database.write {

                SchemaUtils.createMissingTablesAndColumns(this@DatabaseImpl)

            }

        }

        override fun gc(onTimeout: (TdPersist) -> Unit) {

            val time = (SystemClock.now() / 1000L).toInt() - 24 * 60 * 60

            database {

                val result = select { createAt less time }

                result.forEach { row ->

                    val dataArray = row[data].bytes.readData()

                    val dataId = BigInteger(dataArray[0]).toLong()

                    val persist = TdPersist(
                            row[userId],
                            row[persistId],
                            dataId,
                            arrayOf(* dataArray.shift().map { if (it.isEmpty()) null else it.anyFormByteArray() }.toTypedArray()),
                            row[allowFunction],
                            row[allowCancel],
                            row[createAt]
                    )

                    onTimeout(persist)

                }

            }

            database.write {

                deleteWhere { createAt less time }

            }

        }

        override fun read(userIdToRead: Int): TdPersist? = database {

            runCatching {

                select { userId eq userIdToRead }.firstOrNull()?.let { row ->

                    val dataArray = row[data].bytes.readData()

                    val dataId = if (dataArray.isEmpty()) 0L else BigInteger(dataArray[0]).toLong()

                    TdPersist(
                            row[userId],
                            row[persistId],
                            dataId,
                            arrayOf(* dataArray.shift().map { it.anyFormByteArray() }.toTypedArray()),
                            row[allowFunction],
                            row[allowCancel],
                            row[createAt]
                    )

                }

            }.onFailure {

                defaultLog.warn(it)

                deleteWhere { userId eq userIdToRead }

            }.getOrNull()

        }

        override fun remove(userIdToRemove: Int): Unit = database.write {

            deleteWhere { userId eq userIdToRemove }

        }

        override fun save(persist: TdPersist): Unit = database.write {

            upsert(userId) { statement ->

                statement[userId] = persist.userId
                statement[persistId] = persist.persistId
                statement[data] = ExposedBlob(mkData(persist.subId,
                        * persist.data.map { it?.toByteArray(true) ?: byteArrayOf() }.toTypedArray()
                ))
                statement[allowFunction] = persist.allowFunction
                statement[allowCancel] = persist.allowCancel
                statement[createAt] = persist.createAt

            }

        }

    }

}
