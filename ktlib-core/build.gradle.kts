dependencies {

    api(kotlin("reflect"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")

    val vHutool = "5.6.3"
    api("cn.hutool:hutool-core:$vHutool")
    api("cn.hutool:hutool-log:$vHutool")
    api("cn.hutool:hutool-json:$vHutool")
    api("cn.hutool:hutool-http:$vHutool")
    api("org.apache.commons:commons-lang3:3.12.0")

    api("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    api("com.esotericsoftware:kryo:5.1.0")

}