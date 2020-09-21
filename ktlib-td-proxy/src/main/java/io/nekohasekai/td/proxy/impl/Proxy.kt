package io.nekohasekai.td.proxy.impl

interface Proxy : Comparable<Proxy> {

    fun strictKey(): String

}