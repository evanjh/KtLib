@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Sends a callback query to a bot and returns an answer
 * Returns an error with code 502 if the bot fails to answer the query before the query timeout expires
 *
 * @chatId - Identifier of the chat with the message
 * @messageId - Identifier of the message from which the query originated
 * @payload - Query payload
 */
suspend fun TdHandler.getCallbackQueryAnswer(
        chatId: Long,
        messageId: Long,
        payload: CallbackQueryPayload? = null
) = sync<CallbackQueryAnswer>(GetCallbackQueryAnswer(chatId, messageId, payload))

suspend fun TdHandler.getCallbackQueryAnswerOrNull(
        chatId: Long,
        messageId: Long,
        payload: CallbackQueryPayload? = null
) = syncOrNull<CallbackQueryAnswer>(GetCallbackQueryAnswer(chatId, messageId, payload))

fun TdHandler.getCallbackQueryAnswerWith(
        chatId: Long,
        messageId: Long,
        payload: CallbackQueryPayload? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<CallbackQueryAnswer>.() -> Unit)? = null
) = send(GetCallbackQueryAnswer(chatId, messageId, payload), stackIgnore + 1, submit)

/**
 * Sets the result of a callback query
 * For bots only
 *
 * @callbackQueryId - Identifier of the callback query
 * @text - Text of the answer
 * @showAlert - If true, an alert should be shown to the user instead of a toast notification
 * @url - URL to be opened
 * @cacheTime - Time during which the result of the query can be cached, in seconds
 */
suspend fun TdHandler.answerCallbackQuery(
        callbackQueryId: Long,
        text: String? = null,
        showAlert: Boolean,
        url: String? = null,
        cacheTime: Int
) = sync<Ok>(AnswerCallbackQuery(callbackQueryId, text, showAlert, url, cacheTime))

suspend fun TdHandler.answerCallbackQueryOrNull(
        callbackQueryId: Long,
        text: String? = null,
        showAlert: Boolean,
        url: String? = null,
        cacheTime: Int
) = syncOrNull<Ok>(AnswerCallbackQuery(callbackQueryId, text, showAlert, url, cacheTime))

fun TdHandler.answerCallbackQueryWith(
        callbackQueryId: Long,
        text: String? = null,
        showAlert: Boolean,
        url: String? = null,
        cacheTime: Int,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AnswerCallbackQuery(callbackQueryId, text, showAlert, url, cacheTime), stackIgnore + 1, submit)
