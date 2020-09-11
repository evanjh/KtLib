@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Sets the current network type
 * Can be called before authorization
 * Calling this method forces all network connections to reopen, mitigating the delay in switching between different networks, so it should be called whenever the network is changed, even if the network type remains the same
 * Network type is used to check whether the library can use the network at all and also for collecting detailed network data usage statistics
 *
 * @type - The new network type
 *         By default, networkTypeOther
 */
suspend fun TdHandler.setNetworkType(
        type: NetworkType? = null
) = sync<Ok>(SetNetworkType(type))

suspend fun TdHandler.setNetworkTypeOrNull(
        type: NetworkType? = null
) = syncOrNull<Ok>(SetNetworkType(type))

fun TdHandler.setNetworkTypeWith(
        type: NetworkType? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetNetworkType(type), stackIgnore + 1, submit)

/**
 * Returns network data usage statistics
 * Can be called before authorization
 *
 * @onlyCurrent - If true, returns only data for the current library launch
 */
suspend fun TdHandler.getNetworkStatistics(
        onlyCurrent: Boolean
) = sync<NetworkStatistics>(GetNetworkStatistics(onlyCurrent))

suspend fun TdHandler.getNetworkStatisticsOrNull(
        onlyCurrent: Boolean
) = syncOrNull<NetworkStatistics>(GetNetworkStatistics(onlyCurrent))

fun TdHandler.getNetworkStatisticsWith(
        onlyCurrent: Boolean,
        stackIgnore: Int = 0,
        submit: (TdCallback<NetworkStatistics>.() -> Unit)? = null
) = send(GetNetworkStatistics(onlyCurrent), stackIgnore + 1, submit)

/**
 * Adds the specified data to data usage statistics
 * Can be called before authorization
 *
 * @entry - The network statistics entry with the data to be added to statistics
 */
suspend fun TdHandler.addNetworkStatistics(
        entry: NetworkStatisticsEntry? = null
) = sync<Ok>(AddNetworkStatistics(entry))

suspend fun TdHandler.addNetworkStatisticsOrNull(
        entry: NetworkStatisticsEntry? = null
) = syncOrNull<Ok>(AddNetworkStatistics(entry))

fun TdHandler.addNetworkStatisticsWith(
        entry: NetworkStatisticsEntry? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddNetworkStatistics(entry), stackIgnore + 1, submit)

/**
 * Resets all network data usage statistics to zero
 * Can be called before authorization
 */
suspend fun TdHandler.resetNetworkStatistics() = sync<Ok>(ResetNetworkStatistics())

suspend fun TdHandler.resetNetworkStatisticsOrNull() = syncOrNull<Ok>(ResetNetworkStatistics())

fun TdHandler.resetNetworkStatisticsWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ResetNetworkStatistics(), stackIgnore + 1, submit)
