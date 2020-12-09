@file:Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")

package io.nekohasekai.ktlib.td.generator.builder

import io.nekohasekai.ktlib.td.generator.tl.*

fun StringBuilder.buildParameters(parameters: List<String>, addEmptyBrackets: Boolean = false) {
    if (parameters.isNotEmpty()) withMultilineRoundBrackets {
        parameters.joinTo(this, ",\n")
    } else if (addEmptyBrackets) append("()")
}

fun TlProperty.toJavaParamter(): String {

    val propName = name.snakeToCamel()

    return "${type.javaType} $propName"

}

fun TlProperty.toParameter(metadata: TlDataMetadata, prefix: String = "", nullable: Boolean = false, withNullDefault: Boolean = true): String {
    val (withDefault, withNullables) = metadata
    val propName = name.snakeToCamel()

    val default = if (nullable)
        questionToken + if (withNullDefault) nullToken else emptyToken
    else if (type is TlRefType) {

        if (additions.any { it is TlAddition.Nullable } || withNullables) questionToken + if (withNullDefault) nullToken else emptyToken
        else emptyToken

    } else emptyToken

    return "${inlineAnnotations()}$prefix$propName: ${type.kotlinType}$default".replace("??", "?")
}

fun TlProperty.toField(metadata: TlDataMetadata, nullable: Boolean): String {

    val (withDefault, withNullables) = metadata

    val propName = name.snakeToCamel()

    val prefix: String

    val default = if (type !is TlPrimitiveType) {

        if (type is TlRefType && (additions.any { it is TlAddition.Nullable } || withNullables)) {

            prefix = "var"

            questionToken + nullToken

        } else {

            prefix = "lateinit var"

            emptyToken

        }

    } else {

        prefix = "var"

        questionToken + nullToken

    }

    return "${inlineAnnotations()}$prefix $propName: ${type.kotlinType}$default"

}

fun TlProperty.descriptionLines(): List<String> {
    val link = "$addressToken${name.snakeToCamel()}$spaceToken$dashToken$spaceToken"
    val spaces = (1..link.length).joinToString("") { " " }
    return listOf(link + descriptions.first()) + (descriptions.drop(1) + additions.strings()).map { "$spaces$it" }
}


fun TlProperty.inlineAnnotations(): String =
    additions
        .filterIsInstance<TlAddition.Annotation>()
        .takeIf(List<*>::isNotEmpty)
        ?.map(io.nekohasekai.ktlib.td.generator.tl.TlAddition.Annotation::annotation)
        ?.distinct()
        ?.sorted()
        ?.joinToString(spaceToken + addressToken, addressToken, spaceToken)
        ?: ""
