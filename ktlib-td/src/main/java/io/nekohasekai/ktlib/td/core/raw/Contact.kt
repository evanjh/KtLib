@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Adds a user to the contact list or edits an existing contact by their user identifier
 *
 * @contact - The contact to add or edit
 *            Phone number can be empty and needs to be specified only if known, vCard is ignored
 * @sharePhoneNumber - True, if the new contact needs to be allowed to see current user's phone number
 *                     A corresponding rule to userPrivacySettingShowPhoneNumber will be added if needed
 *                     Use the field UserFullInfo.need_phone_number_privacy_exception to check whether the current user needs to be asked to share their phone number
 */
suspend fun TdHandler.addContact(
    contact: Contact? = null,
    sharePhoneNumber: Boolean
){
    sync(AddContact(contact, sharePhoneNumber))
}


suspend fun TdHandler.addContactIgnoreException(
    contact: Contact? = null,
    sharePhoneNumber: Boolean
){
    syncOrNull(AddContact(contact, sharePhoneNumber))
}


fun TdHandler.addContactWith(
    contact: Contact? = null,
    sharePhoneNumber: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddContact(contact, sharePhoneNumber), stackIgnore + 1, submit)

/**
 * Adds new contacts or edits existing contacts by their phone numbers
 * Contacts' user identifiers are ignored
 *
 * @contacts - The list of contacts to import or edit
 *             Contacts' vCard are ignored and are not imported
 */
suspend fun TdHandler.importContacts(
    contacts: Array<Contact>
) = sync(ImportContacts(contacts))

suspend fun TdHandler.importContactsOrNull(
    contacts: Array<Contact>
) = syncOrNull(ImportContacts(contacts))

fun TdHandler.importContactsWith(
    contacts: Array<Contact>,
    stackIgnore: Int = 0,
    submit: (TdCallback<ImportedContacts>.() -> Unit)? = null
) = send(ImportContacts(contacts), stackIgnore + 1, submit)

/**
 * Removes users from the contact list
 *
 * @userIds - Identifiers of users to be deleted
 */
suspend fun TdHandler.removeContacts(
    userIds: IntArray
){
    sync(RemoveContacts(userIds))
}


suspend fun TdHandler.removeContactsIgnoreException(
    userIds: IntArray
){
    syncOrNull(RemoveContacts(userIds))
}


fun TdHandler.removeContactsWith(
    userIds: IntArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveContacts(userIds), stackIgnore + 1, submit)

/**
 * Returns the total number of imported contacts
 */
suspend fun TdHandler.getImportedContactCount() = sync(GetImportedContactCount())

suspend fun TdHandler.getImportedContactCountOrNull() = syncOrNull(GetImportedContactCount())

fun TdHandler.getImportedContactCountWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Count>.() -> Unit)? = null
) = send(GetImportedContactCount(), stackIgnore + 1, submit)

/**
 * Changes imported contacts using the list of current user contacts saved on the device
 * Imports newly added contacts and, if at least the file database is enabled, deletes recently deleted contacts
 * Query result depends on the result of the previous query, so only one query is possible at the same time
 *
 * @contacts - The new list of contacts, contact's vCard are ignored and are not imported
 */
suspend fun TdHandler.changeImportedContacts(
    contacts: Array<Contact>
) = sync(ChangeImportedContacts(contacts))

suspend fun TdHandler.changeImportedContactsOrNull(
    contacts: Array<Contact>
) = syncOrNull(ChangeImportedContacts(contacts))

fun TdHandler.changeImportedContactsWith(
    contacts: Array<Contact>,
    stackIgnore: Int = 0,
    submit: (TdCallback<ImportedContacts>.() -> Unit)? = null
) = send(ChangeImportedContacts(contacts), stackIgnore + 1, submit)

/**
 * Clears all imported contacts, contact list remains unchanged
 */
suspend fun TdHandler.clearImportedContacts(){
    sync(ClearImportedContacts())
}


suspend fun TdHandler.clearImportedContactsIgnoreException(){
    syncOrNull(ClearImportedContacts())
}


fun TdHandler.clearImportedContactsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ClearImportedContacts(), stackIgnore + 1, submit)
