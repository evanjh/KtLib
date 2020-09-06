@file:Suppress("unused")

package io.nekohasekai.ktlib.td.utils

import cn.hutool.http.HtmlUtil
import io.nekohasekai.ktlib.td.core.extensions.displayName
import td.TdApi

val String.asBold get() = "<b>${HtmlUtil.escape(this)}</b>"
val String.asItalic get() = "<i>${HtmlUtil.escape(this)}</i>"
val String.asUnderline get() = "<u>${HtmlUtil.escape(this)}</u>"
val String.asDelete get() = "<del>${HtmlUtil.escape(this)}</del>"
val Any.asCode get() = "<code>${HtmlUtil.escape(toString())}</code>"

fun String.toLink(url: String) = "<a href=\"$url\">${HtmlUtil.escape(this)}</a>"
fun String.toInlineMention(userId: Int) = toLink("tg://user?id=$userId")
val TdApi.User.asInlineMention get() = displayName.toInlineMention(id)
val TdApi.User.asIdMention get() = id.toString().toInlineMention(id)


val TdApi.FormattedText.asHtml: String
    get() {

        var htmlText = ""

        var index = 0

        entities.forEach {

            if (it.offset > index) {

                htmlText += text.substring(index, it.offset)

            }

            index = it.offset + it.length

            val entityText = text.substring(it.offset, index)

            htmlText += when (it.type) {

                is TdApi.TextEntityTypeBold -> entityText.asBold
                is TdApi.TextEntityTypeItalic -> entityText.asItalic
                is TdApi.TextEntityTypeCode -> entityText.asCode
                is TdApi.TextEntityTypePre -> entityText.asCode
                is TdApi.TextEntityTypePreCode -> entityText.asCode
                is TdApi.TextEntityTypeUnderline -> entityText.asUnderline
                is TdApi.TextEntityTypeStrikethrough -> entityText.asDelete

                is TdApi.TextEntityTypeTextUrl -> entityText.toLink((it.type as TdApi.TextEntityTypeTextUrl).url)
                is TdApi.TextEntityTypeMentionName -> entityText.toInlineMention((it.type as TdApi.TextEntityTypeMentionName).userId)

                else -> entityText

            }

        }

        if (text.length > index) {

            htmlText += text.substring(index, text.length)

        }

        return htmlText

    }

fun mkStartPayloadUrl(botUserName: String, vararg payload: String): String {

    return "https://t.me/$botUserName?start=${payload.joinToString("-")}"

}

fun mkStartGroupPayloadUrl(botUserName: String, vararg payload: String): String {

    return "https://t.me/$botUserName?startgroup=${payload.joinToString("-")}"

}