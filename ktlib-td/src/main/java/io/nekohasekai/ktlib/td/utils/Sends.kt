@file:Suppress("unused")

package io.nekohasekai.ktlib.td.utils

import cn.hutool.core.builder.Builder
import cn.hutool.core.util.ArrayUtil
import io.nekohasekai.ktlib.core.*
import io.nekohasekai.ktlib.td.core.TdException
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.getMessage
import io.nekohasekai.ktlib.td.core.raw.getMessageWith
import io.nekohasekai.ktlib.td.core.raw.parseMarkdown
import io.nekohasekai.ktlib.td.core.raw.parseTextEntities
import io.nekohasekai.ktlib.td.extensions.fromPrivate
import io.nekohasekai.ktlib.td.extensions.mkData
import kotlinx.coroutines.CoroutineScope
import td.TdApi.*
import java.io.File
import java.util.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.properties.Delegates

val String.asText: FormattedText get() = FormattedText(this, arrayOf())
val String.asHtml: FormattedText get() = parseTextEntities(this, TextParseModeHTML())
val String.asMarkdown: FormattedText get() = parseMarkdown(FormattedText(this, arrayOf()))

infix fun TdHandler.make(block: MessageFactory.() -> Unit): MessageFactory {

    return MessageFactory(this).apply(block)

}

infix fun TdHandler.make(inputContent: InputMessageContent): MessageFactory {

    return make { input = inputContent }

}


infix fun TdHandler.make(text: FormattedText): MessageFactory {

    return make { input = inputText(text) }

}

infix fun TdHandler.make(text: String): MessageFactory {

    return make { inputText = text }

}

infix fun TdHandler.makeHtml(text: String): MessageFactory {

    return make { inputHtml = text }

}

infix fun TdHandler.makeMd(text: String): MessageFactory {

    return make { inputMarkdown = text }

}

infix fun TdHandler.makeFile(text: String): MessageFactory {

    return make { inputFile = text }

}

infix fun TdHandler.make(file: File): MessageFactory {

    return make { inputFile = file.path }

}

infix fun TdHandler.makeFileId(text: String): MessageFactory {

    return make { inputFileId = text }

}

infix fun TdHandler.makePhoto(text: String): MessageFactory {

    return make { inputPhoto = text }

}

infix fun TdHandler.makePhotoId(text: String): MessageFactory {

    return make { inputPhotoId = text }

}

infix fun TdHandler.makeVideo(text: String): MessageFactory {

    return make { inputVideo = text }

}

infix fun TdHandler.makeVideoId(text: String): MessageFactory {

    return make { inputVideoId = text }

}

fun TdHandler.makeForward(chatId: Number, messageId: Long): MessageFactory {

    return make { input = inputForward(chatId, messageId) }

}

infix fun TdHandler.makeForward(message: Message): MessageFactory {

    return make { input = inputForward(message) }

}


infix fun TdHandler.make(ex: Throwable): MessageFactory {

    return make { inputText = ex.parse() }

}

infix fun TdHandler.makeInlineButton(block: (InlineButtonBuilder.() -> Unit)): MessageFactory {

    return MessageFactory(this).apply {

        replyMarkup = inlineButton(block)

    }

}

infix fun TdHandler.makeInlineButton(buttons: ReplyMarkupInlineKeyboard?): MessageFactory {

    return MessageFactory(this).apply {

        replyMarkup = buttons

    }

}

fun inlineButton(block: InlineButtonBuilder.() -> Unit): ReplyMarkupInlineKeyboard {

    return InlineButtonBuilder().apply(block).build()

}

class InlineButtonBuilder : LinkedList<InlineButtonBuilder.Line>(), Builder<ReplyMarkupInlineKeyboard> {

    class Line : LinkedList<InlineKeyboardButton>() {

        fun urlButton(text: String, url: String) {

            add(InlineKeyboardButton(text, InlineKeyboardButtonTypeUrl(url)))

        }

        fun loginUrlButton(text: String, url: String, id: Int, forwardText: String) {

            add(InlineKeyboardButton(text, InlineKeyboardButtonTypeLoginUrl(url, id, forwardText)))

        }

        fun gameButton(text: String) {

            add(InlineKeyboardButton(text, InlineKeyboardButtonTypeCallbackGame()))

        }

        fun switchButton(text: String, query: String, inCurrentChat: Boolean = true) {

            add(InlineKeyboardButton(text, InlineKeyboardButtonTypeSwitchInline(query, inCurrentChat)))

        }

