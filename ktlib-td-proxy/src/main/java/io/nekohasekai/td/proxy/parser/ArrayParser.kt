package io.nekohasekai.td.proxy.parser

import io.nekohasekai.td.proxy.impl.Proxy

object ArrayParser : Parser<Array<*>> {

    override fun parseProxies(value: Array<*>): List<Proxy> = mutableListOf<Proxy>().apply {

        value.forEach {

            if (it == null) return@forEach

            addAll(Parser.parseProxies(it))

        }

    }

}