package io.nekohasekai.ktlib.td.i18n.store

interface LocaleStore {

    fun getByChat(chatId: Long): Int?
    fun save(chatId: Long, localeId: Int)

    companion object : LocaleStore {

        private var store: LocaleStore = ImMemoryStore()

        fun setImplement(implement: LocaleStore) {

            store = implement

        }

        override fun getByChat(chatId: Long) = store.getByChat(chatId)

        override fun save(chatId: Long, localeId: Int) = store.save(chatId, localeId)
    }

}