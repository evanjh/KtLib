package io.nekohasekai.ktlib.td.i18n

import io.nekohasekai.ktlib.core.receive
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.i18n.store.LocaleStore

val TdHandler.clientLocale by receive<TdHandler, LocaleController> {

    LocaleController.localesByKey[sudo.defaultLang ?: "en_US"]!!

}

fun localeForChat(chatId: Number): LocaleController? {

    return LocaleStore.getByChat(chatId.toLong())?.let { LocaleController.localesById[it] }

}

fun TdHandler.localeFor(chatId: Number): LocaleController {

    return localeForChat(chatId) ?: clientLocale

}

fun TdHandler.localeFor(chatId: Number, userId: Int): LocaleController {

    return localeForChat(userId) ?: localeForChat(chatId) ?: clientLocale

}