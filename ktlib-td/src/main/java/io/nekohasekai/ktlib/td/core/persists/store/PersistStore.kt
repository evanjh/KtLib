package io.nekohasekai.ktlib.td.core.persists.store

import io.nekohasekai.ktlib.td.core.TdClient
import io.nekohasekai.ktlib.td.core.persists.TdPersist

interface PersistStore {

    fun persistRead(client: TdClient, userId: Int): TdPersist?

    fun persistSave(client: TdClient, persist: TdPersist)

    fun persistRemove(client: TdClient, userId: Int)

    fun persistSaveAll(client: TdClient)

    fun persistGc(client: TdClient)

    class Interface(val client: TdClient) {

        var store: PersistStore = InMemoryPersistStore()

        fun setImplement(implement: PersistStore) {

            store = implement

        }

        fun read(userId: Int) = store.persistRead(client, userId)

        fun save(persist: TdPersist) = store.persistSave(client, persist)

        fun remove(userId: Int) = store.persistRemove(client, userId)

        fun saveAll() = store.persistSaveAll(client)

        fun gc() = store.persistGc(client)

    }


}