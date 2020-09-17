@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core

import cn.hutool.cache.impl.LFUCache
import cn.hutool.core.date.SystemClock
import cn.hutool.core.thread.ThreadUtil
import cn.hutool.core.util.RuntimeUtil
import com.esotericsoftware.kryo.KryoException
import io.nekohasekai.ktlib.core.*
import io.nekohasekai.ktlib.db.DatabaseDispatcher
import io.nekohasekai.ktlib.db.openSqliteDatabase
import io.nekohasekai.ktlib.td.core.extensions.*
import io.nekohasekai.ktlib.td.core.persists.store.PersistStore
import io.nekohasekai.ktlib.td.core.raw.*
import io.nekohasekai.ktlib.td.core.utils.confirmTo
import io.nekohasekai.ktlib.td.core.utils.make
import kotlinx.coroutines.*
import td.TdApi.*
import td.TdNative
import java.io.File
import java.math.BigInteger
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.reflect.jvm.isAccessible

open class TdClient(val tag: String = "", val name: String = tag) : TdHandler() {

    override val sudo get() = this

    val clientLog = mkLog(name)

    var options: TdOptions = TdOptions()
    var encryptionKey: ByteArray? = null

    open val skipSelfMessage = true
    open val funPrefix = arrayOf("/", "!")
    open val defaultLang: String? = null

    val payloads = HashMap<String, TdHandler>()
    val functions = HashMap<String, TdHandler>()
    val callbackQueries = HashMap<Long, TdHandler>()

    val persistHandlers = HashMap<Int, TdHandler>()
    val persists by lazy { PersistStore.Interface(this) }

    var handlers = LinkedList<TdHandler>()

    var start by AtomicBoolean()
    var started by MutexBoolean()
    var authing by MutexBoolean()
    var auth by MutexBoolean()
    var stop by AtomicBoolean()
    var closed by MutexBoolean()

    private val clientId by lazy { TdNative.createNativeClient() }
    private val requestId = AtomicLong(1)

    val callbacks = ConcurrentHashMap<Long, TdCallback<*>>()
    val messageCallbacks = ConcurrentHashMap<Long, TdCallback<Message>>()

    private lateinit var _me: User
    override val me by ::_me
    private lateinit var _fullInfo: UserFullInfo
    override val fullInfo by ::_fullInfo

    infix fun addHandler(handler: TdHandler) {

        events.launch {

            handler.onLoad(this@TdClient)

            handlers.add(handler)

        }

    }

    infix fun removeHandler(handler: TdHandler) {

        events.launch {

            handlers.remove(handler)

        }

    }

    private lateinit var _database: DatabaseDispatcher
    override val database by ::_database

    fun initDatabase(dbName: String = "td_data.db", directory: String = options.databaseDirectory): DatabaseDispatcher {

        synchronized(::database) {

            if (!::_database.isInitialized) {

                _database = openSqliteDatabase(File(directory, dbName))

            }

        }

        return database

    }

    override suspend fun gc() {

        persists.gc()

        handlers.toLinkedList().filter { it != this }.forEach { it.gc() }

    }

    override suspend fun onDestroy() {

        persists.saveAll()

    }

    fun clearHandlers() {

        handlers.clear()

    }

    open fun start() {

        synchronized(this) {

            check(!start) { "已经启动过." }

            start = true

            authing = true

        }

        clearHandlers()

        addHandler(this)

        synchronized(postAdd) {

            postAdd.add(this)

        }

        if (!loopThreadInitiated) {

            loopThread = LoopThread()

            loopThread.start()

            RuntimeUtil.addShutdownHook {

                defaultLog.info("Stopping...")

                val clients = clients.toLinkedList()

                clients.forEach { it.stop() }

                runBlocking {

                    events.launch {

                        clients.forEach { it.waitForClose() }

                        defaultLog.info("Closing threads")

                    }.join()

                    eventsContext.close()

                    loopThread.interrupt()

                    loopThread.join()

                }

            }

        }

    }

