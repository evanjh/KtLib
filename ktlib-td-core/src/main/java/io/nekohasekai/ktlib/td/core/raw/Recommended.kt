@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.GetRecommendedChatFilters
import td.TdApi.RecommendedChatFilters

/**
 * Returns recommended chat filters for the current user
 */
suspend fun TdHandler.getRecommendedChatFilters() = sync<RecommendedChatFilters>(GetRecommendedChatFilters())

suspend fun TdHandler.getRecommendedChatFiltersOrNull() = syncOrNull<RecommendedChatFilters>(GetRecommendedChatFilters())

fun TdHandler.getRecommendedChatFiltersWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<RecommendedChatFilters>.() -> Unit)? = null
) = send(GetRecommendedChatFilters(), stackIgnore + 1, submit)
