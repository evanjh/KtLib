package io.nekohasekai.ktlib.td.i18n.store

import java.util.concurrent.ConcurrentHashMap

class ImMemoryStore : ConcurrentHashMap<Long, Int>(), LocaleStore {

    override fun getByChat(chatId: Long) = get(chatId)

    override fun save(chatId: Long, localeId: Int) {

        put(chatId, localeId)

    }

}