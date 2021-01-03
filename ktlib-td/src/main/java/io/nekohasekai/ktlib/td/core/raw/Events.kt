package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*

interface AbsEvents {

    /**
     * The user authorization state has changed
     *
     * @authorizationState - New authorization state
     */
    suspend fun onAuthorizationState(authorizationState: AuthorizationState) = Unit

    /**
     * A new message was received
     * Can also be an outgoing message
     *
     * @message - The new message
     */
    suspend fun handleNewMessage(message: Message) = Unit

    /**
     * A request to send a message has reached the Telegram server
     * This doesn't mean that the message will be sent successfully or even that the send message request will be processed
     * This update will be sent only if the option "use_quick_ack" is set to true
     * This update may be sent multiple times for the same message
     *
     * @chatId - The chat identifier of the sent message
     * @messageId - A temporary message identifier
     */
    suspend fun onMessageSendAcknowledged(chatId: Long, messageId: Long) = Unit

    /**
     * A message has been successfully sent
     *
     * @message - Information about the sent message
     *            Usually only the message identifier, date, and content are changed, but almost all other fields can also change
     * @oldMessageId - The previous temporary message identifier
     */
    suspend fun onMessageSendSucceeded(message: Message, oldMessageId: Long) = Unit

    /**
     * A message failed to send
     * Be aware that some messages being sent can be irrecoverably deleted, in which case updateDeleteMessages will be received instead of this update
     *
     * @message - Contains information about the message which failed to send
     * @oldMessageId - The previous temporary message identifier
     * @errorCode - An error code
     * @errorMessage - Error message
     */
    suspend fun onMessageSendFailed(message: Message, oldMessageId: Long, errorCode: Int, errorMessage: String) = Unit

    /**
     * The message content has changed
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @newContent - New message content
     */
    suspend fun onMessageContent(chatId: Long, messageId: Long, newContent: MessageContent) = Unit

    /**
     * A message was edited
     * Changes in the message content will come in a separate updateMessageContent
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @editDate - Point in time (Unix timestamp) when the message was edited
     * @replyMarkup - New message reply markup
     */
    suspend fun onMessageEdited(chatId: Long, messageId: Long, editDate: Int, replyMarkup: ReplyMarkup?) = Unit

    /**
     * The message pinned state was changed
     *
     * @chatId - Chat identifier
     * @messageId - The message identifier
     * @isPinned - True, if the message is pinned
     */
    suspend fun onMessageIsPinned(chatId: Long, messageId: Long, isPinned: Boolean) = Unit

    /**
     * The information about interactions with a message has changed
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @interactionInfo - New information about interactions with the message
     */
    suspend fun onMessageInteractionInfo(chatId: Long, messageId: Long, interactionInfo: MessageInteractionInfo?) = Unit

    /**
     * The message content was opened
     * Updates voice note messages to "listened", video note messages to "viewed" and starts the TTL timer for self-destructing messages
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     */
    suspend fun onMessageContentOpened(chatId: Long, messageId: Long) = Unit

    /**
     * A message with an unread mention was read
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @unreadMentionCount - The new number of unread mention messages left in the chat
     */
    suspend fun onMessageMentionRead(chatId: Long, messageId: Long, unreadMentionCount: Int) = Unit

    /**
     * A message with a live location was viewed
     * When the update is received, the application is supposed to update the live location
     *
     * @chatId - Identifier of the chat with the live location message
     * @messageId - Identifier of the message with live location
     */
    suspend fun onMessageLiveLocationViewed(chatId: Long, messageId: Long) = Unit

    /**
     * A new chat has been loaded/created
     * This update is guaranteed to come before the chat identifier is returned to the application
     * The chat field changes will be reported through separate updates
     *
     * @chat - The chat
     */
    suspend fun onNewChat(chat: Chat) = Unit

    /**
     * The title of a chat was changed
     *
     * @chatId - Chat identifier
     * @title - The new chat title
     */
    suspend fun onChatTitle(chatId: Long, title: String) = Unit

    /**
     * A chat photo was changed
     *
     * @chatId - Chat identifier
     * @photo - The new chat photo
     */
    suspend fun onChatPhoto(chatId: Long, photo: ChatPhotoInfo?) = Unit

    /**
     * Chat permissions was changed
     *
     * @chatId - Chat identifier
     * @permissions - The new chat permissions
     */
    suspend fun onChatPermissions(chatId: Long, permissions: ChatPermissions) = Unit

