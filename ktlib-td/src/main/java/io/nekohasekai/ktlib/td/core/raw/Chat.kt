@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns information about a chat by its identifier, this is an offline request if the current user is not a bot
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.getChat(
    chatId: Long
) = sync(GetChat(chatId))

suspend fun TdHandler.getChatOrNull(
    chatId: Long
) = syncOrNull(GetChat(chatId))

fun TdHandler.getChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(GetChat(chatId), stackIgnore + 1, submit)

/**
 * Returns an ordered list of chats in a chat list
 * Chats are sorted by the pair (chat.position.order, chat.id) in descending order
 * (For example, to get a list of chats from the beginning, the offset_order should be equal to a biggest signed 64-bit number 9223372036854775807 == 2^63 - 1)
 * For optimal performance the number of returned chats is chosen by the library
 *
 * @chatList - The chat list in which to return chats
 * @offsetOrder - Chat order to return chats from
 * @offsetChatId - Chat identifier to return chats from
 * @limit - The maximum number of chats to be returned
 *          It is possible that fewer chats than the limit are returned even if the end of the list is not reached
 */
suspend fun TdHandler.getChats(
    chatList: ChatList? = null,
    offsetOrder: Long,
    offsetChatId: Long,
    limit: Int
) = sync(GetChats(chatList, offsetOrder, offsetChatId, limit))

suspend fun TdHandler.getChatsOrNull(
    chatList: ChatList? = null,
    offsetOrder: Long,
    offsetChatId: Long,
    limit: Int
) = syncOrNull(GetChats(chatList, offsetOrder, offsetChatId, limit))

fun TdHandler.getChatsWith(
    chatList: ChatList? = null,
    offsetOrder: Long,
    offsetChatId: Long,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetChats(chatList, offsetOrder, offsetChatId, limit), stackIgnore + 1, submit)

/**
 * Searches a public chat by its username
 * Currently only private chats, supergroups and channels can be public
 * Returns the chat if found
 * Otherwise an error is returned
 *
 * @username - Username to be resolved
 */
suspend fun TdHandler.searchPublicChat(
    username: String? = null
) = sync(SearchPublicChat(username))

suspend fun TdHandler.searchPublicChatOrNull(
    username: String? = null
) = syncOrNull(SearchPublicChat(username))

fun TdHandler.searchPublicChatWith(
    username: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(SearchPublicChat(username), stackIgnore + 1, submit)

/**
 * Searches public chats by looking for specified query in their username and title
 * Currently only private chats, supergroups and channels can be public
 * Returns a meaningful number of results
 * Returns nothing if the length of the searched username prefix is less than 5
 * Excludes private chats with contacts and chats from the chat list from the results
 *
 * @query - Query to search for
 */
suspend fun TdHandler.searchPublicChats(
    query: String? = null
) = sync(SearchPublicChats(query))

suspend fun TdHandler.searchPublicChatsOrNull(
    query: String? = null
) = syncOrNull(SearchPublicChats(query))

fun TdHandler.searchPublicChatsWith(
    query: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(SearchPublicChats(query), stackIgnore + 1, submit)

/**
 * Searches for the specified query in the title and username of already known chats, this is an offline request
 * Returns chats in the order seen in the main chat list
 *
 * @query - Query to search for
 *          If the query is empty, returns up to 20 recently found chats
 * @limit - The maximum number of chats to be returned
 */
suspend fun TdHandler.searchChats(
    query: String? = null,
    limit: Int
) = sync(SearchChats(query, limit))

suspend fun TdHandler.searchChatsOrNull(
    query: String? = null,
    limit: Int
) = syncOrNull(SearchChats(query, limit))

fun TdHandler.searchChatsWith(
    query: String? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(SearchChats(query, limit), stackIgnore + 1, submit)

/**
 * Searches for the specified query in the title and username of already known chats via request to the server
 * Returns chats in the order seen in the main chat list
 *
 * @query - Query to search for
 * @limit - The maximum number of chats to be returned
 */
suspend fun TdHandler.searchChatsOnServer(
    query: String? = null,
    limit: Int
) = sync(SearchChatsOnServer(query, limit))

suspend fun TdHandler.searchChatsOnServerOrNull(
    query: String? = null,
    limit: Int
) = syncOrNull(SearchChatsOnServer(query, limit))

fun TdHandler.searchChatsOnServerWith(
    query: String? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(SearchChatsOnServer(query, limit), stackIgnore + 1, submit)

/**
 * Returns a list of users and location-based supergroups nearby
 * The list of users nearby will be updated for 60 seconds after the request by the updates updateUsersNearby
 * The request should be sent again every 25 seconds with adjusted location to not miss new chats
 *
 * @location - Current user location
 */
suspend fun TdHandler.searchChatsNearby(
    location: Location? = null
) = sync(SearchChatsNearby(location))

suspend fun TdHandler.searchChatsNearbyOrNull(
    location: Location? = null
) = syncOrNull(SearchChatsNearby(location))

fun TdHandler.searchChatsNearbyWith(
    location: Location? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatsNearby>.() -> Unit)? = null
) = send(SearchChatsNearby(location), stackIgnore + 1, submit)

/**
 * Returns a list of frequently used chats
 * Supported only if the chat info database is enabled
 *
 * @category - Category of chats to be returned
 * @limit - The maximum number of chats to be returned
 *          Up to 30
 */
suspend fun TdHandler.getTopChats(
    category: TopChatCategory? = null,
    limit: Int
) = sync(GetTopChats(category, limit))

suspend fun TdHandler.getTopChatsOrNull(
    category: TopChatCategory? = null,
    limit: Int
) = syncOrNull(GetTopChats(category, limit))

fun TdHandler.getTopChatsWith(
    category: TopChatCategory? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetTopChats(category, limit), stackIgnore + 1, submit)

/**
 * Removes a chat from the list of frequently used chats
 * Supported only if the chat info database is enabled
 *
 * @category - Category of frequently used chats
 * @chatId - Chat identifier
 */
suspend fun TdHandler.removeTopChat(
    category: TopChatCategory? = null,
    chatId: Long
){
    sync(RemoveTopChat(category, chatId))
}


suspend fun TdHandler.removeTopChatIgnoreException(
    category: TopChatCategory? = null,
    chatId: Long
){
    syncOrNull(RemoveTopChat(category, chatId))
}


fun TdHandler.removeTopChatWith(
    category: TopChatCategory? = null,
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveTopChat(category, chatId), stackIgnore + 1, submit)

/**
 * Adds a chat to the list of recently found chats
 * The chat is added to the beginning of the list
 * If the chat is already in the list, it will be removed from the list first
 *
 * @chatId - Identifier of the chat to add
 */
suspend fun TdHandler.addRecentlyFoundChat(
    chatId: Long
){
    sync(AddRecentlyFoundChat(chatId))
}


suspend fun TdHandler.addRecentlyFoundChatIgnoreException(
    chatId: Long
){
    syncOrNull(AddRecentlyFoundChat(chatId))
}


fun TdHandler.addRecentlyFoundChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddRecentlyFoundChat(chatId), stackIgnore + 1, submit)

/**
 * Removes a chat from the list of recently found chats
 *
 * @chatId - Identifier of the chat to be removed
 */
suspend fun TdHandler.removeRecentlyFoundChat(
    chatId: Long
){
    sync(RemoveRecentlyFoundChat(chatId))
}


suspend fun TdHandler.removeRecentlyFoundChatIgnoreException(
    chatId: Long
){
    syncOrNull(RemoveRecentlyFoundChat(chatId))
}


fun TdHandler.removeRecentlyFoundChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveRecentlyFoundChat(chatId), stackIgnore + 1, submit)

/**
 * Clears the list of recently found chats
 */
suspend fun TdHandler.clearRecentlyFoundChats(){
    sync(ClearRecentlyFoundChats())
}


suspend fun TdHandler.clearRecentlyFoundChatsIgnoreException(){
    syncOrNull(ClearRecentlyFoundChats())
}


fun TdHandler.clearRecentlyFoundChatsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ClearRecentlyFoundChats(), stackIgnore + 1, submit)

/**
 * Returns a list of public chats of the specified type, owned by the user
 *
 * @type - Type of the public chats to return
 */
suspend fun TdHandler.getCreatedPublicChats(
    type: PublicChatType? = null
) = sync(GetCreatedPublicChats(type))

suspend fun TdHandler.getCreatedPublicChatsOrNull(
    type: PublicChatType? = null
) = syncOrNull(GetCreatedPublicChats(type))

fun TdHandler.getCreatedPublicChatsWith(
    type: PublicChatType? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetCreatedPublicChats(type), stackIgnore + 1, submit)

/**
 * Returns a list of basic group and supergroup chats, which can be used as a discussion group for a channel
 * Returned basic group chats must be first upgraded to supergroups before they can be set as a discussion group
 * To set a returned supergroup as a discussion group, access to its old messages must be enabled using toggleSupergroupIsAllHistoryAvailable first
 */
suspend fun TdHandler.getSuitableDiscussionChats() = sync(GetSuitableDiscussionChats())

