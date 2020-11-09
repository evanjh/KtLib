@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns the current authorization state
 * This is an offline request
 * For informational purposes only
 * Use updateAuthorizationState instead to maintain the current authorization state
 * Can be called before initialization
 */
suspend fun TdHandler.getAuthorizationState() = sync(GetAuthorizationState())

suspend fun TdHandler.getAuthorizationStateOrNull() = syncOrNull(GetAuthorizationState())

fun TdHandler.getAuthorizationStateWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<AuthorizationState>.() -> Unit)? = null
) = send(GetAuthorizationState(), stackIgnore + 1, submit)
