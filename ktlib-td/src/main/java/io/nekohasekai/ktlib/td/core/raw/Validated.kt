@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Validates the order information provided by a user and returns the available shipping options for a flexible invoice
 *
 * @chatId - Chat identifier of the Invoice message
 * @messageId - Message identifier
 * @orderInfo - The order information, provided by the user
 * @allowSave - True, if the order information can be saved
 */
suspend fun TdHandler.validateOrderInfo(
    chatId: Long,
    messageId: Long,
    orderInfo: OrderInfo? = null,
    allowSave: Boolean
) = sync<ValidatedOrderInfo>(ValidateOrderInfo(chatId, messageId, orderInfo, allowSave))

suspend fun TdHandler.validateOrderInfoOrNull(
    chatId: Long,
    messageId: Long,
    orderInfo: OrderInfo? = null,
    allowSave: Boolean
) = syncOrNull<ValidatedOrderInfo>(ValidateOrderInfo(chatId, messageId, orderInfo, allowSave))

fun TdHandler.validateOrderInfoWith(
    chatId: Long,
    messageId: Long,
    orderInfo: OrderInfo? = null,
    allowSave: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<ValidatedOrderInfo>.() -> Unit)? = null
) = send(ValidateOrderInfo(chatId, messageId, orderInfo, allowSave), stackIgnore + 1, submit)
