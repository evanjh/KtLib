package io.nekohasekai.td.proxy.impl.shadowsocks

import cn.hutool.core.date.SystemClock
import io.nekohasekai.ktlib.td.core.TdException
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.testProxyWith
import io.nekohasekai.td.proxy.impl.Proxy
import io.nekohasekai.td.proxy.tester.*
import kotlinx.coroutines.delay
import td.TdApi
import java.io.File
import java.net.InetSocketAddress
import java.net.ServerSocket
import kotlin.random.Random

abstract class ExternalSocks5Tester<T : Proxy> : TdHandler(), ProxyTester<T> {

    fun mkNewPort(): Int {

        var port = Random.nextInt(2048, 32768)

        if (!isProxyAvailable(port)) {

            port = Random.nextInt(2048, 32768)

        }

        return port

    }

    private fun isProxyAvailable(port: Int): Boolean {

        if (port !in 2048 until 32768) return false

        runCatching {

            val server = ServerSocket()

            server.bind(InetSocketAddress("127.0.0.1", port))

            server.close()

            Thread.sleep(1000L)

        }.onFailure {

            return false

        }

        return true

    }

    fun createProxyProcess(localDir: File, vararg cmd: String): GuardedProcessPool {

        return GuardedProcessPool(localDir) {

            println(it.message)

        }.apply {

            start(cmd.toList(), null)

        }

    }

    suspend fun testPort(port: Int): Int {

        val start = SystemClock.now()

        var result = -1
        var exception: Exception? = null
        var end = false

        testProxyWith("127.0.0.1", port, TdApi.ProxyTypeSocks5(), testDcTarget, testTimeout) {


            onSuccess {

                result = (SystemClock.now() - start).toInt()
                end = true

            }

            onFailure {

                exception = it
                end = true

            }

        }

        delay(testTimeout.toLong() * 1000L)

        if (result != -1) return result

        throw exception ?: TdException("Timeout expired")

    }

}