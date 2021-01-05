package io.nekohasekai.td.proxy.parser

import cn.hutool.core.codec.Base64
import io.nekohasekai.td.proxy.impl.Proxy
import java.io.File

object FileParser : Parser<File> {

    override fun parseProxies(value: File): List<Proxy> {

        runCatching {

            return StringParser.parseProxies(Base64.decodeStr(value.readText()))

        }

        return listOf()

    }
}