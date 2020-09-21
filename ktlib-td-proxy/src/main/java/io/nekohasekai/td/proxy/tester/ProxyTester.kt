package io.nekohasekai.td.proxy.tester

import io.nekohasekai.td.proxy.impl.Proxy

var testDcTarget = 1
var testTimeout = 5.0

interface ProxyTester<T : Proxy> {

    suspend fun testProxy(proxy: T): Int

    companion object : ProxyTester<Proxy> {

        private val implements = hashMapOf<Class<out Proxy>, ProxyTester<out Proxy>>()

        @Suppress("UNCHECKED_CAST")
        private fun <T : Proxy> getImpl(proxy: T) = implements[proxy.javaClass] as ProxyTester<T>?

        fun <T : Proxy> addTester(clazz: Class<T>, tester: ProxyTester<T>) = implements.put(clazz, tester)

        inline fun <reified T : Proxy> addTester(tester: ProxyTester<T>) = addTester(T::class.java, tester)

        override suspend fun testProxy(proxy: Proxy): Int {

            return (getImpl(proxy) ?: error("no checker impl for ${proxy.javaClass}")).testProxy(proxy)

        }

    }

}