@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Loads asynchronous or zoomed in chat or message statistics graph
 *
 * @chatId - Chat identifier
 * @token - The token for graph loading
 * @x - X-value for zoomed in graph or 0 otherwise
 */
suspend fun TdHandler.getStatisticsGraph(
    chatId: Long,
    token: String? = null,
    x: Long
) = sync<StatisticsGraph>(GetStatisticsGraph(chatId, token, x))

suspend fun TdHandler.getStatisticsGraphOrNull(
    chatId: Long,
    token: String? = null,
    x: Long
) = syncOrNull<StatisticsGraph>(GetStatisticsGraph(chatId, token, x))

fun TdHandler.getStatisticsGraphWith(
    chatId: Long,
    token: String? = null,
    x: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<StatisticsGraph>.() -> Unit)? = null
) = send(GetStatisticsGraph(chatId, token, x), stackIgnore + 1, submit)
