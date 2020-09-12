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
) = sync<Message>(GetMessage(chatId, messageId))

suspend fun TdHandler.getMessageOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull<Message>(GetMessage(chatId, messageId))

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
) = sync<Message>(GetMessageLocally(chatId, messageId))

suspend fun TdHandler.getMessageLocallyOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull<Message>(GetMessageLocally(chatId, messageId))

fun TdHandler.getMessageLocallyWith(
    chatId: Long,
    messageId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(GetMessageLocally(chatId, messageId), stackIgnore + 1, submit)

/**
 * Returns information about a message that is replied by given message
 *
 * @chatId - Identifier of the chat the message belongs to
 * @messageId - Identifier of the message reply to which get
 */
suspend fun TdHandler.getRepliedMessage(
    chatId: Long,
    messageId: Long
) = sync<Message>(GetRepliedMessage(chatId, messageId))

suspend fun TdHandler.getRepliedMessageOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull<Message>(GetRepliedMessage(chatId, messageId))

fun TdHandler.getRepliedMessageWith(
    chatId: Long,
    messageId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(GetRepliedMessage(chatId, messageId), stackIgnore + 1, submit)

/**
 * Returns information about a pinned chat message
 *
 * @chatId - Identifier of the chat the message belongs to
 */
suspend fun TdHandler.getChatPinnedMessage(
    chatId: Long
) = sync<Message>(GetChatPinnedMessage(chatId))

suspend fun TdHandler.getChatPinnedMessageOrNull(
    chatId: Long
) = syncOrNull<Message>(GetChatPinnedMessage(chatId))

fun TdHandler.getChatPinnedMessageWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(GetChatPinnedMessage(chatId), stackIgnore + 1, submit)

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
) = sync<Messages>(GetMessages(chatId, messageIds))

suspend fun TdHandler.getMessagesOrNull(
    chatId: Long,
    messageIds: LongArray
) = syncOrNull<Messages>(GetMessages(chatId, messageIds))

fun TdHandler.getMessagesWith(
    chatId: Long,
    messageIds: LongArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(GetMessages(chatId, messageIds), stackIgnore + 1, submit)

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
 *          If the offset is negative, the limit must be greater or equal to -offset
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 * @onlyLocal - If true, returns only messages that are available locally without sending network requests
 */
suspend fun TdHandler.getChatHistory(
    chatId: Long,
    fromMessageId: Long,
    offset: Int,
    limit: Int,
    onlyLocal: Boolean
) = sync<Messages>(GetChatHistory(chatId, fromMessageId, offset, limit, onlyLocal))

suspend fun TdHandler.getChatHistoryOrNull(
    chatId: Long,
    fromMessageId: Long,
    offset: Int,
    limit: Int,
    onlyLocal: Boolean
) = syncOrNull<Messages>(GetChatHistory(chatId, fromMessageId, offset, limit, onlyLocal))

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
 * Searches for messages with given words in the chat
 * Returns the results in reverse chronological order, i.e
 * In order of decreasing message_id
 * Cannot be used in secret chats with a non-empty query (searchSecretMessages should be used instead), or without an enabled message database
 * For optimal performance the number of returned messages is chosen by the library
 *
 * @chatId - Identifier of the chat in which to search messages
 * @query - Query to search for
 * @senderUserId - If not 0, only messages sent by the specified user will be returned
 *                 Not supported in secret chats
 * @fromMessageId - Identifier of the message starting from which history must be fetched
 *                  Use 0 to get results from the last message
 * @offset - Specify 0 to get results from exactly the from_message_id or a negative offset to get the specified message and some newer messages
 * @limit - The maximum number of messages to be returned
 *          Must be positive and can't be greater than 100
 *          If the offset is negative, the limit must be greater than -offset
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 * @filter - Filter for message content in the search results
 */
suspend fun TdHandler.searchChatMessages(
    chatId: Long,
    query: String? = null,
    senderUserId: Int,
    fromMessageId: Long,
    offset: Int,
    limit: Int,
    filter: SearchMessagesFilter? = null
) = sync<Messages>(SearchChatMessages(chatId, query, senderUserId, fromMessageId, offset, limit, filter))

suspend fun TdHandler.searchChatMessagesOrNull(
    chatId: Long,
    query: String? = null,
    senderUserId: Int,
    fromMessageId: Long,
    offset: Int,
    limit: Int,
    filter: SearchMessagesFilter? = null
) = syncOrNull<Messages>(SearchChatMessages(chatId, query, senderUserId, fromMessageId, offset, limit, filter))

fun TdHandler.searchChatMessagesWith(
    chatId: Long,
    query: String? = null,
    senderUserId: Int,
    fromMessageId: Long,
    offset: Int,
    limit: Int,
    filter: SearchMessagesFilter? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(SearchChatMessages(chatId, query, senderUserId, fromMessageId, offset, limit, filter), stackIgnore + 1, submit)

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
 * @limit - The maximum number of messages to be returned, up to 100
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 */
suspend fun TdHandler.searchMessages(
    chatList: ChatList? = null,
    query: String? = null,
    offsetDate: Int,
    offsetChatId: Long,
    offsetMessageId: Long,
    limit: Int
) = sync<Messages>(SearchMessages(chatList, query, offsetDate, offsetChatId, offsetMessageId, limit))

suspend fun TdHandler.searchMessagesOrNull(
    chatList: ChatList? = null,
    query: String? = null,
    offsetDate: Int,
    offsetChatId: Long,
    offsetMessageId: Long,
    limit: Int
) = syncOrNull<Messages>(SearchMessages(chatList, query, offsetDate, offsetChatId, offsetMessageId, limit))

fun TdHandler.searchMessagesWith(
    chatList: ChatList? = null,
    query: String? = null,
    offsetDate: Int,
    offsetChatId: Long,
    offsetMessageId: Long,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(SearchMessages(chatList, query, offsetDate, offsetChatId, offsetMessageId, limit), stackIgnore + 1, submit)

/**
 * Searches for messages in secret chats
 * Returns the results in reverse chronological order
 * For optimal performance the number of returned messages is chosen by the library
 *
 * @chatId - Identifier of the chat in which to search
 *           Specify 0 to search in all secret chats
 * @query - Query to search for
 *          If empty, searchChatMessages should be used instead
 * @fromSearchId - The identifier from the result of a previous request, use 0 to get results from the last message
 * @limit - The maximum number of messages to be returned
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 * @filter - A filter for the content of messages in the search results
 */
suspend fun TdHandler.searchSecretMessages(
    chatId: Long,
    query: String? = null,
    fromSearchId: Long,
    limit: Int,
    filter: SearchMessagesFilter? = null
) = sync<FoundMessages>(SearchSecretMessages(chatId, query, fromSearchId, limit, filter))

suspend fun TdHandler.searchSecretMessagesOrNull(
    chatId: Long,
    query: String? = null,
    fromSearchId: Long,
    limit: Int,
    filter: SearchMessagesFilter? = null
) = syncOrNull<FoundMessages>(SearchSecretMessages(chatId, query, fromSearchId, limit, filter))

fun TdHandler.searchSecretMessagesWith(
    chatId: Long,
    query: String? = null,
    fromSearchId: Long,
    limit: Int,
    filter: SearchMessagesFilter? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<FoundMessages>.() -> Unit)? = null
) = send(SearchSecretMessages(chatId, query, fromSearchId, limit, filter), stackIgnore + 1, submit)

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
) = sync<Messages>(SearchCallMessages(fromMessageId, limit, onlyMissed))

suspend fun TdHandler.searchCallMessagesOrNull(
    fromMessageId: Long,
    limit: Int,
    onlyMissed: Boolean
) = syncOrNull<Messages>(SearchCallMessages(fromMessageId, limit, onlyMissed))

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
) = sync<Messages>(SearchChatRecentLocationMessages(chatId, limit))

suspend fun TdHandler.searchChatRecentLocationMessagesOrNull(
    chatId: Long,
    limit: Int
) = syncOrNull<Messages>(SearchChatRecentLocationMessages(chatId, limit))

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
suspend fun TdHandler.getActiveLiveLocationMessages() = sync<Messages>(GetActiveLiveLocationMessages())

suspend fun TdHandler.getActiveLiveLocationMessagesOrNull() = syncOrNull<Messages>(GetActiveLiveLocationMessages())

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
) = sync<Message>(GetChatMessageByDate(chatId, date))