        fun dataButton(text: String, id: Int, vararg dataArray: ByteArray) {

            add(
                InlineKeyboardButton(
                    text,
                    InlineKeyboardButtonTypeCallback(mkData(id, *dataArray, randomSuffix = true))
                )
            )

        }

        fun textButton(text: String) {

            add(InlineKeyboardButton(text, InlineKeyboardButtonTypeCallback(byteArrayOf(-1))))

        }

    }

    fun newLine(atFirst: Boolean = false,block: (Line.() -> Unit)? = null): Line {

        return Line().apply {

            block?.invoke(this)

            if (atFirst) addFirst(this) else add(this)

        }

    }

    fun urlLine(text: String, url: String) = newLine().urlButton(text, url)

    fun loginUrlLine(text: String, url: String, id: Int, forwardText: String) =
        newLine().loginUrlButton(text, url, id, forwardText)

    fun gameLine(text: String) = newLine().gameButton(text)

    fun switchLine(text: String, query: String, inCurrentChat: Boolean = true) =
        newLine().switchButton(text, query, inCurrentChat)

    fun dataLine(text: String, id: Int, vararg dataArray: ByteArray) = newLine().dataButton(text, id, * dataArray)

    fun textLine(text: String) = newLine().textButton(text)

    override fun build(): ReplyMarkupInlineKeyboard {

        return ReplyMarkupInlineKeyboard(map { it.toTypedArray() }.toTypedArray())

    }

    override fun isEmpty(): Boolean {

        if (super.isEmpty()) return true

        forEach { if (!it.isEmpty()) return false }

        return true

    }

}


fun keyboardButton(block: KeyboardButtonBuilder.() -> Unit): ReplyMarkupShowKeyboard {

    return KeyboardButtonBuilder().apply(block).build()

}

class KeyboardButtonBuilder : LinkedList<KeyboardButtonBuilder.Line>(), Builder<ReplyMarkupShowKeyboard> {

    class Line : LinkedList<KeyboardButton>() {

        fun textButton(text: String) {

            add(KeyboardButton(text, KeyboardButtonTypeText()))

        }

        fun locationRequestButton(text: String) {

            add(KeyboardButton(text, KeyboardButtonTypeRequestLocation()))

        }

        fun phoneNumberRequestButton(text: String) {

            add(KeyboardButton(text, KeyboardButtonTypeRequestPhoneNumber()))

        }

    }

    fun newLine(block: (Line.() -> Unit)? = null): Line {

        return Line().apply {

            block?.invoke(this)

            add(this)

        }

    }

    fun textLine(text: String) = newLine().textButton(text)
    fun locationRequestLine(text: String) = newLine().locationRequestButton(text)
    fun phoneNumberRequestLine(text: String) = newLine().phoneNumberRequestButton(text)

    var resizeKeyboard = true

    var oneTime = true

    var isPersonal = true

    override fun build(): ReplyMarkupShowKeyboard {

        return ReplyMarkupShowKeyboard(
            map { it.toTypedArray() }.toTypedArray(),
            resizeKeyboard, oneTime, isPersonal
        )

    }

    override fun isEmpty(): Boolean {

        if (super.isEmpty()) return true

        forEach { if (!it.isEmpty()) return false }

        return true

    }

}

fun removeKeyboard(isPersonal: Boolean = true): ReplyMarkupRemoveKeyboard {

    return ReplyMarkupRemoveKeyboard(isPersonal)

}

fun forceReply(isPersonal: Boolean = true): ReplyMarkupForceReply {

    return ReplyMarkupForceReply(isPersonal)

}

fun inputText(textFormatted: FormattedText? = null, block: (TextBuilder.() -> Unit)? = null): InputMessageText {

    return TextBuilder(textFormatted).applyIfNot(block == null, block).build()

}

fun inputPlainText(text: String, block: (TextBuilder.() -> Unit)? = null): InputMessageText {

    return inputText(text.asText, block)
}

fun inputHtmlText(text: String, block: (TextBuilder.() -> Unit)? = null): InputMessageText {

    return inputText(text.asHtml, block)

}

fun inputMarkdownText(text: String, block: (TextBuilder.() -> Unit)? = null): InputMessageText {

    return inputText(text.asMarkdown, block)

}

class TextBuilder(var textFormatted: FormattedText? = null) : Builder<InputMessageText> {

    var text by WriteOnlyField<String> {

        textFormatted = it.asText

    }

    var textHtml by WriteOnlyField<String> {

        textFormatted = it.asHtml

    }

    var inputMarkdown by WriteOnlyField<String> {

        textFormatted = it.asMarkdown

    }

