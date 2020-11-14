@file:Suppress("unused")

package io.nekohasekai.ktlib.td.extensions

val Long.fromPrivate get() = this > 0L
val Long.fromBasicGroup get() = this > -1000000000000L && this < 0
val Long.fromSuperGroupOrChannel get() = this < -1000000000000L

val Long.toSupergroupId get() = (this * -1 - 1000000000000L).toInt()
