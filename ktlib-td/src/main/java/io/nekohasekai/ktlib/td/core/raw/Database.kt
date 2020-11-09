@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Changes the database encryption key
 * Usually the encryption key is never changed and is stored in some OS keychain
 *
 * @newEncryptionKey - New encryption key
 */
suspend fun TdHandler.setDatabaseEncryptionKey(
    newEncryptionKey: ByteArray
){
    sync(SetDatabaseEncryptionKey(newEncryptionKey))
}


suspend fun TdHandler.setDatabaseEncryptionKeyIgnoreException(
    newEncryptionKey: ByteArray
){
    syncOrNull(SetDatabaseEncryptionKey(newEncryptionKey))
}


fun TdHandler.setDatabaseEncryptionKeyWith(
    newEncryptionKey: ByteArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetDatabaseEncryptionKey(newEncryptionKey), stackIgnore + 1, submit)

/**
 * Returns database statistics
 */
suspend fun TdHandler.getDatabaseStatistics() = sync(GetDatabaseStatistics())

suspend fun TdHandler.getDatabaseStatisticsOrNull() = syncOrNull(GetDatabaseStatistics())

fun TdHandler.getDatabaseStatisticsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<DatabaseStatistics>.() -> Unit)? = null
) = send(GetDatabaseStatistics(), stackIgnore + 1, submit)
