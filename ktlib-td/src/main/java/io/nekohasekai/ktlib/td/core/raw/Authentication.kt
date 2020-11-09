@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Sets the phone number of the user and sends an authentication code to the user
 * Works only when the current authorization state is authorizationStateWaitPhoneNumber, or if there is no pending authentication query and the current authorization state is authorizationStateWaitCode, authorizationStateWaitRegistration, or authorizationStateWaitPassword
 *
 * @phoneNumber - The phone number of the user, in international format
 * @settings - Settings for the authentication of the user's phone number
 */
suspend fun TdHandler.setAuthenticationPhoneNumber(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
){
    sync(SetAuthenticationPhoneNumber(phoneNumber, settings))
}


suspend fun TdHandler.setAuthenticationPhoneNumberIgnoreException(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
){
    syncOrNull(SetAuthenticationPhoneNumber(phoneNumber, settings))
}


fun TdHandler.setAuthenticationPhoneNumberWith(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetAuthenticationPhoneNumber(phoneNumber, settings), stackIgnore + 1, submit)

/**
 * Re-sends an authentication code to the user
 * Works only when the current authorization state is authorizationStateWaitCode and the next_code_type of the result is not null
 */
suspend fun TdHandler.resendAuthenticationCode(){
    sync(ResendAuthenticationCode())
}


suspend fun TdHandler.resendAuthenticationCodeIgnoreException(){
    syncOrNull(ResendAuthenticationCode())
}


fun TdHandler.resendAuthenticationCodeWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ResendAuthenticationCode(), stackIgnore + 1, submit)

/**
 * Requests QR code authentication by scanning a QR code on another logged in device
 * Works only when the current authorization state is authorizationStateWaitPhoneNumber, or if there is no pending authentication query and the current authorization state is authorizationStateWaitCode, authorizationStateWaitRegistration, or authorizationStateWaitPassword
 *
 * @otherUserIds - List of user identifiers of other users currently using the application
 */
suspend fun TdHandler.requestQrCodeAuthentication(
    otherUserIds: IntArray
){
    sync(RequestQrCodeAuthentication(otherUserIds))
}


suspend fun TdHandler.requestQrCodeAuthenticationIgnoreException(
    otherUserIds: IntArray
){
    syncOrNull(RequestQrCodeAuthentication(otherUserIds))
}


fun TdHandler.requestQrCodeAuthenticationWith(
    otherUserIds: IntArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RequestQrCodeAuthentication(otherUserIds), stackIgnore + 1, submit)

/**
 * Requests to send a password recovery code to an email address that was previously set up
 * Works only when the current authorization state is authorizationStateWaitPassword
 */
suspend fun TdHandler.requestAuthenticationPasswordRecovery(){
    sync(RequestAuthenticationPasswordRecovery())
}


suspend fun TdHandler.requestAuthenticationPasswordRecoveryIgnoreException(){
    syncOrNull(RequestAuthenticationPasswordRecovery())
}


fun TdHandler.requestAuthenticationPasswordRecoveryWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RequestAuthenticationPasswordRecovery(), stackIgnore + 1, submit)

/**
 * Recovers the password with a password recovery code sent to an email address that was previously set up
 * Works only when the current authorization state is authorizationStateWaitPassword
 *
 * @recoveryCode - Recovery code to check
 */
suspend fun TdHandler.recoverAuthenticationPassword(
    recoveryCode: String? = null
){
    sync(RecoverAuthenticationPassword(recoveryCode))
}


suspend fun TdHandler.recoverAuthenticationPasswordIgnoreException(
    recoveryCode: String? = null
){
    syncOrNull(RecoverAuthenticationPassword(recoveryCode))
}


fun TdHandler.recoverAuthenticationPasswordWith(
    recoveryCode: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RecoverAuthenticationPassword(recoveryCode), stackIgnore + 1, submit)

/**
 * Changes the phone number of the user and sends an authentication code to the user's new phone number
 * On success, returns information about the sent code
 *
 * @phoneNumber - The new phone number of the user in international format
 * @settings - Settings for the authentication of the user's phone number
 */
