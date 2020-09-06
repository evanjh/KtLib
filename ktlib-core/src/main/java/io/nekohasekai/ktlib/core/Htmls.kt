package io.nekohasekai.ktlib.core

import cn.hutool.http.HtmlUtil

fun String.escapeHtmlTags(): String = HtmlUtil.escape(this)
fun String.unescapeHtmlTags(): String = HtmlUtil.unescape(this)