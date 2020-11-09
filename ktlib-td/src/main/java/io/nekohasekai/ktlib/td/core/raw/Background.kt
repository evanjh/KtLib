@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns backgrounds installed by the user
 *
 * @forDarkTheme - True, if the backgrounds need to be ordered for dark theme
 */
suspend fun TdHandler.getBackgrounds(
    forDarkTheme: Boolean
) = sync(GetBackgrounds(forDarkTheme))

suspend fun TdHandler.getBackgroundsOrNull(
    forDarkTheme: Boolean
) = syncOrNull(GetBackgrounds(forDarkTheme))

fun TdHandler.getBackgroundsWith(
    forDarkTheme: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Backgrounds>.() -> Unit)? = null
) = send(GetBackgrounds(forDarkTheme), stackIgnore + 1, submit)

/**
 * Constructs a persistent HTTP URL for a background
 *
 * @name - Background name
 * @type - Background type
 */
suspend fun TdHandler.getBackgroundUrl(
    name: String? = null,
    type: BackgroundType? = null
) = sync(GetBackgroundUrl(name, type))

suspend fun TdHandler.getBackgroundUrlOrNull(
    name: String? = null,
    type: BackgroundType? = null
) = syncOrNull(GetBackgroundUrl(name, type))

fun TdHandler.getBackgroundUrlWith(
    name: String? = null,
    type: BackgroundType? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<HttpUrl>.() -> Unit)? = null
) = send(GetBackgroundUrl(name, type), stackIgnore + 1, submit)

/**
 * Searches for a background by its name
 *
 * @name - The name of the background
 */
suspend fun TdHandler.searchBackground(
    name: String? = null
) = sync(SearchBackground(name))

suspend fun TdHandler.searchBackgroundOrNull(
    name: String? = null
) = syncOrNull(SearchBackground(name))

fun TdHandler.searchBackgroundWith(
    name: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Background>.() -> Unit)? = null
) = send(SearchBackground(name), stackIgnore + 1, submit)

/**
 * Changes the background selected by the user
 * Adds background to the list of installed backgrounds
 *
 * @background - The input background to use, null for filled backgrounds
 * @type - Background type
 *         Null for default background
 *         The method will return error 404 if type is null
 * @forDarkTheme - True, if the background is chosen for dark theme
 */
suspend fun TdHandler.setBackground(
    background: InputBackground? = null,
    type: BackgroundType? = null,
    forDarkTheme: Boolean
) = sync(SetBackground(background, type, forDarkTheme))

suspend fun TdHandler.setBackgroundOrNull(
    background: InputBackground? = null,
    type: BackgroundType? = null,
    forDarkTheme: Boolean
) = syncOrNull(SetBackground(background, type, forDarkTheme))

fun TdHandler.setBackgroundWith(
    background: InputBackground? = null,
    type: BackgroundType? = null,
    forDarkTheme: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Background>.() -> Unit)? = null
) = send(SetBackground(background, type, forDarkTheme), stackIgnore + 1, submit)

/**
 * Removes background from the list of installed backgrounds
 *
 * @backgroundId - The background identifier
 */
suspend fun TdHandler.removeBackground(
    backgroundId: Long
){
    sync(RemoveBackground(backgroundId))
}


suspend fun TdHandler.removeBackgroundIgnoreException(
    backgroundId: Long
){
    syncOrNull(RemoveBackground(backgroundId))
}


fun TdHandler.removeBackgroundWith(
    backgroundId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveBackground(backgroundId), stackIgnore + 1, submit)

/**
 * Resets list of installed backgrounds to its default value
 */
suspend fun TdHandler.resetBackgrounds(){
    sync(ResetBackgrounds())
}


suspend fun TdHandler.resetBackgroundsIgnoreException(){
    syncOrNull(ResetBackgrounds())
}


fun TdHandler.resetBackgroundsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ResetBackgrounds(), stackIgnore + 1, submit)
