@file:Suppress("unused")

package io.nekohasekai.ktlib.td.extensions

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.getSupergroup
import io.nekohasekai.ktlib.td.core.raw.getUser
import td.TdApi.*

val User.displayName get() = "$firstName $lastName".trim()
val Contact.displayName get() = "$firstName $lastName".trim()
val User.displayNameFormatted get() = if (username.isNullOrBlank()) "$displayName ($id)" else "$displayName ($id @$username)"

val User.isUser get() = type is UserTypeRegular
val User.isBot get() = type is UserTypeBot
val User.isDeleted get() = type is UserTypeDeleted
val User.chatId get() = id.toLong()

suspend fun TdHandler.getChatUsername(chat: Chat): String? {
    return when (val type = chat.type) {
        is ChatTypePrivate -> getUser(type.userId).username
        is ChatTypeSecret -> getUser(type.userId).username
        is ChatTypeBasicGroup -> null
        is ChatTypeSupergroup -> getSupergroup(type.supergroupId).username
        else -> error("unknown type")
    }.takeIf { !it.isNullOrBlank() }
}