package io.nekohasekai.ktlib.td.utils

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.*
import io.nekohasekai.ktlib.td.extensions.fromChannel
import io.nekohasekai.ktlib.td.extensions.senderUserId
import td.TdApi.*

suspend fun TdHandler.isMyMessage(message: Message): Boolean {

    return message.senderUserId == me.id || (message.fromChannel && isChannelMessageSendByMe(message))

}

@Deprecated("Non-self channel search now blocked by telegram")
suspend fun TdHandler.getChannelMessageSender(message: Message): Int {

    getChatAdministrators(message.chatId).administrators.forEach {

        if (getUser(it.userId).type is UserTypeDeleted) return@forEach

        searchChatMessages(message.chatId, null, MessageSenderUser(it.userId), message.id, -1, 2, null, 0)
            .messages.takeIf { messages -> messages.isNotEmpty() }
            ?.forEach { msg ->
                if (msg.id == message.id) return it.userId
            }

    }

    return 0

}

suspend fun TdHandler.isChannelMessageSendByMe(message: Message): Boolean {

    searchChatMessages(message.chatId, null, MessageSenderUser(sudo.me.id), message.id, -1, 2, null, 0)
        .messages.takeIf { messages -> messages.isNotEmpty() }
        ?.forEach { msg ->
            if (msg.id == message.id) return true
        }


    return false

}