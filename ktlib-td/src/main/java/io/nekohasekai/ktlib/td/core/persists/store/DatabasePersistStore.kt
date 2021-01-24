@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.persists.store

import cn.hutool.core.date.SystemClock
import com.esotericsoftware.kryo.KryoException
import io.nekohasekai.ktlib.core.anyFormByteArray
import io.nekohasekai.ktlib.core.defaultLog
import io.nekohasekai.ktlib.core.shift
import io.nekohasekai.ktlib.core.toByteArray
import io.nekohasekai.ktlib.db.DatabaseCacheMap
import io.nekohasekai.ktlib.db.DatabaseDispatcher
import io.nekohasekai.ktlib.db.upsert
import io.nekohasekai.ktlib.td.core.TdClient
import io.nekohasekai.ktlib.td.core.persists.TdPersist
import io.nekohasekai.ktlib.td.extensions.asInt
import io.nekohasekai.ktlib.td.extensions.mkData
import io.nekohasekai.ktlib.td.extensions.readData
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import java.math.BigInteger

class DatabasePersistStore @JvmOverloads constructor(
    database: DatabaseDispatcher,
    tableName: String = "td_persists",
    cacheTime: Long = 6 * 60 * 60 * 1000L
) : DatabaseCacheMap<Int, TdPersist>(database, 0, cacheTime), PersistStore {

    private val table = PersistTable(tableName)

    init {

        database.write {

            SchemaUtils.createMissingTablesAndColumns(table)

        }

    }

    class PersistTable(tableName: String) : Table(tableName) {

        val userId = integer("user_id").uniqueIndex()
        val persistId = integer("persist_id")
        val data = blob("data")
        val allowFunction = bool("allow_function")
        val allowCancel = bool("allow_cancel")
        val createAt = integer("create_at").index()

    }

    override fun read(id: Int): TdPersist? {

        return runCatching {

            table.select { table.userId eq id }.firstOrNull()?.let { row ->

                val dataArray = row[table.data].bytes.readData()

                val dataId = if (dataArray.isEmpty()) 0 else dataArray[0].asInt()

                TdPersist(
                    row[table.userId],
                    row[table.persistId],
                    dataId,
                    arrayOf(* dataArray.shift().map { it.anyFormByteArray() }.toTypedArray()),
                    row[table.allowFunction],
                    row[table.allowCancel],
                    row[table.createAt]
                )

            }

        }.onFailure {

            defaultLog.warn(it)
            table.deleteWhere { table.userId eq id }

        }.getOrNull()

    }

    override fun write(id: Int, value: TdPersist) {

        table.upsert(table.userId) { statement ->

            statement[table.userId] = value.userId
            statement[table.persistId] = value.persistId
            statement[table.data] = ExposedBlob(
                mkData(
                    value.subId,
                    * value.data.map { it?.toByteArray(true) ?: byteArrayOf() }.toTypedArray()
                )
            )
            statement[table.allowFunction] = value.allowFunction
            statement[table.allowCancel] = value.allowCancel
            statement[table.createAt] = value.createAt

        }

    }

    override fun delete(id: Int) {

        table.deleteWhere { table.userId eq id }

    }

    override fun persistRead(client: TdClient, userId: Int): TdPersist? {

        return fetch(userId).value

    }

    override fun persistSave(client: TdClient, persist: TdPersist) {

        fetch(persist.userId).set(persist)

    }

    override fun persistRemove(client: TdClient, userId: Int) {

        fetch(userId).set(null)

    }

    override fun persistSaveAll(client: TdClient) {

        forEach { onRemove(it.id, it) }

    }

    override fun persistGc(client: TdClient) {

        persistSaveAll(client)

        clear()

        val time = (SystemClock.now() / 1000L).toInt() - 24 * 60 * 60

        database {

            val result = table.select { table.createAt less time }

            result.forEach { row ->

                val dataArray = try {
                    row[table.data].bytes.readData()
                } catch (e: KryoException) {
                    arrayOf()
                }

                val dataId = BigInteger(dataArray[0]).toInt()

                val persist = TdPersist(
                    row[table.userId],
                    row[table.persistId],
                    dataId,
                    arrayOf(* dataArray.shift().map { if (it.isEmpty()) null else it.anyFormByteArray() }
                        .toTypedArray()),
                    row[table.allowFunction],
                    row[table.allowCancel],
                    row[table.createAt]
                )

                TdClient.events.launch {

                    runCatching {

                        client.persistHandlers[persist.persistId]?.apply {

                            onPersistTimeout(persist.userId, persist.userId.toLong(), persist.subId, persist.data)

                            onSendTimeoutMessage(persist.userId, persist.userId.toLong())

                        }

                    }.onFailure {

                        it.printStackTrace()

                    }

                }

            }

        }

        database.write {

            table.deleteWhere { table.createAt less time }

        }

    }

}