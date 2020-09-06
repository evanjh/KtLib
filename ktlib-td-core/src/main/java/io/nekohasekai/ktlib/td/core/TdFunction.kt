package io.nekohasekai.ktlib.td.core

data class TdFunction(
        val function: String,
        val param: String,
        val params: List<String>,
        val originParams: List<String>
)