@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns information about a basic group by its identifier
 * This is an offline request if the current user is not a bot
 *
 * @basicGroupId - Basic group identifier
 */
suspend fun TdHandler.getBasicGroup(
    basicGroupId: Int
) = sync(GetBasicGroup(basicGroupId))

suspend fun TdHandler.getBasicGroupOrNull(
    basicGroupId: Int
) = syncOrNull(GetBasicGroup(basicGroupId))

fun TdHandler.getBasicGroupWith(
    basicGroupId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<BasicGroup>.() -> Unit)? = null
) = send(GetBasicGroup(basicGroupId), stackIgnore + 1, submit)

/**
 * Returns full information about a basic group by its identifier
 *
 * @basicGroupId - Basic group identifier
 */
suspend fun TdHandler.getBasicGroupFullInfo(
    basicGroupId: Int
) = sync(GetBasicGroupFullInfo(basicGroupId))

suspend fun TdHandler.getBasicGroupFullInfoOrNull(
    basicGroupId: Int
) = syncOrNull(GetBasicGroupFullInfo(basicGroupId))

fun TdHandler.getBasicGroupFullInfoWith(
    basicGroupId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<BasicGroupFullInfo>.() -> Unit)? = null
) = send(GetBasicGroupFullInfo(basicGroupId), stackIgnore + 1, submit)
