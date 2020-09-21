package io.nekohasekai.td.proxy.impl.shadowsocks

import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.RuntimeUtil
import cn.hutool.core.util.ZipUtil
import io.nekohasekai.ktlib.core.*
import io.nekohasekai.td.proxy.impl.ProxyImplement
import io.nekohasekai.td.proxy.parser.LinkParser
import io.nekohasekai.td.proxy.tester.ProxyTester
import java.io.File

object ShadowsocksImpl : ProxyImplement() {

    const val ssRustVersion = "1.8.12"

    lateinit var ssDirectory: File
    lateinit var ssExecutable: File

    override fun init(cacheDir: File) {

        ssDirectory = File(cacheDir, "shadowsocks")
        ssExecutable = File(ssDirectory, "sslocal${if (isWindows) ".exe" else ""}")

        if (!ssExecutable.isFile) {

            val url: String = if (isLinux && isX64) {

                "https://github.com/shadowsocks/shadowsocks-rust/releases/download/v$ssRustVersion/shadowsocks-v$ssRustVersion.x86_64-unknown-linux-gnu.tar.xz"

            } else if (isLinux && isArm64) {

                "https://github.com/shadowsocks/shadowsocks-rust/releases/download/v$ssRustVersion/shadowsocks-v$ssRustVersion.aarch64-unknown-linux-gnu.tar.xz"

            } else if (isWin64) {

                "https://github.com/shadowsocks/shadowsocks-rust/releases/download/v$ssRustVersion/shadowsocks-v$ssRustVersion.x86_64-pc-windows-msvc.zip"

            } else error("unknown dist")

            val cacheFile = File("libs", FileUtil.getName(url))

            downloadFile(url, cacheFile)

            val outDir = File(cacheDir, "shadowsocks")

            outDir.mkdirs()

            if (cacheFile.extension == "zip") {

                ZipUtil.unzip(cacheFile, outDir)

            } else if (cacheFile.name.endsWith("tar.xz")) {

                RuntimeUtil.exec("tar -O $outDir -xf $cacheFile")

            }

            FileUtil.del(cacheFile)

        }

        LinkParser.addParser(ShadowsocksLinkParser)
        ProxyTester.addTester(ShadowsocksTester)

    }

}