suspend fun TdHandler.getSuitableDiscussionChatsOrNull() = syncOrNull(GetSuitableDiscussionChats())

fun TdHandler.getSuitableDiscussionChatsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetSuitableDiscussionChats(), stackIgnore + 1, submit)

/**
 * Returns a list of recently inactive supergroups and channels
 * Can be used when user reaches limit on the number of joined supergroups and channels and receives CHANNELS_TOO_MUCH error
 */
suspend fun TdHandler.getInactiveSupergroupChats() = sync(GetInactiveSupergroupChats())

suspend fun TdHandler.getInactiveSupergroupChatsOrNull() = syncOrNull(GetInactiveSupergroupChats())

fun TdHandler.getInactiveSupergroupChatsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetInactiveSupergroupChats(), stackIgnore + 1, submit)

/**
 * Returns a list of common group chats with a given user
 * Chats are sorted by their type and creation date
 *
 * @userId - User identifier
 * @offsetChatId - Chat identifier starting from which to return chats
 *                 Use 0 for the first request
 * @limit - The maximum number of chats to be returned
 */
suspend fun TdHandler.getGroupsInCommon(
    userId: Int,
    offsetChatId: Long,
    limit: Int
) = sync(GetGroupsInCommon(userId, offsetChatId, limit))

suspend fun TdHandler.getGroupsInCommonOrNull(
    userId: Int,
    offsetChatId: Long,
    limit: Int
) = syncOrNull(GetGroupsInCommon(userId, offsetChatId, limit))

fun TdHandler.getGroupsInCommonWith(
    userId: Int,
    offsetChatId: Long,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetGroupsInCommon(userId, offsetChatId, limit), stackIgnore + 1, submit)

/**
 * Deletes all messages in the chat
 * Use Chat.can_be_deleted_only_for_self and Chat.can_be_deleted_for_all_users fields to find whether and how the method can be applied to the chat
 *
 * @chatId - Chat identifier
 * @removeFromChatList - Pass true if the chat should be removed from the chat list
 * @revoke - Pass true to try to delete chat history for all users
 */
suspend fun TdHandler.deleteChatHistory(
    chatId: Long,
    removeFromChatList: Boolean,
    revoke: Boolean
){
    sync(DeleteChatHistory(chatId, removeFromChatList, revoke))
}


suspend fun TdHandler.deleteChatHistoryIgnoreException(
    chatId: Long,
    removeFromChatList: Boolean,
    revoke: Boolean
){
    syncOrNull(DeleteChatHistory(chatId, removeFromChatList, revoke))
}


fun TdHandler.deleteChatHistoryWith(
    chatId: Long,
    removeFromChatList: Boolean,
    revoke: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteChatHistory(chatId, removeFromChatList, revoke), stackIgnore + 1, submit)

/**
 * Deletes a chat along with all messages in the corresponding chat for all chat members
 * Requires owner privileges
 * For group chats this will release the username and remove all members
 * Chats with more than 1000 members can't be deleted using this method
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.deleteChat(
    chatId: Long
){
    sync(DeleteChat(chatId))
}


suspend fun TdHandler.deleteChatIgnoreException(
    chatId: Long
){
    syncOrNull(DeleteChat(chatId))
}


fun TdHandler.deleteChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteChat(chatId), stackIgnore + 1, submit)

/**
 * Returns approximate number of messages of the specified type in the chat
 *
 * @chatId - Identifier of the chat in which to count messages
 * @filter - Filter for message content
 *           SearchMessagesFilterEmpty is unsupported in this function
 * @returnLocal - If true, returns count that is available locally without sending network requests, returning -1 if the number of messages is unknown
 */
suspend fun TdHandler.getChatMessageCount(
    chatId: Long,
    filter: SearchMessagesFilter? = null,
    returnLocal: Boolean
) = sync(GetChatMessageCount(chatId, filter, returnLocal))

suspend fun TdHandler.getChatMessageCountOrNull(
    chatId: Long,
    filter: SearchMessagesFilter? = null,
    returnLocal: Boolean
) = syncOrNull(GetChatMessageCount(chatId, filter, returnLocal))

fun TdHandler.getChatMessageCountWith(
    chatId: Long,
    filter: SearchMessagesFilter? = null,
    returnLocal: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Count>.() -> Unit)? = null
) = send(GetChatMessageCount(chatId, filter, returnLocal), stackIgnore + 1, submit)

/**
 * Sends a notification about a screenshot taken in a chat
 * Supported only in private and secret chats
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.sendChatScreenshotTakenNotification(
    chatId: Long
){
    sync(SendChatScreenshotTakenNotification(chatId))
}


suspend fun TdHandler.sendChatScreenshotTakenNotificationIgnoreException(
    chatId: Long
){
    syncOrNull(SendChatScreenshotTakenNotification(chatId))
}


fun TdHandler.sendChatScreenshotTakenNotificationWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SendChatScreenshotTakenNotification(chatId), stackIgnore + 1, submit)

/**
 * Deletes all messages sent by the specified user to a chat
 * Supported only for supergroups
 * Requires can_delete_messages administrator privileges
 *
 * @chatId - Chat identifier
 * @userId - User identifier
 */
suspend fun TdHandler.deleteChatMessagesFromUser(
    chatId: Long,
    userId: Int
){
    sync(DeleteChatMessagesFromUser(chatId, userId))
}


suspend fun TdHandler.deleteChatMessagesFromUserIgnoreException(
    chatId: Long,
    userId: Int
){
    syncOrNull(DeleteChatMessagesFromUser(chatId, userId))
}


fun TdHandler.deleteChatMessagesFromUserWith(
    chatId: Long,
    userId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteChatMessagesFromUser(chatId, userId), stackIgnore + 1, submit)

/**
 * Deletes the default reply markup from a chat
 * Must be called after a one-time keyboard or a ForceReply reply markup has been used
 * UpdateChatReplyMarkup will be sent if the reply markup will be changed
 *
 * @chatId - Chat identifier
 * @messageId - The message identifier of the used keyboard
 */
suspend fun TdHandler.deleteChatReplyMarkup(
    chatId: Long,
    messageId: Long
){
    sync(DeleteChatReplyMarkup(chatId, messageId))
}


suspend fun TdHandler.deleteChatReplyMarkupIgnoreException(
    chatId: Long,
    messageId: Long
){
    syncOrNull(DeleteChatReplyMarkup(chatId, messageId))
}


fun TdHandler.deleteChatReplyMarkupWith(
    chatId: Long,
    messageId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteChatReplyMarkup(chatId, messageId), stackIgnore + 1, submit)

/**
 * Sends a notification about user activity in a chat
 *
 * @chatId - Chat identifier
 * @messageThreadId - If not 0, a message thread identifier in which the action was performed
 * @action - The action description
 */
suspend fun TdHandler.sendChatAction(
    chatId: Long,
    messageThreadId: Long,
    action: ChatAction? = null
){
    sync(SendChatAction(chatId, messageThreadId, action))
}


suspend fun TdHandler.sendChatActionIgnoreException(
    chatId: Long,
    messageThreadId: Long,
    action: ChatAction? = null
){
    syncOrNull(SendChatAction(chatId, messageThreadId, action))
}


fun TdHandler.sendChatActionWith(
    chatId: Long,
    messageThreadId: Long,
    action: ChatAction? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SendChatAction(chatId, messageThreadId, action), stackIgnore + 1, submit)

/**
 * Informs TDLib that the chat is opened by the user
 * Many useful activities depend on the chat being opened or closed (e.g., in supergroups and channels all updates are received only for opened chats)
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.openChat(
    chatId: Long
){
    sync(OpenChat(chatId))
}


suspend fun TdHandler.openChatIgnoreException(
    chatId: Long
){
    syncOrNull(OpenChat(chatId))
}


fun TdHandler.openChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(OpenChat(chatId), stackIgnore + 1, submit)

/**
 * Informs TDLib that the chat is closed by the user
 * Many useful activities depend on the chat being opened or closed
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.closeChat(
    chatId: Long
){
    sync(CloseChat(chatId))
}


suspend fun TdHandler.closeChatIgnoreException(
    chatId: Long
){
    syncOrNull(CloseChat(chatId))
}


fun TdHandler.closeChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CloseChat(chatId), stackIgnore + 1, submit)

/**
 * Marks all mentions in a chat as read
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.readAllChatMentions(
    chatId: Long
){
    sync(ReadAllChatMentions(chatId))
}


suspend fun TdHandler.readAllChatMentionsIgnoreException(
    chatId: Long
){
    syncOrNull(ReadAllChatMentions(chatId))
}


fun TdHandler.readAllChatMentionsWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ReadAllChatMentions(chatId), stackIgnore + 1, submit)

/**
 * Returns an existing chat corresponding to a given user
 *
 * @userId - User identifier
 * @force - If true, the chat will be created without network request
 *          In this case all information about the chat except its type, title and photo can be incorrect
 */
suspend fun TdHandler.createPrivateChat(
    userId: Int,
    force: Boolean
) = sync(CreatePrivateChat(userId, force))

suspend fun TdHandler.createPrivateChatOrNull(
    userId: Int,
    force: Boolean
) = syncOrNull(CreatePrivateChat(userId, force))