    suspend fun waitForStart() {

        if (!start) start()

        (::started.apply { isAccessible = true }.getDelegate() as MutexBoolean).waitFor(true)

    }

    suspend fun waitForAuth(): Boolean {

        if (!start) start()

        (::authing.apply { isAccessible = true }.getDelegate() as MutexBoolean).waitFor(false)

        return auth

    }

    suspend fun waitForLogin() {

        if (!start) start()

        (::auth.apply { isAccessible = true }.getDelegate() as MutexBoolean).waitFor(true)

    }

    open fun stop() {

        synchronized(this) {

            check(started) { "未启动." }

            check(!stop) { "重复停止." }

            stop = true

            sendRaw(Close())

        }

        runBlocking {

            handlers.toLinkedList().forEach { it.onDestroy() }

        }

    }

    open suspend fun waitForClose() {

        if (!stop) stop()

        (::closed.apply { isAccessible = true }.getDelegate() as MutexBoolean).waitFor(true)

    }

    override suspend fun handleNewMessage(message: Message) {

        val userId = message.senderUserId

        val chatId = message.chatId

        suspend fun processNewMessage(): Nothing {

            handlers.toLinkedList().filter { it != this }.forEach { it.onNewMessage(userId, chatId, message) }

            onNewMessageLast(userId, chatId, message)

            finishEvent()

        }

        if (!sudo.waitForAuth() || userId == me.id && skipSelfMessage || userBlocked(userId)) return

        val persist = if (message.fromPrivate) persists.read(userId) else null

        run predict@{

            if (message.content !is MessageText) return@predict

            val content = (message.content as MessageText).text

            var param = content.text

            run fn@{

                funPrefix.forEach {

                    if (!param.startsWith(it)) return@forEach

                    param = param.substring(it.length)

                    return@fn

                }

                return@predict

            }

            var function = if (!param.contains(' ')) {

                param.also {

                    param = ""

                }

            } else {

                param.substringBefore(' ').also {

                    param = param.substringAfter(' ')

                }

            }

            val validSuffix = "@${me.username}"

            if (function.endsWith(validSuffix)) {

                function = function.substring(0, function.length - validSuffix.length)

            }

            val params: Array<String>

            val originParams: Array<String>

            if (param.isBlank()) {

                originParams = arrayOf()
                params = originParams

            } else {

                originParams = param.split(' ').toTypedArray()
                params = param.replace("  ", " ").split(' ').toTypedArray()

            }

            try {

                if (persist != null) {

                    val handler = persistHandlers[persist.persistId] ?: finishEvent()

                    if (function == "cancel" && persist.allowCancel) {

                        sudo removePersist userId

                        handler.onPersistCancel(userId, chatId, message, persist.subId, persist.data)

                        handler.onPersistRemoveOrCancel(userId, chatId, persist.subId, persist.data)

                        handler.onSendCanceledMessage(userId, chatId)

                        finishEvent()

                    } else if (!persist.allowFunction && persist.allowCancel) {

                        userCalled(userId, "function in persist to cancel")

                        sudo removePersist userId

                        handler.onPersistCancel(userId, chatId, message, persist.subId, persist.data)

                        handler.onPersistRemoveOrCancel(userId, chatId, persist.subId, persist.data)

                        handler.onSendCanceledMessage(userId, chatId)

                    } else if (persist.allowFunction) {

                        userCalled(userId, "persist function /$function")

                        handler.onPersistFunction(userId, chatId, message, persist.subId, persist.data, function, param, params, originParams)

                        finishEvent()

                    } else return@predict

                }

                onNewMessage(userId, chatId, message)

                if ("start" == function) {

                    if (param.isNotBlank()) {

                        val data = param.split('-').toTypedArray()

                        if (data.isNotEmpty() && payloads.containsKey(data[0])) {

                            userCalled(userId, "startPayload $data[0]")

                            payloads[data[0]]!!.onStartPayload(userId, chatId, message, data[0], data.shift())

                        } else {

                            userCalled(userId, "undefined startPayload $data[0]")

                            handlers.toLinkedList().forEach {

                                if (this@TdClient == it) return@forEach

                                it.onUndefinedPayload(userId, chatId, message, data[0], data.shift())

                            }

                            onUndefinedPayload(userId, chatId, message, data[0], data.shift())

                        }

                    } else {

                        userCalled(userId, "/start")

                        onLaunch(userId, chatId, message)

                    }

                } else if (!functions.containsKey(function)) {

                    userCalled(userId, "undefined function /$function")

                    handlers.toLinkedList().forEach {

                        if (this@TdClient == it) return@forEach

                        it.onUndefinedFunction(userId, chatId, message, function, param, params, originParams)

                    }

                    onUndefinedFunction(userId, chatId, message, function, param, params, originParams)

                } else {

                    userCalled(userId, "function /$function")

                    functions[function]!!.onFunction(userId, chatId, message, function, param, params, originParams)

                }

                finishEvent()

            } catch (ex: Reject) {

                return@predict

            }

        }

        if (persist == null) {

            onNewMessage(userId, chatId, message)

            processNewMessage()

        }

        val handler = persistHandlers[persist.persistId]

        if (handler == null) {

            sudo removePersist userId

            warnUserCalled(userId, "message in undefined persist ${persist.persistId}")

            handleNewMessage(message)

            return

        }

        userCalled(userId, "persist message in ${handler.javaClass.simpleName}")

        handler.onPersistMessage(userId, chatId, message, persist.subId, persist.data)

        finishEvent()

    }

