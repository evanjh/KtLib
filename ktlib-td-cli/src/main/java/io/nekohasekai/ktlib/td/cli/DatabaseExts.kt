package io.nekohasekai.ktlib.td.cli

import io.nekohasekai.ktlib.db.DatabaseDispatcher
import io.nekohasekai.ktlib.db.DatabaseInterface
import io.nekohasekai.ktlib.td.core.TdHandler

val TdHandler.database: DatabaseDispatcher get() = (sudo as DatabaseInterface).database