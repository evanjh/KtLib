@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.AuthorizationState
import td.TdApi.GetAuthorizationState

/**
 * Returns the current authorization state
 * This is an offline request
 * For informational purposes only
 * Use updateAuthorizationState instead to maintain the current authorization state
 * Can be called before initialization
 */
suspend fun TdHandler.getAuthorizationState() = sync<AuthorizationState>(GetAuthorizationState())

suspend fun TdHandler.getAuthorizationStateOrNull() = syncOrNull<AuthorizationState>(GetAuthorizationState())

fun TdHandler.getAuthorizationStateWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<AuthorizationState>.() -> Unit)? = null
) = send(GetAuthorizationState(), stackIgnore + 1, submit)
