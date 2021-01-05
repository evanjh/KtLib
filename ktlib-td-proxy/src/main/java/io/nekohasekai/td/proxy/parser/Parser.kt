package io.nekohasekai.td.proxy.parser

import cn.hutool.json.JSON
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.td.proxy.impl.Proxy
import io.nekohasekai.td.proxy.impl.mtproto.MTProtoProxy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import td.TdApi
import java.io.File

suspend fun TdHandler.addProxyAsync(proxy: Proxy, callback: suspend (TdApi.Proxy) -> Unit = {}) =
    withContext(Dispatchers.IO) {

        async { sync<TdApi.Proxy>(proxy.mkInput()).also { callback(it) } }

    }

fun Proxy.mkInput(): TdApi.AddProxy {

    if (this is MTProtoProxy) {

        return TdApi.AddProxy(server, port, false, TdApi.ProxyTypeMtproto(secret))

    }

    error("unsupported  $this")

}

interface Parser<T : Any> {

    fun parseProxies(value: T): List<Proxy>

    companion object : Parser<Any> {

        override fun parseProxies(value: Any): List<Proxy> {

            @Suppress("UNCHECKED_CAST")
            return when (value) {

                is File -> FileParser.parseProxies(value)

                is JSON -> JSONParser.parseProxies(value)

                is String -> StringParser.parseProxies(value)

                is Map<*, *> -> MapParser.parseMap(value)

                is Iterable<*> -> IterableParser.parseProxies(value as Iterable<Any>)

                else -> StringParser.parseProxies("$value")

            }

        }

    }

}