    var enableWebPagePreview = false

    var clearDraft = false

    override fun build(): InputMessageText {

        return InputMessageText(textFormatted!!, !enableWebPagePreview, clearDraft)

    }

}

fun inputForward(message: Message, block: (ForwardBuilder.() -> Unit)? = null) =
    inputForward(message.chatId, message.id, block)

fun inputForward(chatId: Number, messageId: Long, block: (ForwardBuilder.() -> Unit)? = null): InputMessageForwarded {

    val input = InputMessageForwarded(chatId.toLong(), messageId, false, MessageCopyOptions())

    return ForwardBuilder(input).applyIfNot(block == null, block).build()

}

class ForwardBuilder(val input: InputMessageForwarded) : Builder<InputMessageForwarded> {

    var chatId by input::fromChatId
    var messageId by input::messageId

    var inGameShare by input::inGameShare
    var copyOptions by input::copyOptions

    override fun build() = input
}

interface CaptionInterface {

    var caption: FormattedText?

    companion object : CaptionInterface {
        override var caption: FormattedText? = null
    }

}

const val WHEN_ONLINE = -1

class MessageFactory(val context: TdHandler) : CaptionInterface {

    lateinit var chatId: Number
    var messageId by Delegates.notNull<Long>()
    var input: InputMessageContent? = null

    var replyToMessageId = 0L
    var messageThreadId: Number = 0L

    infix fun replyAt(replyToMessageId: Long): MessageFactory {

        this.replyToMessageId = replyToMessageId

        return this

    }

    infix fun messageThread(messageThreadId: Number): MessageFactory {

        this.messageThreadId = messageThreadId

        return this

    }

    var disableNotification = false

    infix fun disNtf(disableNotification: Boolean): MessageFactory {

        this.disableNotification = disableNotification

        return this

    }

    var fromBackground = false

    infix fun fromBack(fromBackground: Boolean): MessageFactory {

        this.fromBackground = fromBackground

        return this

    }

    var replyMarkup: ReplyMarkup? = null

    infix fun withMarkup(replyMarkup: ReplyMarkup): MessageFactory {

        this.replyMarkup = replyMarkup

        return this

    }

    var schedulingState: MessageSchedulingState? = null

    var sendAt by WriteOnlyField<Int> {

        schedulingState = if (it > 0) MessageSchedulingStateSendAtDate(it) else MessageSchedulingStateSendWhenOnline()

    }

    infix fun sendAt(date: Number): MessageFactory {

        sendAt = date.toInt()

        return this

    }

    infix fun at(messageId: Long): MessageFactory {

        this.messageId = messageId

        return this

    }

    infix fun atCaption(messageId: Long): MessageFactory {

        at(messageId)
        this.caption = (input as InputMessageText).text
        input = null

        return this

    }

    infix fun at(message: Message): MessageFactory {

        this.chatId = message.chatId
        this.messageId = message.id

        return this

    }

    infix fun atCaption(message: Message): MessageFactory {

        at(message)
        this.caption = (input as InputMessageText).text
        input = null

        return this

    }


    infix fun to(chatId: Number): MessageFactory {

        this.chatId = chatId
        return this

    }


    var inputText by WriteOnlyField<String> {

        input = inputPlainText(it)

    }

    var inputHtml by WriteOnlyField<String> {

        input = inputHtmlText(it)

    }

    var inputMarkdown by WriteOnlyField<String> {

        input = inputMarkdownText(it)

    }

    var inputPhoto by WriteOnlyField<String> {

        input = photo(it) { _captionInterface = this }

    }

    var inputPhotoId by WriteOnlyField<String> {

        input = photoId(it) { _captionInterface = this }

    }

    var inputVideo by WriteOnlyField<String> {

        input = video(it) { _captionInterface = this }

    }

    var inputVideoId by WriteOnlyField<String> {

        input = videoId(it) { _captionInterface = this }

    }

    var inputFile by WriteOnlyField<String> {

        input = file(it) { _captionInterface = this }

    }

    var inputFileId by WriteOnlyField<String> {

        input = fileId(it) { _captionInterface = this }

    }

    var inputForward by WriteOnlyField<Message> {

        input = inputForward(it)

    }

    private var _captionInterface: CaptionInterface = CaptionInterface

    override var caption
        get() = _captionInterface.caption
        set(value) = _captionInterface::caption.set(value)

    var CaptionInterface.captionText by WriteOnlyField<String> {

        caption = it.asText

    }

    var CaptionInterface.captionHtml by WriteOnlyField<String> {

        caption = it.asHtml

    }

