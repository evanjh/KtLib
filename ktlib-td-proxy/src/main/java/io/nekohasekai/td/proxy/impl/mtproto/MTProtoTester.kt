@file:Suppress("unused")

package io.nekohasekai.td.proxy.impl.mtproto

import io.nekohasekai.ktlib.td.core.TdException
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.addProxy
import io.nekohasekai.ktlib.td.core.raw.pingProxyWith
import io.nekohasekai.td.proxy.tester.ProxyTester
import io.nekohasekai.td.proxy.tester.testTimeout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import td.TdApi
import java.net.InetSocketAddress
import java.net.Socket

object MTProtoTester : TdHandler(), ProxyTester<MTProtoProxy> {

    override suspend fun testProxy(proxy: MTProtoProxy): Int {

        withContext(Dispatchers.IO) {

            try {
                val socket = Socket()
                socket.connect(InetSocketAddress(proxy.server, proxy.port), (15000))
                socket.close()
            } catch (e: Exception) {
                throw TdException(-1, e.javaClass.simpleName)
            }

        }

        var result = -1
        var exception: Exception? = null
        val finishLock = Mutex(true)
        val pid = addProxy(proxy.server, proxy.port, false, TdApi.ProxyTypeMtproto(proxy.secret)).id

        pingProxyWith(pid) {

            onSuccess {

                result = (it.seconds * 100).toInt()
                finishLock.unlock()

            }

            onFailure {

                exception = it
                finishLock.unlock()

            }

        }

        withTimeoutOrNull(testTimeout.toLong() * 1000L) {

            finishLock.lock()

        }

        sendRaw(TdApi.RemoveProxy(pid))

        if (result != -1) return result

        throw exception ?: TdException("Timeout expired")

    }

}