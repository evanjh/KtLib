@file:Suppress("unused")

package io.nekohasekai.ktlib.db

import io.nekohasekai.ktlib.core.anyFormByteArray
import io.nekohasekai.ktlib.core.fromByteArray
import io.nekohasekai.ktlib.core.toByteArray
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import org.jetbrains.exposed.sql.vendors.currentDialect
import java.io.InputStream
import java.sql.Blob
import java.sql.ResultSet
import kotlin.reflect.KClass

inline fun <reified T : Any> Table.kryo(name: String): Column<T> = registerColumn(name, KryoColumnType(T::class))
inline fun <reified T : Any> Table.kryoAny(name: String): Column<T> = registerColumn(name, KryoAnyColumnType(T::class))

class KryoColumnType<T : Any>(val clazz: KClass<T>) : ColumnType() {

    override fun sqlType() = currentDialect.dataTypeProvider.blobType()

    @Suppress("UNCHECKED_CAST")
    override fun valueFromDB(value: Any): T {

        if (clazz.isInstance(value)) return value as T

        return when (value) {
            is ExposedBlob -> value
            is Blob -> ExposedBlob(value.binaryStream.use { it.readBytes() })
            is InputStream -> ExposedBlob(value.use { it.readBytes() })
            is ByteArray -> ExposedBlob(value)
            else -> error("Unexpected value of type Blob: $value of ${value::class.qualifiedName}")
        }.bytes.fromByteArray(clazz.java)

    }

    override fun notNullValueToDB(value: Any): Any {
        return when {
            clazz.isInstance(value) -> ExposedBlob(value.toByteArray())
            value is Blob -> value.binaryStream
            else -> value
        }
    }

    override fun readObject(rs: ResultSet, index: Int) = rs.getBytes(index)?.let { valueFromDB(it) }

    override fun nonNullValueToString(value: Any) = "?"

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        when (val toSetValue = (value as? ExposedBlob)?.bytes?.inputStream() ?: value) {
            is InputStream -> stmt.setInputStream(index, toSetValue)
            null -> stmt.setNull(index, this)
            else -> super.setParameter(stmt, index, toSetValue)
        }
    }

}

class KryoAnyColumnType<T : Any>(val clazz: KClass<T>) : ColumnType() {

    override fun sqlType() = currentDialect.dataTypeProvider.blobType()

    @Suppress("UNCHECKED_CAST")
    override fun valueFromDB(value: Any): T {

        if (clazz.isInstance(value)) return value as T

        return when (value) {
            is ExposedBlob -> value
            is Blob -> ExposedBlob(value.binaryStream.use { it.readBytes() })
            is InputStream -> ExposedBlob(value.use { it.readBytes() })
            is ByteArray -> ExposedBlob(value)
            else -> error("Unexpected value of type Blob: $value of ${value::class.qualifiedName}")
        }.bytes.anyFormByteArray() as T

    }

    override fun notNullValueToDB(value: Any): Any {
        return when {
            clazz.isInstance(value) -> ExposedBlob(value.toByteArray(true))
            value is Blob -> value.binaryStream
            else -> value
        }
    }

    override fun readObject(rs: ResultSet, index: Int) = rs.getBytes(index)?.let { valueFromDB(it) }

    override fun nonNullValueToString(value: Any) = "?"

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        when (val toSetValue = (value as? ExposedBlob)?.bytes?.inputStream() ?: value) {
            is InputStream -> stmt.setInputStream(index, toSetValue)
            null -> stmt.setNull(index, this)
            else -> super.setParameter(stmt, index, toSetValue)
        }
    }

}