    var CaptionInterface.captionMarkdown by WriteOnlyField<String> {

        caption = it.asMarkdown

    }

    infix fun caption(caption: FormattedText): MessageFactory {

        this.caption = caption

        return this

    }

    infix fun captionText(caption: String): MessageFactory {

        this.captionText = caption

        return this

    }


    infix fun captionHtml(caption: String): MessageFactory {

        this.captionHtml = caption

        return this

    }

    infix fun captionMd(caption: String): MessageFactory {

        this.captionMarkdown = caption

        return this

    }

    class PhotoBuilder(val photo: InputMessagePhoto) : CaptionInterface {

        var thumbnail by photo::thumbnail

        var addedStickerFileIds by photo::addedStickerFileIds

        var width by photo::width

        var height by photo::height

        var ttl by photo::ttl

        override var caption: FormattedText? by photo::caption

    }

    fun photo(path: String, block: (PhotoBuilder.() -> Unit)? = null): InputMessagePhoto {

        return InputMessagePhoto().apply {

            photo = InputFileLocal(path)

            block?.invoke(PhotoBuilder((this)))

        }

    }

    fun photoId(fileId: String, block: (PhotoBuilder.() -> Unit)? = null): InputMessagePhoto {

        return InputMessagePhoto().apply {

            photo = InputFileRemote(fileId)

            block?.invoke(PhotoBuilder((this)))

        }

    }

    class VideoBuilder(val video: InputMessageVideo) : CaptionInterface {

        var thumbnail by video::thumbnail

        var addedStickerFileIds by video::addedStickerFileIds

        var duration by video::duration

        var width by video::width

        var height by video::height

        var supportsStreaming by video::supportsStreaming

        var ttl by video::ttl

        override var caption: FormattedText? by video::caption

    }

    fun video(path: String, block: (VideoBuilder.() -> Unit)? = null): InputMessageVideo {

        return InputMessageVideo().apply {

            video = InputFileLocal(path)

            block?.invoke(VideoBuilder((this)))

        }

    }

    fun videoId(fileId: String, block: (VideoBuilder.() -> Unit)? = null): InputMessageVideo {

        return InputMessageVideo().apply {

            video = InputFileRemote(fileId)

            block?.invoke(VideoBuilder((this)))

        }

    }

    class FileBuilder(val file: InputMessageDocument) : CaptionInterface {

        var document: InputFile? by file::document

        var thumbnail: InputThumbnail? by file::thumbnail

        override var caption: FormattedText? by file::caption

    }

    fun file(
        file: String,
        forceDocument: Boolean = false,
        block: (FileBuilder.() -> Unit)? = null
    ): InputMessageDocument {

        return InputMessageDocument(InputFileLocal(file), null, forceDocument, null).applyIfNot(block == null) {

            block?.invoke(FileBuilder(this))

        }

    }

    fun fileId(
        fileId: String,
        forceDocument: Boolean = false,
        block: (FileBuilder.() -> Unit)? = null
    ): InputMessageDocument {

        return InputMessageDocument(InputFileRemote(fileId), null, forceDocument, null).applyIfNot(block == null) {

            block?.invoke(FileBuilder(this))

        }

    }

    private fun mkOptions(): MessageSendOptions {

        return MessageSendOptions(disableNotification, fromBackground, schedulingState)

    }

    infix fun mkSend(chatId: Number) =
        SendMessage(chatId.toLong(), messageThreadId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input)

    fun mkSend() =
        SendMessage(chatId.toLong(), messageThreadId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input)

    fun mkEdit(chatId: Number, messageId: Long) = EditMessageText(chatId.toLong(), messageId, replyMarkup, input)
    infix fun mkEditTo(chatId: Number) = EditMessageText(chatId.toLong(), messageId, replyMarkup, input)
    infix fun mkEditAt(messageId: Long) = EditMessageText(chatId.toLong(), messageId, replyMarkup, input)
    fun mkEdit() = EditMessageText(chatId.toLong(), messageId, replyMarkup, input)

    var onSuccess: (suspend CoroutineScope.(Message) -> Unit)? = null
    var onFailure: (suspend CoroutineScope.(TdException) -> Unit)? = null

    infix fun onSuccess(callback: (suspend CoroutineScope.(Message) -> Unit)?): MessageFactory {

        onSuccess = callback

        return this

    }

    infix fun onFailure(callback: (suspend CoroutineScope.(TdException) -> Unit)?): MessageFactory {

        onFailure = callback

        return this

    }

