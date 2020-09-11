@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.GetProxies
import td.TdApi.Proxies

/**
 * Returns list of proxies that are currently set up
 * Can be called before authorization
 */
suspend fun TdHandler.getProxies() = sync<Proxies>(GetProxies())

suspend fun TdHandler.getProxiesOrNull() = syncOrNull<Proxies>(GetProxies())

fun TdHandler.getProxiesWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Proxies>.() -> Unit)? = null
) = send(GetProxies(), stackIgnore + 1, submit)
