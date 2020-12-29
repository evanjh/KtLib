package io.nekohasekai.ktlib.td.cli

import io.nekohasekai.ktlib.td.core.TdClient
import io.nekohasekai.ktlib.td.core.raw.checkAuthenticationBotTokenWith
import kotlinx.coroutines.coroutineScope
import td.TdApi.AuthorizationState
import td.TdApi.AuthorizationStateWaitPhoneNumber

open class TdBot(var botToken: String) : TdClient() {

    val botUserId = botToken.substringBefore(':').toInt()

    override val sudo: TdBot get() = this

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

        }

    }

}