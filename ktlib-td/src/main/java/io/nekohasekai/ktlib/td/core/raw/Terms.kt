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
){
    sync(AcceptTermsOfService(termsOfServiceId))
}


suspend fun TdHandler.acceptTermsOfServiceIgnoreException(
    termsOfServiceId: String? = null
){
    syncOrNull(AcceptTermsOfService(termsOfServiceId))
}


fun TdHandler.acceptTermsOfServiceWith(
    termsOfServiceId: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AcceptTermsOfService(termsOfServiceId), stackIgnore + 1, submit)