    /**
     * The last message of a chat was changed
     * If last_message is null, then the last message in the chat became unknown
     * Some new unknown messages might be added to the chat in this case
     *
     * @chatId - Chat identifier
     * @lastMessage - The new last message in the chat
     * @positions - The new chat positions in the chat lists
     */
    suspend fun onChatLastMessage(chatId: Long, lastMessage: Message?, positions: Array<ChatPosition>) = Unit

    /**
     * The position of a chat in a chat list has changed
     * Instead of this update updateChatLastMessage or updateChatDraftMessage might be sent
     *
     * @chatId - Chat identifier
     * @position - New chat position
     *             If new order is 0, then the chat needs to be removed from the list
     */
    suspend fun onChatPosition(chatId: Long, position: ChatPosition) = Unit

    /**
     * A chat was marked as unread or was read
     *
     * @chatId - Chat identifier
     * @isMarkedAsUnread - New value of is_marked_as_unread
     */
    suspend fun onChatIsMarkedAsUnread(chatId: Long, isMarkedAsUnread: Boolean) = Unit

    /**
     * A chat was blocked or unblocked
     *
     * @chatId - Chat identifier
     * @isBlocked - New value of is_blocked
     */
    suspend fun onChatIsBlocked(chatId: Long, isBlocked: Boolean) = Unit

    /**
     * A chat's has_scheduled_messages field has changed
     *
     * @chatId - Chat identifier
     * @hasScheduledMessages - New value of has_scheduled_messages
     */
    suspend fun onChatHasScheduledMessages(chatId: Long, hasScheduledMessages: Boolean) = Unit

    /**
     * A chat voice chat state has changed
     *
     * @chatId - Chat identifier
     * @voiceChatGroupCallId - New value of voice_chat_group_call_id
     * @isVoiceChatEmpty - New value of is_voice_chat_empty
     */
    suspend fun onChatVoiceChat(chatId: Long, voiceChatGroupCallId: Int, isVoiceChatEmpty: Boolean) = Unit

    /**
     * The value of the default disable_notification parameter, used when a message is sent to the chat, was changed
     *
     * @chatId - Chat identifier
     * @defaultDisableNotification - The new default_disable_notification value
     */
    suspend fun onChatDefaultDisableNotification(chatId: Long, defaultDisableNotification: Boolean) = Unit

    /**
     * Incoming messages were read or number of unread messages has been changed
     *
     * @chatId - Chat identifier
     * @lastReadInboxMessageId - Identifier of the last read incoming message
     * @unreadCount - The number of unread messages left in the chat
     */
    suspend fun onChatReadInbox(chatId: Long, lastReadInboxMessageId: Long, unreadCount: Int) = Unit

    /**
     * Outgoing messages were read
     *
     * @chatId - Chat identifier
     * @lastReadOutboxMessageId - Identifier of last read outgoing message
     */
    suspend fun onChatReadOutbox(chatId: Long, lastReadOutboxMessageId: Long) = Unit

    /**
     * The chat unread_mention_count has changed
     *
     * @chatId - Chat identifier
     * @unreadMentionCount - The number of unread mention messages left in the chat
     */
    suspend fun onChatUnreadMentionCount(chatId: Long, unreadMentionCount: Int) = Unit

    /**
     * Notification settings for a chat were changed
     *
     * @chatId - Chat identifier
     * @notificationSettings - The new notification settings
     */
    suspend fun onChatNotificationSettings(chatId: Long, notificationSettings: ChatNotificationSettings) = Unit

    /**
     * Notification settings for some type of chats were updated
     *
     * @scope - Types of chats for which notification settings were updated
     * @notificationSettings - The new notification settings
     */
    suspend fun onScopeNotificationSettings(scope: NotificationSettingsScope, notificationSettings: ScopeNotificationSettings) = Unit

    /**
     * The chat action bar was changed
     *
     * @chatId - Chat identifier
     * @actionBar - The new value of the action bar
     */
    suspend fun onChatActionBar(chatId: Long, actionBar: ChatActionBar?) = Unit

    /**
     * The default chat reply markup was changed
     * Can occur because new messages with reply markup were received or because an old reply markup was hidden by the user
     *
     * @chatId - Chat identifier
     * @replyMarkupMessageId - Identifier of the message from which reply markup needs to be used
     *                         0 if there is no default custom reply markup in the chat
     */
    suspend fun onChatReplyMarkup(chatId: Long, replyMarkupMessageId: Long) = Unit

