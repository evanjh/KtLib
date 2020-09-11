@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.GetCurrentState
import td.TdApi.Updates

/**
 * Returns all updates needed to restore current TDLib state, i.e
 * All actual UpdateAuthorizationState/UpdateUser/UpdateNewChat and others
 * This is especially useful if TDLib is run in a separate process
 * Can be called before initialization
 */
suspend fun TdHandler.getCurrentState() = sync<Updates>(GetCurrentState())

suspend fun TdHandler.getCurrentStateOrNull() = syncOrNull<Updates>(GetCurrentState())

fun TdHandler.getCurrentStateWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Updates>.() -> Unit)? = null
) = send(GetCurrentState(), stackIgnore + 1, submit)
