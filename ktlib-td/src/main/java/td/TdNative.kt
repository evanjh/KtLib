package td

import td.TdApi.Object

@Suppress("unused")
object TdNative {

    @JvmStatic
    external fun createNativeClient(): Int

    @JvmStatic
    external fun nativeClientSend(nativeClientId: Int, eventId: Long, function: TdApi.Function<out Object>)

    @JvmStatic
    external fun nativeClientReceive(
        nativeClientId: IntArray,
        eventIds: LongArray,
        events: Array<Object?>,
        timeout: Double
    ): Int

    @JvmStatic
    external fun <T : Object> nativeClientExecute(function: TdApi.Function<T>): T

}