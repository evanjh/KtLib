@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Returns emoji corresponding to a sticker
 * The list is only for informational purposes, because a sticker is always sent with a fixed emoji from the corresponding Sticker object
 *
 * @sticker - Sticker file identifier
 */
suspend fun TdHandler.getStickerEmojis(
        sticker: InputFile? = null
) = sync<Emojis>(GetStickerEmojis(sticker))

suspend fun TdHandler.getStickerEmojisOrNull(
        sticker: InputFile? = null
) = syncOrNull<Emojis>(GetStickerEmojis(sticker))

fun TdHandler.getStickerEmojisWith(
        sticker: InputFile? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<Emojis>.() -> Unit)? = null
) = send(GetStickerEmojis(sticker), stackIgnore + 1, submit)

/**
 * Searches for emojis by keywords
 * Supported only if the file database is enabled
 *
 * @text - Text to search for
 * @exactMatch - True, if only emojis, which exactly match text needs to be returned
 * @inputLanguageCodes - List of possible IETF language tags of the user's input language
 *                       May be empty if unknown
 */
suspend fun TdHandler.searchEmojis(
        text: String? = null,
        exactMatch: Boolean,
        inputLanguageCodes: Array<String>
) = sync<Emojis>(SearchEmojis(text, exactMatch, inputLanguageCodes))

suspend fun TdHandler.searchEmojisOrNull(
        text: String? = null,
        exactMatch: Boolean,
        inputLanguageCodes: Array<String>
) = syncOrNull<Emojis>(SearchEmojis(text, exactMatch, inputLanguageCodes))

fun TdHandler.searchEmojisWith(
        text: String? = null,
        exactMatch: Boolean,
        inputLanguageCodes: Array<String>,
        stackIgnore: Int = 0,
        submit: (TdCallback<Emojis>.() -> Unit)? = null
) = send(SearchEmojis(text, exactMatch, inputLanguageCodes), stackIgnore + 1, submit)

/**
 * Returns an HTTP URL which can be used to automatically log in to the translation platform and suggest new emoji replacements
 * The URL will be valid for 30 seconds after generation
 *
 * @languageCode - Language code for which the emoji replacements will be suggested
 */
suspend fun TdHandler.getEmojiSuggestionsUrl(
        languageCode: String? = null
) = sync<HttpUrl>(GetEmojiSuggestionsUrl(languageCode))

suspend fun TdHandler.getEmojiSuggestionsUrlOrNull(
        languageCode: String? = null
) = syncOrNull<HttpUrl>(GetEmojiSuggestionsUrl(languageCode))

fun TdHandler.getEmojiSuggestionsUrlWith(
        languageCode: String? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<HttpUrl>.() -> Unit)? = null
) = send(GetEmojiSuggestionsUrl(languageCode), stackIgnore + 1, submit)
