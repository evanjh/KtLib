package io.nekohasekai.ktlib.td.core

import cn.hutool.core.thread.ThreadUtil
import td.TdApi.Error

open class TdException(val error: Error, override val cause: Throwable? = null) : RuntimeException() {

    constructor(code: Int, message: String) : this(Error(code, message))
    constructor(message: String) : this(-1, message)
    constructor(exception: TdException) : this(exception.error, exception)

    val code: Int
        get() = error.code

    override val message: String
        get() = error.message

    override fun toString(): String {
        return "$code : $message"
    }

    val retryAfter get() = message.substringAfter("after").trim().toInt()

    fun waitForRateLimit() {

        if (code == 429) {

            ThreadUtil.sleep(retryAfter * 1000L)

        }

    }

}