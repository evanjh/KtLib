package io.nekohasekai.ktlib.td.cli

import io.nekohasekai.ktlib.td.core.raw.checkAuthenticationBotTokenWith
import kotlinx.coroutines.coroutineScope
import td.TdApi.*

open class TdBot(final override var botToken: String) : TdCli() {

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