suspend fun TdHandler.changePhoneNumber(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = sync(ChangePhoneNumber(phoneNumber, settings))

suspend fun TdHandler.changePhoneNumberOrNull(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = syncOrNull(ChangePhoneNumber(phoneNumber, settings))

fun TdHandler.changePhoneNumberWith(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<AuthenticationCodeInfo>.() -> Unit)? = null
) = send(ChangePhoneNumber(phoneNumber, settings), stackIgnore + 1, submit)

/**
 * Re-sends the authentication code sent to confirm a new phone number for the user
 * Works only if the previously received authenticationCodeInfo next_code_type was not null
 */
suspend fun TdHandler.resendChangePhoneNumberCode() = sync(ResendChangePhoneNumberCode())

suspend fun TdHandler.resendChangePhoneNumberCodeOrNull() = syncOrNull(ResendChangePhoneNumberCode())

fun TdHandler.resendChangePhoneNumberCodeWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<AuthenticationCodeInfo>.() -> Unit)? = null
) = send(ResendChangePhoneNumberCode(), stackIgnore + 1, submit)

/**
 * Sends a code to verify a phone number to be added to a user's Telegram Passport
 *
 * @phoneNumber - The phone number of the user, in international format
 * @settings - Settings for the authentication of the user's phone number
 */
suspend fun TdHandler.sendPhoneNumberVerificationCode(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = sync(SendPhoneNumberVerificationCode(phoneNumber, settings))

suspend fun TdHandler.sendPhoneNumberVerificationCodeOrNull(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = syncOrNull(SendPhoneNumberVerificationCode(phoneNumber, settings))

fun TdHandler.sendPhoneNumberVerificationCodeWith(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<AuthenticationCodeInfo>.() -> Unit)? = null
) = send(SendPhoneNumberVerificationCode(phoneNumber, settings), stackIgnore + 1, submit)

/**
 * Re-sends the code to verify a phone number to be added to a user's Telegram Passport
 */
suspend fun TdHandler.resendPhoneNumberVerificationCode() = sync(ResendPhoneNumberVerificationCode())

suspend fun TdHandler.resendPhoneNumberVerificationCodeOrNull() = syncOrNull(ResendPhoneNumberVerificationCode())

fun TdHandler.resendPhoneNumberVerificationCodeWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<AuthenticationCodeInfo>.() -> Unit)? = null
) = send(ResendPhoneNumberVerificationCode(), stackIgnore + 1, submit)

/**
 * Sends phone number confirmation code
 * Should be called when user presses "https://t.me/confirmphone?phone=*******&hash=**********" or "tg://confirmphone?phone=*******&hash=**********" link
 *
 * @hash - Value of the "hash" parameter from the link
 * @phoneNumber - Value of the "phone" parameter from the link
 * @settings - Settings for the authentication of the user's phone number
 */
suspend fun TdHandler.sendPhoneNumberConfirmationCode(
    hash: String? = null,
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = sync(SendPhoneNumberConfirmationCode(hash, phoneNumber, settings))

suspend fun TdHandler.sendPhoneNumberConfirmationCodeOrNull(
    hash: String? = null,
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = syncOrNull(SendPhoneNumberConfirmationCode(hash, phoneNumber, settings))

fun TdHandler.sendPhoneNumberConfirmationCodeWith(
    hash: String? = null,
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<AuthenticationCodeInfo>.() -> Unit)? = null
) = send(SendPhoneNumberConfirmationCode(hash, phoneNumber, settings), stackIgnore + 1, submit)

/**
 * Resends phone number confirmation code
 */
suspend fun TdHandler.resendPhoneNumberConfirmationCode() = sync(ResendPhoneNumberConfirmationCode())

suspend fun TdHandler.resendPhoneNumberConfirmationCodeOrNull() = syncOrNull(ResendPhoneNumberConfirmationCode())

fun TdHandler.resendPhoneNumberConfirmationCodeWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<AuthenticationCodeInfo>.() -> Unit)? = null
) = send(ResendPhoneNumberConfirmationCode(), stackIgnore + 1, submit)
