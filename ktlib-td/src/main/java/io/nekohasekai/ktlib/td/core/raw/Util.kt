@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Closes the TDLib instance
 * All databases will be flushed to disk and properly closed
 * After the close completes, updateAuthorizationState with authorizationStateClosed will be sent
 * Can be called before initialization
 */
suspend fun TdHandler.close(){
    sync(Close())
}


suspend fun TdHandler.closeIgnoreException(){
    syncOrNull(Close())
}


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
suspend fun TdHandler.destroy(){
    sync(Destroy())
}


suspend fun TdHandler.destroyIgnoreException(){
    syncOrNull(Destroy())
}


fun TdHandler.destroyWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(Destroy(), stackIgnore + 1, submit)

/**
 * Returns an HTTP URL which can be used to automatically authorize the current user on a website after clicking an HTTP link
 * Use the method getExternalLinkInfo to find whether a prior user confirmation is needed
 *
 * @link - The HTTP link
 * @allowWriteAccess - True, if the current user allowed the bot, returned in getExternalLinkInfo, to send them messages
 */
suspend fun TdHandler.getExternalLink(
    link: String? = null,
    allowWriteAccess: Boolean
) = sync(GetExternalLink(link, allowWriteAccess))

suspend fun TdHandler.getExternalLinkOrNull(
    link: String? = null,
    allowWriteAccess: Boolean
) = syncOrNull(GetExternalLink(link, allowWriteAccess))

fun TdHandler.getExternalLinkWith(
    link: String? = null,
    allowWriteAccess: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<HttpUrl>.() -> Unit)? = null
) = send(GetExternalLink(link, allowWriteAccess), stackIgnore + 1, submit)

/**
 * Sets the list of commands supported by the bot
 * For bots only
 *
 * @commands - List of the bot's commands
 */
suspend fun TdHandler.setCommands(
    commands: Array<BotCommand>
){
    sync(SetCommands(commands))
}


suspend fun TdHandler.setCommandsIgnoreException(
    commands: Array<BotCommand>
){
    syncOrNull(SetCommands(commands))
}


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
) = sync(GetRecentlyVisitedTMeUrls(referrer))

suspend fun TdHandler.getRecentlyVisitedTMeUrlsOrNull(
    referrer: String? = null
) = syncOrNull(GetRecentlyVisitedTMeUrls(referrer))

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
){
    sync(SetAlarm(seconds))
}


suspend fun TdHandler.setAlarmIgnoreException(
    seconds: Double
){
    syncOrNull(SetAlarm(seconds))
}


fun TdHandler.setAlarmWith(
    seconds: Double,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetAlarm(seconds), stackIgnore + 1, submit)
