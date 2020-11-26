@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns one of the available Telegram Passport elements
 *
 * @type - Telegram Passport element type
 * @password - Password of the current user
 */
suspend fun TdHandler.getPassportElement(
    type: PassportElementType? = null,
    password: String? = null
) = sync(GetPassportElement(type, password))

suspend fun TdHandler.getPassportElementOrNull(
    type: PassportElementType? = null,
    password: String? = null
) = syncOrNull(GetPassportElement(type, password))

fun TdHandler.getPassportElementWith(
    type: PassportElementType? = null,
    password: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<PassportElement>.() -> Unit)? = null
) = send(GetPassportElement(type, password), stackIgnore + 1, submit)

/**
 * Returns all available Telegram Passport elements
 *
 * @password - Password of the current user
 */
suspend fun TdHandler.getAllPassportElements(
    password: String? = null
) = sync(GetAllPassportElements(password))

suspend fun TdHandler.getAllPassportElementsOrNull(
    password: String? = null
) = syncOrNull(GetAllPassportElements(password))

fun TdHandler.getAllPassportElementsWith(
    password: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<PassportElements>.() -> Unit)? = null
) = send(GetAllPassportElements(password), stackIgnore + 1, submit)

/**
 * Adds an element to the user's Telegram Passport
 * May return an error with a message "PHONE_VERIFICATION_NEEDED" or "EMAIL_VERIFICATION_NEEDED" if the chosen phone number or the chosen email address must be verified first
 *
 * @element - Input Telegram Passport element
 * @password - Password of the current user
 */
suspend fun TdHandler.setPassportElement(
    element: InputPassportElement? = null,
    password: String? = null
) = sync(SetPassportElement(element, password))

suspend fun TdHandler.setPassportElementOrNull(
    element: InputPassportElement? = null,
    password: String? = null
) = syncOrNull(SetPassportElement(element, password))

fun TdHandler.setPassportElementWith(
    element: InputPassportElement? = null,
    password: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<PassportElement>.() -> Unit)? = null
) = send(SetPassportElement(element, password), stackIgnore + 1, submit)

/**
 * Deletes a Telegram Passport element
 *
 * @type - Element type
 */
suspend fun TdHandler.deletePassportElement(
    type: PassportElementType? = null
){
    sync(DeletePassportElement(type))
}


suspend fun TdHandler.deletePassportElementIgnoreException(
    type: PassportElementType? = null
){
    syncOrNull(DeletePassportElement(type))
}


fun TdHandler.deletePassportElementWith(
    type: PassportElementType? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeletePassportElement(type), stackIgnore + 1, submit)

/**
 * Informs the user that some of the elements in their Telegram Passport contain errors
 * For bots only
 * The user will not be able to resend the elements, until the errors are fixed
 *
 * @userId - User identifier
 * @errors - The errors
 */
suspend fun TdHandler.setPassportElementErrors(
    userId: Int,
    errors: Array<InputPassportElementError>
){
    sync(SetPassportElementErrors(userId, errors))
}


suspend fun TdHandler.setPassportElementErrorsIgnoreException(
    userId: Int,
    errors: Array<InputPassportElementError>
){
    syncOrNull(SetPassportElementErrors(userId, errors))
}


fun TdHandler.setPassportElementErrorsWith(
    userId: Int,
    errors: Array<InputPassportElementError>,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetPassportElementErrors(userId, errors), stackIgnore + 1, submit)

/**
 * Returns a Telegram Passport authorization form for sharing data with a service
 *
 * @botUserId - User identifier of the service's bot
 * @scope - Telegram Passport element types requested by the service
 * @publicKey - Service's public_key
 * @nonce - Authorization form nonce provided by the service
 */
suspend fun TdHandler.getPassportAuthorizationForm(
    botUserId: Int,
    scope: String? = null,
    publicKey: String? = null,
    nonce: String? = null
) = sync(GetPassportAuthorizationForm(botUserId, scope, publicKey, nonce))

suspend fun TdHandler.getPassportAuthorizationFormOrNull(
    botUserId: Int,
    scope: String? = null,
    publicKey: String? = null,
    nonce: String? = null
) = syncOrNull(GetPassportAuthorizationForm(botUserId, scope, publicKey, nonce))

fun TdHandler.getPassportAuthorizationFormWith(
    botUserId: Int,
    scope: String? = null,
    publicKey: String? = null,
    nonce: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<PassportAuthorizationForm>.() -> Unit)? = null
) = send(GetPassportAuthorizationForm(botUserId, scope, publicKey, nonce), stackIgnore + 1, submit)

/**
 * Returns already available Telegram Passport elements suitable for completing a Telegram Passport authorization form
 * Result can be received only once for each authorization form
 *
 * @autorizationFormId - Authorization form identifier
 * @password - Password of the current user
 */
suspend fun TdHandler.getPassportAuthorizationFormAvailableElements(
    autorizationFormId: Int,
    password: String? = null
) = sync(GetPassportAuthorizationFormAvailableElements(autorizationFormId, password))

suspend fun TdHandler.getPassportAuthorizationFormAvailableElementsOrNull(
    autorizationFormId: Int,
    password: String? = null
) = syncOrNull(GetPassportAuthorizationFormAvailableElements(autorizationFormId, password))

fun TdHandler.getPassportAuthorizationFormAvailableElementsWith(
    autorizationFormId: Int,
    password: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<PassportElementsWithErrors>.() -> Unit)? = null
) = send(GetPassportAuthorizationFormAvailableElements(autorizationFormId, password), stackIgnore + 1, submit)

/**
 * Sends a Telegram Passport authorization form, effectively sharing data with the service
 * This method must be called after getPassportAuthorizationFormAvailableElements if some previously available elements are going to be reused
 *
 * @autorizationFormId - Authorization form identifier
 * @types - Types of Telegram Passport elements chosen by user to complete the authorization form
 */
suspend fun TdHandler.sendPassportAuthorizationForm(
    autorizationFormId: Int,
    types: Array<PassportElementType>
){
    sync(SendPassportAuthorizationForm(autorizationFormId, types))
}


suspend fun TdHandler.sendPassportAuthorizationFormIgnoreException(
    autorizationFormId: Int,
    types: Array<PassportElementType>
){
    syncOrNull(SendPassportAuthorizationForm(autorizationFormId, types))
}


fun TdHandler.sendPassportAuthorizationFormWith(
    autorizationFormId: Int,
    types: Array<PassportElementType>,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SendPassportAuthorizationForm(autorizationFormId, types), stackIgnore + 1, submit)
