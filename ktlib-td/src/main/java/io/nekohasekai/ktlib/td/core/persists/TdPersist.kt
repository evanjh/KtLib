package io.nekohasekai.ktlib.td.core.persists

import cn.hutool.core.date.SystemClock

data class TdPersist(

        var userId: Int,
        var persistId: Int,
        var subId: Int,
        var data: Array<Any?>,
        var allowFunction: Boolean,
        var allowCancel: Boolean,
        var createAt: Int = (SystemClock.now() / 1000L).toInt(),
        var dontSave: Boolean = false

) {

    constructor() : this(0, 0, 0, arrayOf(), false, true, 0)

}