@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Accepts Telegram terms of services
 *
 * @termsOfServiceId - Terms of service identifier
 */
suspend fun TdHandler.acceptTermsOfService(
    termsOfServiceId: String? = null
) = sync<Ok>(AcceptTermsOfService(termsOfServiceId))

suspend fun TdHandler.acceptTermsOfServiceOrNull(
    termsOfServiceId: String? = null
) = syncOrNull<Ok>(AcceptTermsOfService(termsOfServiceId))

fun TdHandler.acceptTermsOfServiceWith(
    termsOfServiceId: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AcceptTermsOfService(termsOfServiceId), stackIgnore + 1, submit)
