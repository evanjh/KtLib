@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Changes the user answer to a poll
 * A poll in quiz mode can be answered only once
 *
 * @chatId - Identifier of the chat to which the poll belongs
 * @messageId - Identifier of the message containing the poll
 * @optionIds - 0-based identifiers of answer options, chosen by the user
 *              User can choose more than 1 answer option only is the poll allows multiple answers
 */
suspend fun TdHandler.setPollAnswer(
    chatId: Long,
    messageId: Long,
    optionIds: IntArray
){
    sync(SetPollAnswer(chatId, messageId, optionIds))
}


suspend fun TdHandler.setPollAnswerIgnoreException(
    chatId: Long,
    messageId: Long,
    optionIds: IntArray
){
    syncOrNull(SetPollAnswer(chatId, messageId, optionIds))
}


fun TdHandler.setPollAnswerWith(
    chatId: Long,
    messageId: Long,
    optionIds: IntArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetPollAnswer(chatId, messageId, optionIds), stackIgnore + 1, submit)

/**
 * Stops a poll
 * A poll in a message can be stopped when the message has can_be_edited flag set
 *
 * @chatId - Identifier of the chat to which the poll belongs
 * @messageId - Identifier of the message containing the poll
 * @replyMarkup - The new message reply markup
 *                For bots only
 */
suspend fun TdHandler.stopPoll(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null
){
    sync(StopPoll(chatId, messageId, replyMarkup))
}


suspend fun TdHandler.stopPollIgnoreException(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null
){
    syncOrNull(StopPoll(chatId, messageId, replyMarkup))
}


fun TdHandler.stopPollWith(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(StopPoll(chatId, messageId, replyMarkup), stackIgnore + 1, submit)
