@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Closes the TDLib instance after a proper logout
 * Requires an available network connection
 * All local data will be destroyed
 * After the logout completes, updateAuthorizationState with authorizationStateClosed will be sent
 */
suspend fun TdHandler.logOut(){
    sync(LogOut())
}


suspend fun TdHandler.logOutIgnoreException(){
    syncOrNull(LogOut())
}


fun TdHandler.logOutWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(LogOut(), stackIgnore + 1, submit)

/**
 * Saves application log event on the server
 * Can be called before authorization
 *
 * @type - Event type
 * @chatId - Optional chat identifier, associated with the event
 * @data - The log event data
 */
suspend fun TdHandler.saveApplicationLogEvent(
    type: String? = null,
    chatId: Long,
    data: JsonValue? = null
){
    sync(SaveApplicationLogEvent(type, chatId, data))
}


suspend fun TdHandler.saveApplicationLogEventIgnoreException(
    type: String? = null,
    chatId: Long,
    data: JsonValue? = null
){
    syncOrNull(SaveApplicationLogEvent(type, chatId, data))
}


fun TdHandler.saveApplicationLogEventWith(
    type: String? = null,
    chatId: Long,
    data: JsonValue? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SaveApplicationLogEvent(type, chatId, data), stackIgnore + 1, submit)
