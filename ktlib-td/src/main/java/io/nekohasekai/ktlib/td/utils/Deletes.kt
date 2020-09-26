@file:Suppress("unused")

package io.nekohasekai.ktlib.td.utils

import io.nekohasekai.ktlib.td.core.TdHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import td.TdApi.DeleteMessages
import td.TdApi.Message

infix fun TdHandler.delete(message: Message) = delete(message.chatId, message.id)

fun TdHandler.delete(chatId: Number, vararg messageIds: Long) = send<td.TdApi.Ok>(DeleteMessages(chatId.toLong(), messageIds, true)) { onFailure = null }

suspend infix fun TdHandler.syncDelete(message: Message) = syncDelete(message.chatId, message.id)

suspend fun TdHandler.syncDelete(chatId: Number, vararg messageIds: Long) = syncUnit(DeleteMessages(chatId.toLong(), messageIds, true))

infix fun TdHandler.deleteForSelf(message: Message) = deleteForSelf(message.chatId, message.id)

fun TdHandler.deleteForSelf(chatId: Number, vararg messageIds: Long) = sendRaw(DeleteMessages(chatId.toLong(), messageIds, false))

suspend infix fun TdHandler.syncDeleteForSelf(message: Message) = syncDeleteForSelf(message.chatId, message.id)

suspend fun TdHandler.syncDeleteForSelf(chatId: Number, vararg messageIds: Long) = syncUnit(DeleteMessages(chatId.toLong(), messageIds, false))

suspend fun TdHandler.delayDelete(message: Message, timeMs: Long = 3000L) = delayDelete(message.chatId, message.id, timeMs)

suspend fun TdHandler.delayDelete(chatId: Number, messageId: Long, timeMs: Long = 3000L) {

    GlobalScope.launch {

        delay(timeMs)

        delete(chatId, messageId)

    }

}

fun TdHandler.withDelay(timeMs: Long = 3000L, listener: suspend CoroutineScope.(Message) -> Unit): suspend CoroutineScope.(Message) -> Unit {

    return {

        delay(timeMs)

        listener(it)

    }

}

fun TdHandler.deleteDelay(vararg with: Message, timeMs: Long = 3000L) = withDelay(timeMs) {

    delete(it)

    with.forEach { withMsg -> delete(withMsg) }

}

fun TdHandler.deleteDelayIf(condition: Boolean, vararg with: Message, timeMs: Long = 3000L) = withDelay(timeMs) {

    if (condition) {

        delete(it)

        with.forEach { withMsg -> delete(withMsg) }

    }

}