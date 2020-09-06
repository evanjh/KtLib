package io.nekohasekai.ktlib.td.i18n

import io.nekohasekai.ktlib.core.receive
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.i18n.store.LocaleStore

val TdHandler.clientLocale by receive<TdHandler, LocaleController> {

    LocaleController.localesByKey[sudo.defaultLang ?: "en_US"]!!

}

fun localeForChat(chatId: Number): LocaleController? {

    val chatLocale = LocaleStore.getByChat(chatId.toLong())

    if (chatLocale != null) LocaleController.localesById[chatLocale]

    return null

}

fun TdHandler.localeFor(chatId: Number): LocaleController {

    return localeForChat(chatId) ?: clientLocale

}

fun TdHandler.localeFor(chatId: Number, userId: Int): LocaleController {

    return localeForChat(userId) ?: localeForChat(chatId) ?: clientLocale

}