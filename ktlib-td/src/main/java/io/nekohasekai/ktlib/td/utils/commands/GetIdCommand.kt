@file:Suppress("unused")

package io.nekohasekai.ktlib.td.utils.commands

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.getMessageOrNull
import io.nekohasekai.ktlib.td.core.raw.getUserOrNull
import io.nekohasekai.ktlib.td.extensions.*
import io.nekohasekai.ktlib.td.utils.*

class GetIdCommand : TdHandler() {

    override fun onLoad() {

        initFunction("id")

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: td.TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        if (!isBot && !isMyMessage(message)) return

        suspend fun reply(id: Any) {

            if (message.canBeEdited) {

                sudo makeHtml id.toString() syncEditTo message

            } else {

                sudo makeHtml id.toString() syncReplyTo message

            }

        }

        if (message.fromChannel) {

            if (!isBot && message.replyToMessageId != 0L) {

                var msg = "${"ChatId".htmlBold}: ${chatId.htmlCode}"

                val replyToMessage = getMessageOrNull(chatId, message.replyToMessageId)

                val sender = if (replyToMessage != null) {

                    getChannelMessageSender(replyToMessage)

                } else 0

                if (sender != 0) {

                    getUserOrNull(sender)?.also {

                        msg += "\n${"SenderUser".htmlBold}: ${it.htmlInlineMention}"

                    }

                    msg += "\n${"SenderUserId".htmlBold}: ${sender.htmlCode}"

                }

                reply(msg)

            } else {

                reply(chatId)

            }

        } else if (message.fromPrivate) {

            reply(userId)

        } else {

            if (!isBot && message.replyToMessageId != 0L) {

                var msg = "${"ChatId".htmlBold}: ${chatId.htmlCode}"

                val replyToMessage = getMessageOrNull(chatId, message.replyToMessageId)

                val sender = if (replyToMessage != null) getChannelMessageSender(replyToMessage) else 0

                if (sender != 0) {

                    getUserOrNull(sender)?.also {

                        msg += "\n${"SenderUser".htmlBold}: ${it.htmlInlineMention}"

                    }

                    msg += "\n${"SenderUserId".htmlBold}: ${sender.htmlCode}"

                }

                reply(msg)

            } else {

                reply(chatId)

            }

        }

    }

}