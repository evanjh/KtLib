@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns information about a tg:// deep link
 * Use "tg://need_update_for_some_feature" or "tg:some_unsupported_feature" for testing
 * Returns a 404 error for unknown links
 * Can be called before authorization
 *
 * @link - The link
 */
suspend fun TdHandler.getDeepLinkInfo(
    link: String? = null
) = sync(GetDeepLinkInfo(link))

suspend fun TdHandler.getDeepLinkInfoOrNull(
    link: String? = null
) = syncOrNull(GetDeepLinkInfo(link))

fun TdHandler.getDeepLinkInfoWith(
    link: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<DeepLinkInfo>.() -> Unit)? = null
) = send(GetDeepLinkInfo(link), stackIgnore + 1, submit)
