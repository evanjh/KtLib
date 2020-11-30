package td

import td.TdApi.*

@Suppress("unused")
object TdNative {

    @JvmStatic
    external fun createNativeClient(): Long

    @JvmStatic
    external fun nativeClientSend(nativeClientId: Long, eventId: Long, function: TdApi.Function<out Object>)

    @JvmStatic
    external fun nativeClientReceive(nativeClientId: LongArray, eventIds: LongArray, events: Array<Object?>, timeout: Double): Int

    @JvmStatic
    external fun <T: Object> nativeClientExecute(function: TdApi.Function<T>): T

}