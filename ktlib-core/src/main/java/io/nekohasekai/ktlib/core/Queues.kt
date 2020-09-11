@file:Suppress("unused")

package io.nekohasekai.ktlib.core

import cn.hutool.core.date.SystemClock
import cn.hutool.core.thread.ThreadUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class FastQueue<T>(private val capacity: Int, private val onClear: (() -> Unit)?) : LinkedBlockingQueue<T>(Int.MAX_VALUE) {

    override fun offer(e: T): Boolean {
        if (size >= capacity) {
            clear()
            onClear?.invoke()
        }
        return super.offer(e)
    }

}

fun <T> mkFastQueue(capacity: Int, onClear: (() -> Unit)? = null) = FastQueue<T>(capacity, onClear)
fun mkFastPool(capacity: Int = 1, onClear: (() -> Unit)? = null) = ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, mkFastQueue(capacity, onClear))
fun mkFastContext(capacity: Int = 1, onClear: (() -> Unit)? = null) = mkFastPool(capacity, onClear).asCoroutineDispatcher()

fun ExecutorService.executeTimed(time: Long = 5000L, runnable: () -> Unit) {

    execute {

        val start = SystemClock.now()

        runnable()

        val delay = time - (SystemClock.now() - start)

        if (delay > 0) {

            ThreadUtil.sleep(delay)

        }

    }

}

fun ExecutorService.executeBlocking(runnable: suspend CoroutineScope.() -> Unit) {

    execute {

        runBlocking {

            runnable()

        }

    }

}