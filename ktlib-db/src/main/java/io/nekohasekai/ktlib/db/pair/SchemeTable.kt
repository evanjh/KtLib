package io.nekohasekai.ktlib.db.pair

import io.nekohasekai.ktlib.core.fromByteArray
import io.nekohasekai.ktlib.core.toByteArray
import io.nekohasekai.ktlib.db.upsert
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.api.ExposedBlob

class SchemeTable(tableName: String = "scheme") : Table(tableName) {

    val id = text("id").uniqueIndex()
    val value = blob("value").nullable()

    fun <T> getItem(key: String, type: Class<T>): T? {

        return select { id eq key }.firstOrNull()?.let { it[value] }?.bytes?.fromByteArray(type)

    }

    inline fun <reified T> getItem(key: String): T? {

        return getItem(key, T::class.java)

    }

    fun <T> setItem(key: String, value: T) {

        upsert(id) { statement ->
            statement[id] = key
            statement[this.value] = (value as Any?)?.toByteArray()?.let { ExposedBlob(it) }
        }

    }

}

