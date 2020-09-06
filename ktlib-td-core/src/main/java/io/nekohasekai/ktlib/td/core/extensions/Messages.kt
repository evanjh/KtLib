@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.extensions

import td.TdApi.*

val Message.fromPrivate get() = chatId > 0L
val Message.fromBasicGroup get() = chatId > -1000000000000L && chatId < 0
val Message.fromSuperGroup get() = chatId < -1000000000000L && !isChannelPost
val Message.fromChannel get() = isChannelPost
val Message.fromGroup get() = fromBasicGroup || fromSuperGroup

val Message.isServiceMessage
    get() = when (content) {
        is MessageBasicGroupChatCreate,
        is MessageSupergroupChatCreate,
        is MessageChatChangeTitle,
        is MessageChatChangePhoto,
        is MessageChatDeletePhoto,
        is MessageChatAddMembers,
        is MessageChatJoinByLink,
        is MessageChatDeleteMember,
        is MessageChatUpgradeTo,
        is MessageChatUpgradeFrom,
        is MessagePinMessage,
        is MessageCustomServiceAction,
        is MessageGameScore -> true
        else -> false
    }


val Message.text get() = with(content) { if (this is MessageText) text.text else null }

val Message.entities get() = with(content) { if (this is MessageText) text.entities else null }

val MessageContent.asInput: InputMessageContent?
    get() = when (this) {

        is MessageText -> InputMessageText(text, webPage == null, false)

        is MessageAnimation -> {

            val file = InputFileRemote(animation.animation.remote.id!!)
            val thumbnail = if (animation.thumbnail == null) null else InputThumbnail(InputFileRemote(animation.thumbnail!!.file.remote.id), animation.thumbnail!!.width, animation.thumbnail!!.height)

            InputMessageAnimation(file, thumbnail, intArrayOf(), animation.duration, animation.width, animation.height, caption)

        }

        is MessageAudio -> {

            val file = InputFileRemote(audio.audio.remote.id)
            val thumbnail = if (audio.albumCoverThumbnail == null) null else InputThumbnail(InputFileRemote(audio.albumCoverThumbnail!!.file.remote.id), audio.albumCoverThumbnail!!.width, audio.albumCoverThumbnail!!.height)

            InputMessageAudio(file, thumbnail, audio.duration, audio.title, audio.performer, caption)

        }

        is MessageDocument -> {

            val file = InputFileRemote(document.document.remote.id)
            val thumbnail = if (document.thumbnail == null) null else InputThumbnail(InputFileRemote(document.thumbnail!!.file.remote.id), document.thumbnail!!.width, document.thumbnail!!.height)

            InputMessageDocument(file, thumbnail, true, caption)

        }

        is MessagePhoto -> {

            val file = InputFileRemote(photo.sizes[0].photo.remote.id)

            InputMessagePhoto(file, null, intArrayOf(), photo.sizes[0].width, photo.sizes[0].height, caption, 0)

        }

        is MessageSticker -> {

            val file = InputFileRemote(sticker.sticker.remote.id)
            val thumbnail = if (sticker.thumbnail == null) null else InputThumbnail(InputFileRemote(sticker.thumbnail!!.file.remote.id), sticker.thumbnail!!.width, sticker.thumbnail!!.height)

            InputMessageSticker(file, thumbnail, sticker.width, sticker.height)

        }

        is MessageVideo -> {

            val file = InputFileRemote(video.video.remote.id)
            val thumbnail = if (video.thumbnail == null) null else InputThumbnail(InputFileRemote(video.thumbnail!!.file.remote.id), video.thumbnail!!.width, video.thumbnail!!.height)

            InputMessageVideo(file, thumbnail, intArrayOf(), video.duration, video.width, video.height, video.supportsStreaming, caption, 0)

        }

        is MessageVideoNote -> {

            val file = InputFileRemote(videoNote.video.remote.id)
            val thumbnail = if (videoNote.thumbnail == null) null else InputThumbnail(InputFileRemote(videoNote.thumbnail!!.file.remote.id), videoNote.thumbnail!!.width, videoNote.thumbnail!!.height)

            InputMessageVideoNote(file, thumbnail, videoNote.duration, videoNote.length)

        }

        is MessageLocation -> InputMessageLocation(location, livePeriod)

        is MessageVoiceNote -> {

            val file = InputFileRemote(voiceNote.voice.remote.id)

            InputMessageVoiceNote(file, voiceNote.duration, voiceNote.waveform, caption)

        }

        is MessageVenue -> InputMessageVenue(venue)
        is MessageContact -> InputMessageContact(contact)

        else -> null

    }

val Message.asInput get() = content.asInput

val Message.asForward get() = InputMessageForwarded(chatId, id, false, MessageCopyOptions())

val Message.asInputOrForward: InputMessageContent
    get() {

        fun value() = InputMessageForwarded(chatId, id, false, MessageCopyOptions())

        if (forwardInfo != null) return value()

        return content.asInput ?: value()

    }