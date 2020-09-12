@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns storage usage statistics
 * Can be called before authorization
 *
 * @chatLimit - The maximum number of chats with the largest storage usage for which separate statistics should be returned
 *              All other chats will be grouped in entries with chat_id == 0
 *              If the chat info database is not used, the chat_limit is ignored and is always set to 0
 */
suspend fun TdHandler.getStorageStatistics(
    chatLimit: Int
) = sync<StorageStatistics>(GetStorageStatistics(chatLimit))

suspend fun TdHandler.getStorageStatisticsOrNull(
    chatLimit: Int
) = syncOrNull<StorageStatistics>(GetStorageStatistics(chatLimit))

fun TdHandler.getStorageStatisticsWith(
    chatLimit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<StorageStatistics>.() -> Unit)? = null
) = send(GetStorageStatistics(chatLimit), stackIgnore + 1, submit)

/**
 * Quickly returns approximate storage usage statistics
 * Can be called before authorization
 */
suspend fun TdHandler.getStorageStatisticsFast() = sync<StorageStatisticsFast>(GetStorageStatisticsFast())

suspend fun TdHandler.getStorageStatisticsFastOrNull() = syncOrNull<StorageStatisticsFast>(GetStorageStatisticsFast())

fun TdHandler.getStorageStatisticsFastWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<StorageStatisticsFast>.() -> Unit)? = null
) = send(GetStorageStatisticsFast(), stackIgnore + 1, submit)

/**
 * Optimizes storage usage, i.e
 * Deletes some files and returns new storage usage statistics
 * Secret thumbnails can't be deleted
 *
 * @size - Limit on the total size of files after deletion
 *         Pass -1 to use the default limit
 * @ttl - Limit on the time that has passed since the last time a file was accessed (or creation time for some filesystems)
 *        Pass -1 to use the default limit
 * @count - Limit on the total count of files after deletion
 *          Pass -1 to use the default limit
 * @immunityDelay - The amount of time after the creation of a file during which it can't be deleted, in seconds
 *                  Pass -1 to use the default value
 * @fileTypes - If not empty, only files with the given type(s) are considered
 *              By default, all types except thumbnails, profile photos, stickers and wallpapers are deleted
 * @chatIds - If not empty, only files from the given chats are considered
 *            Use 0 as chat identifier to delete files not belonging to any chat (e.g., profile photos)
 * @excludeChatIds - If not empty, files from the given chats are excluded
 *                   Use 0 as chat identifier to exclude all files not belonging to any chat (e.g., profile photos)
 * @returnDeletedFileStatistics - Pass true if deleted file statistics needs to be returned instead of the whole storage usage statistics
 *                                Affects only returned statistics
 * @chatLimit - Same as in getStorageStatistics
 *              Affects only returned statistics
 */
suspend fun TdHandler.optimizeStorage(
    size: Long,
    ttl: Int,
    count: Int,
    immunityDelay: Int,
    fileTypes: Array<FileType>,
    chatIds: LongArray,
    excludeChatIds: LongArray,
    returnDeletedFileStatistics: Boolean,
    chatLimit: Int
) = sync<StorageStatistics>(OptimizeStorage(size, ttl, count, immunityDelay, fileTypes, chatIds, excludeChatIds, returnDeletedFileStatistics, chatLimit))

suspend fun TdHandler.optimizeStorageOrNull(
    size: Long,
    ttl: Int,
    count: Int,
    immunityDelay: Int,
    fileTypes: Array<FileType>,
    chatIds: LongArray,
    excludeChatIds: LongArray,
    returnDeletedFileStatistics: Boolean,
    chatLimit: Int
) = syncOrNull<StorageStatistics>(OptimizeStorage(size, ttl, count, immunityDelay, fileTypes, chatIds, excludeChatIds, returnDeletedFileStatistics, chatLimit))

fun TdHandler.optimizeStorageWith(
    size: Long,
    ttl: Int,
    count: Int,
    immunityDelay: Int,
    fileTypes: Array<FileType>,
    chatIds: LongArray,
    excludeChatIds: LongArray,
    returnDeletedFileStatistics: Boolean,
    chatLimit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<StorageStatistics>.() -> Unit)? = null
) = send(OptimizeStorage(size, ttl, count, immunityDelay, fileTypes, chatIds, excludeChatIds, returnDeletedFileStatistics, chatLimit), stackIgnore + 1, submit)
