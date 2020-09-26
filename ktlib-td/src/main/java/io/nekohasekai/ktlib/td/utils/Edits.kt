@file:Suppress("unused")

package io.nekohasekai.ktlib.td.utils

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.editMessageReplyMarkup
import io.nekohasekai.ktlib.td.core.raw.editMessageReplyMarkupWith
import kotlinx.coroutines.CoroutineScope
import td.TdApi.Message
import td.TdApi.ReplyMarkupInlineKeyboard

class EditButtonFactory(val context: TdHandler) {

    lateinit var chatId: Number
    lateinit var messageId: Number

    var replyMarkupInlineKeyboard: ReplyMarkupInlineKeyboard? = null

    infix fun to(chatId: Number): EditButtonFactory {

        this.chatId = chatId

        return this

    }

    infix fun at(messageId: Number): EditButtonFactory {

        this.messageId = messageId

        return this

    }

    suspend fun syncEditTo(chatId: Number, messageId: Long): Message = context.editMessageReplyMarkup(chatId.toLong(), messageId, replyMarkupInlineKeyboard)

    suspend infix fun syncEditAt(chatId: Number): Message = context.editMessageReplyMarkup(chatId.toLong(), messageId.toLong(), replyMarkupInlineKeyboard)

    suspend infix fun syncEditTo(messageId: Long): Message = context.editMessageReplyMarkup(chatId.toLong(), messageId, replyMarkupInlineKeyboard)

    suspend infix fun syncEditTo(message: Message): Message = context.editMessageReplyMarkup(message.chatId, message.id, replyMarkupInlineKeyboard)

    fun editTo(chatId: Number, messageId: Long) {

        context.editMessageReplyMarkupWith(chatId.toLong(), messageId, replyMarkupInlineKeyboard, 1)

    }

    infix fun editTo(chatId: Number) {

        context.editMessageReplyMarkupWith(chatId.toLong(), messageId.toLong(), replyMarkupInlineKeyboard, 1)

    }

    infix fun editAt(messageId: Long) {

        context.editMessageReplyMarkupWith(chatId.toLong(), messageId, replyMarkupInlineKeyboard, 1)

    }

    infix fun editTo(message: Message) {

        context.editMessageReplyMarkupWith(message.chatId, message.id, replyMarkupInlineKeyboard, 1)

    }

    infix fun edit(handler: (suspend CoroutineScope.(Message) -> Unit)) {

        context.editMessageReplyMarkupWith(chatId.toLong(), messageId.toLong(), replyMarkupInlineKeyboard, 1) { onSuccess(handler) }

    }

}

infix fun TdHandler.makeInlineButton(block: (InlineButtonBuilder.() -> Unit)?): EditButtonFactory {

    return EditButtonFactory(this).apply {

        val builder = InlineButtonBuilder()

        block?.invoke(builder)

        if (!builder.isEmpty()) {

            replyMarkupInlineKeyboard = builder.build()

        }

    }

}

infix fun TdHandler.makeInlineButton(buttons: ReplyMarkupInlineKeyboard): EditButtonFactory {

    return EditButtonFactory(this).apply {

        replyMarkupInlineKeyboard = buttons

    }

}