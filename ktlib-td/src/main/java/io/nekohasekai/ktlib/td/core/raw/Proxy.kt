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
) = sync<Proxy>(AddProxy(server, port, enable, type))

suspend fun TdHandler.addProxyOrNull(
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null
) = syncOrNull<Proxy>(AddProxy(server, port, enable, type))

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
) = sync<Proxy>(EditProxy(proxyId, server, port, enable, type))

suspend fun TdHandler.editProxyOrNull(
    proxyId: Int,
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null
) = syncOrNull<Proxy>(EditProxy(proxyId, server, port, enable, type))

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
) = sync<Ok>(EnableProxy(proxyId))

suspend fun TdHandler.enableProxyOrNull(
    proxyId: Int
) = syncOrNull<Ok>(EnableProxy(proxyId))

fun TdHandler.enableProxyWith(
    proxyId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(EnableProxy(proxyId), stackIgnore + 1, submit)

/**
 * Disables the currently enabled proxy
 * Can be called before authorization
 */
suspend fun TdHandler.disableProxy() = sync<Ok>(DisableProxy())

suspend fun TdHandler.disableProxyOrNull() = syncOrNull<Ok>(DisableProxy())

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
) = sync<Ok>(RemoveProxy(proxyId))

suspend fun TdHandler.removeProxyOrNull(
    proxyId: Int
) = syncOrNull<Ok>(RemoveProxy(proxyId))

fun TdHandler.removeProxyWith(
    proxyId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveProxy(proxyId), stackIgnore + 1, submit)

/**
 * Computes time needed to receive a response from a Telegram server through a proxy
 * Can be called before authorization
 *
 * @proxyId - Proxy identifier
 *            Use 0 to ping a Telegram server without a proxy
 */
suspend fun TdHandler.pingProxy(
    proxyId: Int
) = sync<Seconds>(PingProxy(proxyId))

suspend fun TdHandler.pingProxyOrNull(
    proxyId: Int
) = syncOrNull<Seconds>(PingProxy(proxyId))

fun TdHandler.pingProxyWith(
    proxyId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Seconds>.() -> Unit)? = null
) = send(PingProxy(proxyId), stackIgnore + 1, submit)
