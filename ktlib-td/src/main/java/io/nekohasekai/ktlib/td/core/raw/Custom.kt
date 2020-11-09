@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Adds a custom server language pack to the list of installed language packs in current localization target
 * Can be called before authorization
 *
 * @languagePackId - Identifier of a language pack to be added
 *                   May be different from a name that is used in an "https://t.me/setlanguage/" link
 */
suspend fun TdHandler.addCustomServerLanguagePack(
    languagePackId: String? = null
){
    sync(AddCustomServerLanguagePack(languagePackId))
}


suspend fun TdHandler.addCustomServerLanguagePackIgnoreException(
    languagePackId: String? = null
){
    syncOrNull(AddCustomServerLanguagePack(languagePackId))
}


fun TdHandler.addCustomServerLanguagePackWith(
    languagePackId: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddCustomServerLanguagePack(languagePackId), stackIgnore + 1, submit)

/**
 * Adds or changes a custom local language pack to the current localization target
 *
 * @info - Information about the language pack
 *         Language pack ID must start with 'X', consist only of English letters, digits and hyphens, and must not exceed 64 characters
 *         Can be called before authorization
 * @strings - Strings of the new language pack
 */
suspend fun TdHandler.setCustomLanguagePack(
    info: LanguagePackInfo? = null,
    strings: Array<LanguagePackString>
){
    sync(SetCustomLanguagePack(info, strings))
}


suspend fun TdHandler.setCustomLanguagePackIgnoreException(
    info: LanguagePackInfo? = null,
    strings: Array<LanguagePackString>
){
    syncOrNull(SetCustomLanguagePack(info, strings))
}


fun TdHandler.setCustomLanguagePackWith(
    info: LanguagePackInfo? = null,
    strings: Array<LanguagePackString>,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetCustomLanguagePack(info, strings), stackIgnore + 1, submit)

/**
 * Edits information about a custom local language pack in the current localization target
 * Can be called before authorization
 *
 * @info - New information about the custom local language pack
 */
suspend fun TdHandler.editCustomLanguagePackInfo(
    info: LanguagePackInfo? = null
){
    sync(EditCustomLanguagePackInfo(info))
}


suspend fun TdHandler.editCustomLanguagePackInfoIgnoreException(
    info: LanguagePackInfo? = null
){
    syncOrNull(EditCustomLanguagePackInfo(info))
}


fun TdHandler.editCustomLanguagePackInfoWith(
    info: LanguagePackInfo? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(EditCustomLanguagePackInfo(info), stackIgnore + 1, submit)

/**
 * Adds, edits or deletes a string in a custom local language pack
 * Can be called before authorization
 *
 * @languagePackId - Identifier of a previously added custom local language pack in the current localization target
 * @newString - New language pack string
 */
suspend fun TdHandler.setCustomLanguagePackString(
    languagePackId: String? = null,
    newString: LanguagePackString? = null
){
    sync(SetCustomLanguagePackString(languagePackId, newString))
}


suspend fun TdHandler.setCustomLanguagePackStringIgnoreException(
    languagePackId: String? = null,
    newString: LanguagePackString? = null
){
    syncOrNull(SetCustomLanguagePackString(languagePackId, newString))
}


fun TdHandler.setCustomLanguagePackStringWith(
    languagePackId: String? = null,
    newString: LanguagePackString? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetCustomLanguagePackString(languagePackId, newString), stackIgnore + 1, submit)

/**
 * Sends a custom request
 * For bots only
 *
 * @method - The method name
 * @parameters - JSON-serialized method parameters
 */
suspend fun TdHandler.sendCustomRequest(
    method: String? = null,
    parameters: String? = null
) = sync(SendCustomRequest(method, parameters))

suspend fun TdHandler.sendCustomRequestOrNull(
    method: String? = null,
    parameters: String? = null
) = syncOrNull(SendCustomRequest(method, parameters))

fun TdHandler.sendCustomRequestWith(
    method: String? = null,
    parameters: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<CustomRequestResult>.() -> Unit)? = null
) = send(SendCustomRequest(method, parameters), stackIgnore + 1, submit)

/**
 * Answers a custom query
 * For bots only
 *
 * @customQueryId - Identifier of a custom query
 * @data - JSON-serialized answer to the query
 */
suspend fun TdHandler.answerCustomQuery(
    customQueryId: Long,
    data: String? = null
){
    sync(AnswerCustomQuery(customQueryId, data))
}


suspend fun TdHandler.answerCustomQueryIgnoreException(
    customQueryId: Long,
    data: String? = null
){
    syncOrNull(AnswerCustomQuery(customQueryId, data))
}


fun TdHandler.answerCustomQueryWith(
    customQueryId: Long,
    data: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AnswerCustomQuery(customQueryId, data), stackIgnore + 1, submit)
