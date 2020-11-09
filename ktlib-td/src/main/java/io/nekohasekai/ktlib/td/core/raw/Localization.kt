@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns information about the current localization target
 * This is an offline request if only_local is true
 * Can be called before authorization
 *
 * @onlyLocal - If true, returns only locally available information without sending network requests
 */
suspend fun TdHandler.getLocalizationTargetInfo(
    onlyLocal: Boolean
) = sync(GetLocalizationTargetInfo(onlyLocal))

suspend fun TdHandler.getLocalizationTargetInfoOrNull(
    onlyLocal: Boolean
) = syncOrNull(GetLocalizationTargetInfo(onlyLocal))

fun TdHandler.getLocalizationTargetInfoWith(
    onlyLocal: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<LocalizationTargetInfo>.() -> Unit)? = null
) = send(GetLocalizationTargetInfo(onlyLocal), stackIgnore + 1, submit)
