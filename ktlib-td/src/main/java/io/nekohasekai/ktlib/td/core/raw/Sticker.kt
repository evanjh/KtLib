@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns stickers from the installed sticker sets that correspond to a given emoji
 * If the emoji is not empty, favorite and recently used stickers may also be returned
 *
 * @emoji - String representation of emoji
 *          If empty, returns all known installed stickers
 * @limit - The maximum number of stickers to be returned
 */
suspend fun TdHandler.getStickers(
    emoji: String? = null,
    limit: Int
) = sync(GetStickers(emoji, limit))

suspend fun TdHandler.getStickersOrNull(
    emoji: String? = null,
    limit: Int
) = syncOrNull(GetStickers(emoji, limit))

fun TdHandler.getStickersWith(
    emoji: String? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Stickers>.() -> Unit)? = null
) = send(GetStickers(emoji, limit), stackIgnore + 1, submit)

/**
 * Searches for stickers from public sticker sets that correspond to a given emoji
 *
 * @emoji - String representation of emoji
 * @limit - The maximum number of stickers to be returned
 */
suspend fun TdHandler.searchStickers(
    emoji: String? = null,
    limit: Int
) = sync(SearchStickers(emoji, limit))

suspend fun TdHandler.searchStickersOrNull(
    emoji: String? = null,
    limit: Int
) = syncOrNull(SearchStickers(emoji, limit))

fun TdHandler.searchStickersWith(
    emoji: String? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Stickers>.() -> Unit)? = null
) = send(SearchStickers(emoji, limit), stackIgnore + 1, submit)

/**
 * Returns a list of installed sticker sets
 *
 * @isMasks - Pass true to return mask sticker sets
 *            Pass false to return ordinary sticker sets
 */
suspend fun TdHandler.getInstalledStickerSets(
    isMasks: Boolean
) = sync(GetInstalledStickerSets(isMasks))

suspend fun TdHandler.getInstalledStickerSetsOrNull(
    isMasks: Boolean
) = syncOrNull(GetInstalledStickerSets(isMasks))

fun TdHandler.getInstalledStickerSetsWith(
    isMasks: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<StickerSets>.() -> Unit)? = null
) = send(GetInstalledStickerSets(isMasks), stackIgnore + 1, submit)

/**
 * Returns a list of archived sticker sets
 *
 * @isMasks - Pass true to return mask stickers sets
 *            Pass false to return ordinary sticker sets
 * @offsetStickerSetId - Identifier of the sticker set from which to return the result
 * @limit - The maximum number of sticker sets to return
 */
suspend fun TdHandler.getArchivedStickerSets(
    isMasks: Boolean,
    offsetStickerSetId: Long,
    limit: Int
) = sync(GetArchivedStickerSets(isMasks, offsetStickerSetId, limit))

suspend fun TdHandler.getArchivedStickerSetsOrNull(
    isMasks: Boolean,
    offsetStickerSetId: Long,
    limit: Int
) = syncOrNull(GetArchivedStickerSets(isMasks, offsetStickerSetId, limit))

fun TdHandler.getArchivedStickerSetsWith(
    isMasks: Boolean,
    offsetStickerSetId: Long,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<StickerSets>.() -> Unit)? = null
) = send(GetArchivedStickerSets(isMasks, offsetStickerSetId, limit), stackIgnore + 1, submit)

/**
 * Returns a list of trending sticker sets
 * For the optimal performance the number of returned sticker sets is chosen by the library
 *
 * @offset - The offset from which to return the sticker sets
 * @limit - The maximum number of sticker sets to be returned
 *          Fewer sticker sets may be returned than specified by the limit, even if the end of the list has not been reached
 */
suspend fun TdHandler.getTrendingStickerSets(
    offset: Int,
    limit: Int
) = sync(GetTrendingStickerSets(offset, limit))

suspend fun TdHandler.getTrendingStickerSetsOrNull(
    offset: Int,
    limit: Int
) = syncOrNull(GetTrendingStickerSets(offset, limit))

