package io.nekohasekai.ktlib.core

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.util.concurrent.TimeUnit

val globalOkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .followRedirects(true)
        .followSslRedirects(true)
        .build()

fun downloadFile(path: String, file: File) {

    val response = globalOkHttpClient.newCall(Request.Builder()
            .url(path)
            .build())
            .execute()

    if (!response.isSuccessful) {

        error("HTTP ${response.code}: ${response.body}")

    }

    response.body?.byteStream()?.use {
        file.parentFile?.mkdirs()
        file.createNewFile()
        file.outputStream().use { out -> it.copyTo(out) }
    }

}