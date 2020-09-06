package io.nekohasekai.ktlib.td.generator.tl

data class TlProperty(
        val name: String,
        val type: TlType,
        val descriptions: List<String>,
        val additions: List<TlAddition>
)

