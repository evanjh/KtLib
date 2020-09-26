@file:Suppress("DEPRECATION", "unused")

package io.nekohasekai.ktlib.td.extensions

import cn.hutool.core.date.DateUtil
import cn.hutool.core.util.NumberUtil
import io.nekohasekai.ktlib.core.input
import io.nekohasekai.ktlib.td.i18n.*
import java.util.*

fun nextHour(): Long {

    val nextHour = Date()

    nextHour.hours = nextHour.hours + 1

    nextHour.minutes = 0
    nextHour.seconds = 0

    return nextHour.time

}

fun nextHalfHour(): Long {

    val nextHour = Date()

    nextHour.hours = nextHour.hours + 1

    if (nextHour.minutes < 30) {

        nextHour.minutes = 0

    } else {

        nextHour.minutes = 30

    }

    nextHour.seconds = 0

    return nextHour.time

}

fun nextDay(): Long {

    return DateUtil.tomorrow().time

}

fun String.parseTime(inSecond: Boolean = false): Long {

    var time = 0L

    var count = -1

    for (index in 0 until length) {

        val str = substring(index, index + 1)

        if (NumberUtil.isInteger(str)) {

            val integer = NumberUtil.parseInt(str)

            count = if (count == -1) {
                integer
            } else {
                count * 10 + integer
            }

            continue

        }

        if (count == -1) return -1

        time += when (str) {
            "s" -> count.toLong()
            "m" -> count * 60.toLong()
            "h" -> count * 60 * 60.toLong()
            "d" -> count * 24 * 60 * 60.toLong()
            else -> return -1
        }

        count = -1

    }

    if (count != -1) time += count

    if (inSecond) time *= 1000

    return time

}

fun Number.parseTime(L: LocaleController, inSecond: Boolean = false): String {

    var rawTime = toLong()

    var second = 1L

    if (!inSecond) second = 1000L

    var time = ""

    if (rawTime > 24 * 60 * 60 * second) {

        time += L.TIME_DAY.input(rawTime / (24 * 60 * 60 * second))
        rawTime %= 24 * 60 * 60 * second

    }

    if (rawTime > 60 * 60 * second) {

        if (!time.isBlank()) time += ", "
        time += L.TIME_HOUR.input(rawTime / (60 * 60 * second))
        rawTime %= 60 * 60 * second

    }

    if (rawTime > 60 * second) {

        if (!time.isBlank()) time += ", "
        time += L.TIME_MIN.input(rawTime / (60 * second))
        rawTime %= 60 * second

    }

    if (rawTime > 0) {

        time += L.TIME_SEC.input(rawTime / second)

    }

    return time

}