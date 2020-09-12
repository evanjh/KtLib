@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Loads asynchronous or zoomed in chat statistics graph
 *
 * @chatId - Chat identifier
 * @token - The token for graph loading
 * @x - X-value for zoomed in graph or 0 otherwise
 */
suspend fun TdHandler.getChatStatisticsGraph(
    chatId: Long,
    token: String? = null,
    x: Long
) = sync<StatisticsGraph>(GetChatStatisticsGraph(chatId, token, x))

suspend fun TdHandler.getChatStatisticsGraphOrNull(
    chatId: Long,
    token: String? = null,
    x: Long
) = syncOrNull<StatisticsGraph>(GetChatStatisticsGraph(chatId, token, x))

fun TdHandler.getChatStatisticsGraphWith(
    chatId: Long,
    token: String? = null,
    x: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<StatisticsGraph>.() -> Unit)? = null
) = send(GetChatStatisticsGraph(chatId, token, x), stackIgnore + 1, submit)
