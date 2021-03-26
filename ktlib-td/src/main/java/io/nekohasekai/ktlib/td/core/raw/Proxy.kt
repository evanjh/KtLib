@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Adds a proxy server for network requests
 * Can be called before authorization
 *
 * @server - Proxy server IP address
 * @port - Proxy server port
 * @enable - True, if the proxy should be enabled
 * @type - Proxy type
 */
suspend fun TdHandler.addProxy(
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null
) = sync(AddProxy(server, port, enable, type))

suspend fun TdHandler.addProxyOrNull(
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null
) = syncOrNull(AddProxy(server, port, enable, type))

fun TdHandler.addProxyWith(
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Proxy>.() -> Unit)? = null
) = send(AddProxy(server, port, enable, type), stackIgnore + 1, submit)

/**
 * Edits an existing proxy server for network requests
 * Can be called before authorization
 *
 * @proxyId - Proxy identifier
 * @server - Proxy server IP address
 * @port - Proxy server port
 * @enable - True, if the proxy should be enabled
 * @type - Proxy type
 */
suspend fun TdHandler.editProxy(
    proxyId: Int,
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null
) = sync(EditProxy(proxyId, server, port, enable, type))

suspend fun TdHandler.editProxyOrNull(
    proxyId: Int,
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null
) = syncOrNull(EditProxy(proxyId, server, port, enable, type))

fun TdHandler.editProxyWith(
    proxyId: Int,
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Proxy>.() -> Unit)? = null
) = send(EditProxy(proxyId, server, port, enable, type), stackIgnore + 1, submit)

/**
 * Enables a proxy
 * Only one proxy can be enabled at a time
 * Can be called before authorization
 *
 * @proxyId - Proxy identifier
 */
suspend fun TdHandler.enableProxy(
    proxyId: Int
){
    sync(EnableProxy(proxyId))
}


suspend fun TdHandler.enableProxyIgnoreException(
    proxyId: Int
){
    syncOrNull(EnableProxy(proxyId))
}


fun TdHandler.enableProxyWith(
    proxyId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(EnableProxy(proxyId), stackIgnore + 1, submit)

/**
 * Disables the currently enabled proxy
 * Can be called before authorization
 */
suspend fun TdHandler.disableProxy(){
    sync(DisableProxy())
}


suspend fun TdHandler.disableProxyIgnoreException(){
    syncOrNull(DisableProxy())
}


fun TdHandler.disableProxyWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DisableProxy(), stackIgnore + 1, submit)

/**
 * Removes a proxy server
 * Can be called before authorization
 *
 * @proxyId - Proxy identifier
 */
suspend fun TdHandler.removeProxy(
    proxyId: Int
){
    sync(RemoveProxy(proxyId))
}


suspend fun TdHandler.removeProxyIgnoreException(
    proxyId: Int
){
    syncOrNull(RemoveProxy(proxyId))
}


fun TdHandler.removeProxyWith(
    proxyId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveProxy(proxyId), stackIgnore + 1, submit)

/**
 * Returns an HTTPS link, which can be used to add a proxy
 * Available only for SOCKS5 and MTProto proxies
 * Can be called before authorization
 *
 * @proxyId - Proxy identifier
 */
suspend fun TdHandler.getProxyLink(
    proxyId: Int
) = sync(GetProxyLink(proxyId))

suspend fun TdHandler.getProxyLinkOrNull(
    proxyId: Int
) = syncOrNull(GetProxyLink(proxyId))

fun TdHandler.getProxyLinkWith(
    proxyId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<HttpUrl>.() -> Unit)? = null
) = send(GetProxyLink(proxyId), stackIgnore + 1, submit)

/**
 * Computes time needed to receive a response from a Telegram server through a proxy
 * Can be called before authorization
 *
 * @proxyId - Proxy identifier
 *            Use 0 to ping a Telegram server without a proxy
 */
suspend fun TdHandler.pingProxy(
    proxyId: Int
) = sync(PingProxy(proxyId))

suspend fun TdHandler.pingProxyOrNull(
    proxyId: Int
) = syncOrNull(PingProxy(proxyId))

fun TdHandler.pingProxyWith(
    proxyId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Seconds>.() -> Unit)? = null
) = send(PingProxy(proxyId), stackIgnore + 1, submit)
