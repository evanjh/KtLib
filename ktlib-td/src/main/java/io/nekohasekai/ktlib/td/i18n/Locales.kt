package io.nekohasekai.ktlib.td.i18n

import io.nekohasekai.ktlib.core.receiveLazy
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.getChatOrNull
import io.nekohasekai.ktlib.td.core.raw.getUser
import io.nekohasekai.ktlib.td.i18n.store.LocaleStore
import td.TdApi

val TdHandler.clientLocale by receiveLazy<TdHandler, LocaleController> {

    LocaleController.localesByKey[sudo.defaultLang ?: "en_US"]!!

}

fun localeForChat(chatId: Number): LocaleController? {

    return LocaleStore.localeRead(chatId.toLong())?.let { LocaleController.localesById[it] }

}

private suspend fun TdHandler.defaultLocaleForUser(chatId: Number): LocaleController? {
    val chat = getChatOrNull(chatId.toLong())
    var locale: LocaleController? = null
    if (chat != null && chat.type is TdApi.ChatTypePrivate) {
        val user = getUser((chat.type as TdApi.ChatTypePrivate).userId)
        if (user != null) {
            locale = if (user.languageCode == "zh-hant") {
                LocaleController.localesByKey["zh_TW"]
            } else when (user.languageCode.substringBefore("-")) {
                "zh" -> LocaleController.localesByKey["zh_CN"]
                "ru" -> LocaleController.localesByKey["ru_RU"]
                "en" -> LocaleController.localesByKey["en_US"]
                else -> locale
            }
        }
    }
    return locale
}

suspend fun TdHandler.localeFor(chatId: Number): LocaleController {

    return localeForChat(chatId) ?: defaultLocaleForUser(chatId) ?: clientLocale

}

suspend fun TdHandler.localeFor(chatId: Number, userId: Int): LocaleController {

    return localeForChat(userId) ?: defaultLocaleForUser(chatId) ?: localeForChat(chatId) ?: clientLocale

}