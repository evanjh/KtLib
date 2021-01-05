@file:Suppress("unused")

package io.nekohasekai.ktlib.db

import cn.hutool.cache.impl.LFUCache
import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.ArrayUtil
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.nekohasekai.ktlib.core.defaultLog
import io.nekohasekai.ktlib.core.mkLog
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.StatementContext
import org.jetbrains.exposed.sql.statements.UpsertStatement
import org.jetbrains.exposed.sql.statements.expandArgs
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.sqlite.SQLiteDataSource
import java.io.File
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.sql.DataSource
import kotlin.concurrent.read
import kotlin.concurrent.write

interface DatabaseInterface {

    val database: DatabaseDispatcher

}

interface DatabaseDispatcher : DataSource {

    val database: Database

    operator fun <R> invoke(statement: Transaction.() -> R): R

    fun <R> write(statement: Transaction.() -> R): R

    fun registerCache(cache: DatabaseCacheMap<*, *>)

    fun saveCache()

    fun saveAndGc()

}

object DefaultLogSqlLogger : SqlLogger {
    override fun log(context: StatementContext, transaction: Transaction) {
        defaultLog.debug("Exec: ${context.expandArgs(transaction)}")
    }
}

abstract class AbstractDispatcher : DatabaseDispatcher {

    val cacheMaps = LinkedHashSet<DatabaseCacheMap<*, *>>()

    override fun registerCache(cache: DatabaseCacheMap<*, *>) {

        cacheMaps.add(cache)

    }

    override fun saveCache() {

        for (map in cacheMaps) map.save()

    }

    override fun saveAndGc() {

        for (map in cacheMaps) map.saveAndGc()

    }

}

open class WriteLockDataSourceDispatcher(val dataSource: DataSource) : AbstractDispatcher(), DataSource by dataSource {

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

fun openSqliteDatabase(file: File): DatabaseDispatcher {

    FileUtil.touch(file)

    return WriteLockDataSourceDispatcher(HikariDataSource(HikariConfig().apply {

        dataSource = SQLiteDataSource().apply {

            url = "jdbc:sqlite:${file.canonicalPath}"

        }

    }))

}

fun openInMemorySqliteDatabase(shared: Boolean = false): DatabaseDispatcher {

    return WriteLockDataSourceDispatcher(SQLiteDataSource().apply {

        url = if (!shared) "jdbc:sqlite::memory:" else "jdbc:sqlite::memory:?cache=shared"

    })

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

private val dbLog = mkLog("Database")

abstract class DatabaseCache<ID, T>(
    val id: ID,
    var value: T?,
    var changed: Boolean
) {

    fun remove() = set(null)

    fun set(value: T?) {
        if (this.value == value ||
            ArrayUtil.isArray(value) && ArrayUtil.isArray(this.value) && ArrayUtil.equals(value, this.value)
        ) return
        write(value)
    }

    fun notifyChanged() {
        changed = true
    }

    fun write(value: T? = this.value) {
        this.value = value
        changed = true
        flush()
    }

    abstract fun flush()

}

abstract class KeyValueTable<ID : Comparable<ID>, T>(tableName: String) : IdTable<ID>(tableName) {

    abstract val value: Column<T>

}

open class KeyValueCacheMap<ID : Comparable<ID>, T>(
    database: DatabaseDispatcher,
    val table: KeyValueTable<ID, T>,
    capacity: Int = -1,
    timeout: Long = 0
) : DatabaseCacheMap<ID, T>(database, capacity, timeout) {

    override fun read(id: ID): T? {

        return table.select { table.id eq id }.firstOrNull()?.let { it[table.value] }

    }

    override fun write(id: ID, value: T) {

        table.upsert(table.id) {

            it[table.id] = EntityID(id, table)
            it[table.value] = value

        }

    }

    override fun delete(id: ID) {

        table.deleteWhere { table.id eq id }

    }

}

open class IdTableCacheMap<ID : Comparable<ID>, T : Entity<ID>>(
    database: DatabaseDispatcher,
    val entityClass: EntityClass<ID, T>,
    capacity: Int = -1,
    timeout: Long = 3 * 60 * 60 * 100L
) : DatabaseCacheMap<ID, T>(database, capacity, timeout) {

    override fun read(id: ID): T? = entityClass.findById(id)

    override fun write(id: ID, value: T) {

        value.flush()

    }

    override fun delete(id: ID) {

        entityClass.table.deleteWhere { entityClass.table.id eq id }

    }

}

@Suppress("UNCHECKED_CAST")
open class TwoIndexExistsCacheMap<IX, IY>(
    database: DatabaseDispatcher,
    val table: Table,
    val xColumn: Column<IX>,
    val yColumn: Column<IY>,
    capacity: Int = -1,
    timeout: Long = 0
) : DatabaseCacheMap<Pair<IX, IY>, Boolean>(database, capacity, timeout) {

    override fun read(id: Pair<IX, IY>) = table.select { (xColumn eq id.first) and (yColumn eq id.second) }.count() > 0L

    override fun write(id: Pair<IX, IY>, value: Boolean) {

        val filter = (xColumn eq id.first) and (yColumn eq id.second)

        if (!value) {
            table.deleteWhere { filter }
        } else try {
            table.insert {
                it[xColumn] = id.first
                it[yColumn] = id.second
            }
        } catch (ex: ExposedSQLException) {
            defaultLog.debug("insert failed", ex)
        }

    }

    override fun delete(id: Pair<IX, IY>) {

        table.deleteWhere { (xColumn eq id.first) and (yColumn eq id.second) }

    }

}

@Suppress("UNCHECKED_CAST")
open class TwoIndexCacheMap<IX, IY, T>(
    database: DatabaseDispatcher,
    val table: Table,
    val xColumn: Column<IX>,
    val yColumn: Column<IY>,
    val tColumn: Column<T>,
    capacity: Int = -1,
    timeout: Long = 0
) : DatabaseCacheMap<Pair<IX, IY>, T>(database, capacity, timeout) {

    override fun read(id: Pair<IX, IY>) =
        table.select { (xColumn eq id.first) and (yColumn eq id.second) }.firstOrNull()?.get(tColumn)

    override fun write(id: Pair<IX, IY>, value: T) {

        val filter = (xColumn eq id.first) and (yColumn eq id.second)

        fun insert() = table.insert {

            it[xColumn] = id.first
            it[yColumn] = id.second
            it[tColumn] = value

        }

        try {

            if (table.update({ filter }) { it[tColumn] = value } == 0) {

                insert()

            }

        } catch (ex: ExposedSQLException) {

            try {

                table.deleteWhere { filter }
                insert()

            } catch (exc: ExposedSQLException) {

                dbLog.warn(exc, "Write failed: ")

            }

        }

    }

    override fun delete(id: Pair<IX, IY>) {

        table.deleteWhere { (xColumn eq id.first) and (yColumn eq id.second) }

    }

}

abstract class DatabaseCacheMap<ID, T>(val database: DatabaseDispatcher, capacity: Int = -1, timeout: Long = 0) :
    LFUCache<ID, DatabaseCache<ID, T>>(capacity, timeout) {

    init {

        @Suppress("LeakingThis")
        database.registerCache(this)

    }

    fun save() = synchronized(this) {

        forEach { onRemove(it.id, it) }

    }

    fun saveAndGc() = synchronized(this) {

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

    fun fetch(id: ID, inDb: Boolean = false): DatabaseCache<ID, T> = get(id) {

        object : DatabaseCache<ID, T>(id, if (inDb) read(id) else database { read(id) }, false) {

            override fun flush() {

                onRemove(id, this)

            }

        }

    }

}