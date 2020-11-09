package io.nekohasekai.ktlib.td.core

import td.TdApi
import td.TdNative

fun <T : TdApi.Object> syncRaw(function: TdApi.Function<T>): T {

    val result = TdNative.nativeClientExecute(function)

    if (result is TdApi.Error) {

        throw TdException(result)

    } else {

        return result

    }

}