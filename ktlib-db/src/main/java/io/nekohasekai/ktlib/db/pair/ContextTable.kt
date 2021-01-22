package io.nekohasekai.ktlib.db.pair

import io.nekohasekai.ktlib.core.getValue
import io.nekohasekai.ktlib.core.setValue
import io.nekohasekai.ktlib.db.DatabaseInterface
import io.nekohasekai.ktlib.db.forceCreateTables
import java.util.concurrent.atomic.AtomicBoolean

class ContextTable(databaseInterface: DatabaseInterface, tableName: String = "scheme") : SchemeTable(tableName),
    DatabaseInterface by databaseInterface {

    private var inited by AtomicBoolean()

    private fun postInit() {
        if (inited) return
        synchronized(this) {
            if (inited) return
            database.write {
                forceCreateTables(this@ContextTable)
            }
            inited = true
        }
    }

    override fun <T> getItem(key: String, type: Class<T>): T? {
        postInit()
        return database {
            super.getItem(key, type)
        }
    }

    override fun <T> setItem(key: String, value: T) {
        postInit()
        database.write {
            super.setItem(key, value)
        }
    }

}

fun DatabaseInterface.mkTable(tableName: String = "scheme") = ContextTable(this, tableName)