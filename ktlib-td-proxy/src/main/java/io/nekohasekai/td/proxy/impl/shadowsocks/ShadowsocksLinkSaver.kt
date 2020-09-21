package io.nekohasekai.td.proxy.impl.shadowsocks

import cn.hutool.core.codec.Base64
import com.github.shadowsocks.plugin.PluginOptions
import io.nekohasekai.td.proxy.saver.LinkSaver
import okhttp3.HttpUrl

object ShadowsocksLinkSaver : LinkSaver<ShadowsocksProxy> {

    override fun toLink(proxy: ShadowsocksProxy): String {

        val url = HttpUrl.Builder()
                .scheme("https")
                .encodedUsername(Base64.encodeUrlSafe("$proxy.method:$proxy.password"))
                .host(proxy.server)
                .port(proxy.port)

        if (proxy.name.isNotBlank()) url.fragment(proxy.name)

        if (proxy.plugin.isNotBlank()) url.addQueryParameter("plugin", PluginOptions(proxy.plugin,proxy.pluginOpts).toString(false))

        return url.build().toString().replace("https://","ss://")

    }

    override fun toLink(protocol: String, proxy: ShadowsocksProxy): String {

        if (protocol != "ss") error("unexpected protocol $protocol")

        return toLink(proxy)

    }

}