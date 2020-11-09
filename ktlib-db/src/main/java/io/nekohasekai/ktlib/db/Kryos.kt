package io.nekohasekai.ktlib.db

import io.nekohasekai.ktlib.core.*
import org.jetbrains.exposed.sql.*
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
        return if (clazz.isInstance(value)) {
            ExposedBlob(value.toByteArray())
        } else if (currentDialect.dataTypeProvider.blobAsStream && value is Blob) {
            value.binaryStream
        } else {
            value
        }
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun readObject(rs: ResultSet, index: Int) = if (currentDialect.dataTypeProvider.blobAsStream) {
        rs.getBytes(index)
    } else {
        rs.getBlob(index)?.binaryStream
    }?.let { valueFromDB(it) }

    override fun nonNullValueToString(value: Any) = "?"

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        val toSetValue = (value as? ExposedBlob)?.bytes?.inputStream() ?: value
        when {
            currentDialect.dataTypeProvider.blobAsStream && toSetValue is InputStream -> stmt.setInputStream(index, toSetValue)
            toSetValue == null -> stmt.setInputStream(index, toSetValue)
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
        return if (clazz.isInstance(value)) {
            ExposedBlob(value.toByteArray(true))
        } else if (currentDialect.dataTypeProvider.blobAsStream && value is Blob) {
            value.binaryStream
        } else {
            value
        }
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun readObject(rs: ResultSet, index: Int) = if (currentDialect.dataTypeProvider.blobAsStream) {
        rs.getBytes(index)
    } else {
        rs.getBlob(index)?.binaryStream
    }?.let { valueFromDB(it) }

    override fun nonNullValueToString(value: Any) = "?"

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        val toSetValue = (value as? ExposedBlob)?.bytes?.inputStream() ?: value
        when {
            currentDialect.dataTypeProvider.blobAsStream && toSetValue is InputStream -> stmt.setInputStream(index, toSetValue)
            toSetValue == null -> stmt.setInputStream(index, toSetValue)
            else -> super.setParameter(stmt, index, toSetValue)
        }
    }

}