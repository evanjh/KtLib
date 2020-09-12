@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns the current state of 2-step verification
 */
suspend fun TdHandler.getPasswordState() = sync<PasswordState>(GetPasswordState())

suspend fun TdHandler.getPasswordStateOrNull() = syncOrNull<PasswordState>(GetPasswordState())

fun TdHandler.getPasswordStateWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<PasswordState>.() -> Unit)? = null
) = send(GetPasswordState(), stackIgnore + 1, submit)

/**
 * Changes the password for the user
 * If a new recovery email address is specified, then the change will not be applied until the new recovery email address is confirmed
 *
 * @oldPassword - Previous password of the user
 * @newPassword - New password of the user
 *                May be empty to remove the password
 * @newHint - New password hint
 * @setRecoveryEmailAddress - Pass true if the recovery email address should be changed
 * @newRecoveryEmailAddress - New recovery email address
 */
suspend fun TdHandler.setPassword(
    oldPassword: String? = null,
    newPassword: String? = null,
    newHint: String? = null,
    setRecoveryEmailAddress: Boolean,
    newRecoveryEmailAddress: String? = null
) = sync<PasswordState>(SetPassword(oldPassword, newPassword, newHint, setRecoveryEmailAddress, newRecoveryEmailAddress))

suspend fun TdHandler.setPasswordOrNull(
    oldPassword: String? = null,
    newPassword: String? = null,
    newHint: String? = null,
    setRecoveryEmailAddress: Boolean,
    newRecoveryEmailAddress: String? = null
) = syncOrNull<PasswordState>(SetPassword(oldPassword, newPassword, newHint, setRecoveryEmailAddress, newRecoveryEmailAddress))

fun TdHandler.setPasswordWith(
    oldPassword: String? = null,
    newPassword: String? = null,
    newHint: String? = null,
    setRecoveryEmailAddress: Boolean,
    newRecoveryEmailAddress: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<PasswordState>.() -> Unit)? = null
) = send(SetPassword(oldPassword, newPassword, newHint, setRecoveryEmailAddress, newRecoveryEmailAddress), stackIgnore + 1, submit)

/**
 * Changes the 2-step verification recovery email address of the user
 * If a new recovery email address is specified, then the change will not be applied until the new recovery email address is confirmed
 * If new_recovery_email_address is the same as the email address that is currently set up, this call succeeds immediately and aborts all other requests waiting for an email confirmation
 *
 * @password - Password of the current user
 * @newRecoveryEmailAddress - New recovery email address
 */
suspend fun TdHandler.setRecoveryEmailAddress(
    password: String? = null,
    newRecoveryEmailAddress: String? = null
) = sync<PasswordState>(SetRecoveryEmailAddress(password, newRecoveryEmailAddress))

suspend fun TdHandler.setRecoveryEmailAddressOrNull(
    password: String? = null,
    newRecoveryEmailAddress: String? = null
) = syncOrNull<PasswordState>(SetRecoveryEmailAddress(password, newRecoveryEmailAddress))

fun TdHandler.setRecoveryEmailAddressWith(
    password: String? = null,
    newRecoveryEmailAddress: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<PasswordState>.() -> Unit)? = null
) = send(SetRecoveryEmailAddress(password, newRecoveryEmailAddress), stackIgnore + 1, submit)

/**
 * Checks the 2-step verification recovery email address verification code
 *
 * @code - Verification code
 */
suspend fun TdHandler.checkRecoveryEmailAddressCode(
    code: String? = null
) = sync<PasswordState>(CheckRecoveryEmailAddressCode(code))

suspend fun TdHandler.checkRecoveryEmailAddressCodeOrNull(
    code: String? = null
) = syncOrNull<PasswordState>(CheckRecoveryEmailAddressCode(code))

fun TdHandler.checkRecoveryEmailAddressCodeWith(
    code: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<PasswordState>.() -> Unit)? = null
) = send(CheckRecoveryEmailAddressCode(code), stackIgnore + 1, submit)

/**
 * Resends the 2-step verification recovery email address verification code
 */
suspend fun TdHandler.resendRecoveryEmailAddressCode() = sync<PasswordState>(ResendRecoveryEmailAddressCode())

suspend fun TdHandler.resendRecoveryEmailAddressCodeOrNull() = syncOrNull<PasswordState>(ResendRecoveryEmailAddressCode())

fun TdHandler.resendRecoveryEmailAddressCodeWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<PasswordState>.() -> Unit)? = null
) = send(ResendRecoveryEmailAddressCode(), stackIgnore + 1, submit)

/**
 * Recovers the password using a recovery code sent to an email address that was previously set up
 *
 * @recoveryCode - Recovery code to check
 */
suspend fun TdHandler.recoverPassword(
    recoveryCode: String? = null
) = sync<PasswordState>(RecoverPassword(recoveryCode))

suspend fun TdHandler.recoverPasswordOrNull(
    recoveryCode: String? = null
) = syncOrNull<PasswordState>(RecoverPassword(recoveryCode))

fun TdHandler.recoverPasswordWith(
    recoveryCode: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<PasswordState>.() -> Unit)? = null
) = send(RecoverPassword(recoveryCode), stackIgnore + 1, submit)

/**
 * Creates a new temporary password for processing payments
 *
 * @password - Persistent user password
 * @validFor - Time during which the temporary password will be valid, in seconds
 *             Should be between 60 and 86400
 */
suspend fun TdHandler.createTemporaryPassword(
    password: String? = null,
    validFor: Int
) = sync<TemporaryPasswordState>(CreateTemporaryPassword(password, validFor))

suspend fun TdHandler.createTemporaryPasswordOrNull(
    password: String? = null,
    validFor: Int
) = syncOrNull<TemporaryPasswordState>(CreateTemporaryPassword(password, validFor))

fun TdHandler.createTemporaryPasswordWith(
    password: String? = null,
    validFor: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<TemporaryPasswordState>.() -> Unit)? = null
) = send(CreateTemporaryPassword(password, validFor), stackIgnore + 1, submit)

/**
 * Returns information about the current temporary password
 */
suspend fun TdHandler.getTemporaryPasswordState() = sync<TemporaryPasswordState>(GetTemporaryPasswordState())

suspend fun TdHandler.getTemporaryPasswordStateOrNull() = syncOrNull<TemporaryPasswordState>(GetTemporaryPasswordState())

fun TdHandler.getTemporaryPasswordStateWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<TemporaryPasswordState>.() -> Unit)? = null
) = send(GetTemporaryPasswordState(), stackIgnore + 1, submit)