fun TdHandler.createPrivateChatWith(
    userId: Int,
    force: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreatePrivateChat(userId, force), stackIgnore + 1, submit)

/**
 * Returns an existing chat corresponding to a known basic group
 *
 * @basicGroupId - Basic group identifier
 * @force - If true, the chat will be created without network request
 *          In this case all information about the chat except its type, title and photo can be incorrect
 */
suspend fun TdHandler.createBasicGroupChat(
    basicGroupId: Int,
    force: Boolean
) = sync(CreateBasicGroupChat(basicGroupId, force))

suspend fun TdHandler.createBasicGroupChatOrNull(
    basicGroupId: Int,
    force: Boolean
) = syncOrNull(CreateBasicGroupChat(basicGroupId, force))

fun TdHandler.createBasicGroupChatWith(
    basicGroupId: Int,
    force: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreateBasicGroupChat(basicGroupId, force), stackIgnore + 1, submit)

/**
 * Returns an existing chat corresponding to a known supergroup or channel
 *
 * @supergroupId - Supergroup or channel identifier
 * @force - If true, the chat will be created without network request
 *          In this case all information about the chat except its type, title and photo can be incorrect
 */
suspend fun TdHandler.createSupergroupChat(
    supergroupId: Int,
    force: Boolean
) = sync(CreateSupergroupChat(supergroupId, force))

suspend fun TdHandler.createSupergroupChatOrNull(
    supergroupId: Int,
    force: Boolean
) = syncOrNull(CreateSupergroupChat(supergroupId, force))

fun TdHandler.createSupergroupChatWith(
    supergroupId: Int,
    force: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreateSupergroupChat(supergroupId, force), stackIgnore + 1, submit)

/**
 * Returns an existing chat corresponding to a known secret chat
 *
 * @secretChatId - Secret chat identifier
 */
suspend fun TdHandler.createSecretChat(
    secretChatId: Int
) = sync(CreateSecretChat(secretChatId))

suspend fun TdHandler.createSecretChatOrNull(
    secretChatId: Int
) = syncOrNull(CreateSecretChat(secretChatId))

fun TdHandler.createSecretChatWith(
    secretChatId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreateSecretChat(secretChatId), stackIgnore + 1, submit)

/**
 * Creates a new basic group and sends a corresponding messageBasicGroupChatCreate
 * Returns the newly created chat
 *
 * @userIds - Identifiers of users to be added to the basic group
 * @title - Title of the new basic group
 */
suspend fun TdHandler.createNewBasicGroupChat(
    userIds: IntArray,
    title: String? = null
) = sync(CreateNewBasicGroupChat(userIds, title))

suspend fun TdHandler.createNewBasicGroupChatOrNull(
    userIds: IntArray,
    title: String? = null
) = syncOrNull(CreateNewBasicGroupChat(userIds, title))

fun TdHandler.createNewBasicGroupChatWith(
    userIds: IntArray,
    title: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreateNewBasicGroupChat(userIds, title), stackIgnore + 1, submit)

/**
 * Creates a new supergroup or channel and sends a corresponding messageSupergroupChatCreate
 * Returns the newly created chat
 *
 * @title - Title of the new chat
 * @isChannel - True, if a channel chat needs to be created
 * @description - Chat description
 * @location - Chat location if a location-based supergroup is being created
 * @forImport - True, if the supergroup is created for importing messages using importMessage
 */
suspend fun TdHandler.createNewSupergroupChat(
    title: String? = null,
    isChannel: Boolean,
    description: String? = null,
    location: ChatLocation? = null,
    forImport: Boolean
) = sync(CreateNewSupergroupChat(title, isChannel, description, location, forImport))

suspend fun TdHandler.createNewSupergroupChatOrNull(
    title: String? = null,
    isChannel: Boolean,
    description: String? = null,
    location: ChatLocation? = null,
    forImport: Boolean
) = syncOrNull(CreateNewSupergroupChat(title, isChannel, description, location, forImport))

fun TdHandler.createNewSupergroupChatWith(
    title: String? = null,
    isChannel: Boolean,
    description: String? = null,
    location: ChatLocation? = null,
    forImport: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreateNewSupergroupChat(title, isChannel, description, location, forImport), stackIgnore + 1, submit)

/**
 * Creates a new secret chat
 * Returns the newly created chat
 *
 * @userId - Identifier of the target user
 */
suspend fun TdHandler.createNewSecretChat(
    userId: Int
) = sync(CreateNewSecretChat(userId))

suspend fun TdHandler.createNewSecretChatOrNull(
    userId: Int
) = syncOrNull(CreateNewSecretChat(userId))

fun TdHandler.createNewSecretChatWith(
    userId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreateNewSecretChat(userId), stackIgnore + 1, submit)

/**
 * Creates a new supergroup from an existing basic group and sends a corresponding messageChatUpgradeTo and messageChatUpgradeFrom
 * Requires creator privileges
 * Deactivates the original basic group
 *
 * @chatId - Identifier of the chat to upgrade
 */
suspend fun TdHandler.upgradeBasicGroupChatToSupergroupChat(
    chatId: Long
) = sync(UpgradeBasicGroupChatToSupergroupChat(chatId))

suspend fun TdHandler.upgradeBasicGroupChatToSupergroupChatOrNull(
    chatId: Long
) = syncOrNull(UpgradeBasicGroupChatToSupergroupChat(chatId))

fun TdHandler.upgradeBasicGroupChatToSupergroupChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(UpgradeBasicGroupChatToSupergroupChat(chatId), stackIgnore + 1, submit)

/**
 * Returns chat lists to which the chat can be added
 * This is an offline request
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.getChatListsToAddChat(
    chatId: Long
) = sync(GetChatListsToAddChat(chatId))

suspend fun TdHandler.getChatListsToAddChatOrNull(
    chatId: Long
) = syncOrNull(GetChatListsToAddChat(chatId))

fun TdHandler.getChatListsToAddChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatLists>.() -> Unit)? = null
) = send(GetChatListsToAddChat(chatId), stackIgnore + 1, submit)

/**
 * Adds a chat to a chat list
 * A chat can't be simultaneously in Main and Archive chat lists, so it is automatically removed from another one if needed
 *
 * @chatId - Chat identifier
 * @chatList - The chat list
 *             Use getChatListsToAddChat to get suitable chat lists
 */
suspend fun TdHandler.addChatToList(
    chatId: Long,
    chatList: ChatList? = null
){
    sync(AddChatToList(chatId, chatList))
}


suspend fun TdHandler.addChatToListIgnoreException(
    chatId: Long,
    chatList: ChatList? = null
){
    syncOrNull(AddChatToList(chatId, chatList))
}


fun TdHandler.addChatToListWith(
    chatId: Long,
    chatList: ChatList? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddChatToList(chatId, chatList), stackIgnore + 1, submit)

/**
 * Returns information about a chat filter by its identifier
 *
 * @chatFilterId - Chat filter identifier
 */
suspend fun TdHandler.getChatFilter(
    chatFilterId: Int
) = sync(GetChatFilter(chatFilterId))

suspend fun TdHandler.getChatFilterOrNull(
    chatFilterId: Int
) = syncOrNull(GetChatFilter(chatFilterId))

fun TdHandler.getChatFilterWith(
    chatFilterId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatFilter>.() -> Unit)? = null
) = send(GetChatFilter(chatFilterId), stackIgnore + 1, submit)

/**
 * Creates new chat filter
 * Returns information about the created chat filter
 *
 * @filter - Chat filter
 */
suspend fun TdHandler.createChatFilter(
    filter: ChatFilter? = null
) = sync(CreateChatFilter(filter))

suspend fun TdHandler.createChatFilterOrNull(
    filter: ChatFilter? = null
) = syncOrNull(CreateChatFilter(filter))

fun TdHandler.createChatFilterWith(
    filter: ChatFilter? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatFilterInfo>.() -> Unit)? = null
) = send(CreateChatFilter(filter), stackIgnore + 1, submit)

/**
 * Edits existing chat filter
 * Returns information about the edited chat filter
 *
 * @chatFilterId - Chat filter identifier
 * @filter - The edited chat filter
 */
suspend fun TdHandler.editChatFilter(
    chatFilterId: Int,
    filter: ChatFilter? = null
) = sync(EditChatFilter(chatFilterId, filter))

suspend fun TdHandler.editChatFilterOrNull(
    chatFilterId: Int,
    filter: ChatFilter? = null
) = syncOrNull(EditChatFilter(chatFilterId, filter))

fun TdHandler.editChatFilterWith(
    chatFilterId: Int,
    filter: ChatFilter? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatFilterInfo>.() -> Unit)? = null
) = send(EditChatFilter(chatFilterId, filter), stackIgnore + 1, submit)

/**
 * Deletes existing chat filter
 *
 * @chatFilterId - Chat filter identifier
 */
suspend fun TdHandler.deleteChatFilter(
    chatFilterId: Int
){
    sync(DeleteChatFilter(chatFilterId))
}


suspend fun TdHandler.deleteChatFilterIgnoreException(
    chatFilterId: Int
){
    syncOrNull(DeleteChatFilter(chatFilterId))
}