    open suspend fun onLaunch(userId: Int, chatId: Long, message: Message) = Unit

    override suspend fun handleNewInlineCallbackQuery(id: Long, senderUserId: Int, inlineMessageId: String, chatInstance: Long, payload: CallbackQueryPayload) {

        if (!waitForAuth() || userBlocked(senderUserId)) return

        if (payload is CallbackQueryPayloadGame) return

        var dataArray = try {

            (payload as CallbackQueryPayloadData).data.readData(true)

        } catch (e: KryoException) {

            userCalled(senderUserId, "unknown callback")

            // TODO: handle unknown callback

            return

        }

        val dataId = BigInteger(dataArray[0]).toLong()

        dataArray = dataArray.shift()

        if (!callbackQueries.containsKey(dataId)) {

            warnUserCalled(senderUserId, "queried with invalid dataId $dataId")

            return

        }

        val callback = callbackQueries[dataId]!!

        userCalled(senderUserId, "queried in ${callback.javaClass.simpleName}")

        callback.onNewInlineCallbackQuery(senderUserId, inlineMessageId, id, dataArray)

        finishEvent()

    }

    override suspend fun handleNewCallbackQuery(id: Long, senderUserId: Int, chatId: Long, messageId: Long, chatInstance: Long, payload: CallbackQueryPayload) {

        if (!waitForAuth() || userBlocked(senderUserId)) return

        if (payload is CallbackQueryPayloadGame) return

        payload as CallbackQueryPayloadData

        if (payload.data.size == 1 && payload.data[0].toInt() == -1) {

            sudo confirmTo id

            return

        }

        var dataArray = try {

            payload.data.readData(true)

        } catch (e: KryoException) {

            userCalled(senderUserId, "unknown callback")

            // TODO: handle unknown callback

            return

        }

        val dataId = dataArray[0].toLong()

        dataArray = dataArray.shift()

        if (!callbackQueries.containsKey(dataId)) {

            warnUserCalled(senderUserId, "queried with invalid dataId $dataId")

            return

        }

        val callback = callbackQueries[dataId]!!

        userCalled(senderUserId, "queried in ${callback.javaClass.simpleName}")

        callback.onNewCallbackQuery(senderUserId, chatId, messageId, id, dataArray)

        finishEvent()

    }

