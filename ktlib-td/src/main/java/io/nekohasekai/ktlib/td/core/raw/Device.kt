@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Registers the currently used device for receiving push notifications
 * Returns a globally unique identifier of the push notification subscription
 *
 * @deviceToken - Device token
 * @otherUserIds - List of user identifiers of other users currently using the application
 */
suspend fun TdHandler.registerDevice(
    deviceToken: DeviceToken? = null,
    otherUserIds: IntArray
) = sync<PushReceiverId>(RegisterDevice(deviceToken, otherUserIds))

suspend fun TdHandler.registerDeviceOrNull(
    deviceToken: DeviceToken? = null,
    otherUserIds: IntArray
) = syncOrNull<PushReceiverId>(RegisterDevice(deviceToken, otherUserIds))

fun TdHandler.registerDeviceWith(
    deviceToken: DeviceToken? = null,
    otherUserIds: IntArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<PushReceiverId>.() -> Unit)? = null
) = send(RegisterDevice(deviceToken, otherUserIds), stackIgnore + 1, submit)