suspend fun TdHandler.getChatMessageByDateOrNull(
    chatId: Long,
    date: Int
) = syncOrNull<Message>(GetChatMessageByDate(chatId, date))

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
) = sync<Messages>(GetChatScheduledMessages(chatId))

suspend fun TdHandler.getChatScheduledMessagesOrNull(
    chatId: Long
) = syncOrNull<Messages>(GetChatScheduledMessages(chatId))

fun TdHandler.getChatScheduledMessagesWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(GetChatScheduledMessages(chatId), stackIgnore + 1, submit)

/**
 * Returns a public HTTPS link to a message
 * Available only for messages in supergroups and channels with a username
 *
 * @chatId - Identifier of the chat to which the message belongs
 * @messageId - Identifier of the message
 * @forAlbum - Pass true if a link for a whole media album should be returned
 */
suspend fun TdHandler.getPublicMessageLink(
    chatId: Long,
    messageId: Long,
    forAlbum: Boolean
) = sync<PublicMessageLink>(GetPublicMessageLink(chatId, messageId, forAlbum))

suspend fun TdHandler.getPublicMessageLinkOrNull(
    chatId: Long,
    messageId: Long,
    forAlbum: Boolean
) = syncOrNull<PublicMessageLink>(GetPublicMessageLink(chatId, messageId, forAlbum))

