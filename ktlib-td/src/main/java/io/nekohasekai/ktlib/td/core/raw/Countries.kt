@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns information about existing countries
 * Can be called before authorization
 */
suspend fun TdHandler.getCountries() = sync<Countries>(GetCountries())

suspend fun TdHandler.getCountriesOrNull() = syncOrNull<Countries>(GetCountries())

fun TdHandler.getCountriesWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Countries>.() -> Unit)? = null
) = send(GetCountries(), stackIgnore + 1, submit)
