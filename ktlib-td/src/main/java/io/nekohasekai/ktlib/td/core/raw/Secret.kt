@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Returns information about a secret chat by its identifier
 * This is an offline request
 *
 * @secretChatId - Secret chat identifier
 */
suspend fun TdHandler.getSecretChat(
        secretChatId: Int
) = sync<SecretChat>(GetSecretChat(secretChatId))

suspend fun TdHandler.getSecretChatOrNull(
        secretChatId: Int
) = syncOrNull<SecretChat>(GetSecretChat(secretChatId))

fun TdHandler.getSecretChatWith(
        secretChatId: Int,
        stackIgnore: Int = 0,
        submit: (TdCallback<SecretChat>.() -> Unit)? = null
) = send(GetSecretChat(secretChatId), stackIgnore + 1, submit)

/**
 * Closes a secret chat, effectively transferring its state to secretChatStateClosed
 *
 * @secretChatId - Secret chat identifier
 */
suspend fun TdHandler.closeSecretChat(
        secretChatId: Int
) = sync<Ok>(CloseSecretChat(secretChatId))

suspend fun TdHandler.closeSecretChatOrNull(
        secretChatId: Int
) = syncOrNull<Ok>(CloseSecretChat(secretChatId))

fun TdHandler.closeSecretChatWith(
        secretChatId: Int,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CloseSecretChat(secretChatId), stackIgnore + 1, submit)
