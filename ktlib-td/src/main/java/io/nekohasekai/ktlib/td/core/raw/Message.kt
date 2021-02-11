@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns information about a message
 *
 * @chatId - Identifier of the chat the message belongs to
 * @messageId - Identifier of the message to get
 */
suspend fun TdHandler.getMessage(
    chatId: Long,
    messageId: Long
) = sync(GetMessage(chatId, messageId))

suspend fun TdHandler.getMessageOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull(GetMessage(chatId, messageId))

fun TdHandler.getMessageWith(
    chatId: Long,
    messageId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(GetMessage(chatId, messageId), stackIgnore + 1, submit)

/**
 * Returns information about a message, if it is available locally without sending network request
 * This is an offline request
 *
 * @chatId - Identifier of the chat the message belongs to
 * @messageId - Identifier of the message to get
 */
suspend fun TdHandler.getMessageLocally(
    chatId: Long,
    messageId: Long
) = sync(GetMessageLocally(chatId, messageId))

suspend fun TdHandler.getMessageLocallyOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull(GetMessageLocally(chatId, messageId))

fun TdHandler.getMessageLocallyWith(
    chatId: Long,
    messageId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(GetMessageLocally(chatId, messageId), stackIgnore + 1, submit)

/**
 * Returns information about a message that is replied by a given message
 * Also returns the pinned message, the game message, and the invoice message for messages of the types messagePinMessage, messageGameScore, and messagePaymentSuccessful respectively
 *
 * @chatId - Identifier of the chat the message belongs to
 * @messageId - Identifier of the message reply to which to get
 */
suspend fun TdHandler.getRepliedMessage(
    chatId: Long,
    messageId: Long
) = sync(GetRepliedMessage(chatId, messageId))

suspend fun TdHandler.getRepliedMessageOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull(GetRepliedMessage(chatId, messageId))

fun TdHandler.getRepliedMessageWith(
    chatId: Long,
    messageId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(GetRepliedMessage(chatId, messageId), stackIgnore + 1, submit)

/**
 * Returns information about a newest pinned message in the chat
 *
 * @chatId - Identifier of the chat the message belongs to
 */
suspend fun TdHandler.getChatPinnedMessage(
    chatId: Long
) = sync(GetChatPinnedMessage(chatId))

suspend fun TdHandler.getChatPinnedMessageOrNull(
    chatId: Long
) = syncOrNull(GetChatPinnedMessage(chatId))

fun TdHandler.getChatPinnedMessageWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(GetChatPinnedMessage(chatId), stackIgnore + 1, submit)

/**
 * Returns information about a message with the callback button that originated a callback query
 * For bots only
 *
 * @chatId - Identifier of the chat the message belongs to
 * @messageId - Message identifier
 * @callbackQueryId - Identifier of the callback query
 */
suspend fun TdHandler.getCallbackQueryMessage(
    chatId: Long,
    messageId: Long,
    callbackQueryId: Long
) = sync(GetCallbackQueryMessage(chatId, messageId, callbackQueryId))

suspend fun TdHandler.getCallbackQueryMessageOrNull(
    chatId: Long,
    messageId: Long,
    callbackQueryId: Long
) = syncOrNull(GetCallbackQueryMessage(chatId, messageId, callbackQueryId))

fun TdHandler.getCallbackQueryMessageWith(
    chatId: Long,
    messageId: Long,
    callbackQueryId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(GetCallbackQueryMessage(chatId, messageId, callbackQueryId), stackIgnore + 1, submit)

/**
 * Returns information about messages
 * If a message is not found, returns null on the corresponding position of the result
 *
 * @chatId - Identifier of the chat the messages belong to
 * @messageIds - Identifiers of the messages to get
 */
suspend fun TdHandler.getMessages(
    chatId: Long,
    messageIds: LongArray
) = sync(GetMessages(chatId, messageIds))

suspend fun TdHandler.getMessagesOrNull(
    chatId: Long,
    messageIds: LongArray
) = syncOrNull(GetMessages(chatId, messageIds))

fun TdHandler.getMessagesWith(
    chatId: Long,
    messageIds: LongArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(GetMessages(chatId, messageIds), stackIgnore + 1, submit)

/**
 * Returns information about a message thread
 * Can be used only if message.can_get_message_thread == true
 *
 * @chatId - Chat identifier
 * @messageId - Identifier of the message
 */
suspend fun TdHandler.getMessageThread(
    chatId: Long,
    messageId: Long
) = sync(GetMessageThread(chatId, messageId))

suspend fun TdHandler.getMessageThreadOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull(GetMessageThread(chatId, messageId))

fun TdHandler.getMessageThreadWith(
    chatId: Long,
    messageId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<MessageThreadInfo>.() -> Unit)? = null
) = send(GetMessageThread(chatId, messageId), stackIgnore + 1, submit)

/**
 * Returns messages in a chat
 * The messages are returned in a reverse chronological order (i.e., in order of decreasing message_id)
 * For optimal performance the number of returned messages is chosen by the library
 * This is an offline request if only_local is true
 *
 * @chatId - Chat identifier
 * @fromMessageId - Identifier of the message starting from which history must be fetched
 *                  Use 0 to get results from the last message
 * @offset - Specify 0 to get results from exactly the from_message_id or a negative offset up to 99 to get additionally some newer messages
 * @limit - The maximum number of messages to be returned
 *          Must be positive and can't be greater than 100
 *          If the offset is negative, the limit must be greater than or equal to -offset
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 * @onlyLocal - If true, returns only messages that are available locally without sending network requests
 */
suspend fun TdHandler.getChatHistory(
    chatId: Long,
    fromMessageId: Long,
    offset: Int,
    limit: Int,
    onlyLocal: Boolean
) = sync(GetChatHistory(chatId, fromMessageId, offset, limit, onlyLocal))

suspend fun TdHandler.getChatHistoryOrNull(
    chatId: Long,
    fromMessageId: Long,
    offset: Int,
    limit: Int,
    onlyLocal: Boolean
) = syncOrNull(GetChatHistory(chatId, fromMessageId, offset, limit, onlyLocal))

fun TdHandler.getChatHistoryWith(
    chatId: Long,
    fromMessageId: Long,
    offset: Int,
    limit: Int,
    onlyLocal: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(GetChatHistory(chatId, fromMessageId, offset, limit, onlyLocal), stackIgnore + 1, submit)

/**
 * Returns messages in a message thread of a message
 * Can be used only if message.can_get_message_thread == true
 * Message thread of a channel message is in the channel's linked supergroup
 * The messages are returned in a reverse chronological order (i.e., in order of decreasing message_id)
 * For optimal performance the number of returned messages is chosen by the library
 *
 * @chatId - Chat identifier
 * @messageId - Message identifier, which thread history needs to be returned
 * @fromMessageId - Identifier of the message starting from which history must be fetched
 *                  Use 0 to get results from the last message
 * @offset - Specify 0 to get results from exactly the from_message_id or a negative offset up to 99 to get additionally some newer messages
 * @limit - The maximum number of messages to be returned
 *          Must be positive and can't be greater than 100
 *          If the offset is negative, the limit must be greater than or equal to -offset
 *          Fewer messages may be returned than specified by the limit, even if the end of the message thread history has not been reached
 */
suspend fun TdHandler.getMessageThreadHistory(
    chatId: Long,
    messageId: Long,
    fromMessageId: Long,
    offset: Int,
    limit: Int
) = sync(GetMessageThreadHistory(chatId, messageId, fromMessageId, offset, limit))

suspend fun TdHandler.getMessageThreadHistoryOrNull(
    chatId: Long,
    messageId: Long,
    fromMessageId: Long,
    offset: Int,
    limit: Int
) = syncOrNull(GetMessageThreadHistory(chatId, messageId, fromMessageId, offset, limit))

fun TdHandler.getMessageThreadHistoryWith(
    chatId: Long,
    messageId: Long,
    fromMessageId: Long,
    offset: Int,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(GetMessageThreadHistory(chatId, messageId, fromMessageId, offset, limit), stackIgnore + 1, submit)

/**
 * Searches for messages with given words in the chat
 * Returns the results in reverse chronological order, i.e
 * In order of decreasing message_id
 * Cannot be used in secret chats with a non-empty query (searchSecretMessages should be used instead), or without an enabled message database
 * For optimal performance the number of returned messages is chosen by the library
 *
 * @chatId - Identifier of the chat in which to search messages
 * @query - Query to search for
 * @sender - If not null, only messages sent by the specified sender will be returned
 *           Not supported in secret chats
 * @fromMessageId - Identifier of the message starting from which history must be fetched
 *                  Use 0 to get results from the last message
 * @offset - Specify 0 to get results from exactly the from_message_id or a negative offset to get the specified message and some newer messages
 * @limit - The maximum number of messages to be returned
 *          Must be positive and can't be greater than 100
 *          If the offset is negative, the limit must be greater than -offset
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 * @filter - Filter for message content in the search results
 * @messageThreadId - If not 0, only messages in the specified thread will be returned
 *                    Supergroups only
 */
suspend fun TdHandler.searchChatMessages(
    chatId: Long,
    query: String? = null,
    sender: MessageSender? = null,
    fromMessageId: Long,
    offset: Int,
    limit: Int,
    filter: SearchMessagesFilter? = null,
    messageThreadId: Long
) = sync(SearchChatMessages(chatId, query, sender, fromMessageId, offset, limit, filter, messageThreadId))

suspend fun TdHandler.searchChatMessagesOrNull(
    chatId: Long,
    query: String? = null,
    sender: MessageSender? = null,
    fromMessageId: Long,
    offset: Int,
    limit: Int,
    filter: SearchMessagesFilter? = null,
    messageThreadId: Long
) = syncOrNull(SearchChatMessages(chatId, query, sender, fromMessageId, offset, limit, filter, messageThreadId))

fun TdHandler.searchChatMessagesWith(
    chatId: Long,
    query: String? = null,
    sender: MessageSender? = null,
    fromMessageId: Long,
    offset: Int,
    limit: Int,
    filter: SearchMessagesFilter? = null,
    messageThreadId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(SearchChatMessages(chatId, query, sender, fromMessageId, offset, limit, filter, messageThreadId), stackIgnore + 1, submit)

/**
 * Searches for messages in all chats except secret chats
 * Returns the results in reverse chronological order (i.e., in order of decreasing (date, chat_id, message_id))
 * For optimal performance the number of returned messages is chosen by the library
 *
 * @chatList - Chat list in which to search messages
 *             Pass null to search in all chats regardless of their chat list
 * @query - Query to search for
 * @offsetDate - The date of the message starting from which the results should be fetched
 *               Use 0 or any date in the future to get results from the last message
 * @offsetChatId - The chat identifier of the last found message, or 0 for the first request
 * @offsetMessageId - The message identifier of the last found message, or 0 for the first request
 * @limit - The maximum number of messages to be returned
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 * @filter - Filter for message content in the search results
 *           SearchMessagesFilterCall, searchMessagesFilterMissedCall, searchMessagesFilterMention, searchMessagesFilterUnreadMention, searchMessagesFilterFailedToSend and searchMessagesFilterPinned are unsupported in this function
 * @minDate - If not 0, the minimum date of the messages to return
 * @maxDate - If not 0, the maximum date of the messages to return
 */
suspend fun TdHandler.searchMessages(
    chatList: ChatList? = null,
    query: String? = null,
    offsetDate: Int,
    offsetChatId: Long,
    offsetMessageId: Long,
    limit: Int,
    filter: SearchMessagesFilter? = null,
    minDate: Int,
    maxDate: Int
) = sync(SearchMessages(chatList, query, offsetDate, offsetChatId, offsetMessageId, limit, filter, minDate, maxDate))

suspend fun TdHandler.searchMessagesOrNull(
    chatList: ChatList? = null,
    query: String? = null,
    offsetDate: Int,
    offsetChatId: Long,
    offsetMessageId: Long,
    limit: Int,
    filter: SearchMessagesFilter? = null,
    minDate: Int,
    maxDate: Int
) = syncOrNull(SearchMessages(chatList, query, offsetDate, offsetChatId, offsetMessageId, limit, filter, minDate, maxDate))

fun TdHandler.searchMessagesWith(
    chatList: ChatList? = null,
    query: String? = null,
    offsetDate: Int,
    offsetChatId: Long,
    offsetMessageId: Long,
    limit: Int,
    filter: SearchMessagesFilter? = null,
    minDate: Int,
    maxDate: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(SearchMessages(chatList, query, offsetDate, offsetChatId, offsetMessageId, limit, filter, minDate, maxDate), stackIgnore + 1, submit)

/**
 * Searches for messages in secret chats
 * Returns the results in reverse chronological order
 * For optimal performance the number of returned messages is chosen by the library
 *
 * @chatId - Identifier of the chat in which to search
 *           Specify 0 to search in all secret chats
 * @query - Query to search for
 *          If empty, searchChatMessages should be used instead
 * @offset - Offset of the first entry to return as received from the previous request
 *           Use empty string to get first chunk of results
 * @limit - The maximum number of messages to be returned
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 * @filter - A filter for message content in the search results
 */
suspend fun TdHandler.searchSecretMessages(
    chatId: Long,
    query: String? = null,
    offset: String? = null,
    limit: Int,
    filter: SearchMessagesFilter? = null
) = sync(SearchSecretMessages(chatId, query, offset, limit, filter))

suspend fun TdHandler.searchSecretMessagesOrNull(
    chatId: Long,
    query: String? = null,
    offset: String? = null,
    limit: Int,
    filter: SearchMessagesFilter? = null
) = syncOrNull(SearchSecretMessages(chatId, query, offset, limit, filter))

fun TdHandler.searchSecretMessagesWith(
    chatId: Long,
    query: String? = null,
    offset: String? = null,
    limit: Int,
    filter: SearchMessagesFilter? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<FoundMessages>.() -> Unit)? = null
) = send(SearchSecretMessages(chatId, query, offset, limit, filter), stackIgnore + 1, submit)

/**
 * Searches for call messages
 * Returns the results in reverse chronological order (i
 * E., in order of decreasing message_id)
 * For optimal performance the number of returned messages is chosen by the library
 *
 * @fromMessageId - Identifier of the message from which to search
 *                  Use 0 to get results from the last message
 * @limit - The maximum number of messages to be returned
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 * @onlyMissed - If true, returns only messages with missed calls
 */
suspend fun TdHandler.searchCallMessages(
    fromMessageId: Long,
    limit: Int,
    onlyMissed: Boolean
) = sync(SearchCallMessages(fromMessageId, limit, onlyMissed))

suspend fun TdHandler.searchCallMessagesOrNull(
    fromMessageId: Long,
    limit: Int,
    onlyMissed: Boolean
) = syncOrNull(SearchCallMessages(fromMessageId, limit, onlyMissed))

fun TdHandler.searchCallMessagesWith(
    fromMessageId: Long,
    limit: Int,
    onlyMissed: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(SearchCallMessages(fromMessageId, limit, onlyMissed), stackIgnore + 1, submit)

/**
 * Returns information about the recent locations of chat members that were sent to the chat
 * Returns up to 1 location message per user
 *
 * @chatId - Chat identifier
 * @limit - The maximum number of messages to be returned
 */
suspend fun TdHandler.searchChatRecentLocationMessages(
    chatId: Long,
    limit: Int
) = sync(SearchChatRecentLocationMessages(chatId, limit))

suspend fun TdHandler.searchChatRecentLocationMessagesOrNull(
    chatId: Long,
    limit: Int
) = syncOrNull(SearchChatRecentLocationMessages(chatId, limit))

fun TdHandler.searchChatRecentLocationMessagesWith(
    chatId: Long,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(SearchChatRecentLocationMessages(chatId, limit), stackIgnore + 1, submit)

/**
 * Returns all active live locations that should be updated by the application
 * The list is persistent across application restarts only if the message database is used
 */
suspend fun TdHandler.getActiveLiveLocationMessages() = sync(GetActiveLiveLocationMessages())

suspend fun TdHandler.getActiveLiveLocationMessagesOrNull() = syncOrNull(GetActiveLiveLocationMessages())

fun TdHandler.getActiveLiveLocationMessagesWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(GetActiveLiveLocationMessages(), stackIgnore + 1, submit)

/**
 * Returns the last message sent in a chat no later than the specified date
 *
 * @chatId - Chat identifier
 * @date - Point in time (Unix timestamp) relative to which to search for messages
 */
suspend fun TdHandler.getChatMessageByDate(
    chatId: Long,
    date: Int
) = sync(GetChatMessageByDate(chatId, date))

suspend fun TdHandler.getChatMessageByDateOrNull(
    chatId: Long,
    date: Int
) = syncOrNull(GetChatMessageByDate(chatId, date))

fun TdHandler.getChatMessageByDateWith(
    chatId: Long,
    date: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(GetChatMessageByDate(chatId, date), stackIgnore + 1, submit)

/**
 * Returns all scheduled messages in a chat
 * The messages are returned in a reverse chronological order (i.e., in order of decreasing message_id)
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.getChatScheduledMessages(
    chatId: Long
) = sync(GetChatScheduledMessages(chatId))

suspend fun TdHandler.getChatScheduledMessagesOrNull(
    chatId: Long
) = syncOrNull(GetChatScheduledMessages(chatId))

fun TdHandler.getChatScheduledMessagesWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(GetChatScheduledMessages(chatId), stackIgnore + 1, submit)

/**
 * Returns forwarded copies of a channel message to different public channels
 * For optimal performance the number of returned messages is chosen by the library
 *
 * @chatId - Chat identifier of the message
 * @messageId - Message identifier
 * @offset - Offset of the first entry to return as received from the previous request
 *           Use empty string to get first chunk of results
 * @limit - The maximum number of messages to be returned
 *          Must be positive and can't be greater than 100
 *          Fewer messages may be returned than specified by the limit, even if the end of the list has not been reached
 */
suspend fun TdHandler.getMessagePublicForwards(
    chatId: Long,
    messageId: Long,
    offset: String? = null,
    limit: Int
) = sync(GetMessagePublicForwards(chatId, messageId, offset, limit))

suspend fun TdHandler.getMessagePublicForwardsOrNull(
    chatId: Long,
    messageId: Long,
    offset: String? = null,
    limit: Int
) = syncOrNull(GetMessagePublicForwards(chatId, messageId, offset, limit))

fun TdHandler.getMessagePublicForwardsWith(
    chatId: Long,
    messageId: Long,
    offset: String? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<FoundMessages>.() -> Unit)? = null
) = send(GetMessagePublicForwards(chatId, messageId, offset, limit), stackIgnore + 1, submit)

/**
 * Returns an HTTPS link to a message in a chat
 * Available only for already sent messages in supergroups and channels
 * This is an offline request
 *
 * @chatId - Identifier of the chat to which the message belongs
 * @messageId - Identifier of the message
 * @forAlbum - Pass true to create a link for the whole media album
 * @forComment - Pass true to create a link to the message as a channel post comment, or from a message thread
 */
suspend fun TdHandler.getMessageLink(
    chatId: Long,
    messageId: Long,
    forAlbum: Boolean,
    forComment: Boolean
) = sync(GetMessageLink(chatId, messageId, forAlbum, forComment))

suspend fun TdHandler.getMessageLinkOrNull(
    chatId: Long,
    messageId: Long,
    forAlbum: Boolean,
    forComment: Boolean
) = syncOrNull(GetMessageLink(chatId, messageId, forAlbum, forComment))

fun TdHandler.getMessageLinkWith(
    chatId: Long,
    messageId: Long,
    forAlbum: Boolean,
    forComment: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<MessageLink>.() -> Unit)? = null
) = send(GetMessageLink(chatId, messageId, forAlbum, forComment), stackIgnore + 1, submit)

/**
 * Returns information about a public or private message link
 *
 * @url - The message link in the format "https://t.me/c/...", or "tg://privatepost?...", or "https://t.me/username/...", or "tg://resolve?..."
 */
suspend fun TdHandler.getMessageLinkInfo(
    url: String? = null
) = sync(GetMessageLinkInfo(url))

suspend fun TdHandler.getMessageLinkInfoOrNull(
    url: String? = null
) = syncOrNull(GetMessageLinkInfo(url))

fun TdHandler.getMessageLinkInfoWith(
    url: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<MessageLinkInfo>.() -> Unit)? = null
) = send(GetMessageLinkInfo(url), stackIgnore + 1, submit)

/**
 * Sends a message
 * Returns the sent message
 *
 * @chatId - Target chat
 * @messageThreadId - If not 0, a message thread identifier in which the message will be sent
 * @replyToMessageId - Identifier of the message to reply to or 0
 * @options - Options to be used to send the message
 * @replyMarkup - Markup for replying to the message
 *                For bots only
 * @inputMessageContent - The content of the message to be sent
 */
suspend fun TdHandler.sendMessage(
    chatId: Long,
    messageThreadId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = sync(SendMessage(chatId, messageThreadId, replyToMessageId, options, replyMarkup, inputMessageContent))

suspend fun TdHandler.sendMessageOrNull(
    chatId: Long,
    messageThreadId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull(SendMessage(chatId, messageThreadId, replyToMessageId, options, replyMarkup, inputMessageContent))

fun TdHandler.sendMessageWith(
    chatId: Long,
    messageThreadId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(SendMessage(chatId, messageThreadId, replyToMessageId, options, replyMarkup, inputMessageContent), stackIgnore + 1, submit)

/**
 * Sends 2-10 messages grouped together into an album
 * Currently only audio, document, photo and video messages can be grouped into an album
 * Documents and audio files can be only grouped in an album with messages of the same type
 * Returns sent messages
 *
 * @chatId - Target chat
 * @messageThreadId - If not 0, a message thread identifier in which the messages will be sent
 * @replyToMessageId - Identifier of a message to reply to or 0
 * @options - Options to be used to send the messages
 * @inputMessageContents - Contents of messages to be sent
 *                         At most 10 messages can be added to an album
 */
suspend fun TdHandler.sendMessageAlbum(
    chatId: Long,
    messageThreadId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    inputMessageContents: Array<InputMessageContent>
) = sync(SendMessageAlbum(chatId, messageThreadId, replyToMessageId, options, inputMessageContents))

suspend fun TdHandler.sendMessageAlbumOrNull(
    chatId: Long,
    messageThreadId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    inputMessageContents: Array<InputMessageContent>
) = syncOrNull(SendMessageAlbum(chatId, messageThreadId, replyToMessageId, options, inputMessageContents))

fun TdHandler.sendMessageAlbumWith(
    chatId: Long,
    messageThreadId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    inputMessageContents: Array<InputMessageContent>,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(SendMessageAlbum(chatId, messageThreadId, replyToMessageId, options, inputMessageContents), stackIgnore + 1, submit)

/**
 * Invites a bot to a chat (if it is not yet a member) and sends it the /start command
 * Bots can't be invited to a private chat other than the chat with the bot
 * Bots can't be invited to channels (although they can be added as admins) and secret chats
 * Returns the sent message
 *
 * @botUserId - Identifier of the bot
 * @chatId - Identifier of the target chat
 * @parameter - A hidden parameter sent to the bot for deep linking purposes (https://core.telegram.org/bots#deep-linking)
 */
suspend fun TdHandler.sendBotStartMessage(
    botUserId: Int,
    chatId: Long,
    parameter: String? = null
) = sync(SendBotStartMessage(botUserId, chatId, parameter))

suspend fun TdHandler.sendBotStartMessageOrNull(
    botUserId: Int,
    chatId: Long,
    parameter: String? = null
) = syncOrNull(SendBotStartMessage(botUserId, chatId, parameter))

fun TdHandler.sendBotStartMessageWith(
    botUserId: Int,
    chatId: Long,
    parameter: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(SendBotStartMessage(botUserId, chatId, parameter), stackIgnore + 1, submit)

/**
 * Sends the result of an inline query as a message
 * Returns the sent message
 * Always clears a chat draft message
 *
 * @chatId - Target chat
 * @messageThreadId - If not 0, a message thread identifier in which the message will be sent
 * @replyToMessageId - Identifier of a message to reply to or 0
 * @options - Options to be used to send the message
 * @queryId - Identifier of the inline query
 * @resultId - Identifier of the inline result
 * @hideViaBot - If true, there will be no mention of a bot, via which the message is sent
 *               Can be used only for bots GetOption("animation_search_bot_username"), GetOption("photo_search_bot_username") and GetOption("venue_search_bot_username")
 */
suspend fun TdHandler.sendInlineQueryResultMessage(
    chatId: Long,
    messageThreadId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    queryId: Long,
    resultId: String? = null,
    hideViaBot: Boolean
) = sync(SendInlineQueryResultMessage(chatId, messageThreadId, replyToMessageId, options, queryId, resultId, hideViaBot))

suspend fun TdHandler.sendInlineQueryResultMessageOrNull(
    chatId: Long,
    messageThreadId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    queryId: Long,
    resultId: String? = null,
    hideViaBot: Boolean
) = syncOrNull(SendInlineQueryResultMessage(chatId, messageThreadId, replyToMessageId, options, queryId, resultId, hideViaBot))

fun TdHandler.sendInlineQueryResultMessageWith(
    chatId: Long,
    messageThreadId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    queryId: Long,
    resultId: String? = null,
    hideViaBot: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(SendInlineQueryResultMessage(chatId, messageThreadId, replyToMessageId, options, queryId, resultId, hideViaBot), stackIgnore + 1, submit)

/**
 * Forwards previously sent messages
 * Returns the forwarded messages in the same order as the message identifiers passed in message_ids
 * If a message can't be forwarded, null will be returned instead of the message
 *
 * @chatId - Identifier of the chat to which to forward messages
 * @fromChatId - Identifier of the chat from which to forward messages
 * @messageIds - Identifiers of the messages to forward
 *               Message identifiers must be in a strictly increasing order
 *               At most 100 messages can be forwarded simultaneously
 * @options - Options to be used to send the messages
 * @sendCopy - True, if content of the messages needs to be copied without links to the original messages
 *             Always true if the messages are forwarded to a secret chat
 * @removeCaption - True, if media caption of message copies needs to be removed
 *                  Ignored if send_copy is false
 */
suspend fun TdHandler.forwardMessages(
    chatId: Long,
    fromChatId: Long,
    messageIds: LongArray,
    options: MessageSendOptions? = null,
    sendCopy: Boolean,
    removeCaption: Boolean
) = sync(ForwardMessages(chatId, fromChatId, messageIds, options, sendCopy, removeCaption))

suspend fun TdHandler.forwardMessagesOrNull(
    chatId: Long,
    fromChatId: Long,
    messageIds: LongArray,
    options: MessageSendOptions? = null,
    sendCopy: Boolean,
    removeCaption: Boolean
) = syncOrNull(ForwardMessages(chatId, fromChatId, messageIds, options, sendCopy, removeCaption))

fun TdHandler.forwardMessagesWith(
    chatId: Long,
    fromChatId: Long,
    messageIds: LongArray,
    options: MessageSendOptions? = null,
    sendCopy: Boolean,
    removeCaption: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(ForwardMessages(chatId, fromChatId, messageIds, options, sendCopy, removeCaption), stackIgnore + 1, submit)

/**
 * Resends messages which failed to send
 * Can be called only for messages for which messageSendingStateFailed.can_retry is true and after specified in messageSendingStateFailed.retry_after time passed
 * If a message is re-sent, the corresponding failed to send message is deleted
 * Returns the sent messages in the same order as the message identifiers passed in message_ids
 * If a message can't be re-sent, null will be returned instead of the message
 *
 * @chatId - Identifier of the chat to send messages
 * @messageIds - Identifiers of the messages to resend
 *               Message identifiers must be in a strictly increasing order
 */
suspend fun TdHandler.resendMessages(
    chatId: Long,
    messageIds: LongArray
) = sync(ResendMessages(chatId, messageIds))

suspend fun TdHandler.resendMessagesOrNull(
    chatId: Long,
    messageIds: LongArray
) = syncOrNull(ResendMessages(chatId, messageIds))

fun TdHandler.resendMessagesWith(
    chatId: Long,
    messageIds: LongArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(ResendMessages(chatId, messageIds), stackIgnore + 1, submit)

/**
 * Changes the current TTL setting (sets a new self-destruct timer) in a secret chat and sends the corresponding message
 *
 * @chatId - Chat identifier
 * @ttl - New TTL value, in seconds
 */
suspend fun TdHandler.sendChatSetTtlMessage(
    chatId: Long,
    ttl: Int
) = sync(SendChatSetTtlMessage(chatId, ttl))

suspend fun TdHandler.sendChatSetTtlMessageOrNull(
    chatId: Long,
    ttl: Int
) = syncOrNull(SendChatSetTtlMessage(chatId, ttl))

fun TdHandler.sendChatSetTtlMessageWith(
    chatId: Long,
    ttl: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(SendChatSetTtlMessage(chatId, ttl), stackIgnore + 1, submit)

/**
 * Adds a local message to a chat
 * The message is persistent across application restarts only if the message database is used
 * Returns the added message
 *
 * @chatId - Target chat
 * @sender - The sender sender of the message
 * @replyToMessageId - Identifier of the message to reply to or 0
 * @disableNotification - Pass true to disable notification for the message
 * @inputMessageContent - The content of the message to be added
 */
suspend fun TdHandler.addLocalMessage(
    chatId: Long,
    sender: MessageSender? = null,
    replyToMessageId: Long,
    disableNotification: Boolean,
    inputMessageContent: InputMessageContent? = null
) = sync(AddLocalMessage(chatId, sender, replyToMessageId, disableNotification, inputMessageContent))

suspend fun TdHandler.addLocalMessageOrNull(
    chatId: Long,
    sender: MessageSender? = null,
    replyToMessageId: Long,
    disableNotification: Boolean,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull(AddLocalMessage(chatId, sender, replyToMessageId, disableNotification, inputMessageContent))

fun TdHandler.addLocalMessageWith(
    chatId: Long,
    sender: MessageSender? = null,
    replyToMessageId: Long,
    disableNotification: Boolean,
    inputMessageContent: InputMessageContent? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(AddLocalMessage(chatId, sender, replyToMessageId, disableNotification, inputMessageContent), stackIgnore + 1, submit)

/**
 * Deletes messages
 *
 * @chatId - Chat identifier
 * @messageIds - Identifiers of the messages to be deleted
 * @revoke - Pass true to try to delete messages for all chat members
 *           Always true for supergroups, channels and secret chats
 */
suspend fun TdHandler.deleteMessages(
    chatId: Long,
    messageIds: LongArray,
    revoke: Boolean
){
    sync(DeleteMessages(chatId, messageIds, revoke))
}


suspend fun TdHandler.deleteMessagesIgnoreException(
    chatId: Long,
    messageIds: LongArray,
    revoke: Boolean
){
    syncOrNull(DeleteMessages(chatId, messageIds, revoke))
}


fun TdHandler.deleteMessagesWith(
    chatId: Long,
    messageIds: LongArray,
    revoke: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteMessages(chatId, messageIds, revoke), stackIgnore + 1, submit)

/**
 * Edits the text of a message (or a text of a game message)
 * Returns the edited message after the edit is completed on the server side
 *
 * @chatId - The chat the message belongs to
 * @messageId - Identifier of the message
 * @replyMarkup - The new message reply markup
 *                For bots only
 * @inputMessageContent - New text content of the message
 *                        Should be of type InputMessageText
 */
suspend fun TdHandler.editMessageText(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = sync(EditMessageText(chatId, messageId, replyMarkup, inputMessageContent))

suspend fun TdHandler.editMessageTextOrNull(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull(EditMessageText(chatId, messageId, replyMarkup, inputMessageContent))

fun TdHandler.editMessageTextWith(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(EditMessageText(chatId, messageId, replyMarkup, inputMessageContent), stackIgnore + 1, submit)

/**
 * Edits the message content of a live location
 * Messages can be edited for a limited period of time specified in the live location
 * Returns the edited message after the edit is completed on the server side
 *
 * @chatId - The chat the message belongs to
 * @messageId - Identifier of the message
 * @replyMarkup - The new message reply markup
 *                For bots only
 * @location - New location content of the message
 *             Pass null to stop sharing the live location
 * @heading - The new direction in which the location moves, in degrees
 *            Pass 0 if unknown
 * @proximityAlertRadius - The new maximum distance for proximity alerts, in meters (0-100000)
 *                         Pass 0 if the notification is disabled
 */
suspend fun TdHandler.editMessageLiveLocation(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null,
    heading: Int,
    proximityAlertRadius: Int
) = sync(EditMessageLiveLocation(chatId, messageId, replyMarkup, location, heading, proximityAlertRadius))

suspend fun TdHandler.editMessageLiveLocationOrNull(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null,
    heading: Int,
    proximityAlertRadius: Int
) = syncOrNull(EditMessageLiveLocation(chatId, messageId, replyMarkup, location, heading, proximityAlertRadius))

fun TdHandler.editMessageLiveLocationWith(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null,
    heading: Int,
    proximityAlertRadius: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(EditMessageLiveLocation(chatId, messageId, replyMarkup, location, heading, proximityAlertRadius), stackIgnore + 1, submit)

/**
 * Edits the content of a message with an animation, an audio, a document, a photo or a video
 * The media in the message can't be replaced if the message was set to self-destruct
 * Media can't be replaced by self-destructing media
 * Media in an album can be edited only to contain a photo or a video
 * Returns the edited message after the edit is completed on the server side
 *
 * @chatId - The chat the message belongs to
 * @messageId - Identifier of the message
 * @replyMarkup - The new message reply markup
 *                For bots only
 * @inputMessageContent - New content of the message
 *                        Must be one of the following types: InputMessageAnimation, InputMessageAudio, InputMessageDocument, InputMessagePhoto or InputMessageVideo
 */
suspend fun TdHandler.editMessageMedia(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = sync(EditMessageMedia(chatId, messageId, replyMarkup, inputMessageContent))

suspend fun TdHandler.editMessageMediaOrNull(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull(EditMessageMedia(chatId, messageId, replyMarkup, inputMessageContent))

fun TdHandler.editMessageMediaWith(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(EditMessageMedia(chatId, messageId, replyMarkup, inputMessageContent), stackIgnore + 1, submit)

/**
 * Edits the message content caption
 * Returns the edited message after the edit is completed on the server side
 *
 * @chatId - The chat the message belongs to
 * @messageId - Identifier of the message
 * @replyMarkup - The new message reply markup
 *                For bots only
 * @caption - New message content caption
 *            0-GetOption("message_caption_length_max") characters
 */
suspend fun TdHandler.editMessageCaption(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null
) = sync(EditMessageCaption(chatId, messageId, replyMarkup, caption))

suspend fun TdHandler.editMessageCaptionOrNull(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null
) = syncOrNull(EditMessageCaption(chatId, messageId, replyMarkup, caption))

fun TdHandler.editMessageCaptionWith(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(EditMessageCaption(chatId, messageId, replyMarkup, caption), stackIgnore + 1, submit)

/**
 * Edits the message reply markup
 * For bots only
 * Returns the edited message after the edit is completed on the server side
 *
 * @chatId - The chat the message belongs to
 * @messageId - Identifier of the message
 * @replyMarkup - The new message reply markup
 */
suspend fun TdHandler.editMessageReplyMarkup(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null
) = sync(EditMessageReplyMarkup(chatId, messageId, replyMarkup))

suspend fun TdHandler.editMessageReplyMarkupOrNull(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null
) = syncOrNull(EditMessageReplyMarkup(chatId, messageId, replyMarkup))

fun TdHandler.editMessageReplyMarkupWith(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(EditMessageReplyMarkup(chatId, messageId, replyMarkup), stackIgnore + 1, submit)

/**
 * Edits the time when a scheduled message will be sent
 * Scheduling state of all messages in the same album or forwarded together with the message will be also changed
 *
 * @chatId - The chat the message belongs to
 * @messageId - Identifier of the message
 * @schedulingState - The new message scheduling state
 *                    Pass null to send the message immediately
 */
suspend fun TdHandler.editMessageSchedulingState(
    chatId: Long,
    messageId: Long,
    schedulingState: MessageSchedulingState? = null
){
    sync(EditMessageSchedulingState(chatId, messageId, schedulingState))
}


suspend fun TdHandler.editMessageSchedulingStateIgnoreException(
    chatId: Long,
    messageId: Long,
    schedulingState: MessageSchedulingState? = null
){
    syncOrNull(EditMessageSchedulingState(chatId, messageId, schedulingState))
}


fun TdHandler.editMessageSchedulingStateWith(
    chatId: Long,
    messageId: Long,
    schedulingState: MessageSchedulingState? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(EditMessageSchedulingState(chatId, messageId, schedulingState), stackIgnore + 1, submit)

/**
 * Updates the game score of the specified user in the game
 * For bots only
 *
 * @chatId - The chat to which the message with the game belongs
 * @messageId - Identifier of the message
 * @editMessage - True, if the message should be edited
 * @userId - User identifier
 * @score - The new score
 * @force - Pass true to update the score even if it decreases
 *          If the score is 0, the user will be deleted from the high score table
 */
suspend fun TdHandler.setGameScore(
    chatId: Long,
    messageId: Long,
    editMessage: Boolean,
    userId: Int,
    score: Int,
    force: Boolean
) = sync(SetGameScore(chatId, messageId, editMessage, userId, score, force))

suspend fun TdHandler.setGameScoreOrNull(
    chatId: Long,
    messageId: Long,
    editMessage: Boolean,
    userId: Int,
    score: Int,
    force: Boolean
) = syncOrNull(SetGameScore(chatId, messageId, editMessage, userId, score, force))

fun TdHandler.setGameScoreWith(
    chatId: Long,
    messageId: Long,
    editMessage: Boolean,
    userId: Int,
    score: Int,
    force: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(SetGameScore(chatId, messageId, editMessage, userId, score, force), stackIgnore + 1, submit)

/**
 * Informs TDLib that messages are being viewed by the user
 * Many useful activities depend on whether the messages are currently being viewed or not (e.g., marking messages as read, incrementing a view counter, updating a view counter, removing deleted messages in supergroups and channels)
 *
 * @chatId - Chat identifier
 * @messageThreadId - If not 0, a message thread identifier in which the messages are being viewed
 * @messageIds - The identifiers of the messages being viewed
 * @forceRead - True, if messages in closed chats should be marked as read by the request
 */
suspend fun TdHandler.viewMessages(
    chatId: Long,
    messageThreadId: Long,
    messageIds: LongArray,
    forceRead: Boolean
){
    sync(ViewMessages(chatId, messageThreadId, messageIds, forceRead))
}


suspend fun TdHandler.viewMessagesIgnoreException(
    chatId: Long,
    messageThreadId: Long,
    messageIds: LongArray,
    forceRead: Boolean
){
    syncOrNull(ViewMessages(chatId, messageThreadId, messageIds, forceRead))
}


fun TdHandler.viewMessagesWith(
    chatId: Long,
    messageThreadId: Long,
    messageIds: LongArray,
    forceRead: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ViewMessages(chatId, messageThreadId, messageIds, forceRead), stackIgnore + 1, submit)

/**
 * Informs TDLib that the message content has been opened (e.g., the user has opened a photo, video, document, location or venue, or has listened to an audio file or voice note message)
 * An updateMessageContentOpened update will be generated if something has changed
 *
 * @chatId - Chat identifier of the message
 * @messageId - Identifier of the message with the opened content
 */
suspend fun TdHandler.openMessageContent(
    chatId: Long,
    messageId: Long
){
    sync(OpenMessageContent(chatId, messageId))
}


suspend fun TdHandler.openMessageContentIgnoreException(
    chatId: Long,
    messageId: Long
){
    syncOrNull(OpenMessageContent(chatId, messageId))
}


fun TdHandler.openMessageContentWith(
    chatId: Long,
    messageId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(OpenMessageContent(chatId, messageId), stackIgnore + 1, submit)

/**
 * Clears draft messages in all chats
 *
 * @excludeSecretChats - If true, local draft messages in secret chats will not be cleared
 */
suspend fun TdHandler.clearAllDraftMessages(
    excludeSecretChats: Boolean
){
    sync(ClearAllDraftMessages(excludeSecretChats))
}


suspend fun TdHandler.clearAllDraftMessagesIgnoreException(
    excludeSecretChats: Boolean
){
    syncOrNull(ClearAllDraftMessages(excludeSecretChats))
}


fun TdHandler.clearAllDraftMessagesWith(
    excludeSecretChats: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ClearAllDraftMessages(excludeSecretChats), stackIgnore + 1, submit)

/**
 * Returns information about a file with messages exported from another app
 *
 * @messageFileHead - Beginning of the message file
 *                    Up to 100 first lines
 */
suspend fun TdHandler.getMessageFileType(
    messageFileHead: String? = null
) = sync(GetMessageFileType(messageFileHead))

suspend fun TdHandler.getMessageFileTypeOrNull(
    messageFileHead: String? = null
) = syncOrNull(GetMessageFileType(messageFileHead))

fun TdHandler.getMessageFileTypeWith(
    messageFileHead: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<MessageFileType>.() -> Unit)? = null
) = send(GetMessageFileType(messageFileHead), stackIgnore + 1, submit)

/**
 * Imports messages exported from another app
 *
 * @chatId - Identifier of a chat to which the messages will be imported
 *           It must be an identifier of a private chat with a mutual contact or an identifier of a supergroup chat with can_change_info administrator right
 * @messageFile - File with messages to import
 *                Only inputFileLocal and inputFileGenerated are supported
 *                The file must not be previously uploaded
 * @attachedFiles - Files used in the imported messages
 *                  Only inputFileLocal and inputFileGenerated are supported
 *                  The files must not be previously uploaded
 */
suspend fun TdHandler.importMessages(
    chatId: Long,
    messageFile: InputFile? = null,
    attachedFiles: Array<InputFile>
){
    sync(ImportMessages(chatId, messageFile, attachedFiles))
}


suspend fun TdHandler.importMessagesIgnoreException(
    chatId: Long,
    messageFile: InputFile? = null,
    attachedFiles: Array<InputFile>
){
    syncOrNull(ImportMessages(chatId, messageFile, attachedFiles))
}


fun TdHandler.importMessagesWith(
    chatId: Long,
    messageFile: InputFile? = null,
    attachedFiles: Array<InputFile>,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ImportMessages(chatId, messageFile, attachedFiles), stackIgnore + 1, submit)

/**
 * Changes the block state of a message sender
 * Currently, only users and supergroup chats can be blocked
 *
 * @sender - Message Sender
 * @isBlocked - New value of is_blocked
 */
suspend fun TdHandler.toggleMessageSenderIsBlocked(
    sender: MessageSender? = null,
    isBlocked: Boolean
){
    sync(ToggleMessageSenderIsBlocked(sender, isBlocked))
}


suspend fun TdHandler.toggleMessageSenderIsBlockedIgnoreException(
    sender: MessageSender? = null,
    isBlocked: Boolean
){
    syncOrNull(ToggleMessageSenderIsBlocked(sender, isBlocked))
}


fun TdHandler.toggleMessageSenderIsBlockedWith(
    sender: MessageSender? = null,
    isBlocked: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ToggleMessageSenderIsBlocked(sender, isBlocked), stackIgnore + 1, submit)

/**
 * Blocks an original sender of a message in the Replies chat
 *
 * @messageId - The identifier of an incoming message in the Replies chat
 * @deleteMessage - Pass true if the message must be deleted
 * @deleteAllMessages - Pass true if all messages from the same sender must be deleted
 * @reportSpam - Pass true if the sender must be reported to the Telegram moderators
 */
suspend fun TdHandler.blockMessageSenderFromReplies(
    messageId: Long,
    deleteMessage: Boolean,
    deleteAllMessages: Boolean,
    reportSpam: Boolean
){
    sync(BlockMessageSenderFromReplies(messageId, deleteMessage, deleteAllMessages, reportSpam))
}


suspend fun TdHandler.blockMessageSenderFromRepliesIgnoreException(
    messageId: Long,
    deleteMessage: Boolean,
    deleteAllMessages: Boolean,
    reportSpam: Boolean
){
    syncOrNull(BlockMessageSenderFromReplies(messageId, deleteMessage, deleteAllMessages, reportSpam))
}


fun TdHandler.blockMessageSenderFromRepliesWith(
    messageId: Long,
    deleteMessage: Boolean,
    deleteAllMessages: Boolean,
    reportSpam: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(BlockMessageSenderFromReplies(messageId, deleteMessage, deleteAllMessages, reportSpam), stackIgnore + 1, submit)

/**
 * Returns users and chats that were blocked by the current user
 *
 * @offset - Number of users and chats to skip in the result
 * @limit - The maximum number of users and chats to return
 */
suspend fun TdHandler.getBlockedMessageSenders(
    offset: Int,
    limit: Int
) = sync(GetBlockedMessageSenders(offset, limit))

suspend fun TdHandler.getBlockedMessageSendersOrNull(
    offset: Int,
    limit: Int
) = syncOrNull(GetBlockedMessageSenders(offset, limit))

fun TdHandler.getBlockedMessageSendersWith(
    offset: Int,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<MessageSenders>.() -> Unit)? = null
) = send(GetBlockedMessageSenders(offset, limit), stackIgnore + 1, submit)

/**
 * Returns detailed statistics about a message
 * Can be used only if Message.can_get_statistics == true
 *
 * @chatId - Chat identifier
 * @messageId - Message identifier
 * @isDark - Pass true if a dark theme is used by the application
 */
suspend fun TdHandler.getMessageStatistics(
    chatId: Long,
    messageId: Long,
    isDark: Boolean
) = sync(GetMessageStatistics(chatId, messageId, isDark))

suspend fun TdHandler.getMessageStatisticsOrNull(
    chatId: Long,
    messageId: Long,
    isDark: Boolean
) = syncOrNull(GetMessageStatistics(chatId, messageId, isDark))

fun TdHandler.getMessageStatisticsWith(
    chatId: Long,
    messageId: Long,
    isDark: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<MessageStatistics>.() -> Unit)? = null
) = send(GetMessageStatistics(chatId, messageId, isDark), stackIgnore + 1, submit)
