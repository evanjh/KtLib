@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Returns an invoice payment form
 * This method should be called when the user presses inlineKeyboardButtonBuy
 *
 * @chatId - Chat identifier of the Invoice message
 * @messageId - Message identifier
 */
suspend fun TdHandler.getPaymentForm(
        chatId: Long,
        messageId: Long
) = sync<PaymentForm>(GetPaymentForm(chatId, messageId))

suspend fun TdHandler.getPaymentFormOrNull(
        chatId: Long,
        messageId: Long
) = syncOrNull<PaymentForm>(GetPaymentForm(chatId, messageId))

fun TdHandler.getPaymentFormWith(
        chatId: Long,
        messageId: Long,
        stackIgnore: Int = 0,
        submit: (TdCallback<PaymentForm>.() -> Unit)? = null
) = send(GetPaymentForm(chatId, messageId), stackIgnore + 1, submit)

/**
 * Sends a filled-out payment form to the bot for final verification
 *
 * @chatId - Chat identifier of the Invoice message
 * @messageId - Message identifier
 * @orderInfoId - Identifier returned by ValidateOrderInfo, or an empty string
 * @shippingOptionId - Identifier of a chosen shipping option, if applicable
 * @credentials - The credentials chosen by user for payment
 */
suspend fun TdHandler.sendPaymentForm(
        chatId: Long,
        messageId: Long,
        orderInfoId: String? = null,
        shippingOptionId: String? = null,
        credentials: InputCredentials? = null
) = sync<PaymentResult>(SendPaymentForm(chatId, messageId, orderInfoId, shippingOptionId, credentials))

suspend fun TdHandler.sendPaymentFormOrNull(
        chatId: Long,
        messageId: Long,
        orderInfoId: String? = null,
        shippingOptionId: String? = null,
        credentials: InputCredentials? = null
) = syncOrNull<PaymentResult>(SendPaymentForm(chatId, messageId, orderInfoId, shippingOptionId, credentials))

fun TdHandler.sendPaymentFormWith(
        chatId: Long,
        messageId: Long,
        orderInfoId: String? = null,
        shippingOptionId: String? = null,
        credentials: InputCredentials? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<PaymentResult>.() -> Unit)? = null
) = send(SendPaymentForm(chatId, messageId, orderInfoId, shippingOptionId, credentials), stackIgnore + 1, submit)

/**
 * Returns information about a successful payment
 *
 * @chatId - Chat identifier of the PaymentSuccessful message
 * @messageId - Message identifier
 */
suspend fun TdHandler.getPaymentReceipt(
        chatId: Long,
        messageId: Long
) = sync<PaymentReceipt>(GetPaymentReceipt(chatId, messageId))

suspend fun TdHandler.getPaymentReceiptOrNull(
        chatId: Long,
        messageId: Long
) = syncOrNull<PaymentReceipt>(GetPaymentReceipt(chatId, messageId))

fun TdHandler.getPaymentReceiptWith(
        chatId: Long,
        messageId: Long,
        stackIgnore: Int = 0,
        submit: (TdCallback<PaymentReceipt>.() -> Unit)? = null
) = send(GetPaymentReceipt(chatId, messageId), stackIgnore + 1, submit)

/**
 * Deletes saved credentials for all payment provider bots
 */
suspend fun TdHandler.deleteSavedCredentials() = sync<Ok>(DeleteSavedCredentials())

suspend fun TdHandler.deleteSavedCredentialsOrNull() = syncOrNull<Ok>(DeleteSavedCredentials())

fun TdHandler.deleteSavedCredentialsWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteSavedCredentials(), stackIgnore + 1, submit)
