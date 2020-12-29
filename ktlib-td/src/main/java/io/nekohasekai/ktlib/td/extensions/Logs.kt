package io.nekohasekai.ktlib.td.extensions

import cn.hutool.log.level.Level
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.getUserOrNull

suspend fun TdHandler.warnUserCalled(userId: Int, what: String) {

    userCalled(userId, what, Level.WARN)

}

fun TdHandler.called(what: String, level: Level = Level.TRACE) {

    clientLog.log(level, what)

}

suspend fun TdHandler.userCalled(userId: Int, what: String, level: Level = Level.TRACE) {

    val user = getUserOrNull(userId)

    var msg: String

    if (user != null) {

        msg = user.displayName

        msg += " ( $userId"

        if (!user.username.isNullOrBlank()) {

            msg += " @${user.username}"

        }

        msg += " )"

    } else {

        msg = "$userId"

    }

    clientLog.log(level, "$what <- $msg")

}