fun TdHandler.getTrendingStickerSetsWith(
    offset: Int,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<StickerSets>.() -> Unit)? = null
) = send(GetTrendingStickerSets(offset, limit), stackIgnore + 1, submit)

/**
 * Returns a list of sticker sets attached to a file
 * Currently only photos and videos can have attached sticker sets
 *
 * @fileId - File identifier
 */
suspend fun TdHandler.getAttachedStickerSets(
    fileId: Int
) = sync(GetAttachedStickerSets(fileId))

suspend fun TdHandler.getAttachedStickerSetsOrNull(
    fileId: Int
) = syncOrNull(GetAttachedStickerSets(fileId))

fun TdHandler.getAttachedStickerSetsWith(
    fileId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<StickerSets>.() -> Unit)? = null
) = send(GetAttachedStickerSets(fileId), stackIgnore + 1, submit)

/**
 * Returns information about a sticker set by its identifier
 *
 * @setId - Identifier of the sticker set
 */
suspend fun TdHandler.getStickerSet(
    setId: Long
) = sync(GetStickerSet(setId))

suspend fun TdHandler.getStickerSetOrNull(
    setId: Long
) = syncOrNull(GetStickerSet(setId))

fun TdHandler.getStickerSetWith(
    setId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<StickerSet>.() -> Unit)? = null
) = send(GetStickerSet(setId), stackIgnore + 1, submit)

/**
 * Searches for a sticker set by its name
 *
 * @name - Name of the sticker set
 */
suspend fun TdHandler.searchStickerSet(
    name: String? = null
) = sync(SearchStickerSet(name))

suspend fun TdHandler.searchStickerSetOrNull(
    name: String? = null
) = syncOrNull(SearchStickerSet(name))

fun TdHandler.searchStickerSetWith(
    name: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<StickerSet>.() -> Unit)? = null
) = send(SearchStickerSet(name), stackIgnore + 1, submit)

/**
 * Searches for installed sticker sets by looking for specified query in their title and name
 *
 * @isMasks - Pass true to return mask sticker sets
 *            Pass false to return ordinary sticker sets
 * @query - Query to search for
 * @limit - The maximum number of sticker sets to return
 */
suspend fun TdHandler.searchInstalledStickerSets(
    isMasks: Boolean,
    query: String? = null,
    limit: Int
) = sync(SearchInstalledStickerSets(isMasks, query, limit))

suspend fun TdHandler.searchInstalledStickerSetsOrNull(
    isMasks: Boolean,
    query: String? = null,
    limit: Int
) = syncOrNull(SearchInstalledStickerSets(isMasks, query, limit))

fun TdHandler.searchInstalledStickerSetsWith(
    isMasks: Boolean,
    query: String? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<StickerSets>.() -> Unit)? = null
) = send(SearchInstalledStickerSets(isMasks, query, limit), stackIgnore + 1, submit)

/**
 * Searches for ordinary sticker sets by looking for specified query in their title and name
 * Excludes installed sticker sets from the results
 *
 * @query - Query to search for
 */
suspend fun TdHandler.searchStickerSets(
    query: String? = null
) = sync(SearchStickerSets(query))

suspend fun TdHandler.searchStickerSetsOrNull(
    query: String? = null
) = syncOrNull(SearchStickerSets(query))

fun TdHandler.searchStickerSetsWith(
    query: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<StickerSets>.() -> Unit)? = null
) = send(SearchStickerSets(query), stackIgnore + 1, submit)

/**
 * Installs/uninstalls or activates/archives a sticker set
 *
 * @setId - Identifier of the sticker set
 * @isInstalled - The new value of is_installed
 * @isArchived - The new value of is_archived
 *               A sticker set can't be installed and archived simultaneously
 */
suspend fun TdHandler.changeStickerSet(
    setId: Long,
    isInstalled: Boolean,
    isArchived: Boolean
){
    sync(ChangeStickerSet(setId, isInstalled, isArchived))
}