    /**
     * A chat draft has changed
     * Be aware that the update may come in the currently opened chat but with old content of the draft
     * If the user has changed the content of the draft, this update shouldn't be applied
     *
     * @chatId - Chat identifier
     * @draftMessage - The new draft message
     * @positions - The new chat positions in the chat lists
     */
    suspend fun onChatDraftMessage(chatId: Long, draftMessage: DraftMessage?, positions: Array<ChatPosition>) = Unit

    /**
     * The list of chat filters or a chat filter has changed
     *
     * @chatFilters - The new list of chat filters
     */
    suspend fun onChatFilters(chatFilters: Array<ChatFilterInfo>) = Unit

    /**
     * The number of online group members has changed
     * This update with non-zero count is sent only for currently opened chats
     * There is no guarantee that it will be sent just after the count has changed
     *
     * @chatId - Identifier of the chat
     * @onlineMemberCount - New number of online members in the chat, or 0 if unknown
     */
    suspend fun onChatOnlineMemberCount(chatId: Long, onlineMemberCount: Int) = Unit

    /**
     * A notification was changed
     *
     * @notificationGroupId - Unique notification group identifier
     * @notification - Changed notification
     */
    suspend fun onNotification(notificationGroupId: Int, notification: Notification) = Unit

    /**
     * A list of active notifications in a notification group has changed
     *
     * @notificationGroupId - Unique notification group identifier
     * @type - New type of the notification group
     * @chatId - Identifier of a chat to which all notifications in the group belong
     * @notificationSettingsChatId - Chat identifier, which notification settings must be applied to the added notifications
     * @isSilent - True, if the notifications should be shown without sound
     * @totalCount - Total number of unread notifications in the group, can be bigger than number of active notifications
     * @addedNotifications - List of added group notifications, sorted by notification ID
     * @removedNotificationIds - Identifiers of removed group notifications, sorted by notification ID
     */
    suspend fun onNotificationGroup(notificationGroupId: Int, type: NotificationGroupType, chatId: Long, notificationSettingsChatId: Long, isSilent: Boolean, totalCount: Int, addedNotifications: Array<Notification>, removedNotificationIds: IntArray) = Unit

    /**
     * Contains active notifications that was shown on previous application launches
     * This update is sent only if the message database is used
     * In that case it comes once before any updateNotification and updateNotificationGroup update
     *
     * @groups - Lists of active notification groups
     */
    suspend fun onActiveNotifications(groups: Array<NotificationGroup>) = Unit

    /**
     * Describes whether there are some pending notification updates
     * Can be used to prevent application from killing, while there are some pending notifications
     *
     * @haveDelayedNotifications - True, if there are some delayed notification updates, which will be sent soon
     * @haveUnreceivedNotifications - True, if there can be some yet unreceived notifications, which are being fetched from the server
     */
    suspend fun onHavePendingNotifications(haveDelayedNotifications: Boolean, haveUnreceivedNotifications: Boolean) = Unit

    /**
     * Some messages were deleted
     *
     * @chatId - Chat identifier
     * @messageIds - Identifiers of the deleted messages
     * @isPermanent - True, if the messages are permanently deleted by a user (as opposed to just becoming inaccessible)
     * @fromCache - True, if the messages are deleted only from the cache and can possibly be retrieved again in the future
     */
    suspend fun onDeleteMessages(chatId: Long, messageIds: LongArray, isPermanent: Boolean, fromCache: Boolean) = Unit

    /**
     * User activity in the chat has changed
     *
     * @chatId - Chat identifier
     * @messageThreadId - If not 0, a message thread identifier in which the action was performed
     * @userId - Identifier of a user performing an action
     * @action - The action description
     */
    suspend fun onUserChatAction(chatId: Long, messageThreadId: Long, userId: Int, action: ChatAction) = Unit

    /**
     * The user went online or offline
     *
     * @userId - User identifier
     * @status - New status of the user
     */
    suspend fun onUserStatus(userId: Int, status: UserStatus) = Unit

    /**
     * Some data of a user has changed
     * This update is guaranteed to come before the user identifier is returned to the application
     *
     * @user - New data about the user
     */
    suspend fun onUser(user: User) = Unit

    /**
     * Some data of a basic group has changed
     * This update is guaranteed to come before the basic group identifier is returned to the application
     *
     * @basicGroup - New data about the group
     */
    suspend fun onBasicGroup(basicGroup: BasicGroup) = Unit

    /**
     * Some data of a supergroup or a channel has changed
     * This update is guaranteed to come before the supergroup identifier is returned to the application
     *
     * @supergroup - New data about the supergroup
     */
    suspend fun onSupergroup(supergroup: Supergroup) = Unit

