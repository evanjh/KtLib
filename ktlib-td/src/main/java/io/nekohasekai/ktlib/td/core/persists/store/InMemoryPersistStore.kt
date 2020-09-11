package io.nekohasekai.ktlib.td.core.persists.store

import cn.hutool.core.date.SystemClock
import io.nekohasekai.ktlib.td.core.TdClient
import io.nekohasekai.ktlib.td.core.persists.TdPersist
import kotlinx.coroutines.launch

class InMemoryPersistStore : HashMap<Int, TdPersist>(), PersistStore {

    override fun persistRead(client: TdClient, userId: Int) = get(userId)

    override fun persistSave(client: TdClient, persist: TdPersist) {

        put(persist.userId, persist)

    }

    override fun persistRemove(client: TdClient, userId: Int) {

        remove(userId)

    }

    override fun persistSaveAll(client: TdClient) {
    }

    override fun persistGc(client: TdClient) {

        val time = (SystemClock.now() / 1000L).toInt() - 24 * 60 * 60

        val iterator = iterator()

        while (iterator.hasNext()) {

            val (userId, persist) = iterator.next()

            if (persist.createAt < time) {

                iterator.remove()

                client.persistHandlers[persist.persistId]?.apply {

                    TdClient.events.launch {

                        runCatching {

                            onPersistTimeout(userId, userId.toLong(), persist.subId, persist.data)

                            onSendTimeoutMessage(userId, userId.toLong())

                        }.onFailure {

                            it.printStackTrace()

                        }

                    }

                }

            }

        }
    }

}