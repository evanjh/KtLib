package io.nekohasekai.ktlib.td.i18n

import io.nekohasekai.ktlib.core.defaultLog
import org.yaml.snakeyaml.Yaml

class LocaleSet(private val locale: LocaleController, private val dirName: String? = null) {

    @Suppress("UNCHECKED_CAST")
    val strings: HashMap<String, Any> = hashMapOf()

    fun getString(key: String): String {

        val string = strings[key]?.toString()

        if (string != null) return string

        defaultLog.warn("[$dirName] Missing locale string $key")

        return if (locale.ID != 0) {

            LocaleController.localesById[0]!!.getLocaleSet(dirName).getString(key)

        } else {

            "Error: $key"

        }

    }

    fun getStringList(key: String): Map<String, String> {

        @Suppress("UNCHECKED_CAST")
        return strings[key] as Map<String, String>? ?: mapOf()

    }

    init {

        javaClass.classLoader.getResourceAsStream("${if (dirName != null) "i18n-$dirName" else "i18n"}/${locale.LANG}.yml")?.use {

            @Suppress("UNCHECKED_CAST")
            (Yaml().loadAs(it, HashMap::class.java) as HashMap<String, *>).forEach { (key, value) ->

                strings[key] = (strings[key] ?: value)

            }

        }

    }

}