    /**
     * Some data of a secret chat has changed
     * This update is guaranteed to come before the secret chat identifier is returned to the application
     *
     * @secretChat - New data about the secret chat
     */
    suspend fun onSecretChat(secretChat: SecretChat) = Unit

    /**
     * Some data from userFullInfo has been changed
     *
     * @userId - User identifier
     * @userFullInfo - New full information about the user
     */
    suspend fun onUserFullInfo(userId: Int, userFullInfo: UserFullInfo) = Unit

    /**
     * Some data from basicGroupFullInfo has been changed
     *
     * @basicGroupId - Identifier of a basic group
     * @basicGroupFullInfo - New full information about the group
     */
    suspend fun onBasicGroupFullInfo(basicGroupId: Int, basicGroupFullInfo: BasicGroupFullInfo) = Unit

    /**
     * Some data from supergroupFullInfo has been changed
     *
     * @supergroupId - Identifier of the supergroup or channel
     * @supergroupFullInfo - New full information about the supergroup
     */
    suspend fun onSupergroupFullInfo(supergroupId: Int, supergroupFullInfo: SupergroupFullInfo) = Unit

    /**
     * Service notification from the server
     * Upon receiving this the application must show a popup with the content of the notification
     *
     * @type - Notification type
     *         If type begins with "AUTH_KEY_DROP_", then two buttons "Cancel" and "Log out" should be shown under notification
     *         If user presses the second, all local data should be destroyed using Destroy method
     * @content - Notification content
     */
    suspend fun onServiceNotification(type: String, content: MessageContent) = Unit

    /**
     * Information about a file was updated
     *
     * @file - New data about the file
     */
    suspend fun onFile(file: File) = Unit

    /**
     * The file generation process needs to be started by the application
     *
     * @generationId - Unique identifier for the generation process
     * @originalPath - The path to a file from which a new file is generated
     * @destinationPath - The path to a file that should be created and where the new file should be generated
     * @conversion - String specifying the conversion applied to the original file
     *               If conversion is "#url#" than original_path contains an HTTP/HTTPS URL of a file, which should be downloaded by the application
     */
    suspend fun onFileGenerationStart(generationId: Long, originalPath: String?, destinationPath: String, conversion: String) = Unit

    /**
     * File generation is no longer needed
     *
     * @generationId - Unique identifier for the generation process
     */
    suspend fun onFileGenerationStop(generationId: Long) = Unit

    /**
     * New call was created or information about a call was updated
     *
     * @call - New data about a call
     */
    suspend fun onCall(call: Call) = Unit

    /**
     * Information about a group call was updated
     *
     * @groupCall - New data about a group call
     */
    suspend fun onGroupCall(groupCall: GroupCall) = Unit

    /**
     * Information about a group call participant was changed
     * The updates are sent only after the group call is received through getGroupCall and only if the call is joined or being joined
     *
     * @groupCallId - Identifier of group call
     * @participant - New data about a participant
     */
    suspend fun onGroupCallParticipant(groupCallId: Int, participant: GroupCallParticipant) = Unit

    /**
     * New call signaling data arrived
     *
     * @callId - The call identifier
     * @data - The data
     */
    suspend fun onNewCallSignalingData(callId: Int, data: ByteArray) = Unit

    /**
     * Some privacy setting rules have been changed
     *
     * @setting - The privacy setting
     * @rules - New privacy rules
     */
    suspend fun onUserPrivacySettingRules(setting: UserPrivacySetting, rules: UserPrivacySettingRules) = Unit

    /**
     * Number of unread messages in a chat list has changed
     * This update is sent only if the message database is used
     *
     * @chatList - The chat list with changed number of unread messages
     * @unreadCount - Total number of unread messages
     * @unreadUnmutedCount - Total number of unread messages in unmuted chats
     */
    suspend fun onUnreadMessageCount(chatList: ChatList, unreadCount: Int, unreadUnmutedCount: Int) = Unit

    /**
     * Number of unread chats, i.e
     * With unread messages or marked as unread, has changed
     * This update is sent only if the message database is used
     *
     * @chatList - The chat list with changed number of unread messages
     * @totalCount - Approximate total number of chats in the chat list
     * @unreadCount - Total number of unread chats
     * @unreadUnmutedCount - Total number of unread unmuted chats
     * @markedAsUnreadCount - Total number of chats marked as unread
     * @markedAsUnreadUnmutedCount - Total number of unmuted chats marked as unread
     */
    suspend fun onUnreadChatCount(chatList: ChatList, totalCount: Int, unreadCount: Int, unreadUnmutedCount: Int, markedAsUnreadCount: Int, markedAsUnreadUnmutedCount: Int) = Unit