suspend fun TdHandler.changeStickerSetIgnoreException(
    setId: Long,
    isInstalled: Boolean,
    isArchived: Boolean
){
    syncOrNull(ChangeStickerSet(setId, isInstalled, isArchived))
}


fun TdHandler.changeStickerSetWith(
    setId: Long,
    isInstalled: Boolean,
    isArchived: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ChangeStickerSet(setId, isInstalled, isArchived), stackIgnore + 1, submit)

/**
 * Informs the server that some trending sticker sets have been viewed by the user
 *
 * @stickerSetIds - Identifiers of viewed trending sticker sets
 */
suspend fun TdHandler.viewTrendingStickerSets(
    stickerSetIds: LongArray
){
    sync(ViewTrendingStickerSets(stickerSetIds))
}


suspend fun TdHandler.viewTrendingStickerSetsIgnoreException(
    stickerSetIds: LongArray
){
    syncOrNull(ViewTrendingStickerSets(stickerSetIds))
}


fun TdHandler.viewTrendingStickerSetsWith(
    stickerSetIds: LongArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ViewTrendingStickerSets(stickerSetIds), stackIgnore + 1, submit)

/**
 * Changes the order of installed sticker sets
 *
 * @isMasks - Pass true to change the order of mask sticker sets
 *            Pass false to change the order of ordinary sticker sets
 * @stickerSetIds - Identifiers of installed sticker sets in the new correct order
 */
suspend fun TdHandler.reorderInstalledStickerSets(
    isMasks: Boolean,
    stickerSetIds: LongArray
){
    sync(ReorderInstalledStickerSets(isMasks, stickerSetIds))
}


suspend fun TdHandler.reorderInstalledStickerSetsIgnoreException(
    isMasks: Boolean,
    stickerSetIds: LongArray
){
    syncOrNull(ReorderInstalledStickerSets(isMasks, stickerSetIds))
}


fun TdHandler.reorderInstalledStickerSetsWith(
    isMasks: Boolean,
    stickerSetIds: LongArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ReorderInstalledStickerSets(isMasks, stickerSetIds), stackIgnore + 1, submit)

/**
 * Returns a list of recently used stickers
 *
 * @isAttached - Pass true to return stickers and masks that were recently attached to photos or video files
 *               Pass false to return recently sent stickers
 */
suspend fun TdHandler.getRecentStickers(
    isAttached: Boolean
) = sync(GetRecentStickers(isAttached))

suspend fun TdHandler.getRecentStickersOrNull(
    isAttached: Boolean
) = syncOrNull(GetRecentStickers(isAttached))

fun TdHandler.getRecentStickersWith(
    isAttached: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Stickers>.() -> Unit)? = null
) = send(GetRecentStickers(isAttached), stackIgnore + 1, submit)

/**
 * Manually adds a new sticker to the list of recently used stickers
 * The new sticker is added to the top of the list
 * If the sticker was already in the list, it is removed from the list first
 * Only stickers belonging to a sticker set can be added to this list
 *
 * @isAttached - Pass true to add the sticker to the list of stickers recently attached to photo or video files
 *               Pass false to add the sticker to the list of recently sent stickers
 * @sticker - Sticker file to add
 */
suspend fun TdHandler.addRecentSticker(
    isAttached: Boolean,
    sticker: InputFile? = null
) = sync(AddRecentSticker(isAttached, sticker))

suspend fun TdHandler.addRecentStickerOrNull(
    isAttached: Boolean,
    sticker: InputFile? = null
) = syncOrNull(AddRecentSticker(isAttached, sticker))

fun TdHandler.addRecentStickerWith(
    isAttached: Boolean,
    sticker: InputFile? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Stickers>.() -> Unit)? = null
) = send(AddRecentSticker(isAttached, sticker), stackIgnore + 1, submit)

/**
 * Removes a sticker from the list of recently used stickers
 *
 * @isAttached - Pass true to remove the sticker from the list of stickers recently attached to photo or video files
 *               Pass false to remove the sticker from the list of recently sent stickers
 * @sticker - Sticker file to delete
 */