fun TdHandler.getPublicMessageLinkWith(
    chatId: Long,
    messageId: Long,
    forAlbum: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<PublicMessageLink>.() -> Unit)? = null
) = send(GetPublicMessageLink(chatId, messageId, forAlbum), stackIgnore + 1, submit)

/**
 * Returns a private HTTPS link to a message in a chat
 * Available only for already sent messages in supergroups and channels
 * The link will work only for members of the chat
 *
 * @chatId - Identifier of the chat to which the message belongs
 * @messageId - Identifier of the message
 */
suspend fun TdHandler.getMessageLink(
    chatId: Long,
    messageId: Long
) = sync<HttpUrl>(GetMessageLink(chatId, messageId))

suspend fun TdHandler.getMessageLinkOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull<HttpUrl>(GetMessageLink(chatId, messageId))

fun TdHandler.getMessageLinkWith(
    chatId: Long,
    messageId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<HttpUrl>.() -> Unit)? = null
) = send(GetMessageLink(chatId, messageId), stackIgnore + 1, submit)

/**
 * Returns information about a public or private message link
 *
 * @url - The message link in the format "https://t.me/c/...", or "tg://privatepost?...", or "https://t.me/username/...", or "tg://resolve?..."
 */
suspend fun TdHandler.getMessageLinkInfo(
    url: String? = null
) = sync<MessageLinkInfo>(GetMessageLinkInfo(url))

suspend fun TdHandler.getMessageLinkInfoOrNull(
    url: String? = null
) = syncOrNull<MessageLinkInfo>(GetMessageLinkInfo(url))

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
 * @replyToMessageId - Identifier of the message to reply to or 0
 * @options - Options to be used to send the message
 * @replyMarkup - Markup for replying to the message
 *                For bots only
 * @inputMessageContent - The content of the message to be sent
 */
