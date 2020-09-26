package io.nekohasekai.ktlib.td.utils

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.extensions.fromChannel
import io.nekohasekai.ktlib.td.core.raw.getChatAdministrators
import io.nekohasekai.ktlib.td.core.raw.getUser
import io.nekohasekai.ktlib.td.core.raw.searchChatMessages

suspend fun TdHandler.isMyMessage(message: td.TdApi.Message): Boolean {

    return message.senderUserId == me.id || (message.fromChannel && isChannelMessageSender(message, me.id))

}

suspend fun TdHandler.getChannelMessageSender(message: td.TdApi.Message): Int {

    getChatAdministrators(message.chatId).administrators.forEach {

        if (getUser(it.userId).type is td.TdApi.UserTypeDeleted) return@forEach

        searchChatMessages(message.chatId, null, it.userId, message.id, -1, 2, null)
                .messages.takeIf { messages -> messages.isNotEmpty() }
                ?.forEach { msg ->
                    if (msg.id == message.id) return it.userId
                }

    }

    return 0

}

suspend fun TdHandler.isChannelMessageSender(message: td.TdApi.Message, userId: Int): Boolean {

    searchChatMessages(message.chatId, null, userId, message.id, -1, 2, null)
            .messages.takeIf { messages -> messages.isNotEmpty() }
            ?.forEach { msg ->
                if (msg.id == message.id) return true
            }


    return false

}