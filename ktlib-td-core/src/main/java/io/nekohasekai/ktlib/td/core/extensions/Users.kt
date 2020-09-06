package io.nekohasekai.ktlib.td.core.extensions

import td.TdApi

val TdApi.User.displayName get() = "$firstName $lastName".trim()
val TdApi.User.displayNameFormatted get() = if (username.isNullOrBlank()) "$displayName ($id)" else "$displayName ($id @$username)"

val TdApi.User.isUser get() = type is TdApi.UserTypeRegular
val TdApi.User.isBot get() = type is TdApi.UserTypeBot
val TdApi.User.isDeleted get() = type is TdApi.UserTypeDeleted
val TdApi.User.chatId get() = id.toLong()