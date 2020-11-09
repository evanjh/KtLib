@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns recommended chat filters for the current user
 */
suspend fun TdHandler.getRecommendedChatFilters() = sync(GetRecommendedChatFilters())

suspend fun TdHandler.getRecommendedChatFiltersOrNull() = syncOrNull(GetRecommendedChatFilters())

fun TdHandler.getRecommendedChatFiltersWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<RecommendedChatFilters>.() -> Unit)? = null
) = send(GetRecommendedChatFilters(), stackIgnore + 1, submit)
