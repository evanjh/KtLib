@file:Suppress("unused", "UNUSED_PARAMETER")

// TODO: Finish this

package io.nekohasekai.ktlib.td.utils

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.getChatMember
import io.nekohasekai.ktlib.td.extensions.fromAnonymous
import io.nekohasekai.ktlib.td.extensions.senderUserId
import io.nekohasekai.ktlib.td.i18n.NO_PERMISSION
import io.nekohasekai.ktlib.td.i18n.PERMISSION_DELETE_MESSAGES
import io.nekohasekai.ktlib.td.i18n.localeFor
import td.TdApi
import java.util.*

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


suspend fun TdHandler.checkChatAdmin(message: TdApi.Message): Boolean {

    return message.fromAnonymous || checkChatAdmin(message.chatId, message.senderUserId, message)

}


suspend fun TdHandler.checkChatAdmin(chatId: Long, userId: Int, message: TdApi.Message? = null): Boolean {

    if (message != null && message.chatId == chatId && message.fromAnonymous) return false

    if (isChatAdmin(chatId, userId)) return false

    val text = localeFor(userId).NO_PERMISSION

    sudo make {

        inputMarkdown = text

        message?.id?.also { replyToMessageId = it }

    } onSuccess withDelay {

        if (hasRequiredPermission(chatId, me.id, canDeleteMessages = true)) {

            delete(it);

            message?.run { delete(this) }

        }

    } sendTo (message?.chatId ?: chatId)

    return true

}

suspend fun TdHandler.checkRequiredPermission(
    chatId: Long, userId: Int, message: TdApi.Message? = null,
    canChangeInfo: Boolean = false,
    canPostMessages: Boolean = false,
    canEditMessages: Boolean = false,
    canDeleteMessages: Boolean = false,
    canInviteUsers: Boolean = false,
    canRestrictMembers: Boolean = false,
    canPinMessages: Boolean = false,
    canPromoteMembers: Boolean = false
): Boolean {

    if (message != null && message.chatId == chatId && message.fromAnonymous) return false

    if (hasRequiredPermission(
            chatId,
            userId,
            canChangeInfo,
            canPostMessages,
            canEditMessages,
            canDeleteMessages,
            canInviteUsers,
            canRestrictMembers,
            canPinMessages,
            canPromoteMembers
        )
    ) return false

    val text = localeFor(userId).NO_PERMISSION

    sudo make {

        inputMarkdown = text

        message?.id?.also { replyToMessageId = it }

    } onSuccess withDelay {

        if (hasRequiredPermission(chatId, me.id, canDeleteMessages = true)) {

            delete(it);

            message?.run { delete(this) }

        }

    } sendTo (message?.chatId ?: chatId)

    return true

}

suspend fun TdHandler.hasRequiredPermission(
    chatId: Long, userId: Int,
    canChangeInfo: Boolean = false,
    canPostMessages: Boolean = false,
    canEditMessages: Boolean = false,
    canDeleteMessages: Boolean = false,
    canInviteUsers: Boolean = false,
    canRestrictMembers: Boolean = false,
    canPinMessages: Boolean = false,
    canPromoteMembers: Boolean = false
): Boolean {

    val status = getChatMember(chatId, userId).status

    return status is TdApi.ChatMemberStatusCreator || (status is TdApi.ChatMemberStatusAdministrator &&

            (!canPostMessages || status.canPostMessages) &&
            (!canEditMessages || status.canEditMessages) &&
            (!canDeleteMessages || status.canDeleteMessages) &&
            (!canInviteUsers || status.canInviteUsers) &&
            (!canRestrictMembers || status.canRestrictMembers) &&
            (!canPinMessages || status.canPinMessages) &&
            (!canPromoteMembers || status.canPromoteMembers))


}


// TODO: impl this method to parent
suspend fun TdHandler.checkRequiredPermission(
    userId: Int, chatId: Long, message: TdApi.Message? = null,
    canChangeInfo: Boolean = false,
    canPostMessages: Boolean = false,
    canEditMessages: Boolean = false,
    canDeleteMessages: Boolean = false,
    canInviteUsers: Boolean = false,
    canRestrictMembers: Boolean = false,
    canPinMessages: Boolean = false,
    canPromoteMembers: Boolean = false
): Boolean {

    val status = getChatMember(chatId, userId).status

    val missing = LinkedList<String>()

    if (status is TdApi.ChatMemberStatusCreator) {

        return false

    } else if (status is TdApi.ChatMemberStatusAdministrator) {

        if (canChangeInfo && !status.canChangeInfo) {

            missing.add(localeFor(chatId, userId).PERMISSION_DELETE_MESSAGES)

        }

    }

    val text = localeFor(userId).NO_PERMISSION

    sudo make {

        inputMarkdown = text

        message?.id?.also { replyToMessageId = it }

    } onSuccess withDelay {

        delete(it); message?.run { delete(this) }

    } sendTo chatId

    return true

}

suspend fun TdHandler.checkChatAdmin(chatId: Long, userId: Int, queryId: Long): Boolean {

    if (isChatAdmin(chatId, userId)) return false

    sudo makeAlert localeFor(chatId, userId).NO_PERMISSION cacheTime 114 answerTo queryId

    return true

}