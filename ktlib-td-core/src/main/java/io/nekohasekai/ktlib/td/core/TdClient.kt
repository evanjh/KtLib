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
import io.nekohasekai.ktlib.td.core.raw.*
import io.nekohasekai.ktlib.td.core.utils.confirmTo
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
import kotlin.coroutines.suspendCoroutine

open class TdClient : TdHandler() {

    override val sudo get() = this

    var options: TdOptions = TdOptions()
    var encryptionKey: ByteArray? = null

    open val skipSelfMessage = true
    open val funPrefix = arrayOf("/", "!")
    open val defaultLang: String? = null
    open val supportedLangs: String? = "en_US,zh_CN,zh_TW"

    val payloads = HashMap<String, TdHandler>()
    val functions = HashMap<String, TdHandler>()
    val callbackQueries = HashMap<Long, TdHandler>()

    val persistHandlers = HashMap<Int, TdHandler>()

    var persistImpl: TdPersist.Impl = TdPersist.NoImpl

    val persists by lazy { TdPersist.Map(this) }

    var handlers = LinkedList<TdHandler>()

    var start by AtomicBoolean()
    var started by AtomicBoolean()
    var authing by AtomicBoolean()
    var auth by AtomicBoolean()
    var stop by AtomicBoolean()
    var closed by AtomicBoolean()

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

    fun initPersistDatabase(database: DatabaseDispatcher = initDatabase(), tableName: String = "td_persists") {

        persistImpl = TdPersist.DatabaseImpl(database, tableName)

    }

    override suspend fun gc() {

        persists.forEach { it.save() }

        persists.clear()

        persistImpl.gc {

            events.launch {

                runCatching {

                    persistHandlers[it.persistId]?.apply {

                        onPersistTimeout(it.userId, it.userId.toLong(), it.subId, it.data)

                        onSendTimeoutMessage(it.userId, it.userId.toLong())

                    }

                }.onFailure {

                    it.printStackTrace()

                }

            }

        }

        handlers.filter { it != this }.forEach { it.gc() }

    }

    override suspend fun onDestroy() {

        persists.forEach { it.save() }

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

                    clients.forEach { it.waitForClose() }

                    events.launch {

                        defaultLog.info("Closing threads")

                    }.join()

                    eventsContext.close()

                }

                loopThread.interrupt()

                loopThread.join()

            }

        }

    }

    suspend fun waitForStart() {

        if (!start) start()

        while (!started) delay(100L)

    }

    suspend fun waitForAuth(): Boolean {

        if (!start) start()

        while (authing) delay(100L)

        return auth

    }

    suspend fun waitForLogin() {

        if (!start) start()

        while (!auth) delay(100L)

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

        while (!closed) delay(1000L)

    }

    override suspend fun onNewMessage(userId: Int, chatId: Long, message: Message) = coroutineScope function@{

        if (!sudo.waitForAuth() || userId == me.id && skipSelfMessage) finishEvent()

        val persist = if (message.fromPrivate) persists.fetch(userId).tdPersist else null

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

                    val handler = persistHandlers[persist.persistId]

                    if (handler == null) {

                        finishEvent()

                    }

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

        if (persist == null) return@function

        val handler = persistHandlers[persist.persistId]

        if (handler == null) {

            warnUserCalled(userId, "message in undefined persist ${persist.persistId}")

            finishEvent()

        }

        userCalled(userId, "persist message in ${handler.javaClass.simpleName}")

        handler.onPersistMessage(userId, chatId, message, persist.subId, persist.data)

        finishEvent()

    }

    open suspend fun onLaunch(userId: Int, chatId: Long, message: Message) = Unit

    override suspend fun handleNewInlineCallbackQuery(id: Long, senderUserId: Int, inlineMessageId: String, chatInstance: Long, payload: CallbackQueryPayload) {

        while (!auth) delay(100L)

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

        if (!waitForAuth()) return

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

                    handlers.toLinkedList().forEach { it.onLogin() }

                    authing = false

                    auth = true

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

            suspendCoroutine { continuation ->

                send<T>(function, 1) {

                    onSuccess { result ->

                        continuation.resume(result)

                    }

                    onFailure { exception ->

                        continuation.resumeWithException(TdException(exception).also {

                            it.stackTrace = stackTrace

                        })

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

        check(!closed) { "已停止" }

        TdNative.nativeClientSend(clientId, requestId, function)

    }

    override suspend fun onSendTimeoutMessage(userId: Int, chatId: Long) {

        try {

            getChat(chatId)

            onSendCanceledMessage(userId, chatId)

        } catch (ignored: TdException) {
        }

    }

    companion object {

        val authLog = mkLog("TdAuth")
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

        val loopThreadInitiated get() = Companion::loopThread.isInitialized

        class LoopThread : Thread("TDLib Loop Thread") {

            override fun run() {

                try {

                    runBlocking {

                        while (!isInterrupted) {

                            synchronized(postAdd) {

                                val iter = postAdd.iterator()

                                while (iter.hasNext()) {

                                    val toAdd = iter.next()

                                    clients.add(toAdd)

                                    iter.remove()

                                    toAdd.started = true

                                }

                            }

                            synchronized(postDestroy) {

                                val iter = postDestroy.iterator()

                                while (iter.hasNext()) {

                                    val toDestroy = iter.next()

                                    clients.remove(toDestroy)

                                    TdNative.destroyNativeClient(toDestroy.clientId)

                                    toDestroy.stop = true
                                    toDestroy.closed = true

                                    iter.remove()

                                }

                            }

                            if (clients.isEmpty()) {

                                delay(1000L)

                                continue

                            }

                            val start = SystemClock.now()

                            for (client in clients) {

                                val eventIds = LongArray(MAX_EVENTS)
                                val eventObjs = arrayOfNulls<Object>(MAX_EVENTS)

                                val resultCount = TdNative.nativeClientReceive(client.clientId, eventIds, eventObjs, 0.0)

                                if (resultCount == 0) continue

                                for (index in 0 until resultCount) {

                                    val requestId = eventIds[index]
                                    val eventObj = eventObjs[index]!!

                                    if (requestId != 0L) {

                                        if (!client.callbacks.containsKey(requestId)) continue

                                        val callback = client.callbacks.remove(requestId)!!

                                        launch(Dispatchers.Unconfined) {

                                            runCatching {

                                                if (eventObj is Error) {

                                                    callback.postError(TdException(eventObj))

                                                } else {

                                                    callback.postResult(eventObj)

                                                }

                                            }.onFailure {

                                                defaultLog.error(it, "TdError - Sync\n\nIn callback$eventObj")

                                            }

                                        }

                                    } else {

                                        postUpdate(client, eventObj as Update)

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
                val message: Deferred<Pair<Long, Long>>
        ) {

            val isCompleted get() = message.isCompleted
            val isCancelled get() = message.isCancelled

            lateinit var parent: MessageNode

            fun activeCount(): Int {

                if (!message.isActive) return 0

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

                        if (senderUserId == client.me.id || client.skipFloodCheck(senderUserId, update.message)) {

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

                    }).apply {

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

    open suspend fun onDropMessages(senderUserId: Int, lastMessage: MessageNode) {

        val userName = getUserOrNull(senderUserId)?.displayNameFormatted ?: "$senderUserId"

        eventsLog.warn("[${me.displayName}] $userName has been dropped for 5 min.")

    }

    open suspend fun skipFloodCheck(senderUserId: Int, message: Message) = false

}