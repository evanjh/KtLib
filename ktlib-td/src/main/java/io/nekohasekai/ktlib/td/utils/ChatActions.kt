@file:Suppress("unused")

package io.nekohasekai.ktlib.td.utils

import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.sendChatAction
import io.nekohasekai.ktlib.td.core.raw.sendChatActionWith
import kotlinx.coroutines.CoroutineScope
import td.TdApi.*

val Typing = ChatActionTyping()
val UploadingVideo = ChatActionUploadingVideo()
val RecordingVoiceNote = ChatActionRecordingVoiceNote()
val UploadingVoiceNote = ChatActionUploadingVoiceNote()
val UploadingPhoto = ChatActionUploadingPhoto()
val UploadingDocument = ChatActionUploadingDocument()
val ChoosingLocation = ChatActionChoosingLocation()
val ChoosingContact = ChatActionChoosingContact()
val StartPlayingGame = ChatActionStartPlayingGame()
val RecordingVideoNote = ChatActionRecordingVideoNote()
val UploadingVideoNote = ChatActionUploadingVideoNote()
val CancelChatAction = ChatActionCancel()

infix fun TdHandler.make(action: ChatAction): ChatActionFactory {

    return ChatActionFactory(this, action)

}

class ChatActionFactory(val context: TdHandler, val action: ChatAction) {

    lateinit var chatId: Number

    infix fun to(chatId: Number): ChatActionFactory {

        this.chatId = chatId

        return this

    }

    suspend infix fun syncTo(chatId: Number) {

        context.sendChatAction(chatId.toLong(), action)

    }

    infix fun sendTo(chatId: Number) {

        context.sendChatActionWith(chatId.toLong(), action, 1)

    }

    infix fun send(block: suspend CoroutineScope.(Ok) -> Unit) {

        context.sendChatActionWith(chatId.toLong(), action, 1) { onSuccess(block) }

    }

}