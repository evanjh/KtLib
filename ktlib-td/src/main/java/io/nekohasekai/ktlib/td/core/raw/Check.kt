@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Checks the database encryption key for correctness
 * Works only when the current authorization state is authorizationStateWaitEncryptionKey
 *
 * @encryptionKey - Encryption key to check or set up
 */
suspend fun TdHandler.checkDatabaseEncryptionKey(
    encryptionKey: ByteArray
){
    sync(CheckDatabaseEncryptionKey(encryptionKey))
}


suspend fun TdHandler.checkDatabaseEncryptionKeyIgnoreException(
    encryptionKey: ByteArray
){
    syncOrNull(CheckDatabaseEncryptionKey(encryptionKey))
}


fun TdHandler.checkDatabaseEncryptionKeyWith(
    encryptionKey: ByteArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CheckDatabaseEncryptionKey(encryptionKey), stackIgnore + 1, submit)

/**
 * Checks the authentication code
 * Works only when the current authorization state is authorizationStateWaitCode
 *
 * @code - The verification code received via SMS, Telegram message, phone call, or flash call
 */
suspend fun TdHandler.checkAuthenticationCode(
    code: String? = null
){
    sync(CheckAuthenticationCode(code))
}


suspend fun TdHandler.checkAuthenticationCodeIgnoreException(
    code: String? = null
){
    syncOrNull(CheckAuthenticationCode(code))
}


fun TdHandler.checkAuthenticationCodeWith(
    code: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CheckAuthenticationCode(code), stackIgnore + 1, submit)

/**
 * Checks the authentication password for correctness
 * Works only when the current authorization state is authorizationStateWaitPassword
 *
 * @password - The password to check
 */
suspend fun TdHandler.checkAuthenticationPassword(
    password: String? = null
){
    sync(CheckAuthenticationPassword(password))
}


suspend fun TdHandler.checkAuthenticationPasswordIgnoreException(
    password: String? = null
){
    syncOrNull(CheckAuthenticationPassword(password))
}


fun TdHandler.checkAuthenticationPasswordWith(
    password: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CheckAuthenticationPassword(password), stackIgnore + 1, submit)

/**
 * Checks the authentication token of a bot
 * To log in as a bot
 * Works only when the current authorization state is authorizationStateWaitPhoneNumber
 * Can be used instead of setAuthenticationPhoneNumber and checkAuthenticationCode to log in
 *
 * @token - The bot token
 */
suspend fun TdHandler.checkAuthenticationBotToken(
    token: String? = null
){
    sync(CheckAuthenticationBotToken(token))
}


suspend fun TdHandler.checkAuthenticationBotTokenIgnoreException(
    token: String? = null
){
    syncOrNull(CheckAuthenticationBotToken(token))
}


fun TdHandler.checkAuthenticationBotTokenWith(
    token: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CheckAuthenticationBotToken(token), stackIgnore + 1, submit)

/**
 * Checks whether a username can be set for a chat
 *
 * @chatId - Chat identifier
 *           Should be identifier of a supergroup chat, or a channel chat, or a private chat with self, or zero if chat is being created
 * @username - Username to be checked
 */
suspend fun TdHandler.checkChatUsername(
    chatId: Long,
    username: String? = null
) = sync(CheckChatUsername(chatId, username))

suspend fun TdHandler.checkChatUsernameOrNull(
    chatId: Long,
    username: String? = null
) = syncOrNull(CheckChatUsername(chatId, username))

fun TdHandler.checkChatUsernameWith(
    chatId: Long,
    username: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<CheckChatUsernameResult>.() -> Unit)? = null
) = send(CheckChatUsername(chatId, username), stackIgnore + 1, submit)

/**
 * Checks whether the maximum number of owned public chats has been reached
 * Returns corresponding error if the limit was reached
 *
 * @type - Type of the public chats, for which to check the limit
 */
