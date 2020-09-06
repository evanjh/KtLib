@file:Suppress("unused")

package io.nekohasekai.ktlib.td.i18n.store

import cn.hutool.core.util.RuntimeUtil
import io.nekohasekai.ktlib.db.DatabaseCacheMap
import io.nekohasekai.ktlib.db.DatabaseDispatcher
import io.nekohasekai.ktlib.db.upsert
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select

class DatabaseStore @JvmOverloads constructor(database: DatabaseDispatcher, tableName: String = "td_locales", cacheTime: Long = 6 * 60 * 60 * 1000L) : DatabaseCacheMap<Long, Int>(database, 0, cacheTime), LocaleStore {

    private val table = LocaleTable(tableName)

    init {

        database.write {

            SchemaUtils.createMissingTablesAndColumns(table)

        }

    }

    class LocaleTable(tableName: String) : Table(tableName) {

        var chatId = long("chatId").uniqueIndex()
        var locale = integer("locale")

    }

    override fun read(id: Long): Int? {

        return table.select { table.chatId eq id }.firstOrNull()?.let { it[table.locale] }

    }

    override fun write(id: Long, value: Int) {

        table.upsert(table.chatId) {

            it[table.chatId] = id
            it[table.locale] = value

        }

    }

    override fun delete(id: Long) {

        table.deleteWhere { table.chatId eq id }

    }

    override fun getByChat(chatId: Long) = fetch(chatId).value

    override fun save(chatId: Long, localeId: Int) {

        with(fetch(chatId)) {

            value = localeId

            changed = true

        }

    }
}