    /**
     * An option changed its value
     *
     * @name - The option name
     * @value - The new option value
     */
    suspend fun onOption(name: String, value: OptionValue) = Unit

    /**
     * A sticker set has changed
     *
     * @stickerSet - The sticker set
     */
    suspend fun onStickerSet(stickerSet: StickerSet) = Unit

    /**
     * The list of installed sticker sets was updated
     *
     * @isMasks - True, if the list of installed mask sticker sets was updated
     * @stickerSetIds - The new list of installed ordinary sticker sets
     */
    suspend fun onInstalledStickerSets(isMasks: Boolean, stickerSetIds: LongArray) = Unit

    /**
     * The list of trending sticker sets was updated or some of them were viewed
     *
     * @stickerSets - The prefix of the list of trending sticker sets with the newest trending sticker sets
     */
    suspend fun onTrendingStickerSets(stickerSets: StickerSets) = Unit

    /**
     * The list of recently used stickers was updated
     *
     * @isAttached - True, if the list of stickers attached to photo or video files was updated, otherwise the list of sent stickers is updated
     * @stickerIds - The new list of file identifiers of recently used stickers
     */
    suspend fun onRecentStickers(isAttached: Boolean, stickerIds: IntArray) = Unit

    /**
     * The list of favorite stickers was updated
     *
     * @stickerIds - The new list of file identifiers of favorite stickers
     */
    suspend fun onFavoriteStickers(stickerIds: IntArray) = Unit

    /**
     * The list of saved animations was updated
     *
     * @animationIds - The new list of file identifiers of saved animations
     */
    suspend fun onSavedAnimations(animationIds: IntArray) = Unit

    /**
     * The selected background has changed
     *
     * @forDarkTheme - True, if background for dark theme has changed
     * @background - The new selected background
     */
    suspend fun onSelectedBackground(forDarkTheme: Boolean, background: Background?) = Unit

    /**
     * Some language pack strings have been updated
     *
     * @localizationTarget - Localization target to which the language pack belongs
     * @languagePackId - Identifier of the updated language pack
     * @strings - List of changed language pack strings
     */
    suspend fun onLanguagePackStrings(localizationTarget: String, languagePackId: String, strings: Array<LanguagePackString>) = Unit

    /**
     * The connection state has changed
     * This update must be used only to show a human-readable description of the connection state
     *
     * @state - The new connection state
     */
    suspend fun onConnectionState(state: ConnectionState) = Unit

    /**
     * New terms of service must be accepted by the user
     * If the terms of service are declined, then the deleteAccount method should be called with the reason "Decline ToS update"
     *
     * @termsOfServiceId - Identifier of the terms of service
     * @termsOfService - The new terms of service
     */
    suspend fun onTermsOfService(termsOfServiceId: String, termsOfService: TermsOfService) = Unit

    /**
     * The list of users nearby has changed
     * The update is guaranteed to be sent only 60 seconds after a successful searchChatsNearby request
     *
     * @usersNearby - The new list of users nearby
     */
    suspend fun onUsersNearby(usersNearby: Array<ChatNearby>) = Unit

    /**
     * The list of supported dice emojis has changed
     *
     * @emojis - The new list of supported dice emojis
     */
    suspend fun onDiceEmojis(emojis: Array<String>) = Unit

    /**
     * The parameters of animation search through GetOption("animation_search_bot_username") bot has changed
     *
     * @provider - Name of the animation search provider
     * @emojis - The new list of emojis suggested for searching
     */
    suspend fun onAnimationSearchParameters(provider: String, emojis: Array<String>) = Unit

    /**
     * The list of suggested to the user actions has changed
     *
     * @addedActions - Added suggested actions
     * @removedActions - Removed suggested actions
     */
    suspend fun onSuggestedActions(addedActions: Array<SuggestedAction>, removedActions: Array<SuggestedAction>) = Unit

    /**
     * A new incoming inline query
     * For bots only
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @userLocation - User location
     * @chatType - Contains information about the type of the chat, from which the query originated
     *             May be null if unknown
     * @query - Text of the query
     * @offset - Offset of the first entry to return
     */
    suspend fun onNewInlineQuery(id: Long, senderUserId: Int, userLocation: Location?, chatType: ChatType, query: String, offset: String) = Unit