suspend fun TdHandler.checkCreatedPublicChatsLimit(
    type: PublicChatType? = null
){
    sync(CheckCreatedPublicChatsLimit(type))
}


suspend fun TdHandler.checkCreatedPublicChatsLimitIgnoreException(
    type: PublicChatType? = null
){
    syncOrNull(CheckCreatedPublicChatsLimit(type))
}


fun TdHandler.checkCreatedPublicChatsLimitWith(
    type: PublicChatType? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CheckCreatedPublicChatsLimit(type), stackIgnore + 1, submit)

/**
 * Sets the result of a pre-checkout query
 * For bots only
 *
 * @preCheckoutQueryId - Identifier of the pre-checkout query
 * @errorMessage - An error message, empty on success
 */
suspend fun TdHandler.answerPreCheckoutQuery(
    preCheckoutQueryId: Long,
    errorMessage: String? = null
){
    sync(AnswerPreCheckoutQuery(preCheckoutQueryId, errorMessage))
}


suspend fun TdHandler.answerPreCheckoutQueryIgnoreException(
    preCheckoutQueryId: Long,
    errorMessage: String? = null
){
    syncOrNull(AnswerPreCheckoutQuery(preCheckoutQueryId, errorMessage))
}


fun TdHandler.answerPreCheckoutQueryWith(
    preCheckoutQueryId: Long,
    errorMessage: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AnswerPreCheckoutQuery(preCheckoutQueryId, errorMessage), stackIgnore + 1, submit)

/**
 * Checks the authentication code sent to confirm a new phone number of the user
 *
 * @code - Verification code received by SMS, phone call or flash call
 */
suspend fun TdHandler.checkChangePhoneNumberCode(
    code: String? = null
){
    sync(CheckChangePhoneNumberCode(code))
}


suspend fun TdHandler.checkChangePhoneNumberCodeIgnoreException(
    code: String? = null
){
    syncOrNull(CheckChangePhoneNumberCode(code))
}


fun TdHandler.checkChangePhoneNumberCodeWith(
    code: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CheckChangePhoneNumberCode(code), stackIgnore + 1, submit)

/**
 * Checks the phone number verification code for Telegram Passport
 *
 * @code - Verification code
 */
suspend fun TdHandler.checkPhoneNumberVerificationCode(
    code: String? = null
){
    sync(CheckPhoneNumberVerificationCode(code))
}


suspend fun TdHandler.checkPhoneNumberVerificationCodeIgnoreException(
    code: String? = null
){
    syncOrNull(CheckPhoneNumberVerificationCode(code))
}


fun TdHandler.checkPhoneNumberVerificationCodeWith(
    code: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CheckPhoneNumberVerificationCode(code), stackIgnore + 1, submit)

/**
 * Checks the email address verification code for Telegram Passport
 *
 * @code - Verification code
 */
suspend fun TdHandler.checkEmailAddressVerificationCode(
    code: String? = null
){
    sync(CheckEmailAddressVerificationCode(code))
}


suspend fun TdHandler.checkEmailAddressVerificationCodeIgnoreException(
    code: String? = null
){
    syncOrNull(CheckEmailAddressVerificationCode(code))
}


fun TdHandler.checkEmailAddressVerificationCodeWith(
    code: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CheckEmailAddressVerificationCode(code), stackIgnore + 1, submit)

/**
 * Checks phone number confirmation code
 *
 * @code - The phone number confirmation code
 */
suspend fun TdHandler.checkPhoneNumberConfirmationCode(
    code: String? = null
){
    sync(CheckPhoneNumberConfirmationCode(code))
}


suspend fun TdHandler.checkPhoneNumberConfirmationCodeIgnoreException(
    code: String? = null
){
    syncOrNull(CheckPhoneNumberConfirmationCode(code))
}


fun TdHandler.checkPhoneNumberConfirmationCodeWith(
    code: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CheckPhoneNumberConfirmationCode(code), stackIgnore + 1, submit)
