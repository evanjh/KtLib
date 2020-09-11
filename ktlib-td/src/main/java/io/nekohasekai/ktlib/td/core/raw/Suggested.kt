@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Hides a suggested action
 *
 * @action - Suggested action to hide
 */
suspend fun TdHandler.hideSuggestedAction(
        action: SuggestedAction? = null
) = sync<Ok>(HideSuggestedAction(action))

suspend fun TdHandler.hideSuggestedActionOrNull(
        action: SuggestedAction? = null
) = syncOrNull<Ok>(HideSuggestedAction(action))

fun TdHandler.hideSuggestedActionWith(
        action: SuggestedAction? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(HideSuggestedAction(action), stackIgnore + 1, submit)
