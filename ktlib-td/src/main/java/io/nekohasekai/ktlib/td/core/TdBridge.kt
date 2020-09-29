package io.nekohasekai.ktlib.td.core

object TdBridge {

    private var bridge: ((clientId: String) -> TdClient?)? = null

    fun setBridge(bridge: (clientId: String) -> TdClient?) {
        this.bridge = bridge
    }

    fun getClient(clientId: String) = bridge?.invoke(clientId)

}