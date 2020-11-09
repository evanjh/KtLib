@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns saved order info, if any
 */
suspend fun TdHandler.getSavedOrderInfo() = sync(GetSavedOrderInfo())

suspend fun TdHandler.getSavedOrderInfoOrNull() = syncOrNull(GetSavedOrderInfo())

fun TdHandler.getSavedOrderInfoWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<OrderInfo>.() -> Unit)? = null
) = send(GetSavedOrderInfo(), stackIgnore + 1, submit)

/**
 * Deletes saved order info
 */
suspend fun TdHandler.deleteSavedOrderInfo(){
    sync(DeleteSavedOrderInfo())
}


suspend fun TdHandler.deleteSavedOrderInfoIgnoreException(){
    syncOrNull(DeleteSavedOrderInfo())
}


fun TdHandler.deleteSavedOrderInfoWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteSavedOrderInfo(), stackIgnore + 1, submit)
