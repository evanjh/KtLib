@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.persists.store

import cn.hutool.core.date.SystemClock
import io.nekohasekai.ktlib.core.*
import io.nekohasekai.ktlib.db.DatabaseCacheMap
import io.nekohasekai.ktlib.db.DatabaseDispatcher
import io.nekohasekai.ktlib.td.core.TdClient
import io.nekohasekai.ktlib.td.extensions.mkData
import io.nekohasekai.ktlib.td.extensions.readData
import io.nekohasekai.ktlib.td.core.persists.TdPersist
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import java.math.BigInteger

class DatabaseUniquePersistStore @JvmOverloads constructor(database: DatabaseDispatcher, tableName: String = "td_unique_persists", cacheTime: Long = 6 * 60 * 60 * 1000L) : DatabaseCacheMap<Pair<Int, Int>, TdPersist>(database, 0, cacheTime), PersistStore {

    private val table = PersistsTable(tableName)

    init {

        database.write {

            SchemaUtils.createMissingTablesAndColumns(table)

        }

    }

    class PersistsTable(tableName: String) : Table(tableName) {

        val botId = integer("bot_id").index()
        val userId = integer("user_id").index()
        val persistId = integer("persist_id")
        val data = blob("data")
        val allowFunction = bool("allow_function")
        val allowCancel = bool("allow_cancel")
        val createAt = integer("create_at").index()

        override val primaryKey = PrimaryKey(botId, userId)

    }

    override fun read(id: Pair<Int, Int>): TdPersist? {

        return runCatching {

            table.select { (table.botId eq id.first) and (table.userId eq id.second) }.firstOrNull()?.let { row ->

                val dataArray = row[table.data].bytes.readData()

                val dataId = if (dataArray.isEmpty()) 0L else dataArray[0].asLong

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

            table.deleteWhere { (table.botId eq id.first) and (table.userId eq id.second) }

        }.getOrNull()

    }

    override fun write(id: Pair<Int, Int>, value: TdPersist) {

        table.deleteWhere { (table.botId eq id.first) and (table.userId eq id.second) }

        table.insert { statement ->

            statement[table.userId] = value.userId
            statement[table.persistId] = value.persistId
            statement[table.data] = ExposedBlob(mkData(value.subId,
                    * value.data.map { it?.toByteArray(true) ?: byteArrayOf() }.toTypedArray()
            ))
            statement[table.allowFunction] = value.allowFunction
            statement[table.allowCancel] = value.allowCancel
            statement[table.createAt] = value.createAt

        }

    }

    override fun delete(id: Pair<Int, Int>) {

        table.deleteWhere { (table.botId eq id.first) and (table.userId eq id.second) }

    }

    override fun persistRead(client: TdClient, userId: Int): TdPersist? {

        return fetch(client.me.id to userId).value

    }

    override fun persistSave(client: TdClient, persist: TdPersist) {

        with(fetch(client.me.id to persist.userId)) {

            value = persist
            changed = true

        }

    }

    override fun persistRemove(client: TdClient, userId: Int) {

        with(fetch(client.me.id to userId)) {

            value = null
            changed = true

        }

    }

    override fun persistSaveAll(client: TdClient) {

        forEach { onRemove(it.id, it) }

    }

    override fun persistGc(client: TdClient) {

        persistSaveAll(client)

        clear()

        val time = (SystemClock.now() / 1000L).toInt() - 24 * 60 * 60

        database {

            val result = table.select { (table.botId eq client.me.id) and (table.createAt less time) }

            result.forEach { row ->

                val dataArray = row[table.data].bytes.readData()

                val dataId = BigInteger(dataArray[0]).toLong()

                val persist = TdPersist(
                        row[table.userId],
                        row[table.persistId],
                        dataId,
                        arrayOf(* dataArray.shift().map { if (it.isEmpty()) null else it.anyFormByteArray() }.toTypedArray()),
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

            table.deleteWhere { (table.botId eq client.me.id) and (table.createAt less time) }

        }

    }

}