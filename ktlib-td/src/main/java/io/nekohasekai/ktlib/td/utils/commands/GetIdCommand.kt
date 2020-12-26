@file:Suppress("unused")

package io.nekohasekai.ktlib.td.utils.commands

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.getChat
import io.nekohasekai.ktlib.td.core.raw.getMessageOrNull
import io.nekohasekai.ktlib.td.core.raw.getUser
import io.nekohasekai.ktlib.td.extensions.*
import io.nekohasekai.ktlib.td.utils.isMyMessage
import io.nekohasekai.ktlib.td.utils.make

class GetIdCommand : TdHandler() {

    override fun onLoad() {
        initFunction("id")
    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: td.TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        if (!isBot && !isMyMessage(message)) return

        if (message.fromChannel || message.fromPrivate) {

            sudo make "$chatId" editOrSendToChat message

        } else {

            var text = ""

            fun append(name: String, content: Any, newLine: Boolean = false) {

                text += name.htmlBold + ": $content\n"

                if (newLine) text += "\n"

            }

            append("Chat", getChat(chatId).title.htmlCode)
            append("Id", chatId.htmlCode, true)

            append("Sender", getUser(userId).htmlInlineMention)
            append("Id", userId.htmlCode)

            if (message.replyToMessageId != 0L) {

                val replyTo = getMessageOrNull(chatId, message.replyToMessageId)

                if (replyTo != null) {

                    when (replyTo.senderChatId) {

                        0L -> {

                            append("ReplyTo", getUser(replyTo.senderUserId).htmlInlineMention)
                            append("Id", replyTo.senderUserId.htmlCode)

                        }

                        message.chatId -> append("ReplyTo", "AnonymousAdmin")
                        else -> append("ReplyTo", "Channel")
                    }

                }

            }

            sudo make text replyTo message

        }

    }

}