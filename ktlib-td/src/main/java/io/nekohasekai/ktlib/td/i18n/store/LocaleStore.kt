package io.nekohasekai.ktlib.td.i18n.store

interface LocaleStore {

    fun localeRead(chatId: Long): Int?
    fun localeSave(chatId: Long, localeId: Int)
    fun localeGc()

    companion object : LocaleStore {

        var store: LocaleStore = InMemoryLocaleStore()
            private set

        fun setImplement(implement: LocaleStore) {

            store = implement

        }

        override fun localeRead(chatId: Long) = store.localeRead(chatId)

        override fun localeSave(chatId: Long, localeId: Int) = store.localeSave(chatId, localeId)

        override fun localeGc() = store.localeGc()

    }

}