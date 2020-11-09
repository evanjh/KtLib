package io.nekohasekai.ktlib.td.generator.builder

import cn.hutool.core.util.StrUtil
import io.nekohasekai.ktlib.td.generator.tl.*

fun StringBuilder.buildApi(scheme: TlScheme) {
    // suppress("unused")
    // useExperimentalAnnotationsForFile()
    //append("\n")
    buildJavaPackage("td")
    append("\n")
    buildJavaImport("org.jetbrains.annotations")
    append("\n")
    append("@SuppressWarnings(\"ALL\")\n")
    append("public class TdApi ")
    withCurlyBrackets {
        append("\n")
        append("public static abstract class Object ")
        withCurlyBrackets {
            append("\npublic native String toString();\n\n")
            append("public abstract int getConstructor();\n")
        }
        append("\n")
        append("public static abstract class Function<T extends Object> extends Object ")
        withCurlyBrackets {
            append("\npublic native String toString();\n")
        }
        scheme.data.forEach {
            append("\n")
            buildClass(it, scheme.metadata[it],
                    it.type.toLowerCase().startsWith("input") ||
                            it.type.toLowerCase().endsWith("options"))
        }
    }
}

fun StringBuilder.buildEvents(scheme: TlScheme) {
    // suppress("unused")
    // useExperimentalAnnotationsForFile()
    //append("\n")

    buildPackage("io.nekohasekai.ktlib.td.core.raw")

    append("\n")

    buildImport("td.TdApi")

    append("\n")
    append("interface AbsEvents ")

    withCurlyBrackets {

        scheme.data.forEach { it ->

            if (it is TlClass) {

                if (it.parentType == "Update") {

                    append("\n")

                    buildDescription(it.descriptionsWithProperties())

                    val prefix = if (it.type in arrayOf("updateNewMessage", "updateNewCallbackQuery", "updateNewInlineCallbackQuery")) {

                        "handle"

                    } else {

                        "on"

                    }

                    append("suspend fun $prefix${StrUtil.upperFirst(it.type.substringAfter("update"))}")

                    withRoundBrackets {

                        it.metadata.properties.joinTo(this) { property ->

                            property.toParameter(scheme.metadata[it], "", property.additions.any { it is TlAddition.Nullable }, false)

                        }

                    }

                    append(" = Unit")

                    append("\n")

                }

            }

        }

        append("\n")

        append("suspend fun onUpdate")

        withRoundBrackets {

            append("eventObj: Update")

        }

        append(" ")

        withCurlyBrackets {

            append("\n")

            append("when (eventObj) ")

            withCurlyBrackets {

                scheme.data.forEach { it ->

                    if (it is TlClass) {

                        if (it.parentType == "Update") {

                            append("\n")

                            val prefix = if (it.type in arrayOf("updateNewMessage", "updateNewCallbackQuery", "updateNewInlineCallbackQuery")) {

                                "handle"

                            } else {

                                "on"

                            }

                            append("is ${StrUtil.upperFirst(it.type)} -> $prefix${StrUtil.upperFirst(it.type.substringAfter("update"))}")

                            withRoundBrackets {

                                it.metadata.properties.joinTo(this) {

                                    "eventObj.${it.name.snakeToCamel()}"

                                }

                            }

                            append("\n")

                        }

                    }

                }

            }

            append("\n")

        }

    }
}

fun StringBuilder.useExperimentalAnnotationsForFile() {
    append("@file:UseExperimental")
    withRoundBrackets {
        TlAddition.annotations().joinTo(this, ",\n") {
            "${it.annotation}::class"
        }
    }
    append("\n")
}

fun StringBuilder.useExperimentalAnnotationForFile(annotation: TlAddition.Annotation) {
    append("@file:UseExperimental(").append(annotation.annotation).append("::class)")
}

