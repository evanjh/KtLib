package io.nekohasekai.ktlib.td.generator.builder

import io.nekohasekai.ktlib.td.generator.tl.*

private val customMapping = mapOf(
        "SetName" to "User",
        "SetBio" to "User",
        "DeleteSavedCredentials" to "Payment",
        "GetPushReceiverId" to "Notification"
)

private val ignoredTypes = setOf(
        "T",
        "Ok",
        "Top",
        "Basic",
        "Chats",
        "Count",
        "Error",
        "Formatted",
        "Found",
        "Http",
        "Imported",
        "Messages",
        "Public",
        "Push",
        "Recovery",
        "Saved",
        "Scope",
        "Seconds",
        "Sessions",
        "Stickers",
        "Temporary",
        "Update",
        "Users",
        "Animations",
        "Backgrounds",
        "Draft",
        "Emojis",
        "Hashtags"
)

private val additionalTypes = setOf("Emoji", "Group", "Hashtag")

private val comporator = Comparator<Pair<String, Int>> { t1, t2 ->
    when (val c1 = t1.second.compareTo(t2.second)) {
        0 -> t2.first.length.compareTo(t1.first.length)
        else -> c1
    }
}

fun Iterable<String>.findByType(type: String): String? =
        map { it to type.indexOf(it) }
                .filter { it.second != -1 }
                .sortedWith(comporator)
                .firstOrNull()
                ?.first

fun List<TlData>.types(): List<String> =
        filter { it is TlObject || it is TlAbstract }
                .map { it.type.decapitalize().takeWhile(Char::isLowerCase).capitalize() }
                .distinct()

fun List<TlData>.groupFunctions(): Map<String, List<TlFunction>> {
    val types = (types() - ignoredTypes + additionalTypes).sorted().toSet()
    return filterIsInstance<TlFunction>().filter { TlAddition.Sync !in it.metadata.additions }.map { it to (it.type.capitalize() to it.returnType.capitalize()) }
            .groupBy { (_, pair) ->
                val (funcType, returnType) = pair
                val type = customMapping[funcType]
                        ?: types.findByType(returnType)
                        ?: types.findByType(funcType)
                        ?: "Util"
                type
            }
            .mapValues { (_, list) -> list.map { it.first } }
}

fun StringBuilder.buildFunction(function: TlFunction, metadata: TlDataMetadata) {
    buildDescription(function.descriptionsWithProperties())
    buildAnnotations(function.metadata.additions)
    append("suspend fun TdHandler.").append(function.type.decapitalize())
    buildParameters(function.metadata.properties.map { it.toParameter(metadata) }, addEmptyBrackets = true)
    fun StringBuilder.buildContent() =  withRoundBrackets {
        append(function.type.capitalize())
        if (function.metadata.properties.isNotEmpty()) withRoundBrackets {
            function.metadata.properties.joinTo(this, ", ") { it.name.snakeToCamel() }
        } else append("()")
    }
    if (function.returnType == "Ok") {
        withCurlyBrackets {
            append("sync")
            buildContent()
        }
    } else {
        append(" = sync")
        buildContent()
    }

    append("\n")
}

fun StringBuilder.buildNullaableFunction(function: TlFunction, metadata: TlDataMetadata) {
    //buildDescription(function.descriptionsWithProperties())
    buildAnnotations(function.metadata.additions)
    append("suspend fun TdHandler.").append(function.type.decapitalize())
    if (function.returnType == "Ok") {
        append("IgnoreException")
    } else {
        append("OrNull")
    }
    buildParameters(function.metadata.properties.map { it.toParameter(metadata) }, addEmptyBrackets = true)
    fun StringBuilder.buildContent() =  withRoundBrackets {
        append(function.type.capitalize())
        if (function.metadata.properties.isNotEmpty()) withRoundBrackets {
            function.metadata.properties.joinTo(this, ", ") { it.name.snakeToCamel() }
        } else append("()")
    }
    if (function.returnType == "Ok") {
        withCurlyBrackets {
            append("syncOrNull")
            buildContent()
        }
    } else {
        append(" = syncOrNull")
        buildContent()
    }
    append("\n")
}

fun StringBuilder.buildCallbackFunction(function: TlFunction, metadata: TlDataMetadata) {
    //buildDescription(function.descriptionsWithProperties())
    buildAnnotations(function.metadata.additions)
    append("fun TdHandler.").append(function.type.decapitalize()).append("With")
    buildParameters(listOf(* function.metadata.properties.map { it.toParameter(metadata) }.toTypedArray(), "stackIgnore: Int = 0", "submit: (TdCallback<${function.returnType.capitalize()}>.() -> Unit)? = null"), addEmptyBrackets = true)
    append(" = send")
    withRoundBrackets {
        append(function.type.capitalize())
        if (function.metadata.properties.isNotEmpty()) withRoundBrackets {
            function.metadata.properties.joinTo(this, ", ") { it.name.snakeToCamel() }
        } else append("()")
        append(", stackIgnore + 1, submit")
    }
    append("\n")
}

fun StringBuilder.buildHeader() {
    suppress("unused")
    //useExperimentalAnnotationsForFile()
    append("\n")
    buildPackage("io.nekohasekai.ktlib.td.core.raw")
    append("\n")
    //buildImport("kotlinx.coroutines")
    buildImport("td.TdApi")
    buildImport("io.nekohasekai.ktlib.td.core")
}