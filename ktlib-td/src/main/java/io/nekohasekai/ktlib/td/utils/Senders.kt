package io.nekohasekai.ktlib.td.utils

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.*
import io.nekohasekai.ktlib.td.extensions.fromChannel

suspend fun TdHandler.isMyMessage(message: td.TdApi.Message): Boolean {

    return message.senderUserId == me.id || (message.fromChannel && isChannelMessageSendByMe(message))

}

@Deprecated("Non-self channel search now blocked by telegram")
suspend fun TdHandler.getChannelMessageSender(message: td.TdApi.Message): Int {

    getChatAdministrators(message.chatId).administrators.forEach {

        if (getUser(it.userId).type is td.TdApi.UserTypeDeleted) return@forEach

        searchChatMessages(message.chatId, null, it.userId, message.id, -1, 2, null, 0)
                .messages.takeIf { messages -> messages.isNotEmpty() }
                ?.forEach { msg ->
                    if (msg.id == message.id) return it.userId
                }

    }

    return 0

}

suspend fun TdHandler.isChannelMessageSendByMe(message: td.TdApi.Message): Boolean {

    searchChatMessages(message.chatId, null, sudo.me.id, message.id, -1, 2, null, 0)
            .messages.takeIf { messages -> messages.isNotEmpty() }
            ?.forEach { msg ->
                if (msg.id == message.id) return true
            }


    return false

}