@file:Suppress("unused")

package io.nekohasekai.ktlib.td.i18n

import io.nekohasekai.ktlib.core.input
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.getMessage
import io.nekohasekai.ktlib.td.extensions.*
import io.nekohasekai.ktlib.td.i18n.store.LocaleStore
import io.nekohasekai.ktlib.td.utils.*
import kotlinx.coroutines.delay
import td.TdApi.*

class LocaleSwitcher(private val dataId: Long, private val setupWizard: (suspend (userId: Int, chatId: Long, message: Message) -> Unit)? = null) : TdHandler() {

    companion object {

        const val command = "set_lang"

    }

    fun def() = BotCommand(
            command,
            clientLocale.SELECT_LANGUAGE_DEF
    )

    override fun onLoad() {

        initFunction(command)

        initData(dataId)

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        if (!message.fromPrivate && !isChatAdmin(chatId, userId)) {

            val L = localeFor(userId)

            sudo make L.SELECT_LANGUAGE_NO_PERMISSION.input("/$command") replyTo message

            return

        }

        val L = localeFor(if (message.fromPrivate) userId else chatId)

        startSelect(L, chatId, false)

    }

    fun startSelect(L: LocaleController, chatId: Long, setup: Boolean = true) {

        sudo make L.SELECT_LANGUAGE withMarkup inlineButton {

            LocaleController.allLocales.forEach { locale ->

                if (sudo.localeList?.contains(locale.LANG) == false) return@forEach

                dataLine(locale.NAME, dataId, locale.ID.asByteArray(), setup.asByteArray())

            }

        } sendTo chatId

    }

    override suspend fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, data: Array<ByteArray>) {

        runCatching {

            val message = getMessage(chatId, messageId)

            val setup: Boolean

            val targetLocale = try {

                setup = data[1].toBoolean()

                data[0].toInt()

            } catch (e: Exception) {

                userCalled(userId, "invalid locale switch query")

                sudo makeAlert "Invalid query" cacheTime 114 answerTo queryId

                sudo delete message

                // 非法请求

                return

            }

            sudo confirmTo queryId

            if (!message.fromPrivate && !isChatAdmin(chatId, userId)) {

                sudo makeAlert localeFor(chatId, userId).SELECT_LANGUAGE_NO_PERMISSION cacheTime 114 answerTo queryId

                return

            }

            LocaleStore.localeSave(chatId, targetLocale)

            sudo makeHtml localeFor(chatId).LANGUAGE_SELECTED editTo message

            if (setup) {

                delay(1000L)

                setupWizard?.invoke(userId, chatId, message)

            }

        }

    }

}