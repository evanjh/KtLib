package io.nekohasekai.ktlib.td.core

import cn.hutool.core.thread.ThreadUtil
import io.nekohasekai.ktlib.core.defaultLog
import io.nekohasekai.ktlib.core.shift
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

class TdCallback<T> {

    val stackTrace: Array<StackTraceElement>

    constructor(stackIgnore: Int = 0) {

        stackTrace = ThreadUtil.getStackTrace().shift(3 + stackIgnore)

    }

    constructor(stackTrace: Array<StackTraceElement>) {

        this.stackTrace = stackTrace

    }

    var onSuccess: (suspend CoroutineScope.(T) -> Unit)? = null

    var onFailure: (suspend CoroutineScope.(TdException) -> Unit)? = {

        defaultLog.warn(it)

    }

    infix fun onSuccess(handler: (suspend CoroutineScope.(T) -> Unit)?): TdCallback<T> {

        this.onSuccess = handler

        return this

    }

    infix fun onFailure(handler: (suspend CoroutineScope.(TdException) -> Unit)?): TdCallback<T> {

        this.onFailure = handler

        return this

    }

    @Suppress("UNCHECKED_CAST")
    suspend fun postResult(result: Any) = coroutineScope<Unit> {

        runCatching {

            onSuccess?.invoke(this, result as T)

        }.onFailure { throwable ->

            defaultLog.error("Error in success callback ($result), see exception in next message.", Throwable(throwable).also {

                it.stackTrace = stackTrace

            })

            throw throwable

        }

    }

    suspend fun postError(error: TdException) = coroutineScope<Unit> {

        runCatching {

            onFailure?.invoke(this, error.also { it.stackTrace = stackTrace })

        }.onFailure { throwable ->

            defaultLog.error("Error in error callback ($error), see exception in next message.", Throwable(throwable).also {

                it.stackTrace = stackTrace

            })

            throw throwable

        }

    }

}