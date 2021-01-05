@file:Suppress("unused")

package io.nekohasekai.ktlib.td.utils

import io.nekohasekai.ktlib.core.anyIndexed
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.setCommands
import td.TdApi

suspend fun TdHandler.upsertCommands(vararg commands: TdApi.BotCommand) {

    val oldCommands = fullInfo.botInfo!!.commands

    if (oldCommands.size != commands.size || oldCommands.anyIndexed { index, it -> it ne commands[index] }) {

        setCommands(arrayOf(* commands))

    }

}

private infix fun TdApi.BotCommand.ne(command: TdApi.BotCommand) =
    this.command != command.command || this.description != command.description