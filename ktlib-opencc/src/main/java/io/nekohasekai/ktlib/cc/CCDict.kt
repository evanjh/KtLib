package io.nekohasekai.ktlib.cc

import cn.hutool.core.io.resource.ResourceUtil
import java.util.*
import kotlin.collections.HashMap

internal enum class CCDict {
    HKVariantsRevPhrases,
    HKVariantsRev,
    HKVariants,
    JPShinjitaiCharacters,
    JPShinjitaiPhrases,
    JPVariantsRev,
    JPVariants,
    STCharacters,
    STPhrases,
    TSCharacters,
    TSPhrases,
    TWPhrasesIT,
    TWPhrasesName,
    TWPhrasesOther,
    TWPhrasesRev,
    TWVariantsRevPhrases,
    TWVariantsRev,
    TWVariants;

    lateinit var storage: HashMap<String, LinkedList<String>>
    fun postInit() {
        if (::storage.isInitialized) return
        storage = HashMap()

        ResourceUtil.getUtf8Reader("opencc-dictionary/$name.txt").forEachLine {
            storage[it.substringBefore("\t")] =
                    LinkedList(it.substringAfter("\t").split(" "))
        }
    }
}