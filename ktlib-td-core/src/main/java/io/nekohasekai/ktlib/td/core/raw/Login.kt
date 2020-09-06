@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Returns information about a button of type inlineKeyboardButtonTypeLoginUrl
 * The method needs to be called when the user presses the button
 *
 * @chatId - Chat identifier of the message with the button
 * @messageId - Message identifier of the message with the button
 * @buttonId - Button identifier
 */
suspend fun TdHandler.getLoginUrlInfo(
        chatId: Long,
        messageId: Long,
        buttonId: Int
) = sync<LoginUrlInfo>(GetLoginUrlInfo(chatId, messageId, buttonId))

suspend fun TdHandler.getLoginUrlInfoOrNull(
        chatId: Long,
        messageId: Long,
        buttonId: Int
) = syncOrNull<LoginUrlInfo>(GetLoginUrlInfo(chatId, messageId, buttonId))

fun TdHandler.getLoginUrlInfoWith(
        chatId: Long,
        messageId: Long,
        buttonId: Int,
        stackIgnore: Int = 0,
        submit: (TdCallback<LoginUrlInfo>.() -> Unit)? = null
) = send(GetLoginUrlInfo(chatId, messageId, buttonId), stackIgnore + 1, submit)

/**
 * Returns an HTTP URL which can be used to automatically authorize the user on a website after clicking an inline button of type inlineKeyboardButtonTypeLoginUrl
 * Use the method getLoginUrlInfo to find whether a prior user confirmation is needed
 * If an error is returned, then the button must be handled as an ordinary URL button
 *
 * @chatId - Chat identifier of the message with the button
 * @messageId - Message identifier of the message with the button
 * @buttonId - Button identifier
 * @allowWriteAccess - True, if the user allowed the bot to send them messages
 */
suspend fun TdHandler.getLoginUrl(
        chatId: Long,
        messageId: Long,
        buttonId: Int,
        allowWriteAccess: Boolean
) = sync<HttpUrl>(GetLoginUrl(chatId, messageId, buttonId, allowWriteAccess))

suspend fun TdHandler.getLoginUrlOrNull(
        chatId: Long,
        messageId: Long,
        buttonId: Int,
        allowWriteAccess: Boolean
) = syncOrNull<HttpUrl>(GetLoginUrl(chatId, messageId, buttonId, allowWriteAccess))

fun TdHandler.getLoginUrlWith(
        chatId: Long,
        messageId: Long,
        buttonId: Int,
        allowWriteAccess: Boolean,
        stackIgnore: Int = 0,
        submit: (TdCallback<HttpUrl>.() -> Unit)? = null
) = send(GetLoginUrl(chatId, messageId, buttonId, allowWriteAccess), stackIgnore + 1, submit)
