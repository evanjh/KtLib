@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Confirms QR code authentication on another device
 * Returns created session on success
 *
 * @link - A link from a QR code
 *         The link must be scanned by the in-app camera
 */
suspend fun TdHandler.confirmQrCodeAuthentication(
        link: String? = null
) = sync<Session>(ConfirmQrCodeAuthentication(link))

suspend fun TdHandler.confirmQrCodeAuthenticationOrNull(
        link: String? = null
) = syncOrNull<Session>(ConfirmQrCodeAuthentication(link))

fun TdHandler.confirmQrCodeAuthenticationWith(
        link: String? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<Session>.() -> Unit)? = null
) = send(ConfirmQrCodeAuthentication(link), stackIgnore + 1, submit)

/**
 * Returns all active sessions of the current user
 */
suspend fun TdHandler.getActiveSessions() = sync<Sessions>(GetActiveSessions())

suspend fun TdHandler.getActiveSessionsOrNull() = syncOrNull<Sessions>(GetActiveSessions())

fun TdHandler.getActiveSessionsWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Sessions>.() -> Unit)? = null
) = send(GetActiveSessions(), stackIgnore + 1, submit)

/**
 * Terminates a session of the current user
 *
 * @sessionId - Session identifier
 */
suspend fun TdHandler.terminateSession(
        sessionId: Long
) = sync<Ok>(TerminateSession(sessionId))

suspend fun TdHandler.terminateSessionOrNull(
        sessionId: Long
) = syncOrNull<Ok>(TerminateSession(sessionId))

fun TdHandler.terminateSessionWith(
        sessionId: Long,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(TerminateSession(sessionId), stackIgnore + 1, submit)

/**
 * Terminates all other sessions of the current user
 */
suspend fun TdHandler.terminateAllOtherSessions() = sync<Ok>(TerminateAllOtherSessions())

suspend fun TdHandler.terminateAllOtherSessionsOrNull() = syncOrNull<Ok>(TerminateAllOtherSessions())

fun TdHandler.terminateAllOtherSessionsWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(TerminateAllOtherSessions(), stackIgnore + 1, submit)
