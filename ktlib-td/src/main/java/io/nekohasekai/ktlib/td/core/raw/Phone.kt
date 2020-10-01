@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Shares the phone number of the current user with a mutual contact
 * Supposed to be called when the user clicks on chatActionBarSharePhoneNumber
 *
 * @userId - Identifier of the user with whom to share the phone number
 *           The user must be a mutual contact
 */
suspend fun TdHandler.sharePhoneNumber(
    userId: Int
) = sync<Ok>(SharePhoneNumber(userId))

suspend fun TdHandler.sharePhoneNumberOrNull(
    userId: Int
) = syncOrNull<Ok>(SharePhoneNumber(userId))

fun TdHandler.sharePhoneNumberWith(
    userId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SharePhoneNumber(userId), stackIgnore + 1, submit)

/**
 * Returns information about a phone number by its prefix
 * Can be called before authorization
 *
 * @phoneNumberPrefix - The phone number prefix
 */
suspend fun TdHandler.getPhoneNumberInfo(
    phoneNumberPrefix: String? = null
) = sync<PhoneNumberInfo>(GetPhoneNumberInfo(phoneNumberPrefix))

suspend fun TdHandler.getPhoneNumberInfoOrNull(
    phoneNumberPrefix: String? = null
) = syncOrNull<PhoneNumberInfo>(GetPhoneNumberInfo(phoneNumberPrefix))

fun TdHandler.getPhoneNumberInfoWith(
    phoneNumberPrefix: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<PhoneNumberInfo>.() -> Unit)? = null
) = send(GetPhoneNumberInfo(phoneNumberPrefix), stackIgnore + 1, submit)
