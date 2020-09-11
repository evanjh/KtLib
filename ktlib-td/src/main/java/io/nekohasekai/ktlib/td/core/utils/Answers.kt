@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.utils

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.answerCallbackQuery
import io.nekohasekai.ktlib.td.core.raw.answerCallbackQueryWith

infix fun TdHandler.makeAnswer(block: AnswerFactory.() -> Unit): AnswerFactory {

    return AnswerFactory(this).apply(block)

}

infix fun TdHandler.makeAnswer(text: String): AnswerFactory {

    return AnswerFactory(this).also { it.text = text }

}

infix fun TdHandler.makeAlert(text: String): AnswerFactory {

    return makeAnswer(text).also { it.showAlert = true }

}


infix fun TdHandler.makeAnswerUrl(url: String): AnswerFactory {

    return AnswerFactory(this).also { it.url = url }

}

infix fun TdHandler.confirmTo(queryId: Long) {

    AnswerFactory(this).answerTo(queryId)

}

suspend infix fun TdHandler.syncConfirmTo(queryId: Long) {

    AnswerFactory(this).syncAnswerTo(queryId)

}

class AnswerFactory(val context: TdHandler) {

    var text: String? = null

    var showAlert = false

    var url: String? = null

    var cacheTime = 0

    infix fun showAlert(alert: Boolean): AnswerFactory {

        showAlert = alert

        return this

    }

    infix fun cacheTime(time: Int): AnswerFactory {

        cacheTime = time

        return this

    }

    infix fun answerTo(queryId: Long) {

        context.answerCallbackQueryWith(queryId, text, showAlert, url, cacheTime)

    }

    suspend infix fun syncAnswerTo(queryId: Long) {

        context.answerCallbackQuery(queryId, text, showAlert, url, cacheTime)

    }

}