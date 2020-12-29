package io.nekohasekai.ktlib.td.cli

import io.nekohasekai.ktlib.core.mkLog
import io.nekohasekai.ktlib.td.core.TdClient
import io.nekohasekai.ktlib.td.core.raw.checkAuthenticationBotTokenWith
import io.nekohasekai.ktlib.td.extensions.displayNameFormatted
import kotlinx.coroutines.coroutineScope
import td.TdApi
import td.TdApi.AuthorizationState
import td.TdApi.AuthorizationStateWaitPhoneNumber

open class TdBot(var botToken: String) : TdClient() {

    val botUserId = botToken.substringBefore(':').toInt()

    override val sudo: TdBot get() = this

    private var _clientLog = mkLog(botToken.substringBefore(":"))
    override val clientLog by ::_clientLog

    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) = coroutineScope {

        if (authorizationState is AuthorizationStateWaitPhoneNumber) {
            checkAuthenticationBotTokenWith(botToken) {
                onFailure {
                    authing = false
                    onAuthorizationFailed(it)
                }
            }
        } else {
            super.onAuthorizationState(authorizationState)
            if (authorizationState is TdApi.AuthorizationStateReady) {
                _clientLog = mkLog(me.displayNameFormatted)
            }
        }

    }

}