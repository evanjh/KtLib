@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.extensions

import cn.hutool.http.HtmlUtil
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi

val String.htmlBold get() = "<b>${HtmlUtil.escape(this)}</b>"
val String.htmlItalic get() = "<i>${HtmlUtil.escape(this)}</i>"
val String.htmlUnderline get() = "<u>${HtmlUtil.escape(this)}</u>"
val String.htmlDelete get() = "<del>${HtmlUtil.escape(this)}</del>"
val Any.htmlCode get() = "<code>${HtmlUtil.escape(toString())}</code>"

fun String.htmlLink(url: String) = "<a href=\"$url\">${HtmlUtil.escape(this)}</a>"
fun String.htmlInlineMention(userId: Int) = htmlLink("tg://user?id=$userId")
val TdApi.User.htmlInlineMention get() = displayName.htmlInlineMention(id)
val TdApi.User.htmlIdMention get() = id.toString().htmlInlineMention(id)


val TdApi.FormattedText.htmlString: String
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

                is TdApi.TextEntityTypeBold -> entityText.htmlBold
                is TdApi.TextEntityTypeItalic -> entityText.htmlItalic
                is TdApi.TextEntityTypeCode -> entityText.htmlCode
                is TdApi.TextEntityTypePre -> entityText.htmlCode
                is TdApi.TextEntityTypePreCode -> entityText.htmlCode
                is TdApi.TextEntityTypeUnderline -> entityText.htmlUnderline
                is TdApi.TextEntityTypeStrikethrough -> entityText.htmlDelete

                is TdApi.TextEntityTypeTextUrl -> entityText.htmlLink((it.type as TdApi.TextEntityTypeTextUrl).url)
                is TdApi.TextEntityTypeMentionName -> entityText.htmlInlineMention((it.type as TdApi.TextEntityTypeMentionName).userId)

                else -> entityText

            }

        }

        if (text.length > index) {

            htmlText += text.substring(index, text.length)

        }

        return htmlText

    }

fun mkStartPayloadUrl(botUserName: String, vararg payloads: String): String {

    return "https://t.me/$botUserName?start=${payloads.joinToString("-")}"

}

fun TdHandler.mkStartPayloadUrlForSelf(vararg payloads: String): String {

    return mkStartPayloadUrl(me.username, *payloads)

}

fun mkStartGroupPayloadUrl(botUserName: String, vararg payloads: String): String {

    @Suppress("SpellCheckingInspection")
    return "https://t.me/$botUserName?startgroup=${payloads.joinToString("-")}"

}

fun TdHandler.mkStartGroupPayloadUrlForSelf(vararg payloads: String): String {

    return mkStartPayloadUrl(me.username, * payloads)

}