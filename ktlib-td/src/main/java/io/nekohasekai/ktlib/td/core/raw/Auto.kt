@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns auto-download settings presets for the current user
 */
suspend fun TdHandler.getAutoDownloadSettingsPresets() = sync<AutoDownloadSettingsPresets>(GetAutoDownloadSettingsPresets())

suspend fun TdHandler.getAutoDownloadSettingsPresetsOrNull() = syncOrNull<AutoDownloadSettingsPresets>(GetAutoDownloadSettingsPresets())

fun TdHandler.getAutoDownloadSettingsPresetsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<AutoDownloadSettingsPresets>.() -> Unit)? = null
) = send(GetAutoDownloadSettingsPresets(), stackIgnore + 1, submit)

/**
 * Sets auto-download settings
 *
 * @settings - New user auto-download settings
 * @type - Type of the network for which the new settings are applied
 */
suspend fun TdHandler.setAutoDownloadSettings(
    settings: AutoDownloadSettings? = null,
    type: NetworkType? = null
) = sync<Ok>(SetAutoDownloadSettings(settings, type))

suspend fun TdHandler.setAutoDownloadSettingsOrNull(
    settings: AutoDownloadSettings? = null,
    type: NetworkType? = null
) = syncOrNull<Ok>(SetAutoDownloadSettings(settings, type))

fun TdHandler.setAutoDownloadSettingsWith(
    settings: AutoDownloadSettings? = null,
    type: NetworkType? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetAutoDownloadSettings(settings, type), stackIgnore + 1, submit)