    suspend infix fun syncTo(chatId: Number): Message {

        return context.sync(mkSend(chatId))

    }

    suspend infix fun syncReplyTo(replyToMessage: Message): Message {

        this.replyToMessageId = replyToMessage.id

        return this syncTo replyToMessage.chatId

    }

    infix fun sendTo(chatId: Number) {

        val successCallback = onSuccess
        val failureCallback = onFailure

        context.send<Message>(
            SendMessage(
                chatId.toLong(),
                messageThreadId.toLong(),
                replyToMessageId,
                mkOptions(),
                replyMarkup,
                input
            ), 1
        ) {

            onSuccess = successCallback

            if (failureCallback != null) {

                onFailure = failureCallback

            }

        }

    }

    infix fun replyTo(replyToMessage: Message) {

        this.replyToMessageId = replyToMessage.id

        this sendTo replyToMessage.chatId

    }

    var edit = false

    infix fun edit(edit: Boolean): MessageFactory {

        this.edit = edit

        return this

    }

    suspend infix fun syncOrEditTo(chatId: Number) {

        suspendCoroutine { continuation: Continuation<Unit> ->

            val oldSuccessHandler = onSuccess
            val oldFailureHandler = onFailure

            onSuccess {

                oldSuccessHandler?.invoke(this, it)

                continuation.resume(Unit)

            }

            onFailure {
                oldFailureHandler?.invoke(this, it)
                continuation.resume(Unit)
            }

            sendOrEditTo(chatId)

        }

    }

    infix fun sendOrEditTo(chatId: Number) = if (!edit) sendTo(chatId) else editTo(chatId)

    suspend fun syncEditTo(chatId: Number, messageId: Long): Message {
        val messageOld = context.getMessage(chatId.toLong(), messageId)
        return context.sync(
            when {
                messageOld.content is MessageText && input is InputMessageText -> EditMessageText(
                    chatId.toLong(),
                    messageId,
                    replyMarkup,
                    input
                )
                input != null -> EditMessageMedia(chatId.toLong(), messageId, replyMarkup, input)
                caption != null -> EditMessageCaption(chatId.toLong(), messageId, replyMarkup, caption)
                else -> EditMessageReplyMarkup(chatId.toLong(), messageId, replyMarkup)
            }
        )
    }

    suspend infix fun syncEditTo(chatId: Number) = syncEditTo(chatId, messageId)
    suspend infix fun syncEditAt(messageId: Long) = syncEditTo(chatId, messageId)
    suspend infix fun syncEditTo(message: Message) = syncEditTo(message.chatId, message.id)

    fun editTo(chatId: Number, messageId: Long) {
        val failureCallback = onFailure

        context.getMessageWith(chatId.toLong(), messageId) {
            onSuccess {
                editTo(it)
            }
            if (failureCallback != null) {
                onFailure = failureCallback
            }
        }

    }

    infix fun editTo(chatId: Number) = editTo(chatId, messageId)

    infix fun editAt(messageId: Long) = editTo(chatId, messageId)

    infix fun editOrSendToChat(message: Message) = if (message.canBeEdited) editTo(message) else sendTo(message.chatId)

    infix fun editTo(message: Message) {
        this at message

        val successCallback = onSuccess
        val failureCallback = onFailure

        context.send<Message>(
            when {
                message.content is MessageText && input is InputMessageText -> EditMessageText(
                    chatId.toLong(),
                    messageId,
                    replyMarkup,
                    input
                )
                input != null -> EditMessageMedia(chatId.toLong(), messageId, replyMarkup, input)
                caption != null -> EditMessageCaption(chatId.toLong(), messageId, replyMarkup, caption)
                else -> EditMessageReplyMarkup(chatId.toLong(), messageId, replyMarkup)
            }, 1
        ) {
            onSuccess = successCallback
            if (failureCallback != null) {
                onFailure = failureCallback
            }

        }
    }

    infix fun errorTo(replyToMessage: Message) {

        if (!replyToMessage.fromPrivate) {

            this.replyToMessageId = replyToMessage.id

        }

        onSuccess(context.deleteDelay(replyToMessage))

        this sendTo replyToMessage.chatId

    }

}


operator fun FormattedText.plus(text: FormattedText): FormattedText {

    val result = FormattedText(this.text + text.text, entities.takeIf { it.isNotEmpty() } ?: arrayOf())

    if (text.entities.isNotEmpty()) {

        text.entities.forEach {

            it.offset += this.text.length

        }

        ArrayUtil.append(result.entities, text, entities)

    }

    return result

}