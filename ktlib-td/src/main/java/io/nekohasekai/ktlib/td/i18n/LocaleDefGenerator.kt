package io.nekohasekai.ktlib.td.i18n

import io.nekohasekai.ktlib.core.defaultLog
import io.nekohasekai.ktlib.core.find
import io.nekohasekai.ktlib.core.readPackageName
import org.yaml.snakeyaml.Yaml
import java.io.File
import kotlin.system.exitProcess

@Suppress("UNCHECKED_CAST")
fun main() {

    val projectDir = File("../../src").canonicalFile

    val localesFile = projectDir.find { it.name == "LocaleDefs.kt" }

    if (localesFile == null) {

        System.err.println("LocaleDefs.kt not found.")

        exitProcess(1)

    }

    val i18nDirectory = projectDir.find { it.name.startsWith("i18n-") }

    if (i18nDirectory == null) {

        System.err.println("i18n directory not found.")

        exitProcess(1)

    }

    val moduleName = i18nDirectory.name.substringAfter("-")

    val i18nSources = i18nDirectory.listFiles()

    if (i18nSources.isNullOrEmpty()) {

        System.err.println("empty i18n sources.")

        exitProcess(1)

    }

    val i18nMaps = i18nSources.map { it to Yaml().loadAs(it.readText(), Map::class.java) as Map<String, *> }

    if (i18nMaps.size > 1) {

        // 检查

        for (index in i18nMaps.indices) {

            val currentMap = i18nMaps[index]
            val otherMaps = i18nMaps.filterIndexed { it, _ -> it != index }

            for (key in currentMap.second.keys) {

                otherMaps.forEach {

                    if (!it.second.containsKey(key)) {

                        defaultLog.warn("$key in ${currentMap.first.name} not found in ${it.first.name}")

                    }

                }

            }

        }

    }

    val i18nKeys = i18nMaps[0].second.keys

    localesFile.writeText(
        """
package ${localesFile.readPackageName()}

import ${LocaleController::class.java.name}

private typealias L = LocaleController

private val L.$moduleName by L.receiveLocaleSet("$moduleName")
private val string = L.receiveLocaleString { $moduleName }

${i18nKeys.joinToString("\n") { "internal val L.$it by string" }}
"""
    )

    println("Write ${localesFile.path}")

}