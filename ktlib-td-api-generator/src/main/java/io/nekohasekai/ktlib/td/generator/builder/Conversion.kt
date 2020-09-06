package io.nekohasekai.ktlib.td.generator.builder

fun String.snakeToCamel(): String = split("_").joinToString("") { it.capitalize() }.decapitalize()
