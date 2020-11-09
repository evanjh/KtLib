@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns saved animations
 */
suspend fun TdHandler.getSavedAnimations() = sync(GetSavedAnimations())

suspend fun TdHandler.getSavedAnimationsOrNull() = syncOrNull(GetSavedAnimations())

fun TdHandler.getSavedAnimationsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Animations>.() -> Unit)? = null
) = send(GetSavedAnimations(), stackIgnore + 1, submit)

/**
 * Manually adds a new animation to the list of saved animations
 * The new animation is added to the beginning of the list
 * If the animation was already in the list, it is removed first
 * Only non-secret video animations with MIME type "video/mp4" can be added to the list
 *
 * @animation - The animation file to be added
 *              Only animations known to the server (i.e
 *              Successfully sent via a message) can be added to the list
 */
suspend fun TdHandler.addSavedAnimation(
    animation: InputFile? = null
){
    sync(AddSavedAnimation(animation))
}


suspend fun TdHandler.addSavedAnimationIgnoreException(
    animation: InputFile? = null
){
    syncOrNull(AddSavedAnimation(animation))
}


fun TdHandler.addSavedAnimationWith(
    animation: InputFile? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddSavedAnimation(animation), stackIgnore + 1, submit)

/**
 * Removes an animation from the list of saved animations
 *
 * @animation - Animation file to be removed
 */
suspend fun TdHandler.removeSavedAnimation(
    animation: InputFile? = null
){
    sync(RemoveSavedAnimation(animation))
}


suspend fun TdHandler.removeSavedAnimationIgnoreException(
    animation: InputFile? = null
){
    syncOrNull(RemoveSavedAnimation(animation))
}


fun TdHandler.removeSavedAnimationWith(
    animation: InputFile? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveSavedAnimation(animation), stackIgnore + 1, submit)
