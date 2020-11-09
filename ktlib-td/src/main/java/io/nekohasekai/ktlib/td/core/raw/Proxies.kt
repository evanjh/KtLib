@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns list of proxies that are currently set up
 * Can be called before authorization
 */
suspend fun TdHandler.getProxies() = sync(GetProxies())

suspend fun TdHandler.getProxiesOrNull() = syncOrNull(GetProxies())

fun TdHandler.getProxiesWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Proxies>.() -> Unit)? = null
) = send(GetProxies(), stackIgnore + 1, submit)
