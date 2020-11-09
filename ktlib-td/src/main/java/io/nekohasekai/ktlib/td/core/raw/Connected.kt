@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns all website where the current user used Telegram to log in
 */
suspend fun TdHandler.getConnectedWebsites() = sync(GetConnectedWebsites())

suspend fun TdHandler.getConnectedWebsitesOrNull() = syncOrNull(GetConnectedWebsites())

fun TdHandler.getConnectedWebsitesWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<ConnectedWebsites>.() -> Unit)? = null
) = send(GetConnectedWebsites(), stackIgnore + 1, submit)
