@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Changes the database encryption key
 * Usually the encryption key is never changed and is stored in some OS keychain
 *
 * @newEncryptionKey - New encryption key
 */
suspend fun TdHandler.setDatabaseEncryptionKey(
        newEncryptionKey: ByteArray
) = sync<Ok>(SetDatabaseEncryptionKey(newEncryptionKey))

suspend fun TdHandler.setDatabaseEncryptionKeyOrNull(
        newEncryptionKey: ByteArray
) = syncOrNull<Ok>(SetDatabaseEncryptionKey(newEncryptionKey))

fun TdHandler.setDatabaseEncryptionKeyWith(
        newEncryptionKey: ByteArray,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetDatabaseEncryptionKey(newEncryptionKey), stackIgnore + 1, submit)

/**
 * Returns database statistics
 */
suspend fun TdHandler.getDatabaseStatistics() = sync<DatabaseStatistics>(GetDatabaseStatistics())

suspend fun TdHandler.getDatabaseStatisticsOrNull() = syncOrNull<DatabaseStatistics>(GetDatabaseStatistics())

fun TdHandler.getDatabaseStatisticsWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<DatabaseStatistics>.() -> Unit)? = null
) = send(GetDatabaseStatistics(), stackIgnore + 1, submit)
