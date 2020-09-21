package io.nekohasekai.td.proxy.impl.shadowsocks

import cn.hutool.json.JSONObject
import io.nekohasekai.ktlib.td.core.TdException
import kotlinx.coroutines.*
import java.io.File

object ShadowsocksTester : ExternalSocks5Tester<ShadowsocksProxy>() {

    override suspend fun testProxy(proxy: ShadowsocksProxy) = coroutineScope {

        val cacheDir = File("cache/ss")

        cacheDir.mkdirs()

        val cacheFile = File(cacheDir, "${System.currentTimeMillis()}.json")

        cacheFile.writeText(JSONObject().also {

            it["server"] = proxy.server
            it["server_port"] = proxy.port
            it["password"] = proxy.password
            it["method"] = proxy.method
            it["ipv6"] = true

            if (proxy.plugin.isNotBlank()) {

                it["plugin"] = proxy.plugin
                it["plugin_opts"] = proxy.pluginOpts

            }

        }.toStringPretty())

        val localPort = mkNewPort()

        var process: GuardedProcessPool? = null

        try {

            process = createProxyProcess(
                    ShadowsocksImpl.ssDirectory,
                    ShadowsocksImpl.ssExecutable.canonicalPath,
                    "--local-addr", "127.0.0.1:$localPort",
                    "--config", cacheFile.canonicalPath
            )

            delay(300L)

            return@coroutineScope testPort(localPort)

        } catch (e: Exception) {

            throw TdException(e.message ?: e.javaClass.simpleName)

        } finally {

            process?.cancel()

            cacheFile.delete()

        }

    }

}