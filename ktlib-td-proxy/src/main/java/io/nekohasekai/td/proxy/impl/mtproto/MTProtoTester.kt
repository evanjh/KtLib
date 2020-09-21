@file:Suppress("unused")

package io.nekohasekai.td.proxy.impl.mtproto

import io.nekohasekai.ktlib.td.core.TdException
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.addProxy
import io.nekohasekai.ktlib.td.core.raw.pingProxyWith
import io.nekohasekai.td.proxy.tester.ProxyTester
import io.nekohasekai.td.proxy.tester.testTimeout
import kotlinx.coroutines.delay
import td.TdApi

object MTProtoTester : TdHandler(), ProxyTester<MTProtoProxy> {

    override suspend fun testProxy(proxy: MTProtoProxy): Int {

        var result = -1
        var exception: Exception? = null

        pingProxyWith(addProxy(proxy.server, proxy.port, false, TdApi.ProxyTypeMtproto(proxy.secret)).id) {

            onSuccess {

                result = (it.seconds * 100).toInt()

            }

            onFailure {

                exception = it

            }

        }

        delay(testTimeout.toLong() * 1000L)

        if (result != -1) return result

        throw exception ?: TdException("Timeout expired")

    }

}