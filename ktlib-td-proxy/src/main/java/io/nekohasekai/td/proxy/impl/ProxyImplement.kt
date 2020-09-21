package io.nekohasekai.td.proxy.impl

import java.io.File

abstract class ProxyImplement {

    private var inited = false

    fun postInit(cacheDir: File = File("libs")) {

        if (inited) return

        synchronized(this) {

            if (inited) return

            init(cacheDir)

            inited = true

        }

    }

    abstract fun init(cacheDir: File = File("libs"))

}