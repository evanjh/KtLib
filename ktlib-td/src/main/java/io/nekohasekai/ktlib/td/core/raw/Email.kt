@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns a 2-step verification recovery email address that was previously set up
 * This method can be used to verify a password provided by the user
 *
 * @password - The password for the current user
 */
suspend fun TdHandler.getRecoveryEmailAddress(
    password: String? = null
) = sync(GetRecoveryEmailAddress(password))

suspend fun TdHandler.getRecoveryEmailAddressOrNull(
    password: String? = null
) = syncOrNull(GetRecoveryEmailAddress(password))

fun TdHandler.getRecoveryEmailAddressWith(
    password: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<RecoveryEmailAddress>.() -> Unit)? = null
) = send(GetRecoveryEmailAddress(password), stackIgnore + 1, submit)

/**
 * Requests to send a password recovery code to an email address that was previously set up
 */
suspend fun TdHandler.requestPasswordRecovery() = sync(RequestPasswordRecovery())

suspend fun TdHandler.requestPasswordRecoveryOrNull() = syncOrNull(RequestPasswordRecovery())

fun TdHandler.requestPasswordRecoveryWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<EmailAddressAuthenticationCodeInfo>.() -> Unit)? = null
) = send(RequestPasswordRecovery(), stackIgnore + 1, submit)

/**
 * Sends a code to verify an email address to be added to a user's Telegram Passport
 *
 * @emailAddress - Email address
 */
suspend fun TdHandler.sendEmailAddressVerificationCode(
    emailAddress: String? = null
) = sync(SendEmailAddressVerificationCode(emailAddress))

suspend fun TdHandler.sendEmailAddressVerificationCodeOrNull(
    emailAddress: String? = null
) = syncOrNull(SendEmailAddressVerificationCode(emailAddress))

fun TdHandler.sendEmailAddressVerificationCodeWith(
    emailAddress: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<EmailAddressAuthenticationCodeInfo>.() -> Unit)? = null
) = send(SendEmailAddressVerificationCode(emailAddress), stackIgnore + 1, submit)

/**
 * Re-sends the code to verify an email address to be added to a user's Telegram Passport
 */
suspend fun TdHandler.resendEmailAddressVerificationCode() = sync(ResendEmailAddressVerificationCode())

suspend fun TdHandler.resendEmailAddressVerificationCodeOrNull() = syncOrNull(ResendEmailAddressVerificationCode())

fun TdHandler.resendEmailAddressVerificationCodeWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<EmailAddressAuthenticationCodeInfo>.() -> Unit)? = null
) = send(ResendEmailAddressVerificationCode(), stackIgnore + 1, submit)
