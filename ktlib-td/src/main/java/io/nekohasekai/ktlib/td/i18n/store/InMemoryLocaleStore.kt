package io.nekohasekai.ktlib.td.i18n.store

class InMemoryLocaleStore : HashMap<Long, Int>(), LocaleStore {

    override fun localeRead(chatId: Long) = get(chatId)

    override fun localeSave(chatId: Long, localeId: Int) {

        put(chatId, localeId)

    }

    override fun localeGc() {

        clear()

    }
}