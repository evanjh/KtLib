@file:Suppress("unused", "UNCHECKED_CAST")

package io.nekohasekai.ktlib.core

import cn.hutool.core.collection.CollUtil
import cn.hutool.core.util.ArrayUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.setting.Setting
import java.io.File
import java.math.BigInteger
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

/**
 * 一些基于语言特性的全局函数
 */

fun <T> T.applyIf(boolean: Boolean, block: (T.() -> Unit)?): T {
    if (boolean) block?.invoke(this)
    return this
}

fun <T> T.applyIfNot(boolean: Boolean, block: (T.() -> Unit)?): T {
    if (!boolean) block?.invoke(this)
    return this
}

inline fun <T> T.applyIf(c: T.() -> Boolean, block: (T.() -> Unit)): T {
    if (c(this)) block(this)
    return this
}

inline fun <reified T : Any, reified TN : T?> TN.applyIfNotNull(block: (T.() -> Unit)): TN {
    if (this != null) block(this)
    return this
}

inline fun <T> T.alsoIf(c: (T) -> Boolean, block: ((T) -> Unit)): T {
    if (c(this)) block(this)
    return this
}

inline fun <reified T : Any, reified TN : T?> TN.alsoIfNotNull(block: ((T) -> Unit)): TN {
    if (this != null) block(this)
    return this
}

inline fun <T> Array<out T>.anyIndexed(predicate: (index: Int, T) -> Boolean): Boolean {
    var index = 0
    for (item in this) if (predicate(index++, item)) return true
    return false
}

inline fun <T> Array<out T>.allIndexed(predicate: (index: Int, T) -> Boolean): Boolean {
    var index = 0
    for (item in this) if (!predicate(index++, item)) return false
    return false
}

fun String.input(vararg params: Any): String {

    return StrUtil.format(this, *params)

}

fun Collection<Int>.addAll(): Int {

    var count = 0

    forEach { count += it }

    return count

}

fun Array<Int>.addAll(): Int {

    var count = 0

    forEach { count += it }

    return count

}

fun <T> Iterable<T>.toLinkedList(): List<T> {
    if (this is Collection) {
        return when (size) {
            0 -> emptyList()
            1 -> listOf(if (this is List) get(0) else iterator().next())
            else -> this.toMutableLinkedList()
        }
    }
    return this.toMutableLinkedList().optimizeReadOnlyList()
}

fun <T> Collection<T>.toMutableLinkedList(): LinkedList<T> {
    return LinkedList(this)
}

fun <T> Iterable<T>.toMutableLinkedList(): LinkedList<T> {
    if (this is Collection<T>)
        return this.toMutableLinkedList()
    return toCollection(LinkedList())
}

private fun <T> List<T>.optimizeReadOnlyList() = when (size) {
    0 -> emptyList()
    1 -> listOf(this[0])
    else -> this
}

val Number.asByteArray get() = BigInteger.valueOf(toLong()).toByteArray()!!

val ByteArray.asLong get() = BigInteger(this).toLong()
val ByteArray.asInt get() = BigInteger(this).toInt()

fun <T> Array<T>.shift(): Array<T> {

    return shift(1)

}

fun <T> Array<T>.shift(size: Int): Array<T> {

    return ArrayUtil.sub(this, size, this.size)

}

fun <T> Collection<T>.shift() = shift(1)

fun <T> Collection<T>.shift(size: Int): Collection<T> {

    return LinkedList(CollUtil.sub(this, size, this.size))

}

class WriteOnlyField<T>(val setter: (T) -> Unit) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = error("WriteOnlyField : ${property.name}")

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {

        setter.invoke(value)

    }

}

open class Field<T>(val getter: () -> T, val setter: (T) -> Unit) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = getter()

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {

        setter.invoke(value)

    }

}

class WeakField<T> {

    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("Property ${property.name} should be initialized before get.")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        this.value = value
    }

}

fun <T, R> receive(getter: T.(property: KProperty<*>) -> R) = Receiver(getter)

class Receiver<T, R>(val getter: T.(property: KProperty<*>) -> R) {

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any?, property: KProperty<*>): R {

        return getter(thisRef as T, property)

    }

}

fun <T : Any, R> receiveLazy(initializer: T.(property: KProperty<*>) -> R) = receiveHashLazy({ it }, initializer)
fun <T : Any, H : Any, R> receiveHashLazy(hash: (T) -> H, initializer: T.(property: KProperty<*>) -> R) = LazyReceiver(hash, initializer)

class LazyReceiver<T : Any, H : Any, R>(val hash: (T) -> H, val initializer: T.(property: KProperty<*>) -> R) {

    private val isInitialized = HashMap<H, Unit>()
    private val cache = HashMap<H, R>()

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any, property: KProperty<*>): R {

        val hashObj = hash(thisRef as T)

        if (isInitialized[hashObj] != null) return cache[hashObj] as R

        synchronized(this) {

            if (isInitialized[hashObj] != null) return cache[hashObj] as R

            return initializer(thisRef, property).apply {

                cache[hashObj] = this

                isInitialized[hashObj] = Unit

            }

        }

    }

}

operator fun <F> KProperty0<F>.getValue(thisRef: Any?, property: KProperty<*>): F = get()
operator fun <F> KMutableProperty0<F>.setValue(thisRef: Any?, property: KProperty<*>, value: F) = set(value)

operator fun AtomicBoolean.getValue(thisRef: Any?, property: KProperty<*>): Boolean = get()
operator fun AtomicBoolean.setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) = set(value)

operator fun AtomicInteger.getValue(thisRef: Any?, property: KProperty<*>): Int = get()
operator fun AtomicInteger.setValue(thisRef: Any?, property: KProperty<*>, value: Int) = set(value)

operator fun AtomicLong.getValue(thisRef: Any?, property: KProperty<*>): Long = get()
operator fun AtomicLong.setValue(thisRef: Any?, property: KProperty<*>, value: Long) = set(value)

operator fun <T> AtomicReference<T>.getValue(thisRef: Any?, property: KProperty<*>): T = get()
operator fun <T> AtomicReference<T>.setValue(thisRef: Any?, property: KProperty<*>, value: T) = set(value)

operator fun <K, V> Map<K, V>.getValue(thisRef: K, property: KProperty<*>) = get(thisRef)
operator fun <K, V> MutableMap<K, V>.setValue(thisRef: K, property: KProperty<*>, value: V?) {

    if (value != null) {

        put(thisRef, value)

    } else {

        remove(thisRef)

    }

}

fun readSettings(path: String): Setting? {

    return Setting((File(path).takeIf { it.isFile } ?: return null).canonicalPath, false)

}