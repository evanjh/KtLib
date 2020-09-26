package io.nekohasekai.td.proxy.connection

import td.TdApi

enum class ConnectionState {

    WaitingForNetwork, ConnectingToProxy, Connecting, Updating, Ready;

    companion object {

        fun fromState(state: TdApi.ConnectionState): ConnectionState {

            return when (state) {

                is TdApi.ConnectionStateWaitingForNetwork -> WaitingForNetwork
                is TdApi.ConnectionStateConnectingToProxy -> ConnectingToProxy
                is TdApi.ConnectionStateConnecting -> Connecting
                is TdApi.ConnectionStateUpdating -> Updating
                is TdApi.ConnectionStateReady -> Ready
                else -> error("Invalid Connection State: $state")

            }

        }

    }

}