fun TdHandler.deleteChatFilterWith(
    chatFilterId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteChatFilter(chatFilterId), stackIgnore + 1, submit)

/**
 * Changes the order of chat filters
 *
 * @chatFilterIds - Identifiers of chat filters in the new correct order
 */
suspend fun TdHandler.reorderChatFilters(
    chatFilterIds: IntArray
){
    sync(ReorderChatFilters(chatFilterIds))
}


suspend fun TdHandler.reorderChatFiltersIgnoreException(
    chatFilterIds: IntArray
){
    syncOrNull(ReorderChatFilters(chatFilterIds))
}


fun TdHandler.reorderChatFiltersWith(
    chatFilterIds: IntArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ReorderChatFilters(chatFilterIds), stackIgnore + 1, submit)

/**
 * Changes the chat title
 * Supported only for basic groups, supergroups and channels
 * Requires can_change_info administrator right
 *
 * @chatId - Chat identifier
 * @title - New title of the chat
 */
suspend fun TdHandler.setChatTitle(
    chatId: Long,
    title: String? = null
){
    sync(SetChatTitle(chatId, title))
}


suspend fun TdHandler.setChatTitleIgnoreException(
    chatId: Long,
    title: String? = null
){
    syncOrNull(SetChatTitle(chatId, title))
}


fun TdHandler.setChatTitleWith(
    chatId: Long,
    title: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatTitle(chatId, title), stackIgnore + 1, submit)

/**
 * Changes the photo of a chat
 * Supported only for basic groups, supergroups and channels
 * Requires can_change_info administrator right
 *
 * @chatId - Chat identifier
 * @photo - New chat photo
 *          Pass null to delete the chat photo
 */
suspend fun TdHandler.setChatPhoto(
    chatId: Long,
    photo: InputChatPhoto? = null
){
    sync(SetChatPhoto(chatId, photo))
}


suspend fun TdHandler.setChatPhotoIgnoreException(
    chatId: Long,
    photo: InputChatPhoto? = null
){
    syncOrNull(SetChatPhoto(chatId, photo))
}


fun TdHandler.setChatPhotoWith(
    chatId: Long,
    photo: InputChatPhoto? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatPhoto(chatId, photo), stackIgnore + 1, submit)

/**
 * Changes the message TTL setting (sets a new self-destruct timer) in a chat
 * Requires can_delete_messages administrator right in basic groups, supergroups and channels Message TTL setting of a chat with the current user (Saved Messages) and the chat 777000 (Telegram) can't be changed
 *
 * @chatId - Chat identifier
 * @ttl - New TTL value, in seconds
 *        Must be one of 0, 86400, 604800 unless chat is secret
 */
suspend fun TdHandler.setChatMessageTtlSetting(
    chatId: Long,
    ttl: Int
){
    sync(SetChatMessageTtlSetting(chatId, ttl))
}


suspend fun TdHandler.setChatMessageTtlSettingIgnoreException(
    chatId: Long,
    ttl: Int
){
    syncOrNull(SetChatMessageTtlSetting(chatId, ttl))
}


fun TdHandler.setChatMessageTtlSettingWith(
    chatId: Long,
    ttl: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatMessageTtlSetting(chatId, ttl), stackIgnore + 1, submit)

/**
 * Changes the chat members permissions
 * Supported only for basic groups and supergroups
 * Requires can_restrict_members administrator right
 *
 * @chatId - Chat identifier
 * @permissions - New non-administrator members permissions in the chat
 */
suspend fun TdHandler.setChatPermissions(
    chatId: Long,
    permissions: ChatPermissions? = null
){
    sync(SetChatPermissions(chatId, permissions))
}


suspend fun TdHandler.setChatPermissionsIgnoreException(
    chatId: Long,
    permissions: ChatPermissions? = null
){
    syncOrNull(SetChatPermissions(chatId, permissions))
}


fun TdHandler.setChatPermissionsWith(
    chatId: Long,
    permissions: ChatPermissions? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatPermissions(chatId, permissions), stackIgnore + 1, submit)

/**
 * Changes the draft message in a chat
 *
 * @chatId - Chat identifier
 * @messageThreadId - If not 0, a message thread identifier in which the draft was changed
 * @draftMessage - New draft message
 */
suspend fun TdHandler.setChatDraftMessage(
    chatId: Long,
    messageThreadId: Long,
    draftMessage: DraftMessage? = null
){
    sync(SetChatDraftMessage(chatId, messageThreadId, draftMessage))
}


suspend fun TdHandler.setChatDraftMessageIgnoreException(
    chatId: Long,
    messageThreadId: Long,
    draftMessage: DraftMessage? = null
){
    syncOrNull(SetChatDraftMessage(chatId, messageThreadId, draftMessage))
}


fun TdHandler.setChatDraftMessageWith(
    chatId: Long,
    messageThreadId: Long,
    draftMessage: DraftMessage? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatDraftMessage(chatId, messageThreadId, draftMessage), stackIgnore + 1, submit)

/**
 * Changes the notification settings of a chat
 * Notification settings of a chat with the current user (Saved Messages) can't be changed
 *
 * @chatId - Chat identifier
 * @notificationSettings - New notification settings for the chat
 *                         If the chat is muted for more than 1 week, it is considered to be muted forever
 */
suspend fun TdHandler.setChatNotificationSettings(
    chatId: Long,
    notificationSettings: ChatNotificationSettings? = null
){
    sync(SetChatNotificationSettings(chatId, notificationSettings))
}


suspend fun TdHandler.setChatNotificationSettingsIgnoreException(
    chatId: Long,
    notificationSettings: ChatNotificationSettings? = null
){
    syncOrNull(SetChatNotificationSettings(chatId, notificationSettings))
}


fun TdHandler.setChatNotificationSettingsWith(
    chatId: Long,
    notificationSettings: ChatNotificationSettings? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatNotificationSettings(chatId, notificationSettings), stackIgnore + 1, submit)

/**
 * Changes the marked as unread state of a chat
 *
 * @chatId - Chat identifier
 * @isMarkedAsUnread - New value of is_marked_as_unread
 */
suspend fun TdHandler.toggleChatIsMarkedAsUnread(
    chatId: Long,
    isMarkedAsUnread: Boolean
){
    sync(ToggleChatIsMarkedAsUnread(chatId, isMarkedAsUnread))
}


suspend fun TdHandler.toggleChatIsMarkedAsUnreadIgnoreException(
    chatId: Long,
    isMarkedAsUnread: Boolean
){
    syncOrNull(ToggleChatIsMarkedAsUnread(chatId, isMarkedAsUnread))
}


fun TdHandler.toggleChatIsMarkedAsUnreadWith(
    chatId: Long,
    isMarkedAsUnread: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ToggleChatIsMarkedAsUnread(chatId, isMarkedAsUnread), stackIgnore + 1, submit)

/**
 * Changes the value of the default disable_notification parameter, used when a message is sent to a chat
 *
 * @chatId - Chat identifier
 * @defaultDisableNotification - New value of default_disable_notification
 */
suspend fun TdHandler.toggleChatDefaultDisableNotification(
    chatId: Long,
    defaultDisableNotification: Boolean
){
    sync(ToggleChatDefaultDisableNotification(chatId, defaultDisableNotification))
}


suspend fun TdHandler.toggleChatDefaultDisableNotificationIgnoreException(
    chatId: Long,
    defaultDisableNotification: Boolean
){
    syncOrNull(ToggleChatDefaultDisableNotification(chatId, defaultDisableNotification))
}


fun TdHandler.toggleChatDefaultDisableNotificationWith(
    chatId: Long,
    defaultDisableNotification: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ToggleChatDefaultDisableNotification(chatId, defaultDisableNotification), stackIgnore + 1, submit)

/**
 * Changes application-specific data associated with a chat
 *
 * @chatId - Chat identifier
 * @clientData - New value of client_data
 */
suspend fun TdHandler.setChatClientData(
    chatId: Long,
    clientData: String? = null
){
    sync(SetChatClientData(chatId, clientData))
}


suspend fun TdHandler.setChatClientDataIgnoreException(
    chatId: Long,
    clientData: String? = null
){
    syncOrNull(SetChatClientData(chatId, clientData))
}


fun TdHandler.setChatClientDataWith(
    chatId: Long,
    clientData: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatClientData(chatId, clientData), stackIgnore + 1, submit)

/**
 * Changes information about a chat
 * Available for basic groups, supergroups, and channels
 * Requires can_change_info administrator right
 *
 * @chatId - Identifier of the chat
 * @description - New chat description
 */
suspend fun TdHandler.setChatDescription(
    chatId: Long,
    description: String? = null
){
    sync(SetChatDescription(chatId, description))
}


suspend fun TdHandler.setChatDescriptionIgnoreException(
    chatId: Long,
    description: String? = null
){
    syncOrNull(SetChatDescription(chatId, description))
}


fun TdHandler.setChatDescriptionWith(
    chatId: Long,
    description: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatDescription(chatId, description), stackIgnore + 1, submit)

