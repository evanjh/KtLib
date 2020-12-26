@file:Suppress("unused")

package io.nekohasekai.ktlib.td.utils

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.setChatMemberStatus
import io.nekohasekai.ktlib.td.extensions.Minutes
import io.nekohasekai.ktlib.td.extensions.asSeconds
import td.TdApi

suspend fun TdHandler.muteMember(chatId: Long, userId: Int, until: Int = 0) {
    setChatMemberStatus(
        chatId, userId, TdApi.ChatMemberStatusRestricted(
            true, until, TdApi.ChatPermissions()
        )
    )
}

suspend fun TdHandler.kickMember(chatId: Long, userId: Int, delay: Boolean = true) {
    if (delay) {
        banChatMember(chatId, userId, (System.currentTimeMillis() + 1 * Minutes).asSeconds)
    } else {
        setChatMemberStatus(
            chatId,
            userId,
            TdApi.ChatMemberStatusLeft()
        )
    }
}

suspend fun TdHandler.banChatMember(chatId: Long, userId: Int, until: Int = 0) {
    setChatMemberStatus(
        chatId,
        userId,
        TdApi.ChatMemberStatusBanned(until)
    )
}