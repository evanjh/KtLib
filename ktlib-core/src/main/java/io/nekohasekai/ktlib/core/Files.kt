package io.nekohasekai.ktlib.core

import java.io.File

fun File.find(iterator: (File) -> Boolean): File? {

    if (iterator(this)) return this

    listFiles()?.forEach {

        val result = it.find(iterator)

        if (result != null) return result

    }

    return null

}

fun File.readPackageName(): String {

    var packageName = ""

    var parent = parentFile

    while (parent != null) {

        if (parent.name in arrayOf("java", "kotlin", "src")) break

        packageName = if (packageName.isBlank()) parent.name else parent.name + "." + packageName

        parent = parent.parentFile

    }

    return packageName

}