/**
 * Changes the discussion group of a channel chat
 * Requires can_change_info administrator right in the channel if it is specified
 *
 * @chatId - Identifier of the channel chat
 *           Pass 0 to remove a link from the supergroup passed in the second argument to a linked channel chat (requires can_pin_messages rights in the supergroup)
 * @discussionChatId - Identifier of a new channel's discussion group
 *                     Use 0 to remove the discussion group
 *                     Use the method getSuitableDiscussionChats to find all suitable groups
 *                     Basic group chats must be first upgraded to supergroup chats
 *                     If new chat members don't have access to old messages in the supergroup, then toggleSupergroupIsAllHistoryAvailable must be used first to change that
 */
suspend fun TdHandler.setChatDiscussionGroup(
    chatId: Long,
    discussionChatId: Long
){
    sync(SetChatDiscussionGroup(chatId, discussionChatId))
}


suspend fun TdHandler.setChatDiscussionGroupIgnoreException(
    chatId: Long,
    discussionChatId: Long
){
    syncOrNull(SetChatDiscussionGroup(chatId, discussionChatId))
}


fun TdHandler.setChatDiscussionGroupWith(
    chatId: Long,
    discussionChatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatDiscussionGroup(chatId, discussionChatId), stackIgnore + 1, submit)

/**
 * Changes the location of a chat
 * Available only for some location-based supergroups, use supergroupFullInfo.can_set_location to check whether the method is allowed to use
 *
 * @chatId - Chat identifier
 * @location - New location for the chat
 *             Must be valid and not null
 */
suspend fun TdHandler.setChatLocation(
    chatId: Long,
    location: ChatLocation? = null
){
    sync(SetChatLocation(chatId, location))
}


suspend fun TdHandler.setChatLocationIgnoreException(
    chatId: Long,
    location: ChatLocation? = null
){
    syncOrNull(SetChatLocation(chatId, location))
}


fun TdHandler.setChatLocationWith(
    chatId: Long,
    location: ChatLocation? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatLocation(chatId, location), stackIgnore + 1, submit)

/**
 * Changes the slow mode delay of a chat
 * Available only for supergroups
 * Requires can_restrict_members rights
 *
 * @chatId - Chat identifier
 * @slowModeDelay - New slow mode delay for the chat
 *                  Must be one of 0, 10, 30, 60, 300, 900, 3600
 */
suspend fun TdHandler.setChatSlowModeDelay(
    chatId: Long,
    slowModeDelay: Int
){
    sync(SetChatSlowModeDelay(chatId, slowModeDelay))
}


suspend fun TdHandler.setChatSlowModeDelayIgnoreException(
    chatId: Long,
    slowModeDelay: Int
){
    syncOrNull(SetChatSlowModeDelay(chatId, slowModeDelay))
}


fun TdHandler.setChatSlowModeDelayWith(
    chatId: Long,
    slowModeDelay: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatSlowModeDelay(chatId, slowModeDelay), stackIgnore + 1, submit)

/**
 * Pins a message in a chat
 * Requires can_pin_messages rights or can_edit_messages rights in the channel
 *
 * @chatId - Identifier of the chat
 * @messageId - Identifier of the new pinned message
 * @disableNotification - True, if there should be no notification about the pinned message
 *                        Notifications are always disabled in channels and private chats
 * @onlyForSelf - True, if the message needs to be pinned for one side only
 *                Private chats only
 */
suspend fun TdHandler.pinChatMessage(
    chatId: Long,
    messageId: Long,
    disableNotification: Boolean,
    onlyForSelf: Boolean
){
    sync(PinChatMessage(chatId, messageId, disableNotification, onlyForSelf))
}


suspend fun TdHandler.pinChatMessageIgnoreException(
    chatId: Long,
    messageId: Long,
    disableNotification: Boolean,
    onlyForSelf: Boolean
){
    syncOrNull(PinChatMessage(chatId, messageId, disableNotification, onlyForSelf))
}