suspend fun TdHandler.sendMessage(
    chatId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = sync<Message>(SendMessage(chatId, replyToMessageId, options, replyMarkup, inputMessageContent))

suspend fun TdHandler.sendMessageOrNull(
    chatId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull<Message>(SendMessage(chatId, replyToMessageId, options, replyMarkup, inputMessageContent))

fun TdHandler.sendMessageWith(
    chatId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(SendMessage(chatId, replyToMessageId, options, replyMarkup, inputMessageContent), stackIgnore + 1, submit)

/**
 * Sends messages grouped together into an album
 * Currently only photo and video messages can be grouped into an album
 * Returns sent messages
 *
 * @chatId - Target chat
 * @replyToMessageId - Identifier of a message to reply to or 0
 * @options - Options to be used to send the messages
 * @inputMessageContents - Contents of messages to be sent
 */
suspend fun TdHandler.sendMessageAlbum(
    chatId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    inputMessageContents: Array<InputMessageContent>
) = sync<Messages>(SendMessageAlbum(chatId, replyToMessageId, options, inputMessageContents))

suspend fun TdHandler.sendMessageAlbumOrNull(
    chatId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    inputMessageContents: Array<InputMessageContent>
) = syncOrNull<Messages>(SendMessageAlbum(chatId, replyToMessageId, options, inputMessageContents))

fun TdHandler.sendMessageAlbumWith(
    chatId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    inputMessageContents: Array<InputMessageContent>,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(SendMessageAlbum(chatId, replyToMessageId, options, inputMessageContents), stackIgnore + 1, submit)

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
) = sync<Message>(SendBotStartMessage(botUserId, chatId, parameter))

suspend fun TdHandler.sendBotStartMessageOrNull(
    botUserId: Int,
    chatId: Long,
    parameter: String? = null
) = syncOrNull<Message>(SendBotStartMessage(botUserId, chatId, parameter))

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
 * @replyToMessageId - Identifier of a message to reply to or 0
 * @options - Options to be used to send the message
 * @queryId - Identifier of the inline query
 * @resultId - Identifier of the inline result
 * @hideViaBot - If true, there will be no mention of a bot, via which the message is sent
 *               Can be used only for bots GetOption("animation_search_bot_username"), GetOption("photo_search_bot_username") and GetOption("venue_search_bot_username")
 */
suspend fun TdHandler.sendInlineQueryResultMessage(
    chatId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    queryId: Long,
    resultId: String? = null,
    hideViaBot: Boolean
) = sync<Message>(SendInlineQueryResultMessage(chatId, replyToMessageId, options, queryId, resultId, hideViaBot))

suspend fun TdHandler.sendInlineQueryResultMessageOrNull(
    chatId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    queryId: Long,
    resultId: String? = null,
    hideViaBot: Boolean
) = syncOrNull<Message>(SendInlineQueryResultMessage(chatId, replyToMessageId, options, queryId, resultId, hideViaBot))

fun TdHandler.sendInlineQueryResultMessageWith(
    chatId: Long,
    replyToMessageId: Long,
    options: MessageSendOptions? = null,
    queryId: Long,
    resultId: String? = null,
    hideViaBot: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(SendInlineQueryResultMessage(chatId, replyToMessageId, options, queryId, resultId, hideViaBot), stackIgnore + 1, submit)

/**
 * Forwards previously sent messages
 * Returns the forwarded messages in the same order as the message identifiers passed in message_ids
 * If a message can't be forwarded, null will be returned instead of the message
 *
 * @chatId - Identifier of the chat to which to forward messages
 * @fromChatId - Identifier of the chat from which to forward messages
 * @messageIds - Identifiers of the messages to forward
 * @options - Options to be used to send the messages
 * @asAlbum - True, if the messages should be grouped into an album after forwarding
 *            For this to work, no more than 10 messages may be forwarded, and all of them must be photo or video messages
 * @sendCopy - True, if content of the messages needs to be copied without links to the original messages
 *             Always true if the messages are forwarded to a secret chat
 * @removeCaption - True, if media captions of message copies needs to be removed
 *                  Ignored if send_copy is false
 */
suspend fun TdHandler.forwardMessages(
    chatId: Long,
    fromChatId: Long,
    messageIds: LongArray,
    options: MessageSendOptions? = null,
    asAlbum: Boolean,
    sendCopy: Boolean,
    removeCaption: Boolean
) = sync<Messages>(ForwardMessages(chatId, fromChatId, messageIds, options, asAlbum, sendCopy, removeCaption))

suspend fun TdHandler.forwardMessagesOrNull(
    chatId: Long,
    fromChatId: Long,
    messageIds: LongArray,
    options: MessageSendOptions? = null,
    asAlbum: Boolean,
    sendCopy: Boolean,
    removeCaption: Boolean
) = syncOrNull<Messages>(ForwardMessages(chatId, fromChatId, messageIds, options, asAlbum, sendCopy, removeCaption))

fun TdHandler.forwardMessagesWith(
    chatId: Long,
    fromChatId: Long,
    messageIds: LongArray,
    options: MessageSendOptions? = null,
    asAlbum: Boolean,
    sendCopy: Boolean,
    removeCaption: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Messages>.() -> Unit)? = null
) = send(ForwardMessages(chatId, fromChatId, messageIds, options, asAlbum, sendCopy, removeCaption), stackIgnore + 1, submit)

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
) = sync<Messages>(ResendMessages(chatId, messageIds))

suspend fun TdHandler.resendMessagesOrNull(
    chatId: Long,
    messageIds: LongArray
) = syncOrNull<Messages>(ResendMessages(chatId, messageIds))

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
) = sync<Message>(SendChatSetTtlMessage(chatId, ttl))

suspend fun TdHandler.sendChatSetTtlMessageOrNull(
    chatId: Long,
    ttl: Int
) = syncOrNull<Message>(SendChatSetTtlMessage(chatId, ttl))

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
 * @senderUserId - Identifier of the user who will be shown as the sender of the message
 *                 May be 0 for channel posts
 * @replyToMessageId - Identifier of the message to reply to or 0
 * @disableNotification - Pass true to disable notification for the message
 * @inputMessageContent - The content of the message to be added
 */
suspend fun TdHandler.addLocalMessage(
    chatId: Long,
    senderUserId: Int,
    replyToMessageId: Long,
    disableNotification: Boolean,
    inputMessageContent: InputMessageContent? = null
) = sync<Message>(AddLocalMessage(chatId, senderUserId, replyToMessageId, disableNotification, inputMessageContent))

suspend fun TdHandler.addLocalMessageOrNull(
    chatId: Long,
    senderUserId: Int,
    replyToMessageId: Long,
    disableNotification: Boolean,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull<Message>(AddLocalMessage(chatId, senderUserId, replyToMessageId, disableNotification, inputMessageContent))

fun TdHandler.addLocalMessageWith(
    chatId: Long,
    senderUserId: Int,
    replyToMessageId: Long,
    disableNotification: Boolean,
    inputMessageContent: InputMessageContent? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(AddLocalMessage(chatId, senderUserId, replyToMessageId, disableNotification, inputMessageContent), stackIgnore + 1, submit)

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
) = sync<Ok>(DeleteMessages(chatId, messageIds, revoke))

suspend fun TdHandler.deleteMessagesOrNull(
    chatId: Long,
    messageIds: LongArray,
    revoke: Boolean
) = syncOrNull<Ok>(DeleteMessages(chatId, messageIds, revoke))

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
) = sync<Message>(EditMessageText(chatId, messageId, replyMarkup, inputMessageContent))

suspend fun TdHandler.editMessageTextOrNull(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull<Message>(EditMessageText(chatId, messageId, replyMarkup, inputMessageContent))

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
 */
suspend fun TdHandler.editMessageLiveLocation(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null
) = sync<Message>(EditMessageLiveLocation(chatId, messageId, replyMarkup, location))

suspend fun TdHandler.editMessageLiveLocationOrNull(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null
) = syncOrNull<Message>(EditMessageLiveLocation(chatId, messageId, replyMarkup, location))

fun TdHandler.editMessageLiveLocationWith(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Message>.() -> Unit)? = null
) = send(EditMessageLiveLocation(chatId, messageId, replyMarkup, location), stackIgnore + 1, submit)

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
) = sync<Message>(EditMessageMedia(chatId, messageId, replyMarkup, inputMessageContent))

