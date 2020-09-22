package io.nekohasekai.ktlib.db

import cn.hutool.cache.impl.LFUCache
import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.RuntimeUtil
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.nekohasekai.ktlib.core.defaultLog
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpsertStatement
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.sqlite.SQLiteDataSource
import java.io.File
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.sql.DataSource
import kotlin.concurrent.read
import kotlin.concurrent.write

interface DatabaseDispatcher : DataSource {

    val database: Database

    operator fun <R> invoke(statement: Transaction.() -> R): R

    fun <R> write(statement: Transaction.() -> R): R

}

fun openSqliteDatabase(file: File): DatabaseDispatcher {

    FileUtil.touch(file)

    val dataSource = HikariDataSource(HikariConfig().apply {

        dataSource = SQLiteDataSource().apply {

            url = "jdbc:sqlite:${file.canonicalPath}"

        }

    })

    return object : DatabaseDispatcher, DataSource by dataSource {

        override val database by lazy { Database.connect(this) }

        val readWriteLock = ReentrantReadWriteLock()

        override fun <R> invoke(statement: Transaction.() -> R) = readWriteLock.read {

            transaction(database) {

                statement()

            }

        }

        override fun <R> write(statement: Transaction.() -> R) = readWriteLock.write {

            transaction(database) {

                statement()

            }

        }
    }

}

fun forceCreateTables(vararg tables: Table) {

    for (table in tables) {

        try {

            SchemaUtils.createMissingTablesAndColumns(table)

        } catch (e: ExposedSQLException) {

            defaultLog.warn("Destroy old table ${table.nameInDatabaseCase()}")

            SchemaUtils.drop(table)

            SchemaUtils.createMissingTablesAndColumns(table)

        }

    }

}

inline fun <T : Table> T.upsert(
        conflictColumn: Column<*>? = null,
        conflictIndex: Index? = null,
        body: T.(UpsertStatement<Number>) -> Unit
) = UpsertStatement<Number>(this, conflictColumn, conflictIndex).apply {
    body(this)
    execute(TransactionManager.current())
}

abstract class DatabaseCache<ID, T>(
        val id: ID,
        var value: T?,
        var changed: Boolean
) {

    abstract fun flush()

}

abstract class KeyValueTable<ID : Comparable<ID>, T>(tableName: String) : IdTable<ID>(tableName) {

    abstract val value: Column<T>

}

open class KeyValueCacheMap<ID : Comparable<ID>, T>(database: DatabaseDispatcher, val table: KeyValueTable<ID, T>, capacity: Int = -1, timeout: Long = 0) : DatabaseCacheMap<ID, T>(database, capacity, timeout) {

    override fun read(id: ID): T? {

        return table.select { table.id eq id }.firstOrNull()?.let { it[table.value] }

    }

    override fun write(id: ID, value: T) {

        delete(id)

        table.insert {

            it[table.id] = EntityID(id, table)
            it[table.value] = value

        }

    }

    override fun delete(id: ID) {

        table.deleteWhere { table.id eq id }

    }

}

open class IdTableCacheMap<ID : Comparable<ID>, T : Entity<ID>>(database: DatabaseDispatcher, val entityClass: EntityClass<ID, T>, capacity: Int = -1, timeout: Long = 3 * 60 * 60 * 100L) : DatabaseCacheMap<ID, T>(database, capacity, timeout) {

    override fun read(id: ID): T? = entityClass.findById(id)

    override fun write(id: ID, value: T) {

        value.flush()

    }

    override fun delete(id: ID) {

        entityClass.table.deleteWhere { entityClass.table.id eq id }

    }

}

abstract class DatabaseCacheMap<ID, T>(val database: DatabaseDispatcher, capacity: Int = -1, timeout: Long = 0) : LFUCache<ID, DatabaseCache<ID, T>>(capacity, timeout) {

    init {

        RuntimeUtil.addShutdownHook {

            forEach { onRemove(it.id, it) }

        }

    }

    fun gc() {

        map { it.id }.forEach { remove(it) }

    }

    abstract fun read(id: ID): T?
    abstract fun write(id: ID, value: T)
    abstract fun delete(id: ID)

    override fun onRemove(key: ID, cachedObject: DatabaseCache<ID, T>) {

        if (cachedObject.changed) database.write {

            if (cachedObject.value != null) write(cachedObject.id, cachedObject.value!!) else delete(cachedObject.id)

            cachedObject.changed = false

        }

    }

    fun fetch(id: ID): DatabaseCache<ID, T> = get(id) {

        object : DatabaseCache<ID, T>(id, database { read(id) }, false) {

            override fun flush() {

                onRemove(id, this)

            }

        }

    }

}