@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns application config, provided by the server
 * Can be called before authorization
 */
suspend fun TdHandler.getApplicationConfig() = sync<JsonValue>(GetApplicationConfig())

suspend fun TdHandler.getApplicationConfigOrNull() = syncOrNull<JsonValue>(GetApplicationConfig())

fun TdHandler.getApplicationConfigWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<JsonValue>.() -> Unit)? = null
) = send(GetApplicationConfig(), stackIgnore + 1, submit)
