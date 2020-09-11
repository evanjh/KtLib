@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.ConnectedWebsites
import td.TdApi.GetConnectedWebsites

/**
 * Returns all website where the current user used Telegram to log in
 */
suspend fun TdHandler.getConnectedWebsites() = sync<ConnectedWebsites>(GetConnectedWebsites())

suspend fun TdHandler.getConnectedWebsitesOrNull() = syncOrNull<ConnectedWebsites>(GetConnectedWebsites())

fun TdHandler.getConnectedWebsitesWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<ConnectedWebsites>.() -> Unit)? = null
) = send(GetConnectedWebsites(), stackIgnore + 1, submit)