    /**
     * The user has chosen a result of an inline query
     * For bots only
     *
     * @senderUserId - Identifier of the user who sent the query
     * @userLocation - User location
     * @query - Text of the query
     * @resultId - Identifier of the chosen result
     * @inlineMessageId - Identifier of the sent inline message, if known
     */
    suspend fun onNewChosenInlineResult(senderUserId: Int, userLocation: Location?, query: String, resultId: String, inlineMessageId: String) = Unit

    /**
     * A new incoming callback query
     * For bots only
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @chatId - Identifier of the chat where the query was sent
     * @messageId - Identifier of the message, from which the query originated
     * @chatInstance - Identifier that uniquely corresponds to the chat to which the message was sent
     * @payload - Query payload
     */
    suspend fun handleNewCallbackQuery(id: Long, senderUserId: Int, chatId: Long, messageId: Long, chatInstance: Long, payload: CallbackQueryPayload) = Unit

    /**
     * A new incoming callback query from a message sent via a bot
     * For bots only
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @inlineMessageId - Identifier of the inline message, from which the query originated
     * @chatInstance - An identifier uniquely corresponding to the chat a message was sent to
     * @payload - Query payload
     */
    suspend fun handleNewInlineCallbackQuery(id: Long, senderUserId: Int, inlineMessageId: String, chatInstance: Long, payload: CallbackQueryPayload) = Unit

    /**
     * A new incoming shipping query
     * For bots only
     * Only for invoices with flexible price
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @invoicePayload - Invoice payload
     * @shippingAddress - User shipping address
     */
    suspend fun onNewShippingQuery(id: Long, senderUserId: Int, invoicePayload: String, shippingAddress: Address) = Unit

    /**
     * A new incoming pre-checkout query
     * For bots only
     * Contains full information about a checkout
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @currency - Currency for the product price
     * @totalAmount - Total price for the product, in the minimal quantity of the currency
     * @invoicePayload - Invoice payload
     * @shippingOptionId - Identifier of a shipping option chosen by the user
     *                     May be empty if not applicable
     * @orderInfo - Information about the order
     */
    suspend fun onNewPreCheckoutQuery(id: Long, senderUserId: Int, currency: String, totalAmount: Long, invoicePayload: ByteArray, shippingOptionId: String?, orderInfo: OrderInfo?) = Unit

    /**
     * A new incoming event
     * For bots only
     *
     * @event - A JSON-serialized event
     */
    suspend fun onNewCustomEvent(event: String) = Unit

    /**
     * A new incoming query
     * For bots only
     *
     * @id - The query identifier
     * @data - JSON-serialized query data
     * @timeout - Query timeout
     */
    suspend fun onNewCustomQuery(id: Long, data: String, timeout: Int) = Unit

    /**
     * A poll was updated
     * For bots only
     *
     * @poll - New data about the poll
     */
    suspend fun onPoll(poll: Poll) = Unit

    /**
     * A user changed the answer to a poll
     * For bots only
     *
     * @pollId - Unique poll identifier
     * @userId - The user, who changed the answer to the poll
     * @optionIds - 0-based identifiers of answer options, chosen by the user
     */
    suspend fun onPollAnswer(pollId: Long, userId: Int, optionIds: IntArray) = Unit

