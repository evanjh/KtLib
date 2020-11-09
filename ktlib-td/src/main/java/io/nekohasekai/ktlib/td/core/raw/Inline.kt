@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Edits the text of an inline text or game message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 * @inputMessageContent - New text content of the message
 *                        Should be of type InputMessageText
 */
suspend fun TdHandler.editInlineMessageText(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = sync<Ok>(EditInlineMessageText(inlineMessageId, replyMarkup, inputMessageContent))

suspend fun TdHandler.editInlineMessageTextOrNull(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull<Ok>(EditInlineMessageText(inlineMessageId, replyMarkup, inputMessageContent))

fun TdHandler.editInlineMessageTextWith(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(EditInlineMessageText(inlineMessageId, replyMarkup, inputMessageContent), stackIgnore + 1, submit)

/**
 * Edits the content of a live location in an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 * @location - New location content of the message
 *             Pass null to stop sharing the live location
 * @heading - The new direction in which the location moves, in degrees
 *            Pass 0 if unknown
 * @proximityAlertRadius - The new maximum distance for proximity alerts, in meters (0-100000)
 *                         Pass 0 if the notification is disabled
 */
suspend fun TdHandler.editInlineMessageLiveLocation(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null,
    heading: Int,
    proximityAlertRadius: Int
) = sync<Ok>(EditInlineMessageLiveLocation(inlineMessageId, replyMarkup, location, heading, proximityAlertRadius))

suspend fun TdHandler.editInlineMessageLiveLocationOrNull(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null,
    heading: Int,
    proximityAlertRadius: Int
) = syncOrNull<Ok>(EditInlineMessageLiveLocation(inlineMessageId, replyMarkup, location, heading, proximityAlertRadius))

fun TdHandler.editInlineMessageLiveLocationWith(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null,
    heading: Int,
    proximityAlertRadius: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(EditInlineMessageLiveLocation(inlineMessageId, replyMarkup, location, heading, proximityAlertRadius), stackIgnore + 1, submit)

/**
 * Edits the content of a message with an animation, an audio, a document, a photo or a video in an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 *                For bots only
 * @inputMessageContent - New content of the message
 *                        Must be one of the following types: InputMessageAnimation, InputMessageAudio, InputMessageDocument, InputMessagePhoto or InputMessageVideo
 */
suspend fun TdHandler.editInlineMessageMedia(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = sync<Ok>(EditInlineMessageMedia(inlineMessageId, replyMarkup, inputMessageContent))

suspend fun TdHandler.editInlineMessageMediaOrNull(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull<Ok>(EditInlineMessageMedia(inlineMessageId, replyMarkup, inputMessageContent))

fun TdHandler.editInlineMessageMediaWith(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(EditInlineMessageMedia(inlineMessageId, replyMarkup, inputMessageContent), stackIgnore + 1, submit)

/**
 * Edits the caption of an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 * @caption - New message content caption
 *            0-GetOption("message_caption_length_max") characters
 */
suspend fun TdHandler.editInlineMessageCaption(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null
) = sync<Ok>(EditInlineMessageCaption(inlineMessageId, replyMarkup, caption))

suspend fun TdHandler.editInlineMessageCaptionOrNull(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null
) = syncOrNull<Ok>(EditInlineMessageCaption(inlineMessageId, replyMarkup, caption))

fun TdHandler.editInlineMessageCaptionWith(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(EditInlineMessageCaption(inlineMessageId, replyMarkup, caption), stackIgnore + 1, submit)

/**
 * Edits the reply markup of an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 */
suspend fun TdHandler.editInlineMessageReplyMarkup(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null
) = sync<Ok>(EditInlineMessageReplyMarkup(inlineMessageId, replyMarkup))

suspend fun TdHandler.editInlineMessageReplyMarkupOrNull(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null
) = syncOrNull<Ok>(EditInlineMessageReplyMarkup(inlineMessageId, replyMarkup))

fun TdHandler.editInlineMessageReplyMarkupWith(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(EditInlineMessageReplyMarkup(inlineMessageId, replyMarkup), stackIgnore + 1, submit)

/**
 * Sends an inline query to a bot and returns its results
 * Returns an error with code 502 if the bot fails to answer the query before the query timeout expires
 *
 * @botUserId - The identifier of the target bot
 * @chatId - Identifier of the chat where the query was sent
 * @userLocation - Location of the user, only if needed
 * @query - Text of the query
 * @offset - Offset of the first entry to return
 */
suspend fun TdHandler.getInlineQueryResults(
    botUserId: Int,
    chatId: Long,
    userLocation: Location? = null,
    query: String? = null,
    offset: String? = null
) = sync<InlineQueryResults>(GetInlineQueryResults(botUserId, chatId, userLocation, query, offset))

suspend fun TdHandler.getInlineQueryResultsOrNull(
    botUserId: Int,
    chatId: Long,
    userLocation: Location? = null,
    query: String? = null,
    offset: String? = null
) = syncOrNull<InlineQueryResults>(GetInlineQueryResults(botUserId, chatId, userLocation, query, offset))

fun TdHandler.getInlineQueryResultsWith(
    botUserId: Int,
    chatId: Long,
    userLocation: Location? = null,
    query: String? = null,
    offset: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<InlineQueryResults>.() -> Unit)? = null
) = send(GetInlineQueryResults(botUserId, chatId, userLocation, query, offset), stackIgnore + 1, submit)

/**
 * Sets the result of an inline query
 * For bots only
 *
 * @inlineQueryId - Identifier of the inline query
 * @isPersonal - True, if the result of the query can be cached for the specified user
 * @results - The results of the query
 * @cacheTime - Allowed time to cache the results of the query, in seconds
 * @nextOffset - Offset for the next inline query
 *               Pass an empty string if there are no more results
 * @switchPmText - If non-empty, this text should be shown on the button that opens a private chat with the bot and sends a start message to the bot with the parameter switch_pm_parameter
 * @switchPmParameter - The parameter for the bot start message
 */
suspend fun TdHandler.answerInlineQuery(
    inlineQueryId: Long,
    isPersonal: Boolean,
    results: Array<InputInlineQueryResult>,
    cacheTime: Int,
    nextOffset: String? = null,
    switchPmText: String? = null,
    switchPmParameter: String? = null
) = sync<Ok>(AnswerInlineQuery(inlineQueryId, isPersonal, results, cacheTime, nextOffset, switchPmText, switchPmParameter))

suspend fun TdHandler.answerInlineQueryOrNull(
    inlineQueryId: Long,
    isPersonal: Boolean,
    results: Array<InputInlineQueryResult>,
    cacheTime: Int,
    nextOffset: String? = null,
    switchPmText: String? = null,
    switchPmParameter: String? = null
) = syncOrNull<Ok>(AnswerInlineQuery(inlineQueryId, isPersonal, results, cacheTime, nextOffset, switchPmText, switchPmParameter))

fun TdHandler.answerInlineQueryWith(
    inlineQueryId: Long,
    isPersonal: Boolean,
    results: Array<InputInlineQueryResult>,
    cacheTime: Int,
    nextOffset: String? = null,
    switchPmText: String? = null,
    switchPmParameter: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AnswerInlineQuery(inlineQueryId, isPersonal, results, cacheTime, nextOffset, switchPmText, switchPmParameter), stackIgnore + 1, submit)

/**
 * Updates the game score of the specified user in a game
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @editMessage - True, if the message should be edited
 * @userId - User identifier
 * @score - The new score
 * @force - Pass true to update the score even if it decreases
 *          If the score is 0, the user will be deleted from the high score table
 */
suspend fun TdHandler.setInlineGameScore(
    inlineMessageId: String? = null,
    editMessage: Boolean,
    userId: Int,
    score: Int,
    force: Boolean
) = sync<Ok>(SetInlineGameScore(inlineMessageId, editMessage, userId, score, force))

suspend fun TdHandler.setInlineGameScoreOrNull(
    inlineMessageId: String? = null,
    editMessage: Boolean,
    userId: Int,
    score: Int,
    force: Boolean
) = syncOrNull<Ok>(SetInlineGameScore(inlineMessageId, editMessage, userId, score, force))

fun TdHandler.setInlineGameScoreWith(
    inlineMessageId: String? = null,
    editMessage: Boolean,
    userId: Int,
    score: Int,
    force: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetInlineGameScore(inlineMessageId, editMessage, userId, score, force), stackIgnore + 1, submit)
