@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Closes the TDLib instance
 * All databases will be flushed to disk and properly closed
 * After the close completes, updateAuthorizationState with authorizationStateClosed will be sent
 * Can be called before initialization
 */
suspend fun TdHandler.close() = sync<Ok>(Close())

suspend fun TdHandler.closeOrNull() = syncOrNull<Ok>(Close())

fun TdHandler.closeWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(Close(), stackIgnore + 1, submit)

/**
 * Closes the TDLib instance, destroying all local data without a proper logout
 * The current user session will remain in the list of all active sessions
 * All local data will be destroyed
 * After the destruction completes updateAuthorizationState with authorizationStateClosed will be sent
 * Can be called before authorization
 */
suspend fun TdHandler.destroy() = sync<Ok>(Destroy())

suspend fun TdHandler.destroyOrNull() = syncOrNull<Ok>(Destroy())

fun TdHandler.destroyWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(Destroy(), stackIgnore + 1, submit)

/**
 * Sets the list of commands supported by the bot
 * For bots only
 *
 * @commands - List of the bot's commands
 */
suspend fun TdHandler.setCommands(
        commands: Array<BotCommand>
) = sync<Ok>(SetCommands(commands))

suspend fun TdHandler.setCommandsOrNull(
        commands: Array<BotCommand>
) = syncOrNull<Ok>(SetCommands(commands))

fun TdHandler.setCommandsWith(
        commands: Array<BotCommand>,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetCommands(commands), stackIgnore + 1, submit)

/**
 * Returns t.me URLs recently visited by a newly registered user
 *
 * @referrer - Google Play referrer to identify the user
 */
suspend fun TdHandler.getRecentlyVisitedTMeUrls(
        referrer: String? = null
) = sync<TMeUrls>(GetRecentlyVisitedTMeUrls(referrer))

suspend fun TdHandler.getRecentlyVisitedTMeUrlsOrNull(
        referrer: String? = null
) = syncOrNull<TMeUrls>(GetRecentlyVisitedTMeUrls(referrer))

fun TdHandler.getRecentlyVisitedTMeUrlsWith(
        referrer: String? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<TMeUrls>.() -> Unit)? = null
) = send(GetRecentlyVisitedTMeUrls(referrer), stackIgnore + 1, submit)

/**
 * Succeeds after a specified amount of time has passed
 * Can be called before initialization
 *
 * @seconds - Number of seconds before the function returns
 */
suspend fun TdHandler.setAlarm(
        seconds: Double
) = sync<Ok>(SetAlarm(seconds))

suspend fun TdHandler.setAlarmOrNull(
        seconds: Double
) = syncOrNull<Ok>(SetAlarm(seconds))

fun TdHandler.setAlarmWith(
        seconds: Double,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetAlarm(seconds), stackIgnore + 1, submit)
