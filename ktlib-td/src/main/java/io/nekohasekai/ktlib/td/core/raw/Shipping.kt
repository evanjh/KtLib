@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Sets the result of a shipping query
 * For bots only
 *
 * @shippingQueryId - Identifier of the shipping query
 * @shippingOptions - Available shipping options
 * @errorMessage - An error message, empty on success
 */
suspend fun TdHandler.answerShippingQuery(
        shippingQueryId: Long,
        shippingOptions: Array<ShippingOption>,
        errorMessage: String? = null
) = sync<Ok>(AnswerShippingQuery(shippingQueryId, shippingOptions, errorMessage))

suspend fun TdHandler.answerShippingQueryOrNull(
        shippingQueryId: Long,
        shippingOptions: Array<ShippingOption>,
        errorMessage: String? = null
) = syncOrNull<Ok>(AnswerShippingQuery(shippingQueryId, shippingOptions, errorMessage))

fun TdHandler.answerShippingQueryWith(
        shippingQueryId: Long,
        shippingOptions: Array<ShippingOption>,
        errorMessage: String? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AnswerShippingQuery(shippingQueryId, shippingOptions, errorMessage), stackIgnore + 1, submit)
