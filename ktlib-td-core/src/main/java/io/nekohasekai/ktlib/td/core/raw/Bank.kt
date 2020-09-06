@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.BankCardInfo
import td.TdApi.GetBankCardInfo

/**
 * Returns information about a bank card
 *
 * @bankCardNumber - The bank card number
 */
suspend fun TdHandler.getBankCardInfo(
        bankCardNumber: String? = null
) = sync<BankCardInfo>(GetBankCardInfo(bankCardNumber))

suspend fun TdHandler.getBankCardInfoOrNull(
        bankCardNumber: String? = null
) = syncOrNull<BankCardInfo>(GetBankCardInfo(bankCardNumber))

fun TdHandler.getBankCardInfoWith(
        bankCardNumber: String? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<BankCardInfo>.() -> Unit)? = null
) = send(GetBankCardInfo(bankCardNumber), stackIgnore + 1, submit)