fun TdHandler.pinChatMessageWith(
    chatId: Long,
    messageId: Long,
    disableNotification: Boolean,
    onlyForSelf: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(PinChatMessage(chatId, messageId, disableNotification, onlyForSelf), stackIgnore + 1, submit)

/**
 * Removes a pinned message from a chat
 * Requires can_pin_messages rights in the group or can_edit_messages rights in the channel
 *
 * @chatId - Identifier of the chat
 * @messageId - Identifier of the removed pinned message
 */
suspend fun TdHandler.unpinChatMessage(
    chatId: Long,
    messageId: Long
){
    sync(UnpinChatMessage(chatId, messageId))
}


suspend fun TdHandler.unpinChatMessageIgnoreException(
    chatId: Long,
    messageId: Long
){
    syncOrNull(UnpinChatMessage(chatId, messageId))
}


fun TdHandler.unpinChatMessageWith(
    chatId: Long,
    messageId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(UnpinChatMessage(chatId, messageId), stackIgnore + 1, submit)

/**
 * Removes all pinned messages from a chat
 * Requires can_pin_messages rights in the group or can_edit_messages rights in the channel
 *
 * @chatId - Identifier of the chat
 */
suspend fun TdHandler.unpinAllChatMessages(
    chatId: Long
){
    sync(UnpinAllChatMessages(chatId))
}


suspend fun TdHandler.unpinAllChatMessagesIgnoreException(
    chatId: Long
){
    syncOrNull(UnpinAllChatMessages(chatId))
}


fun TdHandler.unpinAllChatMessagesWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(UnpinAllChatMessages(chatId), stackIgnore + 1, submit)

/**
 * Adds the current user as a new member to a chat
 * Private and secret chats can't be joined using this method
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.joinChat(
    chatId: Long
){
    sync(JoinChat(chatId))
}


suspend fun TdHandler.joinChatIgnoreException(
    chatId: Long
){
    syncOrNull(JoinChat(chatId))
}


fun TdHandler.joinChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(JoinChat(chatId), stackIgnore + 1, submit)

/**
 * Removes the current user from chat members
 * Private and secret chats can't be left using this method
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.leaveChat(
    chatId: Long
){
    sync(LeaveChat(chatId))
}


suspend fun TdHandler.leaveChatIgnoreException(
    chatId: Long
){
    syncOrNull(LeaveChat(chatId))
}


fun TdHandler.leaveChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(LeaveChat(chatId), stackIgnore + 1, submit)

/**
 * Adds a new member to a chat
 * Members can't be added to private or secret chats
 *
 * @chatId - Chat identifier
 * @userId - Identifier of the user
 * @forwardLimit - The number of earlier messages from the chat to be forwarded to the new member
 *                 Ignored for supergroups and channels
 */
suspend fun TdHandler.addChatMember(
    chatId: Long,
    userId: Int,
    forwardLimit: Int
){
    sync(AddChatMember(chatId, userId, forwardLimit))
}


suspend fun TdHandler.addChatMemberIgnoreException(
    chatId: Long,
    userId: Int,
    forwardLimit: Int
){
    syncOrNull(AddChatMember(chatId, userId, forwardLimit))
}


fun TdHandler.addChatMemberWith(
    chatId: Long,
    userId: Int,
    forwardLimit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddChatMember(chatId, userId, forwardLimit), stackIgnore + 1, submit)

/**
 * Adds multiple new members to a chat
 * Currently this method is only available for supergroups and channels
 * This method can't be used to join a chat
 * Members can't be added to a channel if it has more than 200 members
 *
 * @chatId - Chat identifier
 * @userIds - Identifiers of the users to be added to the chat
 *            The maximum number of added users is 20 for supergroups and 100 for channels
 */
suspend fun TdHandler.addChatMembers(
    chatId: Long,
    userIds: IntArray
){
    sync(AddChatMembers(chatId, userIds))
}


suspend fun TdHandler.addChatMembersIgnoreException(
    chatId: Long,
    userIds: IntArray
){
    syncOrNull(AddChatMembers(chatId, userIds))
}


fun TdHandler.addChatMembersWith(
    chatId: Long,
    userIds: IntArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddChatMembers(chatId, userIds), stackIgnore + 1, submit)

/**
 * Changes the status of a chat member, needs appropriate privileges
 * This function is currently not suitable for adding new members to the chat and transferring chat ownership
 * Instead, use addChatMember or transferChatOwnership
 *
 * @chatId - Chat identifier
 * @userId - User identifier
 * @status - The new status of the member in the chat
 */
suspend fun TdHandler.setChatMemberStatus(
    chatId: Long,
    userId: Int,
    status: ChatMemberStatus? = null
){
    sync(SetChatMemberStatus(chatId, userId, status))
}


suspend fun TdHandler.setChatMemberStatusIgnoreException(
    chatId: Long,
    userId: Int,
    status: ChatMemberStatus? = null
){
    syncOrNull(SetChatMemberStatus(chatId, userId, status))
}


fun TdHandler.setChatMemberStatusWith(
    chatId: Long,
    userId: Int,
    status: ChatMemberStatus? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatMemberStatus(chatId, userId, status), stackIgnore + 1, submit)

/**
 * Bans a member in a chat
 * Members can't be banned in private or secret chats
 * In supergroups and channels, the user will not be able to return to the group on their own using invite links, etc., unless unbanned first
 *
 * @chatId - Chat identifier
 * @userId - Identifier of the user
 * @bannedUntilDate - Point in time (Unix timestamp) when the user will be unbanned
 *                    0 if never
 *                    If the user is banned for more than 366 days or for less than 30 seconds from the current time, the user is considered to be banned forever
 *                    Ignored in basic groups
 * @revokeMessages - Pass true to delete all messages in the chat for the user
 *                   Always true for supergroups and channels
 */
suspend fun TdHandler.banChatMember(
    chatId: Long,
    userId: Int,
    bannedUntilDate: Int,
    revokeMessages: Boolean
){
    sync(BanChatMember(chatId, userId, bannedUntilDate, revokeMessages))
}


suspend fun TdHandler.banChatMemberIgnoreException(
    chatId: Long,
    userId: Int,
    bannedUntilDate: Int,
    revokeMessages: Boolean
){
    syncOrNull(BanChatMember(chatId, userId, bannedUntilDate, revokeMessages))
}


fun TdHandler.banChatMemberWith(
    chatId: Long,
    userId: Int,
    bannedUntilDate: Int,
    revokeMessages: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(BanChatMember(chatId, userId, bannedUntilDate, revokeMessages), stackIgnore + 1, submit)

/**
 * Changes the owner of a chat
 * The current user must be a current owner of the chat
 * Use the method canTransferOwnership to check whether the ownership can be transferred from the current session
 * Available only for supergroups and channel chats
 *
 * @chatId - Chat identifier
 * @userId - Identifier of the user to which transfer the ownership
 *           The ownership can't be transferred to a bot or to a deleted user
 * @password - The password of the current user
 */
suspend fun TdHandler.transferChatOwnership(
    chatId: Long,
    userId: Int,
    password: String? = null
){
    sync(TransferChatOwnership(chatId, userId, password))
}


suspend fun TdHandler.transferChatOwnershipIgnoreException(
    chatId: Long,
    userId: Int,
    password: String? = null
){
    syncOrNull(TransferChatOwnership(chatId, userId, password))
}


fun TdHandler.transferChatOwnershipWith(
    chatId: Long,
    userId: Int,
    password: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(TransferChatOwnership(chatId, userId, password), stackIgnore + 1, submit)

/**
 * Returns information about a single member of a chat
 *
 * @chatId - Chat identifier
 * @userId - User identifier
 */
suspend fun TdHandler.getChatMember(
    chatId: Long,
    userId: Int
) = sync(GetChatMember(chatId, userId))

suspend fun TdHandler.getChatMemberOrNull(
    chatId: Long,
    userId: Int
) = syncOrNull(GetChatMember(chatId, userId))

fun TdHandler.getChatMemberWith(
    chatId: Long,
    userId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatMember>.() -> Unit)? = null
) = send(GetChatMember(chatId, userId), stackIgnore + 1, submit)

/**
 * Searches for a specified query in the first name, last name and username of the members of a specified chat
 * Requires administrator rights in channels
 *
 * @chatId - Chat identifier
 * @query - Query to search for
 * @limit - The maximum number of users to be returned
 * @filter - The type of users to return
 *           By default, chatMembersFilterMembers
 */
suspend fun TdHandler.searchChatMembers(
    chatId: Long,
    query: String? = null,
    limit: Int,
    filter: ChatMembersFilter? = null
) = sync(SearchChatMembers(chatId, query, limit, filter))

suspend fun TdHandler.searchChatMembersOrNull(
    chatId: Long,
    query: String? = null,
    limit: Int,
    filter: ChatMembersFilter? = null
) = syncOrNull(SearchChatMembers(chatId, query, limit, filter))

fun TdHandler.searchChatMembersWith(
    chatId: Long,
    query: String? = null,
    limit: Int,
    filter: ChatMembersFilter? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatMembers>.() -> Unit)? = null
) = send(SearchChatMembers(chatId, query, limit, filter), stackIgnore + 1, submit)

/**
 * Returns a list of administrators of the chat with their custom titles
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.getChatAdministrators(
    chatId: Long
) = sync(GetChatAdministrators(chatId))

suspend fun TdHandler.getChatAdministratorsOrNull(
    chatId: Long
) = syncOrNull(GetChatAdministrators(chatId))

fun TdHandler.getChatAdministratorsWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatAdministrators>.() -> Unit)? = null
) = send(GetChatAdministrators(chatId), stackIgnore + 1, submit)

/**
 * Returns list of chats with non-default notification settings
 *
 * @scope - If specified, only chats from the specified scope will be returned
 * @compareSound - If true, also chats with non-default sound will be returned
 */
suspend fun TdHandler.getChatNotificationSettingsExceptions(
    scope: NotificationSettingsScope? = null,
    compareSound: Boolean
) = sync(GetChatNotificationSettingsExceptions(scope, compareSound))

suspend fun TdHandler.getChatNotificationSettingsExceptionsOrNull(
    scope: NotificationSettingsScope? = null,
    compareSound: Boolean
) = syncOrNull(GetChatNotificationSettingsExceptions(scope, compareSound))

fun TdHandler.getChatNotificationSettingsExceptionsWith(
    scope: NotificationSettingsScope? = null,
    compareSound: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetChatNotificationSettingsExceptions(scope, compareSound), stackIgnore + 1, submit)

/**
 * Changes the pinned state of a chat
 * There can be up to GetOption("pinned_chat_count_max")/GetOption("pinned_archived_chat_count_max") pinned non-secret chats and the same number of secret chats in the main/arhive chat list
 *
 * @chatList - Chat list in which to change the pinned state of the chat
 * @chatId - Chat identifier
 * @isPinned - True, if the chat is pinned
 */
suspend fun TdHandler.toggleChatIsPinned(
    chatList: ChatList? = null,
    chatId: Long,
    isPinned: Boolean
){
    sync(ToggleChatIsPinned(chatList, chatId, isPinned))
}


suspend fun TdHandler.toggleChatIsPinnedIgnoreException(
    chatList: ChatList? = null,
    chatId: Long,
    isPinned: Boolean
){
    syncOrNull(ToggleChatIsPinned(chatList, chatId, isPinned))
}


fun TdHandler.toggleChatIsPinnedWith(
    chatList: ChatList? = null,
    chatId: Long,
    isPinned: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ToggleChatIsPinned(chatList, chatId, isPinned), stackIgnore + 1, submit)

/**
 * Changes the order of pinned chats
 *
 * @chatList - Chat list in which to change the order of pinned chats
 * @chatIds - The new list of pinned chats
 */
suspend fun TdHandler.setPinnedChats(
    chatList: ChatList? = null,
    chatIds: LongArray
){
    sync(SetPinnedChats(chatList, chatIds))
}


suspend fun TdHandler.setPinnedChatsIgnoreException(
    chatList: ChatList? = null,
    chatIds: LongArray
){
    syncOrNull(SetPinnedChats(chatList, chatIds))
}


fun TdHandler.setPinnedChatsWith(
    chatList: ChatList? = null,
    chatIds: LongArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetPinnedChats(chatList, chatIds), stackIgnore + 1, submit)

/**
 * Replaces current primary invite link for a chat with a new primary invite link
 * Available for basic groups, supergroups, and channels
 * Requires administrator privileges and can_invite_users right
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.replacePrimaryChatInviteLink(
    chatId: Long
) = sync(ReplacePrimaryChatInviteLink(chatId))

suspend fun TdHandler.replacePrimaryChatInviteLinkOrNull(
    chatId: Long
) = syncOrNull(ReplacePrimaryChatInviteLink(chatId))

fun TdHandler.replacePrimaryChatInviteLinkWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatInviteLink>.() -> Unit)? = null
) = send(ReplacePrimaryChatInviteLink(chatId), stackIgnore + 1, submit)

/**
 * Creates a new invite link for a chat
 * Available for basic groups, supergroups, and channels
 * Requires administrator privileges and can_invite_users right in the chat
 *
 * @chatId - Chat identifier
 * @expireDate - Point in time (Unix timestamp) when the link will expire
 *               Pass 0 if never
 * @memberLimit - Maximum number of chat members that can join the chat by the link simultaneously
 *                Pass 0 if not limited
 */
suspend fun TdHandler.createChatInviteLink(
    chatId: Long,
    expireDate: Int,
    memberLimit: Int
) = sync(CreateChatInviteLink(chatId, expireDate, memberLimit))

suspend fun TdHandler.createChatInviteLinkOrNull(
    chatId: Long,
    expireDate: Int,
    memberLimit: Int
) = syncOrNull(CreateChatInviteLink(chatId, expireDate, memberLimit))

fun TdHandler.createChatInviteLinkWith(
    chatId: Long,
    expireDate: Int,
    memberLimit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatInviteLink>.() -> Unit)? = null
) = send(CreateChatInviteLink(chatId, expireDate, memberLimit), stackIgnore + 1, submit)

/**
 * Edits a non-primary invite link for a chat
 * Available for basic groups, supergroups, and channels
 * Requires administrator privileges and can_invite_users right in the chat for own links and owner privileges for other links
 *
 * @chatId - Chat identifier
 * @inviteLink - Invite link to be edited
 * @expireDate - Point in time (Unix timestamp) when the link will expire
 *               Pass 0 if never
 * @memberLimit - Maximum number of chat members that can join the chat by the link simultaneously
 *                Pass 0 if not limited
 */
suspend fun TdHandler.editChatInviteLink(
    chatId: Long,
    inviteLink: String? = null,
    expireDate: Int,
    memberLimit: Int
) = sync(EditChatInviteLink(chatId, inviteLink, expireDate, memberLimit))

suspend fun TdHandler.editChatInviteLinkOrNull(
    chatId: Long,
    inviteLink: String? = null,
    expireDate: Int,
    memberLimit: Int
) = syncOrNull(EditChatInviteLink(chatId, inviteLink, expireDate, memberLimit))

fun TdHandler.editChatInviteLinkWith(
    chatId: Long,
    inviteLink: String? = null,
    expireDate: Int,
    memberLimit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatInviteLink>.() -> Unit)? = null
) = send(EditChatInviteLink(chatId, inviteLink, expireDate, memberLimit), stackIgnore + 1, submit)

