package io.nekohasekai.ktlib.td.core

import td.TdApi
import td.TdNative

fun <T : TdApi.Object> syncRaw(function: TdApi.Function): T {

    val result = TdNative.nativeClientExecute(function)

    if (result is TdApi.Error) {

        throw TdException(result)

    } else {

        @Suppress("UNCHECKED_CAST")
        return result as T

    }

}