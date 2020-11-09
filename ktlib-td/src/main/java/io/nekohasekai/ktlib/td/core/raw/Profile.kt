@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Changes a profile photo for the current user
 *
 * @photo - Profile photo to set
 */
suspend fun TdHandler.setProfilePhoto(
    photo: InputChatPhoto? = null
){
    sync(SetProfilePhoto(photo))
}


suspend fun TdHandler.setProfilePhotoIgnoreException(
    photo: InputChatPhoto? = null
){
    syncOrNull(SetProfilePhoto(photo))
}


fun TdHandler.setProfilePhotoWith(
    photo: InputChatPhoto? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetProfilePhoto(photo), stackIgnore + 1, submit)

/**
 * Deletes a profile photo
 *
 * @profilePhotoId - Identifier of the profile photo to delete
 */
suspend fun TdHandler.deleteProfilePhoto(
    profilePhotoId: Long
){
    sync(DeleteProfilePhoto(profilePhotoId))
}


suspend fun TdHandler.deleteProfilePhotoIgnoreException(
    profilePhotoId: Long
){
    syncOrNull(DeleteProfilePhoto(profilePhotoId))
}


fun TdHandler.deleteProfilePhotoWith(
    profilePhotoId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteProfilePhoto(profilePhotoId), stackIgnore + 1, submit)
