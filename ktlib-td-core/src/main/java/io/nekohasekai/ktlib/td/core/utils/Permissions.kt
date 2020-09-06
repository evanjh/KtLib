@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.utils

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.getChatMember
import td.TdApi

suspend fun TdHandler.isChatAdmin(chatId: Long, userId: Int): Boolean {

    val status = getChatMember(chatId, userId).status

    return status is TdApi.ChatMemberStatusAdministrator || status is TdApi.ChatMemberStatusCreator

}

suspend fun TdHandler.isChatFullAdmin(chatId: Long, userId: Int): Boolean {

    val status = getChatMember(chatId, userId).status

    return (status is TdApi.ChatMemberStatusAdministrator &&
            status.canChangeInfo &&
            status.canDeleteMessages &&
            status.canRestrictMembers &&
            status.canPromoteMembers) || status is TdApi.ChatMemberStatusCreator

}