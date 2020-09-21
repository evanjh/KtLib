package io.nekohasekai.td.proxy.parser

import io.nekohasekai.td.proxy.impl.Proxy

object IterableParser : Parser<Iterable<*>> {

    override fun parseProxies(value: Iterable<*>): List<Proxy> = mutableListOf<Proxy>().apply {

        value.forEach {

            if (it == null) return@forEach

            addAll(Parser.parseProxies(it))

        }

    }

}