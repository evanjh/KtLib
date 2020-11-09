@file:Suppress("unused")

package io.nekohasekai.ktlib.td.utils

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import td.TdApi
import td.TdApi.*
import java.util.*

suspend fun TdHandler.getChats(): List<Chat> {

    return LinkedList<Chat>().apply {

        fetchChats {

            addAll(it)

            true

        }

    }

}

/**
 * 遍历聊天对话
 * @param listener 返回 false 中止遍历
 */
suspend fun TdHandler.fetchChats(startsAt: Long = 0L, listener: suspend CoroutineScope.(List<Chat>) -> Boolean) = coroutineScope {

    var chatId = startsAt

    var chatIds: LongArray
    var nextChats = longArrayOf()

    while (true) {

        chatIds = if (nextChats.isNotEmpty()) nextChats else getChats(ChatListMain(), Long.MAX_VALUE, chatId, 114514).chatIds

        chatId = chatIds[chatIds.size - 1]

        nextChats = getChats(ChatListMain(), getChat(chatId).positions.filter { it.list is ChatListMain }[0].order, chatId, 114514).chatIds

        if (!listener(chatIds.map { getChat(it) })) break

        if (nextChats.isEmpty()) break

    }

    println(">> archive")

    nextChats = longArrayOf()

    while (true) {

        chatIds = if (nextChats.isNotEmpty()) nextChats else getChats(ChatListArchive(), Long.MAX_VALUE, chatId, 114514).chatIds

        chatId = chatIds[chatIds.size - 1]

        nextChats = getChats(ChatListArchive(), getChat(chatId).positions.filter { it.list is ChatListArchive }[0].order, chatId, 114514).chatIds

        if (!listener(chatIds.map { getChat(it) })) break

        if (nextChats.isEmpty()) break

    }

}

suspend fun TdHandler.getGroupsInCommon(userId: Int): List<Chat> {

    return LinkedList<Chat>().apply {

        fetchGroupsInCommon(userId) {

            addAll(it)

            true

        }

    }

}

/**
 * 遍历聊天对话
 * @param listener 返回 false 中止遍历
 */
suspend fun TdHandler.fetchGroupsInCommon(userId: Int, startsAt: Long = 0L, listener: suspend CoroutineScope.(List<Chat>) -> Boolean) = coroutineScope {

    var chatId = startsAt

    var chatIds: LongArray
    var nextChats = longArrayOf()

    while (true) {

        chatIds = if (nextChats.isNotEmpty()) nextChats else getGroupsInCommon(userId, chatId, 114514).chatIds

        chatId = chatIds[chatIds.size - 1]

        nextChats = getGroupsInCommon(userId, chatId, 114514).chatIds

        if (!listener(chatIds.map { getChat(it) })) break

        if (nextChats.isEmpty()) break

    }

}

/**
 * 遍历聊天所有消息
 * @param listener 返回 false 中止遍历
 */
suspend fun TdHandler.fetchMessages(chatId: Long, startsAt: Long = 0L, listener: suspend CoroutineScope.(Array<Message>) -> Boolean) = coroutineScope {

    var messageId = startsAt

    var messages: Array<Message>
    var nextMessages: Array<Message> = arrayOf()

    while (true) {

        messages = if (nextMessages.isNotEmpty()) nextMessages else getChatHistory(chatId, messageId, 0, 100, false).messages

        messageId = messages[messages.size - 1].id

        nextMessages = getChatHistory(chatId, messageId, 0, 100, false).messages

        if (!listener(messages)) break

        if (nextMessages.isEmpty()) break

    }

}

/**
 * 遍历聊天用户消息
 * @param listener 返回 false 中止遍历
 */
suspend fun TdHandler.fetchUserMessages(chatId: Long, userId: Int, startsAt: Long = 0L, listener: suspend CoroutineScope.(Array<Message>) -> Boolean) = coroutineScope {

    var messageId = startsAt

    var messages: Array<Message>
    var nextMessages: Array<Message> = arrayOf()

    while (true) {

        messages = if (nextMessages.isNotEmpty()) nextMessages else searchChatMessages(chatId, "", MessageSenderUser(userId), messageId, 0, 100, null, 0).messages

        messageId = messages[messages.size - 1].id

        nextMessages = searchChatMessages(chatId, "", MessageSenderUser(userId), messageId, 0, 100, null, 0).messages

        if (!listener(messages)) break

        if (nextMessages.isEmpty()) break

    }

}

suspend fun TdHandler.fetchSupergroupUsers(chatId: Long, listener: suspend CoroutineScope.(Array<ChatMember>) -> Boolean) = coroutineScope {

    val supergroupId = (getChat(chatId).type as ChatTypeSupergroup).supergroupId

    var offset = 0
    var members: Array<ChatMember>

    while (true) {

        members = getSupergroupMembers(supergroupId, offset = offset, limit = 200).members

        if (members.isEmpty() || !listener(members) || members.size < 200) break

        offset += 200

    }

}