@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.i18n.store

import io.nekohasekai.ktlib.db.*
import org.jetbrains.exposed.sql.*

class DatabaseLocaleStore @JvmOverloads constructor(database: DatabaseDispatcher, tableName: String = "td_locales", cacheTime: Long = 6 * 60 * 60 * 1000L) : DatabaseCacheMap<Long, Int>(database, 0, cacheTime), LocaleStore {

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

    override fun localeRead(chatId: Long) = fetch(chatId).value

    override fun localeSave(chatId: Long, localeId: Int) {

        with(fetch(chatId)) {

            value = localeId

            changed = true

        }

    }

    override fun localeGc() = gc()

}