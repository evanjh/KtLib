@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Changes the location of the current user
 * Needs to be called if GetOption("is_location_visible") is true and location changes for more than 1 kilometer
 *
 * @location - The new location of the user
 */
suspend fun TdHandler.setLocation(
    location: Location? = null
) = sync<Ok>(SetLocation(location))

suspend fun TdHandler.setLocationOrNull(
    location: Location? = null
) = syncOrNull<Ok>(SetLocation(location))

fun TdHandler.setLocationWith(
    location: Location? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetLocation(location), stackIgnore + 1, submit)