    override suspend fun onUndefinedPayload(userId: Int, chatId: Long, message: Message, payload: String, params: Array<String>) {

        if (!message.fromPrivate) return

        onLaunch(userId, chatId, message)

        finishEvent()

    }

    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) = coroutineScope {

        when (authorizationState) {

            is AuthorizationStateWaitTdlibParameters -> {

                setTdlibParameters(options.build())

            }

            is AuthorizationStateWaitEncryptionKey -> {

                checkDatabaseEncryptionKey(encryptionKey ?: byteArrayOf())

            }

            is AuthorizationStateReady -> {

                _me = getMe()

                try {

                    _fullInfo = getUserFullInfo(me.id)

                    handlers.toLinkedList().forEach { it.beforeLogin() }

                    authing = false

                    auth = true

                    handlers.toLinkedList().forEach { it.onLogin() }

                } catch (ignored: TdException) {

                    return@coroutineScope

                }

            }

            is AuthorizationStateLoggingOut -> {

                handlers.toLinkedList().forEach { it.onLogout() }

            }

            is AuthorizationStateClosed -> {

                synchronized(postDestroy) {

                    postDestroy.add(sudo)

                }

            }
        }

    }

    open suspend fun onAuthorizationFailed(ex: TdException) {
    }

    override suspend fun onMessageSendSucceeded(message: Message, oldMessageId: Long) {

        val callback = messageCallbacks.remove(oldMessageId) ?: return

        callback.postResult(message)

    }

    override suspend fun onMessageSendFailed(message: Message, oldMessageId: Long, errorCode: Int, errorMessage: String) {

        val callback = messageCallbacks.remove(oldMessageId) ?: return

        callback.postError(TdException(Error(errorCode, errorMessage)))

    }

    override suspend fun <T : Object> sync(function: td.TdApi.Function): T {

        val stackTrace = ThreadUtil.getStackTrace().shift(3)

        return withContext(Dispatchers.Unconfined) {

            suspendCancellableCoroutine { continuation ->

                send<T>(function, 1) {

                    onSuccess { result ->

                        continuation.resume(result)

                    }

                    onFailure { exception ->

                        if (exception.code == 500) {

                            continuation.cancel()

                        } else {

                            continuation.resumeWithException(TdException(exception).also {

                                it.stackTrace = stackTrace

                            })

                        }

                    }

                }

            }

        }

    }

    class SendMessageFailedException(cause: TdException) : TdException(cause)

    @Suppress("UNCHECKED_CAST")
    override fun <T : Object> send(function: td.TdApi.Function, stackIgnore: Int, submit: (TdCallback<T>.() -> Unit)?) {

        val callback: TdCallback<T>

        if (function is SendMessage) {

            submit as (TdCallback<Message>.() -> Unit)?

            val origin = TdCallback<Message>(stackIgnore + 1).applyIf(submit != null, submit)

            callback = TdCallback<Message>(stackIgnore + 1).onSuccess {

                messageCallbacks[it.id] = origin

            }.onFailure {

                origin.postError(SendMessageFailedException(it))

            } as TdCallback<T>

        } else if (function is SendMessageAlbum || function is ForwardMessages) {

            submit as (TdCallback<Messages>.() -> Unit)?

            val origin = TdCallback<Messages>(stackIgnore + 1).applyIf(submit != null, submit)

            callback = TdCallback<Messages>(stackIgnore + 1).onSuccess { result ->

                val lock = ReentrantLock()

                val resultMap = HashMap<Long, Message>()

                result.messages.forEach { oldMessage ->

                    messageCallbacks[oldMessage.id] = TdCallback<Message>(origin.stackTrace).apply {

                        onSuccess {

                            val finish = lock.withLock {

                                resultMap[oldMessage.id] = it

                                if (resultMap.size == result.messages.size) {

                                    for (index in result.messages.indices) {

                                        result.messages[index] = resultMap[result.messages[index].id]

                                    }

                                    true

                                } else {

                                    false

                                }

                            }

                            if (finish) {

                                origin.postResult(result)

                            }

                        }

                        onFailure {

                            origin.postError(SendMessageFailedException(it))

                        }

                    }

                }

            }.onFailure {

                origin.postError(SendMessageFailedException(it))

            } as TdCallback<T>

        } else {

            callback = TdCallback<T>(stackIgnore + 1).applyIf(submit != null, submit)

        }

        val requestId = requestId.getAndIncrement()

        callbacks[requestId] = callback

        sendRaw(requestId, function)

    }

    override fun sendRaw(function: td.TdApi.Function) {

        val requestId = requestId.getAndIncrement()

        sendRaw(requestId, function)

    }

    private fun sendRaw(requestId: Long, function: td.TdApi.Function) {

        check(!closed) { "Client closed" }

        TdNative.nativeClientSend(clientId, requestId, function)

    }

    override suspend fun onSendCanceledMessage(userId: Int, chatId: Long) {

        sudo make "Canceled." syncTo chatId

    }

    override suspend fun onSendTimeoutMessage(userId: Int, chatId: Long) {

        try {

            getChat(chatId)

            onSendCanceledMessage(userId, chatId)

        } catch (ignored: TdException) {
        }

    }

    companion object {

        val eventsLog = mkLog("TdEvents")

        val timer = Timer("Global Timer")

        @Suppress("EXPERIMENTAL_API_USAGE")
        val eventsContext = newSingleThreadContext("TDLib Events Task")

        val events = CoroutineScope(eventsContext)

        private val postAdd = LinkedList<TdClient>()
        private val postDestroy = LinkedList<TdClient>()
        val clients = LinkedList<TdClient>()

        private const val MAX_EVENTS = 1000

        lateinit var loopThread: Thread

        var STOPPING by AtomicBoolean()

        val loopThreadInitiated get() = Companion::loopThread.isInitialized

        class LoopThread : Thread("TDLib Loop Thread") {

            override fun run() {

                try {

                    runBlocking {

                        while (!isInterrupted) {

                            synchronized(postAdd) {

                                val iterator = postAdd.iterator()

                                while (iterator.hasNext()) {

                                    val toAdd = iterator.next()

                                    clients.add(toAdd)

                                    iterator.remove()

                                    toAdd.started = true

                                }

                            }

                            synchronized(postDestroy) {

                                val iterator = postDestroy.iterator()

                                while (iterator.hasNext()) {

                                    val toDestroy = iterator.next()

                                    clients.remove(toDestroy)

                                    TdNative.destroyNativeClient(toDestroy.clientId)

                                    toDestroy.stop = true
                                    toDestroy.closed = true

                                    iterator.remove()

                                }

                            }

                            if (clients.isEmpty()) {

                                delay(1000L)

                                continue

                            }

                            val start = SystemClock.now()

                            for (client in clients) {

                                val eventIds = LongArray(MAX_EVENTS)
                                val events = arrayOfNulls<Object>(MAX_EVENTS)

                                val resultCount = TdNative.nativeClientReceive(client.clientId, eventIds, events, 0.0)

                                if (resultCount == 0) continue

                                for (index in 0 until resultCount) {

                                    val requestId = eventIds[index]
                                    val event = events[index]!!

                                    if (requestId != 0L) {

                                        if (!client.callbacks.containsKey(requestId)) continue

                                        val callback = client.callbacks.remove(requestId)!!

                                        launch(Dispatchers.Unconfined) {

                                            runCatching {

                                                if (event is Error) {

                                                    callback.postError(TdException(event))

                                                } else {

                                                    callback.postResult(event)

                                                }

                                            }.onFailure {

                                                defaultLog.error(it, "TdError - Sync\n\nIn callback$event")

                                            }

                                        }

                                    } else {

                                        postUpdate(client, event as Update)

                                    }

                                }

                            }

                            if (clients.isEmpty()) break

                            val useTime = SystemClock.now() - start

                            if (useTime < 100L) {

                                delay(100L - useTime)

                            }

                        }

                    }

                } catch (ignored: InterruptedException) {
                }

            }

        }

        class MessageNode(
                val message: Deferred<Pair<Long, Long>>,
                val outdated: Boolean
        ) {

            val isCompleted get() = message.isCompleted
            val isCancelled get() = message.isCancelled

            lateinit var parent: MessageNode

            fun activeCount(): Int {

                if (outdated || !message.isActive) return 0

                return if (::parent.isInitialized) parent.activeCount() + 1 else 1

            }

            fun count(): Int = if (::parent.isInitialized) parent.count() + 1 else 1

            fun drop() {

                if (message.isActive) {

                    message.cancel()

                    if (::parent.isInitialized) parent.drop()

                }

            }

        }

        private val messagesMap = LFUCache<Int, MessageNode>(0, 5 * 60 * 1000L)

        private suspend fun postUpdate(client: TdClient, update: Update) {

            suspend fun process(): Long {

                client.handlers.toLinkedList().forEach { handler ->

                    handler.runCatching {

                        handler.onUpdate(update)

                    }.onFailure {

                        if (it is CancellationException) return 0L

                        if (it is Finish) return 0L

                        if (it is FinishWithDelay) return it.delay

                        defaultLog.error(it, "TdError - Sync\n\nIn event $update")

                    }

                }

                return 0L

            }

            when (update) {

                is UpdateMessageSendSucceeded,
                is UpdateMessageSendFailed,
                is UpdateMessageSendAcknowledged -> {

                    GlobalScope.launch(Dispatchers.Unconfined) {

                        process()

                    }

                    return

                }

            }

            if (update is UpdateNewMessage) {

                suspend fun processMessage() {

                    if (!client.waitForAuth()) return

                    val senderUserId = update.message.senderUserId

                    val lastMessage = messagesMap[senderUserId]

                    val activeCount: Int
                    var count = 0

                    if (lastMessage != null) {

                        if (lastMessage.isCancelled) return

                        activeCount = lastMessage.activeCount()
                        count = lastMessage.count()

                        if (senderUserId == 0 || senderUserId == client.me.id || client.skipFloodCheck(senderUserId, update.message)) {

                            // 跳过检查

                        } else if (activeCount > 20 || count > 200) {

                            lastMessage.drop()

                            client.onDropMessages(senderUserId, lastMessage)

                            return

                        }

                    }

                    messagesMap.put(senderUserId, MessageNode(events.async {

                        if (lastMessage != null) {

                            val lastStart = lastMessage.message.await()

                            if (lastStart.second > 0L) delay(lastStart.first + lastStart.second - SystemClock.now())

                            if (count > 50) delay(count * 10L)

                        }

                        SystemClock.now() to process()

                    }, (SystemClock.now() / 1000L) - update.message.date > 30 * 1000L).apply {

                        if (lastMessage != null) parent = lastMessage

                    })

                }

                events.launch { processMessage() }

                return

            }

            events.launch {

                process()

            }

        }

    }

    open suspend fun onNewMessageLast(userId: Int, chatId: Long, message: Message) = Unit

    open suspend fun onDropMessages(senderUserId: Int, lastMessage: MessageNode) {

        val userName = getUserOrNull(senderUserId)?.displayNameFormatted ?: "$senderUserId"

        clientLog.warn("[${me.displayName}] $userName has been dropped for 5 min.")

    }

    open suspend fun skipFloodCheck(senderUserId: Int, message: Message) = me.isUser
    open suspend fun userBlocked(userId: Int) = false

}