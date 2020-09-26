package io.nekohasekai.td.proxy

import cn.hutool.http.HttpUtil
import io.nekohasekai.ktlib.core.*
import io.nekohasekai.ktlib.td.cli.TdCli
import io.nekohasekai.ktlib.td.core.TdClient
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.*
import io.nekohasekai.td.proxy.connection.ConnectionState
import io.nekohasekai.td.proxy.impl.mtproto.MTProtoImpl
import io.nekohasekai.td.proxy.parser.Parser
import io.nekohasekai.td.proxy.parser.addProxyAsync
import kotlinx.coroutines.*
import td.TdApi
import java.io.File
import java.util.*
import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.timerTask

class ConnectionManager : TdHandler() {

    lateinit var log: NekoLog

    init {

        MTProtoImpl.postInit()

    }

    val retestTask = timerTask {

        GlobalScope.launch(Dispatchers.IO) {

            testAll(true)

        }

    }

    override fun onLoad() {

        log = mkLog("TdProxy (${sudo.tag})")

        TdClient.timer.schedule(retestTask, 10 * 60 * 1000L, 10 * 60 * 1000L)

    }

    var currentState = ConnectionState.Connecting

    private var lastTask by AtomicReference<TimerTask?>()
    var timeout = 5000L

    var autoProxy = false

    override suspend fun onConnectionState(state: TdApi.ConnectionState) {

        currentState = ConnectionState.fromState(state)

        log.debug(currentState.toString())

        if (!autoProxy) return

        if (currentState == ConnectionState.Connecting || currentState == ConnectionState.ConnectingToProxy) {

            TdClient.timer.schedule(object : TimerTask() {

                init {

                    lastTask?.cancel()
                    lastTask = this

                }

                override fun run() {

                    if (lastTask != this) return

                    GlobalScope.launch(Dispatchers.IO) {

                        onConnectionTimeout()

                    }

                }

            }, timeout)

        } else if (currentState == ConnectionState.Updating || currentState == ConnectionState.Ready) {

            lastTask?.cancel()
            lastTask = null

        }

    }

    override suspend fun onDestroy() {

        lastTask?.cancel()
        lastTask = null
        retestTask.cancel()

    }

    val cacheFile = File("${(sudo as? TdCli)?.cacheDir ?: "cache"}/proxy_list")
    var lastUpdate = -1L

    val urls = listOf(
            "https://gitlab.com/NekohaSekai/nekox-proxy-list/-/raw/master/proxy_list",
            "https://nekox-dev.github.io/ProxyList/proxy_list",
            "https://gitee.com/nekoshizuku/AwesomeRepo/raw/master/proxy_list"
    )

    var updating = false

    suspend fun onConnectionTimeout() {

        when (getAuthorizationState()) {

            is TdApi.AuthorizationStateWaitPhoneNumber,
            is TdApi.AuthorizationStateWaitCode,
            is TdApi.AuthorizationStateWaitPassword -> return

        }

        if (updating) return

        synchronized(this) {

            if (updating) return

            updating = true

        }

        runCatching {

            if (System.currentTimeMillis() - lastUpdate > 1 * 30 * 60 * 1000L) {

                updateProxyList()

            }

            enableProxy()

        }.onFailure {

            log.warn(it)

        }

        updating = false

    }

    val proxyPing = hashMapOf<TdApi.Proxy, Float>()
    var TdApi.Proxy.ping by proxyPing

    suspend fun updateProxyList() {

        log.info("拉取公共代理列表")

        val errors = mutableListOf<String>()

        urls.forEach {

            runCatching {

                val proxyList = HttpUtil.createGet(it)
                        .setConnectionTimeout(2000)
                        .setReadTimeout(2000)
                        .execute()
                        .body()

                var needReload = getProxies().proxies.let { it.isEmpty() || it.all { proxy -> !proxy.isEnabled } }

                if (!cacheFile.isFile || cacheFile.readText() != proxyList) {

                    cacheFile.writeText(proxyList)

                    needReload = true

                }

                if (needReload) {

                    reloadProxyList()

                    testAll(true)

                }

                lastUpdate = System.currentTimeMillis()

                return

            }.onFailure {

                it.printStackTrace()

                errors.add(it.message ?: it.javaClass.simpleName)

            }

        }

        error("所有尝试都失败: \n${errors.joinToString("\n")}")

    }

    suspend fun reloadProxyList() {

        log.info("重新载入代理列表")

        proxyPing.clear()

        val proxyList = Parser.parseProxies(cacheFile)

        if (proxyList.isEmpty()) {

            log.warn("代理列表为空")

            return

        }

        getProxies().proxies.forEach { removeProxy(it.id) }

        proxyList.map { addProxyAsync(it) }.awaitAll()

    }

    suspend fun enableProxy() {

        var proxies = getProxies().proxies.toMutableList()

        val enabled = proxies.find { it.isEnabled }

        if (enabled != null) {

            val index = proxies.indexOf(enabled)

            if (index != -1) {

                val newProxies = mutableListOf<TdApi.Proxy>()

                newProxies.addAll(proxies.subList(index, proxies.size))
                newProxies.addAll(proxies.subList(0, index))

                proxies = newProxies

            }

        }

        if (proxies.all { !proxyPing.containsKey(it) }) {

            enableProxy(proxies[0].id)

            GlobalScope.launch(Dispatchers.IO) { testAll() }

            return

        } else if (!proxies.all { proxyPing.containsKey(it) }) {

            testAll()

            return

        }

        val min = proxyPing.filter { it.value > 0 }.minByOrNull { it.value }?.key

        if (min != null) {

            enableProxy(min.id)

            return

        }

        proxies.forEach {

            if (it.ping == -1f) return@forEach

            enableProxy(it.id)

            return

        }

    }

    suspend fun testAll(force: Boolean = false) {

        val proxies = getProxies().proxies

        proxies.forEach { proxy ->

            if (!proxyPing.containsKey(proxy) || force) {

                val currMin = proxyPing.values.filter { it > 0 }.minOrNull()

                runCatching {

                    val ping = pingProxy(proxy.id)

                    proxy.ping = ping.seconds.toFloat()

                    if (currMin == null || proxy.ping!! < currMin) {

                        enableProxy(proxy.id)

                    }

                }.onFailure {

                    proxy.ping = -1f

                }

            }

        }

    }

}