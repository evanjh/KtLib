@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns information about a language pack
 * Returned language pack identifier may be different from a provided one
 * Can be called before authorization
 *
 * @languagePackId - Language pack identifier
 */
suspend fun TdHandler.getLanguagePackInfo(
    languagePackId: String? = null
) = sync<LanguagePackInfo>(GetLanguagePackInfo(languagePackId))

suspend fun TdHandler.getLanguagePackInfoOrNull(
    languagePackId: String? = null
) = syncOrNull<LanguagePackInfo>(GetLanguagePackInfo(languagePackId))

fun TdHandler.getLanguagePackInfoWith(
    languagePackId: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<LanguagePackInfo>.() -> Unit)? = null
) = send(GetLanguagePackInfo(languagePackId), stackIgnore + 1, submit)

/**
 * Returns strings from a language pack in the current localization target by their keys
 * Can be called before authorization
 *
 * @languagePackId - Language pack identifier of the strings to be returned
 * @keys - Language pack keys of the strings to be returned
 *         Leave empty to request all available strings
 */
suspend fun TdHandler.getLanguagePackStrings(
    languagePackId: String? = null,
    keys: Array<String>
) = sync<LanguagePackStrings>(GetLanguagePackStrings(languagePackId, keys))

suspend fun TdHandler.getLanguagePackStringsOrNull(
    languagePackId: String? = null,
    keys: Array<String>
) = syncOrNull<LanguagePackStrings>(GetLanguagePackStrings(languagePackId, keys))

fun TdHandler.getLanguagePackStringsWith(
    languagePackId: String? = null,
    keys: Array<String>,
    stackIgnore: Int = 0,
    submit: (TdCallback<LanguagePackStrings>.() -> Unit)? = null
) = send(GetLanguagePackStrings(languagePackId, keys), stackIgnore + 1, submit)

/**
 * Fetches the latest versions of all strings from a language pack in the current localization target from the server
 * This method doesn't need to be called explicitly for the current used/base language packs
 * Can be called before authorization
 *
 * @languagePackId - Language pack identifier
 */
suspend fun TdHandler.synchronizeLanguagePack(
    languagePackId: String? = null
) = sync<Ok>(SynchronizeLanguagePack(languagePackId))

suspend fun TdHandler.synchronizeLanguagePackOrNull(
    languagePackId: String? = null
) = syncOrNull<Ok>(SynchronizeLanguagePack(languagePackId))

fun TdHandler.synchronizeLanguagePackWith(
    languagePackId: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SynchronizeLanguagePack(languagePackId), stackIgnore + 1, submit)

/**
 * Deletes all information about a language pack in the current localization target
 * The language pack which is currently in use (including base language pack) or is being synchronized can't be deleted
 * Can be called before authorization
 *
 * @languagePackId - Identifier of the language pack to delete
 */
suspend fun TdHandler.deleteLanguagePack(
    languagePackId: String? = null
) = sync<Ok>(DeleteLanguagePack(languagePackId))

suspend fun TdHandler.deleteLanguagePackOrNull(
    languagePackId: String? = null
) = syncOrNull<Ok>(DeleteLanguagePack(languagePackId))

fun TdHandler.deleteLanguagePackWith(
    languagePackId: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteLanguagePack(languagePackId), stackIgnore + 1, submit)
