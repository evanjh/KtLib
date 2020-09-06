package td

@Suppress("unused")
object TdNative {

    @JvmStatic
    external fun createNativeClient(): Long

    @JvmStatic
    external fun nativeClientSend(nativeClientId: Long, eventId: Long, function: td.TdApi.Function)

    @JvmStatic
    external fun nativeClientReceive(nativeClientId: Long, eventIds: LongArray, events: Array<td.TdApi.Object?>, timeout: Double): Int

    @JvmStatic
    external fun nativeClientExecute(function: td.TdApi.Function): td.TdApi.Object

    @JvmStatic
    external fun destroyNativeClient(nativeClientId: Long)

}