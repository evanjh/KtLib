package io.nekohasekai.ktlib.td.core

import io.nekohasekai.ktlib.td.core.raw.checkAuthenticationBotTokenWith
import kotlinx.coroutines.coroutineScope
import td.TdApi.AuthorizationState
import td.TdApi.AuthorizationStateWaitPhoneNumber

open class TdBot(val botToken: String) : TdClient() {

    val botUserId = botToken.substringBefore(':').toInt()

    override val sudo: TdBot get() = this

    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) = coroutineScope {

        super.onAuthorizationState(authorizationState)

        if (authorizationState is AuthorizationStateWaitPhoneNumber) {

            checkAuthenticationBotTokenWith(botToken) {

                onFailure {

                    authing = false

                    onAuthorizationFailed(it)

                }

            }

        }

    }

}