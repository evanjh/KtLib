@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Loads an asynchronous or a zoomed in statistical graph
 *
 * @chatId - Chat identifier
 * @token - The token for graph loading
 * @x - X-value for zoomed in graph or 0 otherwise
 */
suspend fun TdHandler.getStatisticalGraph(
    chatId: Long,
    token: String? = null,
    x: Long
) = sync(GetStatisticalGraph(chatId, token, x))

suspend fun TdHandler.getStatisticalGraphOrNull(
    chatId: Long,
    token: String? = null,
    x: Long
) = syncOrNull(GetStatisticalGraph(chatId, token, x))

fun TdHandler.getStatisticalGraphWith(
    chatId: Long,
    token: String? = null,
    x: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<StatisticalGraph>.() -> Unit)? = null
) = send(GetStatisticalGraph(chatId, token, x), stackIgnore + 1, submit)