suspend fun TdHandler.editMessageMediaOrNull(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull<Message>(EditMessageMedia(chatId, messageId, replyMarkup, inputMessageContent))

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
) = sync<Message>(EditMessageCaption(chatId, messageId, replyMarkup, caption))

suspend fun TdHandler.editMessageCaptionOrNull(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null
) = syncOrNull<Message>(EditMessageCaption(chatId, messageId, replyMarkup, caption))

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
) = sync<Message>(EditMessageReplyMarkup(chatId, messageId, replyMarkup))

suspend fun TdHandler.editMessageReplyMarkupOrNull(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null
) = syncOrNull<Message>(EditMessageReplyMarkup(chatId, messageId, replyMarkup))

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
) = sync<Ok>(EditMessageSchedulingState(chatId, messageId, schedulingState))

suspend fun TdHandler.editMessageSchedulingStateOrNull(
    chatId: Long,
    messageId: Long,
    schedulingState: MessageSchedulingState? = null
) = syncOrNull<Ok>(EditMessageSchedulingState(chatId, messageId, schedulingState))

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
) = sync<Message>(SetGameScore(chatId, messageId, editMessage, userId, score, force))

suspend fun TdHandler.setGameScoreOrNull(
    chatId: Long,
    messageId: Long,
    editMessage: Boolean,
    userId: Int,
    score: Int,
    force: Boolean
) = syncOrNull<Message>(SetGameScore(chatId, messageId, editMessage, userId, score, force))

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
 * @messageIds - The identifiers of the messages being viewed
 * @forceRead - True, if messages in closed chats should be marked as read
 */
suspend fun TdHandler.viewMessages(
    chatId: Long,
    messageIds: LongArray,
    forceRead: Boolean
) = sync<Ok>(ViewMessages(chatId, messageIds, forceRead))

suspend fun TdHandler.viewMessagesOrNull(
    chatId: Long,
    messageIds: LongArray,
    forceRead: Boolean
) = syncOrNull<Ok>(ViewMessages(chatId, messageIds, forceRead))

fun TdHandler.viewMessagesWith(
    chatId: Long,
    messageIds: LongArray,
    forceRead: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ViewMessages(chatId, messageIds, forceRead), stackIgnore + 1, submit)

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
) = sync<Ok>(OpenMessageContent(chatId, messageId))

suspend fun TdHandler.openMessageContentOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull<Ok>(OpenMessageContent(chatId, messageId))

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
) = sync<Ok>(ClearAllDraftMessages(excludeSecretChats))

suspend fun TdHandler.clearAllDraftMessagesOrNull(
    excludeSecretChats: Boolean
) = syncOrNull<Ok>(ClearAllDraftMessages(excludeSecretChats))

fun TdHandler.clearAllDraftMessagesWith(
    excludeSecretChats: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ClearAllDraftMessages(excludeSecretChats), stackIgnore + 1, submit)