suspend fun TdHandler.removeRecentSticker(
    isAttached: Boolean,
    sticker: InputFile? = null
){
    sync(RemoveRecentSticker(isAttached, sticker))
}


suspend fun TdHandler.removeRecentStickerIgnoreException(
    isAttached: Boolean,
    sticker: InputFile? = null
){
    syncOrNull(RemoveRecentSticker(isAttached, sticker))
}


fun TdHandler.removeRecentStickerWith(
    isAttached: Boolean,
    sticker: InputFile? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveRecentSticker(isAttached, sticker), stackIgnore + 1, submit)

/**
 * Clears the list of recently used stickers
 *
 * @isAttached - Pass true to clear the list of stickers recently attached to photo or video files
 *               Pass false to clear the list of recently sent stickers
 */
suspend fun TdHandler.clearRecentStickers(
    isAttached: Boolean
){
    sync(ClearRecentStickers(isAttached))
}


suspend fun TdHandler.clearRecentStickersIgnoreException(
    isAttached: Boolean
){
    syncOrNull(ClearRecentStickers(isAttached))
}


fun TdHandler.clearRecentStickersWith(
    isAttached: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ClearRecentStickers(isAttached), stackIgnore + 1, submit)

/**
 * Returns favorite stickers
 */
suspend fun TdHandler.getFavoriteStickers() = sync(GetFavoriteStickers())

suspend fun TdHandler.getFavoriteStickersOrNull() = syncOrNull(GetFavoriteStickers())

fun TdHandler.getFavoriteStickersWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Stickers>.() -> Unit)? = null
) = send(GetFavoriteStickers(), stackIgnore + 1, submit)

/**
 * Adds a new sticker to the list of favorite stickers
 * The new sticker is added to the top of the list
 * If the sticker was already in the list, it is removed from the list first
 * Only stickers belonging to a sticker set can be added to this list
 *
 * @sticker - Sticker file to add
 */
suspend fun TdHandler.addFavoriteSticker(
    sticker: InputFile? = null
){
    sync(AddFavoriteSticker(sticker))
}


suspend fun TdHandler.addFavoriteStickerIgnoreException(
    sticker: InputFile? = null
){
    syncOrNull(AddFavoriteSticker(sticker))
}


fun TdHandler.addFavoriteStickerWith(
    sticker: InputFile? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddFavoriteSticker(sticker), stackIgnore + 1, submit)

/**
 * Removes a sticker from the list of favorite stickers
 *
 * @sticker - Sticker file to delete from the list
 */
suspend fun TdHandler.removeFavoriteSticker(
    sticker: InputFile? = null
){
    sync(RemoveFavoriteSticker(sticker))
}


suspend fun TdHandler.removeFavoriteStickerIgnoreException(
    sticker: InputFile? = null
){
    syncOrNull(RemoveFavoriteSticker(sticker))
}


fun TdHandler.removeFavoriteStickerWith(
    sticker: InputFile? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveFavoriteSticker(sticker), stackIgnore + 1, submit)

/**
 * Creates a new sticker set
 * For bots only
 * Returns the newly created sticker set
 *
 * @userId - Sticker set owner
 * @title - Sticker set title
 * @name - Sticker set name
 *         Can contain only English letters, digits and underscores
 *         Must end with *"_by_<bot username>"* (*<bot_username>* is case insensitive)
 * @isMasks - True, if stickers are masks
 *            Animated stickers can't be masks
 * @stickers - List of stickers to be added to the set
 *             All stickers must be of the same type
 */
suspend fun TdHandler.createNewStickerSet(
    userId: Int,
    title: String? = null,
    name: String? = null,
    isMasks: Boolean,
    stickers: Array<InputSticker>
) = sync(CreateNewStickerSet(userId, title, name, isMasks, stickers))

