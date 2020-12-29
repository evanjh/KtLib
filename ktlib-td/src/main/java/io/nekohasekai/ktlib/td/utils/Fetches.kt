@file:Suppress("unused")

package io.nekohasekai.ktlib.td.utils

import io.nekohasekai.ktlib.core.defaultLog
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.getChat
import io.nekohasekai.ktlib.td.core.raw.getChats
import io.nekohasekai.ktlib.td.core.raw.getGroupsInCommon
import io.nekohasekai.ktlib.td.core.raw.getSupergroupMembers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import td.TdApi.*
import java.util.*

private suspend inline fun <reified T : Object> fetchAll(crossinline method: suspend (listener: suspend CoroutineScope.(Array<T>) -> Boolean) -> Unit): Array<T> {
    val result = LinkedList<T>()
    method { result.addAll(it);true }
    return result.toTypedArray()
}

suspend fun TdHandler.getChats() = fetchAll<Chat> { fetchChats(listener = it) }
suspend fun TdHandler.getChatPinnedMessages(chatId: Long) = fetchAll<Message> {
    fetchMessages(SearchChatMessages(chatId, "", null, 0, 0, 100, SearchMessagesFilterPinned(), 0), it)
}

/**
 * 遍历聊天对话
 * @param listener 返回 false 中止遍历
 */
suspend fun TdHandler.fetchChats(startsAt: Long = 0L, listener: suspend CoroutineScope.(Array<Chat>) -> Boolean) =
    coroutineScope {

        var chatId = startsAt

        var chatIds: LongArray
        var nextChats = longArrayOf()

        while (true) {

            chatIds =
                if (nextChats.isNotEmpty()) nextChats else getChats(
                    ChatListMain(),
                    Long.MAX_VALUE,
                    chatId,
                    114514
                ).chatIds

            if (chatIds.isNotEmpty()) {

                chatId = chatIds[chatIds.size - 1]

                nextChats = getChats(
                    ChatListMain(),
                    getChat(chatId).positions.filter { it.list is ChatListMain }[0].order,
                    chatId,
                    114514
                ).chatIds

            }

            if (!listener(chatIds.map { getChat(it) }.toTypedArray())) break

            if (nextChats.isEmpty()) break

        }

        println(">> archive")

        nextChats = longArrayOf()

        while (true) {

            chatIds = if (nextChats.isNotEmpty()) nextChats else getChats(
                ChatListArchive(),
                Long.MAX_VALUE,
                chatId,
                114514
            ).chatIds

            if (chatIds.isNotEmpty()) {

                chatId = chatIds[chatIds.size - 1]

                nextChats = getChats(
                    ChatListArchive(),
                    getChat(chatId).positions.filter { it.list is ChatListArchive }[0].order,
                    chatId,
                    114514
                ).chatIds

            }

            if (!listener(chatIds.map { getChat(it) }.toTypedArray())) break

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
suspend fun TdHandler.fetchGroupsInCommon(
    userId: Int,
    startsAt: Long = 0L,
    listener: suspend CoroutineScope.(List<Chat>) -> Boolean
) = coroutineScope {

    var chatId = startsAt

    var chatIds: LongArray
    var nextChats = longArrayOf()

    while (true) {

        chatIds = if (nextChats.isNotEmpty()) nextChats else getGroupsInCommon(userId, chatId, 114514).chatIds

        if (chatIds.isNotEmpty()) {

            chatId = chatIds[chatIds.size - 1]

            nextChats = getGroupsInCommon(userId, chatId, 114514).chatIds

        }

        if (!listener(chatIds.map { getChat(it) })) break

        if (nextChats.isEmpty()) break

    }

}

suspend fun TdHandler.fetchMessages(
    chatId: Long,
    startsAt: Long = 0L,
    listener: suspend CoroutineScope.(Array<Message>) -> Boolean
) {
    fetchMessages(GetChatHistory(chatId, startsAt, 0, 100, false), listener)
}

suspend fun TdHandler.fetchMessages(
    query: GetChatHistory,
    listener: suspend CoroutineScope.(Array<Message>) -> Boolean
) = coroutineScope {

    var messages: Array<Message>
    var nextMessages: Array<Message> = arrayOf()

    while (true) {

        messages = if (nextMessages.isNotEmpty()) nextMessages else sync(query).messages

        if (messages.isNotEmpty()) {

            query.fromMessageId = messages[messages.size - 1].id

            nextMessages = sync(query).messages

        }

        if (!listener(messages)) break

        if (nextMessages.isEmpty()) break

    }

}

suspend fun TdHandler.fetchMessages(
    query: SearchMessages,
    listener: suspend CoroutineScope.(Array<Message>) -> Boolean
) = coroutineScope {

    var messages: Array<Message>
    var nextMessages: Array<Message> = arrayOf()

    while (true) {

        messages = if (nextMessages.isNotEmpty()) nextMessages else sync(query).messages

        if (messages.isNotEmpty()) {

            query.offsetMessageId = messages[messages.size - 1].id

            nextMessages = sync(query).messages

        }

        if (!listener(messages)) break

        if (nextMessages.isEmpty()) break

    }

}

suspend fun TdHandler.fetchMessages(
    query: SearchChatMessages,
    listener: suspend CoroutineScope.(Array<Message>) -> Boolean
) = coroutineScope {

    var messages: Array<Message>
    var nextMessages: Array<Message> = arrayOf()

    while (true) {

        defaultLog.trace("start search")

        messages = if (nextMessages.isNotEmpty()) nextMessages else sync(query).messages

        if (messages.isNotEmpty()) {
            defaultLog.trace("next search")
            query.fromMessageId = messages[messages.size - 1].id
            nextMessages = sync(query).messages
        }

        if (!listener(messages)) {
            defaultLog.trace("accepted")
            return@coroutineScope
        }

        if (nextMessages.isEmpty()) {
            defaultLog.trace("no more message")
            return@coroutineScope
        }

    }

}

suspend fun TdHandler.fetchUserMessages(
    chatId: Long,
    userId: Int,
    startsAt: Long = 0L,
    listener: suspend CoroutineScope.(Array<Message>) -> Boolean
) = coroutineScope {

    fetchMessages(SearchChatMessages(chatId, "", MessageSenderUser(userId), startsAt, 0, 100, null, 0), listener)

}

suspend fun TdHandler.fetchSupergroupUsers(
    chatId: Long,
    listener: suspend CoroutineScope.(Array<ChatMember>) -> Boolean
) = coroutineScope {

    val supergroupId = (getChat(chatId).type as ChatTypeSupergroup).supergroupId

    var offset = 0
    var members: Array<ChatMember>

    while (true) {

        members = getSupergroupMembers(supergroupId, offset = offset, limit = 200).members

        if (members.isEmpty() || !listener(members) || members.size < 200) break

        offset += 200

    }

}