@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns an IETF language tag of the language preferred in the country, which should be used to fill native fields in Telegram Passport personal details
 * Returns a 404 error if unknown
 *
 * @countryCode - A two-letter ISO 3166-1 alpha-2 country code
 */
suspend fun TdHandler.getPreferredCountryLanguage(
    countryCode: String? = null
) = sync<Text>(GetPreferredCountryLanguage(countryCode))

suspend fun TdHandler.getPreferredCountryLanguageOrNull(
    countryCode: String? = null
) = syncOrNull<Text>(GetPreferredCountryLanguage(countryCode))

fun TdHandler.getPreferredCountryLanguageWith(
    countryCode: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Text>.() -> Unit)? = null
) = send(GetPreferredCountryLanguage(countryCode), stackIgnore + 1, submit)

/**
 * Uses current user IP address to find their country
 * Returns two-letter ISO 3166-1 alpha-2 country code
 * Can be called before authorization
 */
suspend fun TdHandler.getCountryCode() = sync<Text>(GetCountryCode())

suspend fun TdHandler.getCountryCodeOrNull() = syncOrNull<Text>(GetCountryCode())

fun TdHandler.getCountryCodeWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Text>.() -> Unit)? = null
) = send(GetCountryCode(), stackIgnore + 1, submit)

/**
 * Returns the default text for invitation messages to be used as a placeholder when the current user invites friends to Telegram
 */
suspend fun TdHandler.getInviteText() = sync<Text>(GetInviteText())

suspend fun TdHandler.getInviteTextOrNull() = syncOrNull<Text>(GetInviteText())

fun TdHandler.getInviteTextWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Text>.() -> Unit)? = null
) = send(GetInviteText(), stackIgnore + 1, submit)

/**
 * Returns an HTTPS link, which can be used to add a proxy
 * Available only for SOCKS5 and MTProto proxies
 * Can be called before authorization
 *
 * @proxyId - Proxy identifier
 */
suspend fun TdHandler.getProxyLink(
    proxyId: Int
) = sync<Text>(GetProxyLink(proxyId))

suspend fun TdHandler.getProxyLinkOrNull(
    proxyId: Int
) = syncOrNull<Text>(GetProxyLink(proxyId))

fun TdHandler.getProxyLinkWith(
    proxyId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Text>.() -> Unit)? = null
) = send(GetProxyLink(proxyId), stackIgnore + 1, submit)
