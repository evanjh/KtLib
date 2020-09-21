package io.nekohasekai.td.proxy.impl.mtproto

import io.nekohasekai.td.proxy.impl.Proxy

class MTProtoProxy : Proxy {

    lateinit var server: String
    var port: Int = 0
    lateinit var secret: String

    override fun toString() = "$server:$port"

    override fun strictKey(): String {

        var s = server

        while (s.count { it == '.' } > 2) {

            s = s.substringAfter('.')

        }

        return s

    }

    override fun compareTo(other: Proxy): Int {

        if (this == other) return 0

        return -1

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MTProtoProxy

        if (server != other.server) return false
        if (port != other.port) return false
        if (secret != other.secret) return false

        return true
    }

    override fun hashCode(): Int {
        var result = server.hashCode()
        result = 31 * result + secret.hashCode()
        result = 31 * result + "$port".hashCode()
        return result
    }

}