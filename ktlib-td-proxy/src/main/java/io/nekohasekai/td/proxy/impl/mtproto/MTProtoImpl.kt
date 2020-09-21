package io.nekohasekai.td.proxy.impl.mtproto

import io.nekohasekai.td.proxy.impl.ProxyImplement
import io.nekohasekai.td.proxy.parser.LinkParser
import io.nekohasekai.td.proxy.parser.MapParser
import io.nekohasekai.td.proxy.saver.LinkSaver
import io.nekohasekai.td.proxy.tester.ProxyTester
import java.io.File

object MTProtoImpl : ProxyImplement() {

    override fun init(cacheDir: File) {

        LinkParser.addParser(MTProtoLinkParser)
        LinkSaver.addSaver(MTProtoLinkSaver)
        MapParser.addParser(MTProtoMapParser)
        ProxyTester.addTester(MTProtoTester)

    }

}