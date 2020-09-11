@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.GetLocalizationTargetInfo
import td.TdApi.LocalizationTargetInfo

/**
 * Returns information about the current localization target
 * This is an offline request if only_local is true
 * Can be called before authorization
 *
 * @onlyLocal - If true, returns only locally available information without sending network requests
 */
suspend fun TdHandler.getLocalizationTargetInfo(
        onlyLocal: Boolean
) = sync<LocalizationTargetInfo>(GetLocalizationTargetInfo(onlyLocal))

suspend fun TdHandler.getLocalizationTargetInfoOrNull(
        onlyLocal: Boolean
) = syncOrNull<LocalizationTargetInfo>(GetLocalizationTargetInfo(onlyLocal))

fun TdHandler.getLocalizationTargetInfoWith(
        onlyLocal: Boolean,
        stackIgnore: Int = 0,
        submit: (TdCallback<LocalizationTargetInfo>.() -> Unit)? = null
) = send(GetLocalizationTargetInfo(onlyLocal), stackIgnore + 1, submit)
