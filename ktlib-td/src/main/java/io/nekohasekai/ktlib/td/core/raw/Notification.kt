@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Removes an active notification from notification list
 * Needs to be called only if the notification is removed by the current user
 *
 * @notificationGroupId - Identifier of notification group to which the notification belongs
 * @notificationId - Identifier of removed notification
 */
suspend fun TdHandler.removeNotification(
    notificationGroupId: Int,
    notificationId: Int
){
    sync(RemoveNotification(notificationGroupId, notificationId))
}


suspend fun TdHandler.removeNotificationIgnoreException(
    notificationGroupId: Int,
    notificationId: Int
){
    syncOrNull(RemoveNotification(notificationGroupId, notificationId))
}


fun TdHandler.removeNotificationWith(
    notificationGroupId: Int,
    notificationId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveNotification(notificationGroupId, notificationId), stackIgnore + 1, submit)

/**
 * Removes a group of active notifications
 * Needs to be called only if the notification group is removed by the current user
 *
 * @notificationGroupId - Notification group identifier
 * @maxNotificationId - The maximum identifier of removed notifications
 */
suspend fun TdHandler.removeNotificationGroup(
    notificationGroupId: Int,
    maxNotificationId: Int
){
    sync(RemoveNotificationGroup(notificationGroupId, maxNotificationId))
}


suspend fun TdHandler.removeNotificationGroupIgnoreException(
    notificationGroupId: Int,
    maxNotificationId: Int
){
    syncOrNull(RemoveNotificationGroup(notificationGroupId, maxNotificationId))
}


fun TdHandler.removeNotificationGroupWith(
    notificationGroupId: Int,
    maxNotificationId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveNotificationGroup(notificationGroupId, maxNotificationId), stackIgnore + 1, submit)

/**
 * Returns the notification settings for chats of a given type
 *
 * @scope - Types of chats for which to return the notification settings information
 */
suspend fun TdHandler.getScopeNotificationSettings(
    scope: NotificationSettingsScope? = null
) = sync(GetScopeNotificationSettings(scope))

suspend fun TdHandler.getScopeNotificationSettingsOrNull(
    scope: NotificationSettingsScope? = null
) = syncOrNull(GetScopeNotificationSettings(scope))

fun TdHandler.getScopeNotificationSettingsWith(
    scope: NotificationSettingsScope? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ScopeNotificationSettings>.() -> Unit)? = null
) = send(GetScopeNotificationSettings(scope), stackIgnore + 1, submit)

/**
 * Changes notification settings for chats of a given type
 *
 * @scope - Types of chats for which to change the notification settings
 * @notificationSettings - The new notification settings for the given scope
 */
suspend fun TdHandler.setScopeNotificationSettings(
    scope: NotificationSettingsScope? = null,
    notificationSettings: ScopeNotificationSettings? = null
){
    sync(SetScopeNotificationSettings(scope, notificationSettings))
}


suspend fun TdHandler.setScopeNotificationSettingsIgnoreException(
    scope: NotificationSettingsScope? = null,
    notificationSettings: ScopeNotificationSettings? = null
){
    syncOrNull(SetScopeNotificationSettings(scope, notificationSettings))
}


fun TdHandler.setScopeNotificationSettingsWith(
    scope: NotificationSettingsScope? = null,
    notificationSettings: ScopeNotificationSettings? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetScopeNotificationSettings(scope, notificationSettings), stackIgnore + 1, submit)

/**
 * Resets all notification settings to their default values
 * By default, all chats are unmuted, the sound is set to "default" and message previews are shown
 */
suspend fun TdHandler.resetAllNotificationSettings(){
    sync(ResetAllNotificationSettings())
}


suspend fun TdHandler.resetAllNotificationSettingsIgnoreException(){
    syncOrNull(ResetAllNotificationSettings())
}


fun TdHandler.resetAllNotificationSettingsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ResetAllNotificationSettings(), stackIgnore + 1, submit)

/**
 * Handles a push notification
 * Returns error with code 406 if the push notification is not supported and connection to the server is required to fetch new data
 * Can be called before authorization
 *
 * @payload - JSON-encoded push notification payload with all fields sent by the server, and "google.sent_time" and "google.notification.sound" fields added
 */
suspend fun TdHandler.processPushNotification(
    payload: String? = null
){
    sync(ProcessPushNotification(payload))
}


suspend fun TdHandler.processPushNotificationIgnoreException(
    payload: String? = null
){
    syncOrNull(ProcessPushNotification(payload))
}


fun TdHandler.processPushNotificationWith(
    payload: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ProcessPushNotification(payload), stackIgnore + 1, submit)
