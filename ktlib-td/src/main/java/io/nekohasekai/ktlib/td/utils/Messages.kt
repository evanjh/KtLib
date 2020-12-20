package io.nekohasekai.ktlib.td.utils

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.getChat
import io.nekohasekai.ktlib.td.core.raw.getUser
import io.nekohasekai.ktlib.td.extensions.*
import td.TdApi

suspend fun TdHandler.formatMessage(message: TdApi.Message, prefix: String = ""): String {

    var format =
        "[" + getChat(message.chatId).title + "] " + (prefix.takeIf { it.isNotBlank() }?.let { "[$it] " } ?: "")

    if (message.fromGroup) {

        format += if (message.fromAnonymous) {

            "AnonymousAdmin"

        } else {

            when (val sender = message.sender) {
                is TdApi.MessageSenderUser -> getUser(sender.userId).displayNameFormatted
                is TdApi.MessageSenderChat -> getChat(sender.chatId).title
                else -> TODO()
            }

        } + ": "

    }

    format += message.text ?: message.textOrCaption?.let { "<${message.content.type}>] $it" }
            ?: "<${message.content.type}>"

    return format

}