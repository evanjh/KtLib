@file:Suppress("MemberVisibilityCanBePrivate")

package io.nekohasekai.ktlib.td.i18n

import io.nekohasekai.ktlib.core.receive
import io.nekohasekai.ktlib.core.receiveLazy
import io.nekohasekai.ktlib.td.i18n.store.LocaleStore
import java.util.*
import kotlin.collections.HashMap

@Suppress("PropertyName", "unused")
open class LocaleController(val LANG: String) {

    private val sets = hashMapOf<String, LocaleSet>()

    val ID by lazy { ID_STR.toInt() }

    fun getLocaleSet(name: String? = null): LocaleSet {

        if (sets.containsKey(name ?: "")) return sets[name]!!

        synchronized(sets) {

            if (sets.containsKey(name ?: "")) return sets[name]!!

            return LocaleSet(this, name).also { sets[name ?: ""] = it }

        }

    }

    companion object {

        private const val supportedLangs = "en_US,zh_CN,zh_TW"

        fun receiveLocaleSet(name: String? = null) = receiveLazy<LocaleController, LocaleSet> { getLocaleSet(name) }

        fun receiveLocaleString(set: LocaleController.() -> LocaleSet) = receive<LocaleController, String> { set().getString(it.name) }
        fun receiveLocaleStringList(set: LocaleController.() -> LocaleSet) = receive<LocaleController, Map<String, String>> { set().getStringList(it.name) }

        val localesById = HashMap<Int, LocaleController>()
        val localesByKey = HashMap<String, LocaleController>()

        val allLocales = LinkedList<LocaleController>().apply {

            (supportedLangs).split(",").forEach {

                val localeInstance = LocaleController(it)

                add(localeInstance)

                localesByKey[localeInstance.LANG] = localeInstance
                localesById[localeInstance.ID] = localeInstance

            }

        }

        @Deprecated("use localeFor", replaceWith = ReplaceWith("localeFor(chatId)","io.nekohasekai.ktlib.td.i18n.localeFor"),level = DeprecationLevel.ERROR)
        fun forChat(chatId: Number): LocaleController? {

            return localeForChat(chatId)

        }

    }


}