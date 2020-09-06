@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Returns saved order info, if any
 */
suspend fun TdHandler.getSavedOrderInfo() = sync<OrderInfo>(GetSavedOrderInfo())

suspend fun TdHandler.getSavedOrderInfoOrNull() = syncOrNull<OrderInfo>(GetSavedOrderInfo())

fun TdHandler.getSavedOrderInfoWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<OrderInfo>.() -> Unit)? = null
) = send(GetSavedOrderInfo(), stackIgnore + 1, submit)

/**
 * Deletes saved order info
 */
suspend fun TdHandler.deleteSavedOrderInfo() = sync<Ok>(DeleteSavedOrderInfo())

suspend fun TdHandler.deleteSavedOrderInfoOrNull() = syncOrNull<Ok>(DeleteSavedOrderInfo())

fun TdHandler.deleteSavedOrderInfoWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteSavedOrderInfo(), stackIgnore + 1, submit)
