package io.nekohasekai.ktlib.core

import cn.hutool.core.io.FileUtil
import cn.hutool.core.io.IoUtil
import cn.hutool.core.io.StreamProgress
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.headersContentLength
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

val globalOkHttpClient = OkHttpClient.Builder()
    .connectTimeout(5, TimeUnit.SECONDS)
    .readTimeout(5, TimeUnit.SECONDS)
    .followRedirects(true)
    .followSslRedirects(true)
    .build()

fun downloadFile(path: String, file: File) {


    val cacheFile = File(file.parentFile, "${file.nameWithoutExtension}.part")
    val response = globalOkHttpClient.newCall(Request.Builder().url(path).applyIf(cacheFile.length() > 0) {
        header("Range", "bytes=${file.length()}-")
    }.build()).execute()

    if (!response.isSuccessful) error("HTTP ${response.code}: ${response.body}")
    val contentLength = response.headersContentLength().toFloat()

    val output = if (response.code == 206) {
        FileOutputStream(cacheFile, true)
    } else {
        cacheFile.parentFile.mkdirs()
        cacheFile.outputStream()
    }

    val start = System.currentTimeMillis()

    response.body?.byteStream()?.use { stream ->
        file.parentFile?.mkdirs()
        file.createNewFile()
        IoUtil.copyByNIO(stream, output, IoUtil.DEFAULT_BUFFER_SIZE, object : StreamProgress {
            override fun start() = Unit

            override fun progress(progressSize: Long) {
                var duration = (System.currentTimeMillis() - start) / 1000
                duration = duration.coerceAtLeast(1)
                val speed = (progressSize / (1024 * duration)).toInt()
                val ratio = progressSize / contentLength
                val percent = ratio * 100
                val eta = (duration / ratio * (1 - ratio)).toInt()
                val minutes = eta / 60
                val seconds = eta % 60

                System.out.printf(
                    "\r%.2f%%, %d MB, %d KB/s, ETA %d min %d s",
                    percent,
                    progressSize / (1024 * 1024),
                    speed,
                    minutes,
                    seconds
                )

            }

            override fun finish() {
                FileUtil.move(cacheFile, file, true)
            }
        })
    }
}