/**
 * Returns information about an invite link
 * Requires administrator privileges and can_invite_users right in the chat to get own links and owner privileges to get other links
 *
 * @chatId - Chat identifier
 * @inviteLink - Invite link to get
 */
suspend fun TdHandler.getChatInviteLink(
    chatId: Long,
    inviteLink: String? = null
) = sync(GetChatInviteLink(chatId, inviteLink))

suspend fun TdHandler.getChatInviteLinkOrNull(
    chatId: Long,
    inviteLink: String? = null
) = syncOrNull(GetChatInviteLink(chatId, inviteLink))

fun TdHandler.getChatInviteLinkWith(
    chatId: Long,
    inviteLink: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatInviteLink>.() -> Unit)? = null
) = send(GetChatInviteLink(chatId, inviteLink), stackIgnore + 1, submit)

/**
 * Returns list of chat administrators with number of their invite links
 * Requires owner privileges in the chat
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.getChatInviteLinkCounts(
    chatId: Long
) = sync(GetChatInviteLinkCounts(chatId))

suspend fun TdHandler.getChatInviteLinkCountsOrNull(
    chatId: Long
) = syncOrNull(GetChatInviteLinkCounts(chatId))

fun TdHandler.getChatInviteLinkCountsWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatInviteLinkCounts>.() -> Unit)? = null
) = send(GetChatInviteLinkCounts(chatId), stackIgnore + 1, submit)

/**
 * Returns invite links for a chat created by specified administrator
 * Requires administrator privileges and can_invite_users right in the chat to get own links and owner privileges to get other links
 *
 * @chatId - Chat identifier
 * @creatorUserId - User identifier of a chat administrator
 *                  Must be an identifier of the current user for non-owner
 * @isRevoked - Pass true if revoked links needs to be returned instead of active or expired
 * @offsetDate - Creation date of an invite link starting after which to return invite links
 *               Use 0 to get results from the beginning
 * @offsetInviteLink - Invite link starting after which to return invite links
 *                     Use empty string to get results from the beginning
 * @limit - Maximum number of invite links to return
 */
suspend fun TdHandler.getChatInviteLinks(
    chatId: Long,
    creatorUserId: Int,
    isRevoked: Boolean,
    offsetDate: Int,
    offsetInviteLink: String? = null,
    limit: Int
) = sync(GetChatInviteLinks(chatId, creatorUserId, isRevoked, offsetDate, offsetInviteLink, limit))

suspend fun TdHandler.getChatInviteLinksOrNull(
    chatId: Long,
    creatorUserId: Int,
    isRevoked: Boolean,
    offsetDate: Int,
    offsetInviteLink: String? = null,
    limit: Int
) = syncOrNull(GetChatInviteLinks(chatId, creatorUserId, isRevoked, offsetDate, offsetInviteLink, limit))

fun TdHandler.getChatInviteLinksWith(
    chatId: Long,
    creatorUserId: Int,
    isRevoked: Boolean,
    offsetDate: Int,
    offsetInviteLink: String? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatInviteLinks>.() -> Unit)? = null
) = send(GetChatInviteLinks(chatId, creatorUserId, isRevoked, offsetDate, offsetInviteLink, limit), stackIgnore + 1, submit)

/**
 * Returns chat members joined a chat by an invite link
 * Requires administrator privileges and can_invite_users right in the chat for own links and owner privileges for other links
 *
 * @chatId - Chat identifier
 * @inviteLink - Invite link for which to return chat members
 * @offsetMember - A chat member from which to return next chat members
 *                 Use null to get results from the beginning
 * @limit - Maximum number of chat members to return
 */
suspend fun TdHandler.getChatInviteLinkMembers(
    chatId: Long,
    inviteLink: String? = null,
    offsetMember: ChatInviteLinkMember? = null,
    limit: Int
) = sync(GetChatInviteLinkMembers(chatId, inviteLink, offsetMember, limit))

suspend fun TdHandler.getChatInviteLinkMembersOrNull(
    chatId: Long,
    inviteLink: String? = null,
    offsetMember: ChatInviteLinkMember? = null,
    limit: Int
) = syncOrNull(GetChatInviteLinkMembers(chatId, inviteLink, offsetMember, limit))

fun TdHandler.getChatInviteLinkMembersWith(
    chatId: Long,
    inviteLink: String? = null,
    offsetMember: ChatInviteLinkMember? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatInviteLinkMembers>.() -> Unit)? = null
) = send(GetChatInviteLinkMembers(chatId, inviteLink, offsetMember, limit), stackIgnore + 1, submit)

/**
 * Revokes invite link for a chat
 * Available for basic groups, supergroups, and channels
 * Requires administrator privileges and can_invite_users right in the chat for own links and owner privileges for other links
 * If a primary link is revoked, then additionally to the revoked link returns new primary link
 *
 * @chatId - Chat identifier
 * @inviteLink - Invite link to be revoked
 */
suspend fun TdHandler.revokeChatInviteLink(
    chatId: Long,
    inviteLink: String? = null
) = sync(RevokeChatInviteLink(chatId, inviteLink))

suspend fun TdHandler.revokeChatInviteLinkOrNull(
    chatId: Long,
    inviteLink: String? = null
) = syncOrNull(RevokeChatInviteLink(chatId, inviteLink))

fun TdHandler.revokeChatInviteLinkWith(
    chatId: Long,
    inviteLink: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatInviteLinks>.() -> Unit)? = null
) = send(RevokeChatInviteLink(chatId, inviteLink), stackIgnore + 1, submit)

/**
 * Deletes revoked chat invite links
 * Requires administrator privileges and can_invite_users right in the chat for own links and owner privileges for other links
 *
 * @chatId - Chat identifier
 * @inviteLink - Invite link to revoke
 */
suspend fun TdHandler.deleteRevokedChatInviteLink(
    chatId: Long,
    inviteLink: String? = null
){
    sync(DeleteRevokedChatInviteLink(chatId, inviteLink))
}


suspend fun TdHandler.deleteRevokedChatInviteLinkIgnoreException(
    chatId: Long,
    inviteLink: String? = null
){
    syncOrNull(DeleteRevokedChatInviteLink(chatId, inviteLink))
}


fun TdHandler.deleteRevokedChatInviteLinkWith(
    chatId: Long,
    inviteLink: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteRevokedChatInviteLink(chatId, inviteLink), stackIgnore + 1, submit)

/**
 * Deletes all revoked chat invite links created by a given chat administrator
 * Requires administrator privileges and can_invite_users right in the chat for own links and owner privileges for other links
 *
 * @chatId - Chat identifier
 * @creatorUserId - User identifier of a chat administrator, which links will be deleted
 *                  Must be an identifier of the current user for non-owner
 */
suspend fun TdHandler.deleteAllRevokedChatInviteLinks(
    chatId: Long,
    creatorUserId: Int
){
    sync(DeleteAllRevokedChatInviteLinks(chatId, creatorUserId))
}


suspend fun TdHandler.deleteAllRevokedChatInviteLinksIgnoreException(
    chatId: Long,
    creatorUserId: Int
){
    syncOrNull(DeleteAllRevokedChatInviteLinks(chatId, creatorUserId))
}


fun TdHandler.deleteAllRevokedChatInviteLinksWith(
    chatId: Long,
    creatorUserId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteAllRevokedChatInviteLinks(chatId, creatorUserId), stackIgnore + 1, submit)

/**
 * Checks the validity of an invite link for a chat and returns information about the corresponding chat
 *
 * @inviteLink - Invite link to be checked
 *               Must have URL "t.me", "telegram.me", or "telegram.dog" and query beginning with "/joinchat/" or "/+"
 */
suspend fun TdHandler.checkChatInviteLink(
    inviteLink: String? = null
) = sync(CheckChatInviteLink(inviteLink))

suspend fun TdHandler.checkChatInviteLinkOrNull(
    inviteLink: String? = null
) = syncOrNull(CheckChatInviteLink(inviteLink))

