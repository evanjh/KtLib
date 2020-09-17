package io.nekohasekai.ktlib.core

import kotlinx.coroutines.sync.Mutex
import kotlin.reflect.KProperty

class MutexBoolean(val initialValue: Boolean = false) {

    val trueMutex = Mutex(!initialValue)
    val falseMutex = Mutex(initialValue)

    var value
        get() = (if (initialValue) trueMutex else falseMutex).isLocked
        set(value) {
            if (value != this.value) {
                if (value xor initialValue) {
                    trueMutex.unlock()
                    if (!falseMutex.isLocked) falseMutex.tryLock()
                } else {
                    falseMutex.unlock()
                    if (!trueMutex.isLocked) trueMutex.tryLock()
                }
            }
        }

    suspend fun waitFor(target: Boolean) {

        val targetMutex = (if (target xor initialValue) trueMutex else falseMutex)

        if (!targetMutex.isLocked) return

        targetMutex.lock()
        targetMutex.unlock()

    }

    operator fun getValue(thisRef: Any, property: KProperty<*>) = value

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        this.value = value
    }

}