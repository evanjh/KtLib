@file:Suppress("unused")

package io.nekohasekai.td.proxy.parser.td

import io.nekohasekai.ktlib.td.extensions.text
import io.nekohasekai.td.proxy.impl.Proxy
import io.nekohasekai.td.proxy.parser.LinkParser
import io.nekohasekai.td.proxy.parser.Parser
import io.nekohasekai.td.proxy.parser.StringParser
import td.TdApi

object MessageParser : Parser<TdApi.Message> {

    override fun parseProxies(value: TdApi.Message) = mutableSetOf<Proxy>().apply {

        value.text?.also {

            addAll(StringParser.parseProxies(it))

        }

        val buttons = value.replyMarkup

        if (buttons is TdApi.ReplyMarkupInlineKeyboard) {

            buttons.rows.forEach { row ->

                row.forEach {

                    val type = it.type

                    if (type is TdApi.InlineKeyboardButtonTypeUrl) {

                        addAll(LinkParser.parseProxies(type.url))

                    }

                }

            }

        }

    }.toList()

}