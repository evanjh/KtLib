@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Hides a suggested action
 *
 * @action - Suggested action to hide
 */
suspend fun TdHandler.hideSuggestedAction(
    action: SuggestedAction? = null
){
    sync(HideSuggestedAction(action))
}


suspend fun TdHandler.hideSuggestedActionIgnoreException(
    action: SuggestedAction? = null
){
    syncOrNull(HideSuggestedAction(action))
}


fun TdHandler.hideSuggestedActionWith(
    action: SuggestedAction? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(HideSuggestedAction(action), stackIgnore + 1, submit)
