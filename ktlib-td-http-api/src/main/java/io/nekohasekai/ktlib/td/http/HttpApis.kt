@file:Suppress("unused")

package io.nekohasekai.ktlib.td.http

import cn.hutool.core.net.URLEncoder
import cn.hutool.core.util.CharsetUtil
import cn.hutool.http.HttpUtil
import com.google.gson.Gson
import com.pengrad.telegrambot.request.BaseRequest
import com.pengrad.telegrambot.response.BaseResponse
import io.nekohasekai.ktlib.core.applyIf
import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

private val GSON = Gson()

private const val API_URL = "https://api.telegram.org"

suspend fun httpReadFile(botToken: String, filePath: String) = withContext<ByteArray>(Dispatchers.IO) {

    runCatching {

        HttpUtil.createGet(getFilePath(botToken, filePath)).execute().bodyBytes()

    }.getOrThrow()

}

private fun getFilePath(botToken: String, filePath: String): String? {

    val slash = filePath.lastIndexOf('/') + 1
    val path = filePath.substring(0, slash)
    val fileName = filePath.substring(slash)

    return API_URL + "/file/bot" + botToken + "/" + path + URLEncoder.DEFAULT.encode(fileName, CharsetUtil.CHARSET_UTF_8).replace("+", "%20")

}

fun <T : BaseRequest<T, R>, R : BaseResponse> httpSend(botToken: String, request: BaseRequest<T, R>, submit: (TdCallback<R>.() -> Unit)? = null) {

    TdCallback<R>(1).applyIf(submit != null, submit).apply {

        GlobalScope.launch(Dispatchers.IO) {

            try {

                val result = httpSync(botToken, request)

                postResult(result)

            } catch (ex: TdException) {

                postError(ex)

            }

        }

    }

}

suspend fun <T : BaseRequest<T, R>, R : BaseResponse> httpSync(botToken: String, request: BaseRequest<T, R>): R = withContext(Dispatchers.IO) {

    val httpRequest = HttpUtil.createPost(API_URL + "/bot" + botToken + "/" + request.method)

    for ((name, value) in request.parameters) {

        when (value) {

            is ByteArray -> httpRequest.form(name, value, request.fileName)

            is File -> httpRequest.form(name, value, request.fileName)

            else -> httpRequest.form(name, value)

        }

    }

    val body = try {

        httpRequest.execute().body()

    } catch (ex: Exception) {

        throw TdException(ex.message ?: ex.toString())

    }

    val response = GSON.fromJson<R>(body, request.responseType)!!

    if (!response.isOk) {

        throw TdException(response.errorCode(), response.description())

    }

    response

}