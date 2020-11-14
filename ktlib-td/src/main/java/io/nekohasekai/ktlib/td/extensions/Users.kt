@file:Suppress("unused")

package io.nekohasekai.ktlib.td.extensions

import td.TdApi.*

val User.displayName get() = "$firstName $lastName".trim()
val Contact.displayName get() = "$firstName $lastName".trim()
val User.displayNameFormatted get() = if (username.isNullOrBlank()) "$displayName ($id)" else "$displayName ($id @$username)"

val User.isUser get() = type is UserTypeRegular
val User.isBot get() = type is UserTypeBot
val User.isDeleted get() = type is UserTypeDeleted
val User.chatId get() = id.toLong()