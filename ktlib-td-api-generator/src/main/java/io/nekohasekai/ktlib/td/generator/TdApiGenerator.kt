package io.nekohasekai.ktlib.td.generator

import cn.hutool.core.io.FileUtil
import io.nekohasekai.ktlib.core.downloadFile
import io.nekohasekai.ktlib.core.find
import io.nekohasekai.ktlib.td.generator.builder.*
import io.nekohasekai.ktlib.td.generator.tl.*
import io.nekohasekai.ktlib.td.generator.tl.parser.parseTlData
import io.nekohasekai.ktlib.td.generator.tl.parser.readTlScheme
import java.io.File
import kotlin.collections.set

object TdApiGenerator {

    @JvmStatic
    fun main(args: Array<String>) {

        var projectRoot = File(".")
        if (File("ktlib/ktlib-td").isDirectory) {
            projectRoot = File("ktlib")
        }

        val scheme = "https://raw.githubusercontent.com/tdlib/td/master/td/generate/scheme/td_api.tl"

        val file = File(projectRoot, "ktlib-td-api-generator/td_api.tl")

        println(file.canonicalPath)

        if (!file.isFile) {

            downloadFile(scheme, file)

        }

        FileUtil.del("$projectRoot/ktlib-td/src/main/java/io/nekohasekai/ktlib/td/core/raw")

        val api = generateApi(file.readBytes())

        api.forEach { (path, src) ->

            with(File("$projectRoot/ktlib-td/src/main/java/$path")) {

                println("write $path")

                parentFile.mkdirs()

                writeText(src)

            }

        }

    }

    fun generateApi(scheme: ByteArray): Map<String, String> {

        val tlData = scheme.readTlScheme().parseTlData()
        val metadata = tlData.extractMetadata()
        val tlScheme = TlScheme(tlData, metadata)
        val functionsMap = tlData.groupFunctions()
        val syncFunctions = tlData.filterIsInstance<TlFunction>().filter { TlAddition.Sync in it.metadata.additions }

        val map = mutableMapOf<String, String>()

        fun String.nested(path: String, block: String.() -> Unit) {
            ("$this/$path").block()
        }

        fun String.file(name: String, block: StringBuilder.() -> Unit) {
            val nested = "$this/$name"
            map[nested] = buildString(block)
        }

        with("td") {

            file("TdApi.java") {

                buildApi(tlScheme)

            }

        }

        with("io/nekohasekai/ktlib/td/core/raw") {

            functionsMap.forEach { (type, functions) ->

                file("$type.kt") {

                    buildHeader()

                    functions.forEach {

                        append("\n")

                        buildFunction(it, metadata[it])

                        append("\n")

                        buildNullaableFunction(it, metadata[it])

                        append("\n")

                        buildCallbackFunction(it, metadata[it])

                    }

                }


            }

            file("Static.kt") {

                buildHeader()

                syncFunctions.forEach {

                    append("\n")

                    buildSyncFunction(it, metadata[it])

                    append("\n")

                    buildRawSyncFunction(it)

                }

            }

            file("Events.kt") {

                buildEvents(tlScheme)

            }

        }

        return map
    }

}