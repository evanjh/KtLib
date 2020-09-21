package io.nekohasekai.td.proxy.impl.mtproto

import io.nekohasekai.td.proxy.parser.MapParser
import io.nekohasekai.td.proxy.impl.Proxy

object MTProtoMapParser : MapParser {

    override val name = "mtproto"

    val keyServerAlias = hashSetOf<String>()
    val keyPortAlias = hashSetOf<String>()
    val keySecretAlias = hashSetOf<String>()

    override fun parseProxy(value: Map<String, String>): Proxy {

        val server = value["server"] ?: run {

            keyServerAlias.forEach { alias ->

                value[alias]?.also { return@run it }

            }

            null

        } ?: error("no server field found")

        val port = (value["port"] ?: run {

            keyPortAlias.forEach { alias ->

                value[alias]?.also { return@run it }

            }

            null

        } ?: error("no port field found")).let { runCatching { it.toInt() }.getOrNull() } ?: error("invalid port")

        val secret = value["secret"] ?: run {

            keySecretAlias.forEach { alias ->

                value[alias]?.also { return@run it }

            }

            null

        } ?: error("no secret field found")

        return MTProtoProxy().also {

            it.server = server
            it.port = port
            it.secret = secret

        }

    }

}