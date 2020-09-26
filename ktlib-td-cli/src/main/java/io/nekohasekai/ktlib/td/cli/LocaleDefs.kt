package io.nekohasekai.ktlib.td.cli

import io.nekohasekai.ktlib.td.i18n.LocaleController

private typealias L = LocaleController

private val L.cli by L.receiveLocaleSet("cli")
val string = L.receiveLocaleString { cli }

internal val L.INVALID_ENV_VARIABLE_BOT_TOKEN by string
internal val L.INPUT_PHONE_NUMBER_OR_BOT_TOKEN by string
internal val L.INPUT_PHONE_NUMBER by string
internal val L.INPUT_BOT_TOKEN by string

internal val L.INVALID_BOT_TOKEN by string
internal val L.INVALID_PHONE_NUMBER by string
internal val L.INPUT_AUTH_CODE by string
internal val L.AUTH_CODE_RESENT by string
internal val L.RESEND_FAILED by string