suspend fun TdHandler.createNewStickerSetOrNull(
    userId: Int,
    title: String? = null,
    name: String? = null,
    isMasks: Boolean,
    stickers: Array<InputSticker>
) = syncOrNull(CreateNewStickerSet(userId, title, name, isMasks, stickers))

fun TdHandler.createNewStickerSetWith(
    userId: Int,
    title: String? = null,
    name: String? = null,
    isMasks: Boolean,
    stickers: Array<InputSticker>,
    stackIgnore: Int = 0,
    submit: (TdCallback<StickerSet>.() -> Unit)? = null
) = send(CreateNewStickerSet(userId, title, name, isMasks, stickers), stackIgnore + 1, submit)

/**
 * Adds a new sticker to a set
 * For bots only
 * Returns the sticker set
 *
 * @userId - Sticker set owner
 * @name - Sticker set name
 * @sticker - Sticker to add to the set
 */
suspend fun TdHandler.addStickerToSet(
    userId: Int,
    name: String? = null,
    sticker: InputSticker? = null
) = sync(AddStickerToSet(userId, name, sticker))

suspend fun TdHandler.addStickerToSetOrNull(
    userId: Int,
    name: String? = null,
    sticker: InputSticker? = null
) = syncOrNull(AddStickerToSet(userId, name, sticker))

fun TdHandler.addStickerToSetWith(
    userId: Int,
    name: String? = null,
    sticker: InputSticker? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<StickerSet>.() -> Unit)? = null
) = send(AddStickerToSet(userId, name, sticker), stackIgnore + 1, submit)

/**
 * Sets a sticker set thumbnail
 * For bots only
 * Returns the sticker set
 *
 * @userId - Sticker set owner
 * @name - Sticker set name
 * @thumbnail - Thumbnail to set in PNG or TGS format
 *              Animated thumbnail must be set for animated sticker sets and only for them
 *              Pass a zero InputFileId to delete the thumbnail
 */
suspend fun TdHandler.setStickerSetThumbnail(
    userId: Int,
    name: String? = null,
    thumbnail: InputFile? = null
) = sync(SetStickerSetThumbnail(userId, name, thumbnail))

suspend fun TdHandler.setStickerSetThumbnailOrNull(
    userId: Int,
    name: String? = null,
    thumbnail: InputFile? = null
) = syncOrNull(SetStickerSetThumbnail(userId, name, thumbnail))

fun TdHandler.setStickerSetThumbnailWith(
    userId: Int,
    name: String? = null,
    thumbnail: InputFile? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<StickerSet>.() -> Unit)? = null
) = send(SetStickerSetThumbnail(userId, name, thumbnail), stackIgnore + 1, submit)

/**
 * Changes the position of a sticker in the set to which it belongs
 * For bots only
 * The sticker set must have been created by the bot
 *
 * @sticker - Sticker
 * @position - New position of the sticker in the set, zero-based
 */
suspend fun TdHandler.setStickerPositionInSet(
    sticker: InputFile? = null,
    position: Int
){
    sync(SetStickerPositionInSet(sticker, position))
}


suspend fun TdHandler.setStickerPositionInSetIgnoreException(
    sticker: InputFile? = null,
    position: Int
){
    syncOrNull(SetStickerPositionInSet(sticker, position))
}


fun TdHandler.setStickerPositionInSetWith(
    sticker: InputFile? = null,
    position: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetStickerPositionInSet(sticker, position), stackIgnore + 1, submit)

/**
 * Removes a sticker from the set to which it belongs
 * For bots only
 * The sticker set must have been created by the bot
 *
 * @sticker - Sticker
 */
suspend fun TdHandler.removeStickerFromSet(
    sticker: InputFile? = null
){
    sync(RemoveStickerFromSet(sticker))
}


suspend fun TdHandler.removeStickerFromSetIgnoreException(
    sticker: InputFile? = null
){
    syncOrNull(RemoveStickerFromSet(sticker))
}


fun TdHandler.removeStickerFromSetWith(
    sticker: InputFile? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveStickerFromSet(sticker), stackIgnore + 1, submit)
