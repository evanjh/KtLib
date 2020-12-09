@file:Suppress("unused")

package io.nekohasekai.ktlib.core

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import com.esotericsoftware.kryo.util.Pool
import org.objenesis.instantiator.util.UnsafeUtils
import java.io.ByteArrayOutputStream

fun <T> mkPool(threadSafe: Boolean = true, softReferences: Boolean = true, maximumCapacity: Int = Int.MAX_VALUE, initializer: () -> T) = object : Pool<T>(threadSafe, softReferences, maximumCapacity) {

    init {
        hideOpenJDK11IllegalWarning()
    }

    override fun create() = initializer()

}

private var legalAccessHidden = false

fun hideOpenJDK11IllegalWarning() {

    if (legalAccessHidden) return

    legalAccessHidden = true

    runCatching {

        val unsafe = UnsafeUtils.getUnsafe()

        val loggerClass = Class.forName("jdk.internal.module.IllegalAccessLogger")
        val loggerField = loggerClass.getDeclaredField("logger")

        unsafe.putObjectVolatile(loggerClass, unsafe.staticFieldOffset(loggerField), null)

    }

}

inline operator fun <T, R> Pool<T>.invoke(function: T.() -> R): R = obtain().let { function(it).apply { free(it) } }

val globalKryo = mkPool { Kryo().apply { isRegistrationRequired = false } }

fun Any.toByteArray(withClass: Boolean = false): ByteArray {

    val thisRef = this

    return globalKryo {

        ByteArrayOutputStream().apply {

            Output(this).apply {

                if (withClass) {

                    writeClassAndObject(this, thisRef)

                } else {

                    writeObject(this, thisRef)

                }

                close()

            }

        }.toByteArray()

    }

}

fun <T> ByteArray.fromByteArray(clazz: Class<T>): T {

    val input = Input(this)

    return globalKryo {

        readObject(input, clazz)

    }

}

inline fun <reified T> ByteArray.fromByteArray() = fromByteArray(T::class.java)

fun ByteArray.anyFormByteArray(): Any {

    val input = Input(this)

    return globalKryo { readClassAndObject(input) }

}