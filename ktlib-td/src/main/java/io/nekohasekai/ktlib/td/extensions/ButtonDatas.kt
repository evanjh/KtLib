package io.nekohasekai.ktlib.td.extensions

import cn.hutool.core.util.ArrayUtil
import cn.hutool.core.util.RandomUtil
import io.nekohasekai.ktlib.core.fromByteArray
import io.nekohasekai.ktlib.core.toByteArray
import java.math.BigInteger

fun Boolean.asByteArray() = byteArrayOf(if (this) 1 else 0)
fun Number.asByteArray(): ByteArray = toLong().toBigInteger().toByteArray()
fun ByteArray.toLong() = BigInteger(this).toLong()
fun ByteArray.toInt() = BigInteger(this).toInt()
fun ByteArray.toBoolean() = this[0].toInt() == 1

fun mkData(id: Long, vararg dataArray: ByteArray, randomSuffix: Boolean = false): ByteArray {

    var data = arrayOf(id.asByteArray(), * dataArray).toByteArray()

    if (randomSuffix) {

        data = byteArrayOf(* data, * RandomUtil.randomBytes(3))

    }

    return data

}

fun ByteArray.readData(randomSuffix: Boolean = false): Array<ByteArray> {

    var data = this

    if (randomSuffix) {

        data = ArrayUtil.sub(data, 0, -3)

    }

    return data.fromByteArray()

}