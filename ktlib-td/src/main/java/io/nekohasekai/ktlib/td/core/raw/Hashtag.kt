@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Searches for recently used hashtags by their prefix
 *
 * @prefix - Hashtag prefix to search for
 * @limit - The maximum number of hashtags to be returned
 */
suspend fun TdHandler.searchHashtags(
    prefix: String? = null,
    limit: Int
) = sync<Hashtags>(SearchHashtags(prefix, limit))

suspend fun TdHandler.searchHashtagsOrNull(
    prefix: String? = null,
    limit: Int
) = syncOrNull<Hashtags>(SearchHashtags(prefix, limit))

fun TdHandler.searchHashtagsWith(
    prefix: String? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Hashtags>.() -> Unit)? = null
) = send(SearchHashtags(prefix, limit), stackIgnore + 1, submit)

/**
 * Removes a hashtag from the list of recently used hashtags
 *
 * @hashtag - Hashtag to delete
 */
suspend fun TdHandler.removeRecentHashtag(
    hashtag: String? = null
) = sync<Ok>(RemoveRecentHashtag(hashtag))

suspend fun TdHandler.removeRecentHashtagOrNull(
    hashtag: String? = null
) = syncOrNull<Ok>(RemoveRecentHashtag(hashtag))

fun TdHandler.removeRecentHashtagWith(
    hashtag: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveRecentHashtag(hashtag), stackIgnore + 1, submit)
