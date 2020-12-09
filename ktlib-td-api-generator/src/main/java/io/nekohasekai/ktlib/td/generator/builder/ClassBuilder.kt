@file:Suppress("UNUSED_PARAMETER")

package io.nekohasekai.ktlib.td.generator.builder

import io.nekohasekai.ktlib.td.generator.tl.*

fun StringBuilder.buildClass(data: TlData, metadata: TlDataMetadata, paramNullable: Boolean) {

    val declaration = when (data) {
        is TlAbstract -> "public static abstract class "
        is TlClass -> "public static class "
    }

    val className = data.type.capitalize()

    buildDescription(data.descriptionsWithProperties())
    // buildAnnotations(listOf(TlAddition.JvmOverloads))
    append(declaration).append(className)

    if (data is TlFunction) {

        append(" extends ").append(data.parentType.capitalize()).append("<${data.returnType.capitalize()}>").append(spaceToken)

    } else {

        append(" extends ").append(data.parentType.capitalize()).append(spaceToken)

    }

    if (data is TlClass) {

        withCurlyBrackets {

            append("\n")

            if (data.metadata.properties.isNotEmpty()) {

                data.metadata.properties.joinTo(this, "\n") {

                    val type = if (it.additions.any { it is TlAddition.Nullable }) {

                        "@Nullable "

                    } else {

                        emptyToken

                    }

                    type + "public ${it.toJavaParamter()};"

                }

                append("\n")

            }

            if (data.metadata.properties.isNotEmpty()) {


                append("\npublic $className() {}\n\n")

                append("public $className")

                withRoundBrackets {

                    data.metadata.properties.joinTo(this) { property ->

                        val type = if (property.additions.any { it is TlAddition.Nullable }) {

                            "@Nullable "

                        } else {

                            emptyToken

                        }

                        type + property.toJavaParamter()

                    }

                }

                append(spaceToken)

                withCurlyBrackets {

                    data.metadata.properties.joinTo(this, "\n", "\n", "\n") {

                        val propName = it.name.snakeToCamel()

                        "this.$propName = $propName;"

                    }

                }

                append("\n")

            }

            buildConstructorField(data.crc)

            append("\n")

        }

    } else append("{}")

    append("\n")

}

fun StringBuilder.buildConstructorField(crc: Int) {

    append("@Override\n")
    append("public int getConstructor() { return $crc; }")

}

fun TlData.descriptions(): List<String> = metadata.descriptions + metadata.additions.strings()

fun TlData.descriptionsWithProperties(): List<String> =
    descriptions() + (when (metadata.properties.isEmpty()) {
        true -> emptyList()
        false -> listOf("") + metadata.properties.flatMap(TlProperty::descriptionLines)
    })
