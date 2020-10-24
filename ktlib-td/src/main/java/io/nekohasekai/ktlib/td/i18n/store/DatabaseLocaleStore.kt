@file:Suppress("unused")

package io.nekohasekai.ktlib.td.i18n.store

import io.nekohasekai.ktlib.db.*
import org.jetbrains.exposed.sql.*

class DatabaseLocaleStore @JvmOverloads constructor(database: DatabaseDispatcher, tableName: String = "td_locales", capability: Int = 0, cacheTime: Long = 0L) : DatabaseCacheMap<Long, Int>(database, capability, cacheTime), LocaleStore {

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

        fetch(chatId).set(localeId)

    }

}