fun TdHandler.checkChatInviteLinkWith(
    inviteLink: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatInviteLinkInfo>.() -> Unit)? = null
) = send(CheckChatInviteLink(inviteLink), stackIgnore + 1, submit)

/**
 * Uses an invite link to add the current user to the chat if possible
 *
 * @inviteLink - Invite link to import
 *               Must have URL "t.me", "telegram.me", or "telegram.dog" and query beginning with "/joinchat/" or "/+"
 */
suspend fun TdHandler.joinChatByInviteLink(
    inviteLink: String? = null
) = sync(JoinChatByInviteLink(inviteLink))

suspend fun TdHandler.joinChatByInviteLinkOrNull(
    inviteLink: String? = null
) = syncOrNull(JoinChatByInviteLink(inviteLink))

fun TdHandler.joinChatByInviteLinkWith(
    inviteLink: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(JoinChatByInviteLink(inviteLink), stackIgnore + 1, submit)

/**
 * Returns the profile photos of a user
 * The result of this query may be outdated: some photos might have been deleted already
 *
 * @userId - User identifier
 * @offset - The number of photos to skip
 * @limit - The maximum number of photos to be returned
 */
suspend fun TdHandler.getUserProfilePhotos(
    userId: Int,
    offset: Int,
    limit: Int
) = sync(GetUserProfilePhotos(userId, offset, limit))

suspend fun TdHandler.getUserProfilePhotosOrNull(
    userId: Int,
    offset: Int,
    limit: Int
) = syncOrNull(GetUserProfilePhotos(userId, offset, limit))

fun TdHandler.getUserProfilePhotosWith(
    userId: Int,
    offset: Int,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatPhotos>.() -> Unit)? = null
) = send(GetUserProfilePhotos(userId, offset, limit), stackIgnore + 1, submit)

/**
 * Returns information about members or banned users in a supergroup or channel
 * Can be used only if SupergroupFullInfo.can_get_members == true
 * Additionally, administrator privileges may be required for some filters
 *
 * @supergroupId - Identifier of the supergroup or channel
 * @filter - The type of users to return
 *           By default, supergroupMembersFilterRecent
 * @offset - Number of users to skip
 * @limit - The maximum number of users be returned
 *          Up to 200
 */
suspend fun TdHandler.getSupergroupMembers(
    supergroupId: Int,
    filter: SupergroupMembersFilter? = null,
    offset: Int,
    limit: Int
) = sync(GetSupergroupMembers(supergroupId, filter, offset, limit))

suspend fun TdHandler.getSupergroupMembersOrNull(
    supergroupId: Int,
    filter: SupergroupMembersFilter? = null,
    offset: Int,
    limit: Int
) = syncOrNull(GetSupergroupMembers(supergroupId, filter, offset, limit))

fun TdHandler.getSupergroupMembersWith(
    supergroupId: Int,
    filter: SupergroupMembersFilter? = null,
    offset: Int,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatMembers>.() -> Unit)? = null
) = send(GetSupergroupMembers(supergroupId, filter, offset, limit), stackIgnore + 1, submit)

/**
 * Returns a list of service actions taken by chat members and administrators in the last 48 hours
 * Available only for supergroups and channels
 * Requires administrator rights
 * Returns results in reverse chronological order (i
 * E., in order of decreasing event_id)
 *
 * @chatId - Chat identifier
 * @query - Search query by which to filter events
 * @fromEventId - Identifier of an event from which to return results
 *                Use 0 to get results from the latest events
 * @limit - The maximum number of events to return
 * @filters - The types of events to return
 *            By default, all types will be returned
 * @userIds - User identifiers by which to filter events
 *            By default, events relating to all users will be returned
 */
suspend fun TdHandler.getChatEventLog(
    chatId: Long,
    query: String? = null,
    fromEventId: Long,
    limit: Int,
    filters: ChatEventLogFilters? = null,
    userIds: IntArray
) = sync(GetChatEventLog(chatId, query, fromEventId, limit, filters, userIds))

suspend fun TdHandler.getChatEventLogOrNull(
    chatId: Long,
    query: String? = null,
    fromEventId: Long,
    limit: Int,
    filters: ChatEventLogFilters? = null,
    userIds: IntArray
) = syncOrNull(GetChatEventLog(chatId, query, fromEventId, limit, filters, userIds))

fun TdHandler.getChatEventLogWith(
    chatId: Long,
    query: String? = null,
    fromEventId: Long,
    limit: Int,
    filters: ChatEventLogFilters? = null,
    userIds: IntArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatEvents>.() -> Unit)? = null
) = send(GetChatEventLog(chatId, query, fromEventId, limit, filters, userIds), stackIgnore + 1, submit)

/**
 * Removes a chat action bar without any other action
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.removeChatActionBar(
    chatId: Long
){
    sync(RemoveChatActionBar(chatId))
}


suspend fun TdHandler.removeChatActionBarIgnoreException(
    chatId: Long
){
    syncOrNull(RemoveChatActionBar(chatId))
}


fun TdHandler.removeChatActionBarWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveChatActionBar(chatId), stackIgnore + 1, submit)

/**
 * Reports a chat to the Telegram moderators
 * A chat can be reported only from the chat action bar, or if this is a private chat with a bot, a private chat with a user sharing their location, a supergroup, or a channel, since other chats can't be checked by moderators
 *
 * @chatId - Chat identifier
 * @messageIds - Identifiers of reported messages, if any
 * @reason - The reason for reporting the chat
 * @text - Additional report details
 */
suspend fun TdHandler.reportChat(
    chatId: Long,
    messageIds: LongArray,
    reason: ChatReportReason? = null,
    text: String? = null
){
    sync(ReportChat(chatId, messageIds, reason, text))
}


suspend fun TdHandler.reportChatIgnoreException(
    chatId: Long,
    messageIds: LongArray,
    reason: ChatReportReason? = null,
    text: String? = null
){
    syncOrNull(ReportChat(chatId, messageIds, reason, text))
}


fun TdHandler.reportChatWith(
    chatId: Long,
    messageIds: LongArray,
    reason: ChatReportReason? = null,
    text: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ReportChat(chatId, messageIds, reason, text), stackIgnore + 1, submit)

/**
 * Reports a chat photo to the Telegram moderators
 * A chat photo can be reported only if this is a private chat with a bot, a private chat with a user sharing their location, a supergroup, or a channel, since other chats can't be checked by moderators
 *
 * @chatId - Chat identifier
 * @fileId - Identifier of the photo to report
 *           Only full photos from chatPhoto can be reported
 * @reason - The reason for reporting the chat photo
 * @text - Additional report details
 */
suspend fun TdHandler.reportChatPhoto(
    chatId: Long,
    fileId: Int,
    reason: ChatReportReason? = null,
    text: String? = null
){
    sync(ReportChatPhoto(chatId, fileId, reason, text))
}


suspend fun TdHandler.reportChatPhotoIgnoreException(
    chatId: Long,
    fileId: Int,
    reason: ChatReportReason? = null,
    text: String? = null
){
    syncOrNull(ReportChatPhoto(chatId, fileId, reason, text))
}


fun TdHandler.reportChatPhotoWith(
    chatId: Long,
    fileId: Int,
    reason: ChatReportReason? = null,
    text: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ReportChatPhoto(chatId, fileId, reason, text), stackIgnore + 1, submit)

/**
 * Returns an HTTP URL with the chat statistics
 * Currently this method of getting the statistics are disabled and can be deleted in the future
 *
 * @chatId - Chat identifier
 * @parameters - Parameters from "tg://statsrefresh?params=******" link
 * @isDark - Pass true if a URL with the dark theme must be returned
 */
suspend fun TdHandler.getChatStatisticsUrl(
    chatId: Long,
    parameters: String? = null,
    isDark: Boolean
) = sync(GetChatStatisticsUrl(chatId, parameters, isDark))

suspend fun TdHandler.getChatStatisticsUrlOrNull(
    chatId: Long,
    parameters: String? = null,
    isDark: Boolean
) = syncOrNull(GetChatStatisticsUrl(chatId, parameters, isDark))

fun TdHandler.getChatStatisticsUrlWith(
    chatId: Long,
    parameters: String? = null,
    isDark: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<HttpUrl>.() -> Unit)? = null
) = send(GetChatStatisticsUrl(chatId, parameters, isDark), stackIgnore + 1, submit)

/**
 * Returns detailed statistics about a chat
 * Currently this method can be used only for supergroups and channels
 * Can be used only if SupergroupFullInfo.can_get_statistics == true
 *
 * @chatId - Chat identifier
 * @isDark - Pass true if a dark theme is used by the application
 */
suspend fun TdHandler.getChatStatistics(
    chatId: Long,
    isDark: Boolean
) = sync(GetChatStatistics(chatId, isDark))

suspend fun TdHandler.getChatStatisticsOrNull(
    chatId: Long,
    isDark: Boolean
) = syncOrNull(GetChatStatistics(chatId, isDark))

fun TdHandler.getChatStatisticsWith(
    chatId: Long,
    isDark: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatStatistics>.() -> Unit)? = null
) = send(GetChatStatistics(chatId, isDark), stackIgnore + 1, submit)
