@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Changes the period of inactivity after which the account of the current user will automatically be deleted
 *
 * @ttl - New account TTL
 */
suspend fun TdHandler.setAccountTtl(
    ttl: AccountTtl? = null
){
    sync(SetAccountTtl(ttl))
}


suspend fun TdHandler.setAccountTtlIgnoreException(
    ttl: AccountTtl? = null
){
    syncOrNull(SetAccountTtl(ttl))
}


fun TdHandler.setAccountTtlWith(
    ttl: AccountTtl? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetAccountTtl(ttl), stackIgnore + 1, submit)

/**
 * Returns the period of inactivity after which the account of the current user will automatically be deleted
 */
suspend fun TdHandler.getAccountTtl() = sync(GetAccountTtl())

suspend fun TdHandler.getAccountTtlOrNull() = syncOrNull(GetAccountTtl())

fun TdHandler.getAccountTtlWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<AccountTtl>.() -> Unit)? = null
) = send(GetAccountTtl(), stackIgnore + 1, submit)

/**
 * Deletes the account of the current user, deleting all information associated with the user from the server
 * The phone number of the account can be used to create a new account
 * Can be called before authorization when the current authorization state is authorizationStateWaitPassword
 *
 * @reason - The reason why the account was deleted
 */
suspend fun TdHandler.deleteAccount(
    reason: String? = null
){
    sync(DeleteAccount(reason))
}


suspend fun TdHandler.deleteAccountIgnoreException(
    reason: String? = null
){
    syncOrNull(DeleteAccount(reason))
}


fun TdHandler.deleteAccountWith(
    reason: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteAccount(reason), stackIgnore + 1, submit)
