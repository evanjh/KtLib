@file:Suppress("unused")

package io.nekohasekai.ktlib.td.i18n

import io.nekohasekai.ktlib.core.input
import io.nekohasekai.ktlib.core.receive
import io.nekohasekai.ktlib.td.core.TdClient
import td.TdApi

private typealias L = LocaleController

private val L.shared by LocaleController.receiveLocaleSet("shared")
private val string = LocaleController.receiveLocaleString { shared }

val L.NAME by string
val L.ID_STR by string

val L.ENABLE by string
val L.DISABLE by string
val L.ENABLED by string
val L.DISABLED by string

val L.DELETE by string
val L.DELETED by string

val L.SET by string
val L.EDIT by string
val L.RESET by string
val L.BACK_ARROW by string

val L.LOADING by string
val L.SETTING_SAVED by string
val L.NO_PERMISSION by string
val L.UNKNOWN_PARAMETER by string
val L.FN_GROUP_ONLY by string
val L.FN_BASIC_GROUP_ONLY by string
val L.FN_SUPER_GROUP_ONLY by string
val L.FN_CHANNEL_ONLY by string
val L.FN_PRIVATE_ONLY by string
val L.FN_PUBLIC_ONLY by string

val L.MISSING_PERMISSION by string
val L.PERMISSION_CHANGE_INFO by string
val L.PERMISSION_DELETE_MESSAGES by string
val L.PERMISSION_RESTRICT_MEMBERS by string

internal val L.FAILED by string

fun L.failed(message: L.() -> String) = FAILED.input(message())

val L.TIME_SEC by string
val L.TIME_MIN by string
val L.TIME_HOUR by string
val L.TIME_DAY by string
val L.TIME_FORMAT by string
val L.TIME_FORMAT_INVALID by string

val L.CANCEL_NOTIFICATION by string
val L.PREVIOUS_NOTIFICATION by string
val L.CANCELED by string
val L.NOTHING_TO_CANCEL by string

val L.LICENSE by string

internal val L.HELP_DEF by string
internal val L.CANCEL_DEF by string

val TdClient.HELP_COMMAND by receive<TdClient, TdApi.BotCommand> { TdApi.BotCommand("help", clientLocale.HELP_DEF) }
val TdClient.CANCEL_COMMAND by receive<TdClient, TdApi.BotCommand> { TdApi.BotCommand("cancel", clientLocale.CANCEL_DEF) }

// Switcher

internal val L.SELECT_LANGUAGE by string
internal val L.SELECT_LANGUAGE_DEF by string
internal val L.SELECT_LANGUAGE_NO_PERMISSION by string
internal val L.LANGUAGE_SELECTED by string