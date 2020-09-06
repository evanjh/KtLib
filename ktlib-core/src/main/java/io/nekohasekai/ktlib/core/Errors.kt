package io.nekohasekai.ktlib.core

import cn.hutool.core.util.StrUtil
import java.io.ByteArrayOutputStream
import java.io.PrintWriter

fun Throwable.parse(): String {

    val out = ByteArrayOutputStream()

    printStackTrace(PrintWriter(out, true))

    return StrUtil.utf8Str(out)

}
