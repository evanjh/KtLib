@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns the high scores for a game and some part of the high score table in the range of the specified user
 * For bots only
 *
 * @chatId - The chat that contains the message with the game
 * @messageId - Identifier of the message
 * @userId - User identifier
 */
suspend fun TdHandler.getGameHighScores(
    chatId: Long,
    messageId: Long,
    userId: Int
) = sync<GameHighScores>(GetGameHighScores(chatId, messageId, userId))

suspend fun TdHandler.getGameHighScoresOrNull(
    chatId: Long,
    messageId: Long,
    userId: Int
) = syncOrNull<GameHighScores>(GetGameHighScores(chatId, messageId, userId))

fun TdHandler.getGameHighScoresWith(
    chatId: Long,
    messageId: Long,
    userId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<GameHighScores>.() -> Unit)? = null
) = send(GetGameHighScores(chatId, messageId, userId), stackIgnore + 1, submit)

/**
 * Returns game high scores and some part of the high score table in the range of the specified user
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @userId - User identifier
 */
suspend fun TdHandler.getInlineGameHighScores(
    inlineMessageId: String? = null,
    userId: Int
) = sync<GameHighScores>(GetInlineGameHighScores(inlineMessageId, userId))

suspend fun TdHandler.getInlineGameHighScoresOrNull(
    inlineMessageId: String? = null,
    userId: Int
) = syncOrNull<GameHighScores>(GetInlineGameHighScores(inlineMessageId, userId))

fun TdHandler.getInlineGameHighScoresWith(
    inlineMessageId: String? = null,
    userId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<GameHighScores>.() -> Unit)? = null
) = send(GetInlineGameHighScores(inlineMessageId, userId), stackIgnore + 1, submit)
