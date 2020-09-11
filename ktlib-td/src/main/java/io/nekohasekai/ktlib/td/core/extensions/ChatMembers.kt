@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.extensions

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.getChat
import td.TdApi.*

val ChatMember.isMember get() = status.isMember
val ChatMember.isAdmin get() = status.isAdmin

val ChatMemberStatus.isMember
    get() = when (this) {
        is ChatMemberStatusLeft,
        is ChatMemberStatusBanned -> false
        else -> true
    }

val ChatMemberStatus.isAdmin
    get() = when (this) {
        is ChatMemberStatusCreator,
        is ChatMemberStatusAdministrator -> true
        else -> false
    }

suspend fun TdHandler.canSendMessages(chatId: Long, status: ChatMemberStatus): Boolean {
    return when (status) {
        is ChatMemberStatusLeft,
        is ChatMemberStatusBanned -> false
        is ChatMemberStatusRestricted -> status.permissions.canSendMessages
        is ChatMemberStatusMember -> getChat(chatId).permissions.canSendMessages
        else -> true
    }
}

suspend fun TdHandler.canSendMediaMessages(chatId: Long, status: ChatMemberStatus): Boolean {
    return when (status) {
        is ChatMemberStatusLeft,
        is ChatMemberStatusBanned -> false
        is ChatMemberStatusRestricted -> status.permissions.canSendMediaMessages
        is ChatMemberStatusMember -> getChat(chatId).permissions.canSendMediaMessages
        else -> true
    }
}

suspend fun TdHandler.canSendPolls(chatId: Long, status: ChatMemberStatus): Boolean {
    return when (status) {
        is ChatMemberStatusLeft,
        is ChatMemberStatusBanned -> false
        is ChatMemberStatusRestricted -> status.permissions.canSendPolls
        is ChatMemberStatusMember -> getChat(chatId).permissions.canSendPolls
        else -> true
    }
}

suspend fun TdHandler.canSendOtherMessages(chatId: Long, status: ChatMemberStatus): Boolean {
    return when (status) {
        is ChatMemberStatusLeft,
        is ChatMemberStatusBanned -> false
        is ChatMemberStatusRestricted -> status.permissions.canSendOtherMessages
        is ChatMemberStatusMember -> getChat(chatId).permissions.canSendOtherMessages
        else -> true
    }
}

suspend fun TdHandler.canAddWebPagePreviews(chatId: Long, status: ChatMemberStatus): Boolean {
    return when (status) {
        is ChatMemberStatusLeft,
        is ChatMemberStatusBanned -> false
        is ChatMemberStatusRestricted -> status.permissions.canAddWebPagePreviews
        is ChatMemberStatusMember -> getChat(chatId).permissions.canAddWebPagePreviews
        else -> true
    }
}

suspend fun TdHandler.canChangeInfo(chatId: Long, status: ChatMemberStatus): Boolean {
    return when (status) {
        is ChatMemberStatusLeft,
        is ChatMemberStatusBanned -> false
        is ChatMemberStatusRestricted -> status.permissions.canChangeInfo
        is ChatMemberStatusMember -> getChat(chatId).permissions.canChangeInfo
        else -> true
    }
}

suspend fun TdHandler.canInviteUsers(chatId: Long, status: ChatMemberStatus): Boolean {
    return when (status) {
        is ChatMemberStatusLeft,
        is ChatMemberStatusBanned -> false
        is ChatMemberStatusRestricted -> status.permissions.canInviteUsers
        is ChatMemberStatusMember -> getChat(chatId).permissions.canInviteUsers
        else -> true
    }
}

suspend fun TdHandler.canPinMessages(chatId: Long, status: ChatMemberStatus): Boolean {
    return when (status) {
        is ChatMemberStatusLeft,
        is ChatMemberStatusBanned -> false
        is ChatMemberStatusRestricted -> status.permissions.canPinMessages
        is ChatMemberStatusMember -> getChat(chatId).permissions.canPinMessages
        else -> true
    }
}