    suspend fun onUpdate(eventObj: Update) {

        when (eventObj) {

            is UpdateAuthorizationState -> onAuthorizationState(eventObj.authorizationState)

            is UpdateNewMessage -> handleNewMessage(eventObj.message)

            is UpdateMessageSendAcknowledged -> onMessageSendAcknowledged(eventObj.chatId, eventObj.messageId)

            is UpdateMessageSendSucceeded -> onMessageSendSucceeded(eventObj.message, eventObj.oldMessageId)

            is UpdateMessageSendFailed -> onMessageSendFailed(eventObj.message, eventObj.oldMessageId, eventObj.errorCode, eventObj.errorMessage)

            is UpdateMessageContent -> onMessageContent(eventObj.chatId, eventObj.messageId, eventObj.newContent)

            is UpdateMessageEdited -> onMessageEdited(eventObj.chatId, eventObj.messageId, eventObj.editDate, eventObj.replyMarkup)

            is UpdateMessageIsPinned -> onMessageIsPinned(eventObj.chatId, eventObj.messageId, eventObj.isPinned)

            is UpdateMessageInteractionInfo -> onMessageInteractionInfo(eventObj.chatId, eventObj.messageId, eventObj.interactionInfo)

            is UpdateMessageContentOpened -> onMessageContentOpened(eventObj.chatId, eventObj.messageId)

            is UpdateMessageMentionRead -> onMessageMentionRead(eventObj.chatId, eventObj.messageId, eventObj.unreadMentionCount)

            is UpdateMessageLiveLocationViewed -> onMessageLiveLocationViewed(eventObj.chatId, eventObj.messageId)

            is UpdateNewChat -> onNewChat(eventObj.chat)

            is UpdateChatTitle -> onChatTitle(eventObj.chatId, eventObj.title)

            is UpdateChatPhoto -> onChatPhoto(eventObj.chatId, eventObj.photo)

            is UpdateChatPermissions -> onChatPermissions(eventObj.chatId, eventObj.permissions)

            is UpdateChatLastMessage -> onChatLastMessage(eventObj.chatId, eventObj.lastMessage, eventObj.positions)

            is UpdateChatPosition -> onChatPosition(eventObj.chatId, eventObj.position)

            is UpdateChatIsMarkedAsUnread -> onChatIsMarkedAsUnread(eventObj.chatId, eventObj.isMarkedAsUnread)

            is UpdateChatIsBlocked -> onChatIsBlocked(eventObj.chatId, eventObj.isBlocked)

            is UpdateChatHasScheduledMessages -> onChatHasScheduledMessages(eventObj.chatId, eventObj.hasScheduledMessages)

            is UpdateChatVoiceChat -> onChatVoiceChat(eventObj.chatId, eventObj.voiceChatGroupCallId, eventObj.isVoiceChatEmpty)

            is UpdateChatDefaultDisableNotification -> onChatDefaultDisableNotification(eventObj.chatId, eventObj.defaultDisableNotification)

            is UpdateChatReadInbox -> onChatReadInbox(eventObj.chatId, eventObj.lastReadInboxMessageId, eventObj.unreadCount)

            is UpdateChatReadOutbox -> onChatReadOutbox(eventObj.chatId, eventObj.lastReadOutboxMessageId)

            is UpdateChatUnreadMentionCount -> onChatUnreadMentionCount(eventObj.chatId, eventObj.unreadMentionCount)

            is UpdateChatNotificationSettings -> onChatNotificationSettings(eventObj.chatId, eventObj.notificationSettings)

            is UpdateScopeNotificationSettings -> onScopeNotificationSettings(eventObj.scope, eventObj.notificationSettings)

            is UpdateChatActionBar -> onChatActionBar(eventObj.chatId, eventObj.actionBar)

            is UpdateChatReplyMarkup -> onChatReplyMarkup(eventObj.chatId, eventObj.replyMarkupMessageId)

            is UpdateChatDraftMessage -> onChatDraftMessage(eventObj.chatId, eventObj.draftMessage, eventObj.positions)

            is UpdateChatFilters -> onChatFilters(eventObj.chatFilters)

            is UpdateChatOnlineMemberCount -> onChatOnlineMemberCount(eventObj.chatId, eventObj.onlineMemberCount)

            is UpdateNotification -> onNotification(eventObj.notificationGroupId, eventObj.notification)

            is UpdateNotificationGroup -> onNotificationGroup(eventObj.notificationGroupId, eventObj.type, eventObj.chatId, eventObj.notificationSettingsChatId, eventObj.isSilent, eventObj.totalCount, eventObj.addedNotifications, eventObj.removedNotificationIds)

            is UpdateActiveNotifications -> onActiveNotifications(eventObj.groups)

            is UpdateHavePendingNotifications -> onHavePendingNotifications(eventObj.haveDelayedNotifications, eventObj.haveUnreceivedNotifications)

            is UpdateDeleteMessages -> onDeleteMessages(eventObj.chatId, eventObj.messageIds, eventObj.isPermanent, eventObj.fromCache)

            is UpdateUserChatAction -> onUserChatAction(eventObj.chatId, eventObj.messageThreadId, eventObj.userId, eventObj.action)

            is UpdateUserStatus -> onUserStatus(eventObj.userId, eventObj.status)

            is UpdateUser -> onUser(eventObj.user)

            is UpdateBasicGroup -> onBasicGroup(eventObj.basicGroup)

            is UpdateSupergroup -> onSupergroup(eventObj.supergroup)

            is UpdateSecretChat -> onSecretChat(eventObj.secretChat)

            is UpdateUserFullInfo -> onUserFullInfo(eventObj.userId, eventObj.userFullInfo)

            is UpdateBasicGroupFullInfo -> onBasicGroupFullInfo(eventObj.basicGroupId, eventObj.basicGroupFullInfo)

            is UpdateSupergroupFullInfo -> onSupergroupFullInfo(eventObj.supergroupId, eventObj.supergroupFullInfo)

            is UpdateServiceNotification -> onServiceNotification(eventObj.type, eventObj.content)

            is UpdateFile -> onFile(eventObj.file)

            is UpdateFileGenerationStart -> onFileGenerationStart(eventObj.generationId, eventObj.originalPath, eventObj.destinationPath, eventObj.conversion)

            is UpdateFileGenerationStop -> onFileGenerationStop(eventObj.generationId)

            is UpdateCall -> onCall(eventObj.call)

            is UpdateGroupCall -> onGroupCall(eventObj.groupCall)

            is UpdateGroupCallParticipant -> onGroupCallParticipant(eventObj.groupCallId, eventObj.participant)

            is UpdateNewCallSignalingData -> onNewCallSignalingData(eventObj.callId, eventObj.data)

            is UpdateUserPrivacySettingRules -> onUserPrivacySettingRules(eventObj.setting, eventObj.rules)

            is UpdateUnreadMessageCount -> onUnreadMessageCount(eventObj.chatList, eventObj.unreadCount, eventObj.unreadUnmutedCount)

            is UpdateUnreadChatCount -> onUnreadChatCount(eventObj.chatList, eventObj.totalCount, eventObj.unreadCount, eventObj.unreadUnmutedCount, eventObj.markedAsUnreadCount, eventObj.markedAsUnreadUnmutedCount)

            is UpdateOption -> onOption(eventObj.name, eventObj.value)

            is UpdateStickerSet -> onStickerSet(eventObj.stickerSet)

            is UpdateInstalledStickerSets -> onInstalledStickerSets(eventObj.isMasks, eventObj.stickerSetIds)

            is UpdateTrendingStickerSets -> onTrendingStickerSets(eventObj.stickerSets)

            is UpdateRecentStickers -> onRecentStickers(eventObj.isAttached, eventObj.stickerIds)

            is UpdateFavoriteStickers -> onFavoriteStickers(eventObj.stickerIds)

            is UpdateSavedAnimations -> onSavedAnimations(eventObj.animationIds)

            is UpdateSelectedBackground -> onSelectedBackground(eventObj.forDarkTheme, eventObj.background)

            is UpdateLanguagePackStrings -> onLanguagePackStrings(eventObj.localizationTarget, eventObj.languagePackId, eventObj.strings)

            is UpdateConnectionState -> onConnectionState(eventObj.state)

            is UpdateTermsOfService -> onTermsOfService(eventObj.termsOfServiceId, eventObj.termsOfService)

            is UpdateUsersNearby -> onUsersNearby(eventObj.usersNearby)

            is UpdateDiceEmojis -> onDiceEmojis(eventObj.emojis)

            is UpdateAnimationSearchParameters -> onAnimationSearchParameters(eventObj.provider, eventObj.emojis)

            is UpdateSuggestedActions -> onSuggestedActions(eventObj.addedActions, eventObj.removedActions)

            is UpdateNewInlineQuery -> onNewInlineQuery(eventObj.id, eventObj.senderUserId, eventObj.userLocation, eventObj.chatType, eventObj.query, eventObj.offset)

            is UpdateNewChosenInlineResult -> onNewChosenInlineResult(eventObj.senderUserId, eventObj.userLocation, eventObj.query, eventObj.resultId, eventObj.inlineMessageId)

            is UpdateNewCallbackQuery -> handleNewCallbackQuery(eventObj.id, eventObj.senderUserId, eventObj.chatId, eventObj.messageId, eventObj.chatInstance, eventObj.payload)

            is UpdateNewInlineCallbackQuery -> handleNewInlineCallbackQuery(eventObj.id, eventObj.senderUserId, eventObj.inlineMessageId, eventObj.chatInstance, eventObj.payload)

            is UpdateNewShippingQuery -> onNewShippingQuery(eventObj.id, eventObj.senderUserId, eventObj.invoicePayload, eventObj.shippingAddress)

            is UpdateNewPreCheckoutQuery -> onNewPreCheckoutQuery(eventObj.id, eventObj.senderUserId, eventObj.currency, eventObj.totalAmount, eventObj.invoicePayload, eventObj.shippingOptionId, eventObj.orderInfo)

            is UpdateNewCustomEvent -> onNewCustomEvent(eventObj.event)

            is UpdateNewCustomQuery -> onNewCustomQuery(eventObj.id, eventObj.data, eventObj.timeout)

            is UpdatePoll -> onPoll(eventObj.poll)

            is UpdatePollAnswer -> onPollAnswer(eventObj.pollId, eventObj.userId, eventObj.optionIds)

        }


    }

}
