package io.nekohasekai.ktlib.td.core

import cn.hutool.core.thread.ThreadUtil
import td.TdApi
import td.TdApi.Error

open class TdException(val error: Error, override val cause: Throwable? = null) : RuntimeException() {

    constructor(code: Int, message: String) : this(Error(code, message))
    constructor(message: String) : this(-1, message)
    constructor(exception: TdException) : this(exception.error, exception) {
        if (exception::request.isInitialized) request = exception.request
    }

    lateinit var request: TdApi.Function<*>

    val code: Int
        get() = error.code

    override val message: String
        get() = error.message

    override fun toString(): String {
        var message = "$code : $message"
        if (::request.isInitialized) {
            message += "\n\nRequest = $request\n"
        }
        return message
    }

    val retryAfter get() = message.substringAfter("after").trim().toInt()

    fun waitForRateLimit() {

        if (code == 429) {

            ThreadUtil.sleep(retryAfter * 1000L)

        }

    }

}