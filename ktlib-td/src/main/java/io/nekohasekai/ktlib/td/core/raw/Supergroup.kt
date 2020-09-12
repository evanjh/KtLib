@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns information about a supergroup or a channel by its identifier
 * This is an offline request if the current user is not a bot
 *
 * @supergroupId - Supergroup or channel identifier
 */
suspend fun TdHandler.getSupergroup(
    supergroupId: Int
) = sync<Supergroup>(GetSupergroup(supergroupId))

suspend fun TdHandler.getSupergroupOrNull(
    supergroupId: Int
) = syncOrNull<Supergroup>(GetSupergroup(supergroupId))

fun TdHandler.getSupergroupWith(
    supergroupId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Supergroup>.() -> Unit)? = null
) = send(GetSupergroup(supergroupId), stackIgnore + 1, submit)

/**
 * Returns full information about a supergroup or a channel by its identifier, cached for up to 1 minute
 *
 * @supergroupId - Supergroup or channel identifier
 */
suspend fun TdHandler.getSupergroupFullInfo(
    supergroupId: Int
) = sync<SupergroupFullInfo>(GetSupergroupFullInfo(supergroupId))

suspend fun TdHandler.getSupergroupFullInfoOrNull(
    supergroupId: Int
) = syncOrNull<SupergroupFullInfo>(GetSupergroupFullInfo(supergroupId))

fun TdHandler.getSupergroupFullInfoWith(
    supergroupId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<SupergroupFullInfo>.() -> Unit)? = null
) = send(GetSupergroupFullInfo(supergroupId), stackIgnore + 1, submit)

/**
 * Changes the username of a supergroup or channel, requires owner privileges in the supergroup or channel
 *
 * @supergroupId - Identifier of the supergroup or channel
 * @username - New value of the username
 *             Use an empty string to remove the username
 */
suspend fun TdHandler.setSupergroupUsername(
    supergroupId: Int,
    username: String? = null
) = sync<Ok>(SetSupergroupUsername(supergroupId, username))

suspend fun TdHandler.setSupergroupUsernameOrNull(
    supergroupId: Int,
    username: String? = null
) = syncOrNull<Ok>(SetSupergroupUsername(supergroupId, username))

fun TdHandler.setSupergroupUsernameWith(
    supergroupId: Int,
    username: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetSupergroupUsername(supergroupId, username), stackIgnore + 1, submit)

/**
 * Changes the sticker set of a supergroup
 * Requires can_change_info rights
 *
 * @supergroupId - Identifier of the supergroup
 * @stickerSetId - New value of the supergroup sticker set identifier
 *                 Use 0 to remove the supergroup sticker set
 */
suspend fun TdHandler.setSupergroupStickerSet(
    supergroupId: Int,
    stickerSetId: Long
) = sync<Ok>(SetSupergroupStickerSet(supergroupId, stickerSetId))

suspend fun TdHandler.setSupergroupStickerSetOrNull(
    supergroupId: Int,
    stickerSetId: Long
) = syncOrNull<Ok>(SetSupergroupStickerSet(supergroupId, stickerSetId))

fun TdHandler.setSupergroupStickerSetWith(
    supergroupId: Int,
    stickerSetId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetSupergroupStickerSet(supergroupId, stickerSetId), stackIgnore + 1, submit)

/**
 * Toggles sender signatures messages sent in a channel
 * Requires can_change_info rights
 *
 * @supergroupId - Identifier of the channel
 * @signMessages - New value of sign_messages
 */
suspend fun TdHandler.toggleSupergroupSignMessages(
    supergroupId: Int,
    signMessages: Boolean
) = sync<Ok>(ToggleSupergroupSignMessages(supergroupId, signMessages))

suspend fun TdHandler.toggleSupergroupSignMessagesOrNull(
    supergroupId: Int,
    signMessages: Boolean
) = syncOrNull<Ok>(ToggleSupergroupSignMessages(supergroupId, signMessages))

fun TdHandler.toggleSupergroupSignMessagesWith(
    supergroupId: Int,
    signMessages: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ToggleSupergroupSignMessages(supergroupId, signMessages), stackIgnore + 1, submit)

/**
 * Toggles whether the message history of a supergroup is available to new members
 * Requires can_change_info rights
 *
 * @supergroupId - The identifier of the supergroup
 * @isAllHistoryAvailable - The new value of is_all_history_available
 */
suspend fun TdHandler.toggleSupergroupIsAllHistoryAvailable(
    supergroupId: Int,
    isAllHistoryAvailable: Boolean
) = sync<Ok>(ToggleSupergroupIsAllHistoryAvailable(supergroupId, isAllHistoryAvailable))

suspend fun TdHandler.toggleSupergroupIsAllHistoryAvailableOrNull(
    supergroupId: Int,
    isAllHistoryAvailable: Boolean
) = syncOrNull<Ok>(ToggleSupergroupIsAllHistoryAvailable(supergroupId, isAllHistoryAvailable))

fun TdHandler.toggleSupergroupIsAllHistoryAvailableWith(
    supergroupId: Int,
    isAllHistoryAvailable: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ToggleSupergroupIsAllHistoryAvailable(supergroupId, isAllHistoryAvailable), stackIgnore + 1, submit)

/**
 * Reports some messages from a user in a supergroup as spam
 * Requires administrator rights in the supergroup
 *
 * @supergroupId - Supergroup identifier
 * @userId - User identifier
 * @messageIds - Identifiers of messages sent in the supergroup by the user
 *               This list must be non-empty
 */
suspend fun TdHandler.reportSupergroupSpam(
    supergroupId: Int,
    userId: Int,
    messageIds: LongArray
) = sync<Ok>(ReportSupergroupSpam(supergroupId, userId, messageIds))

suspend fun TdHandler.reportSupergroupSpamOrNull(
    supergroupId: Int,
    userId: Int,
    messageIds: LongArray
) = syncOrNull<Ok>(ReportSupergroupSpam(supergroupId, userId, messageIds))

fun TdHandler.reportSupergroupSpamWith(
    supergroupId: Int,
    userId: Int,
    messageIds: LongArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ReportSupergroupSpam(supergroupId, userId, messageIds), stackIgnore + 1, submit)

/**
 * Deletes a supergroup or channel along with all messages in the corresponding chat
 * This will release the supergroup or channel username and remove all members
 * Requires owner privileges in the supergroup or channel
 * Chats with more than 1000 members can't be deleted using this method
 *
 * @supergroupId - Identifier of the supergroup or channel
 */
suspend fun TdHandler.deleteSupergroup(
    supergroupId: Int
) = sync<Ok>(DeleteSupergroup(supergroupId))

suspend fun TdHandler.deleteSupergroupOrNull(
    supergroupId: Int
) = syncOrNull<Ok>(DeleteSupergroup(supergroupId))

fun TdHandler.deleteSupergroupWith(
    supergroupId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteSupergroup(supergroupId), stackIgnore + 1, submit)
