dependencies {

    val vHutool = "5.5.8"
    api("cn.hutool:hutool-core:$vHutool")
    api("cn.hutool:hutool-log:$vHutool")
    api("cn.hutool:hutool-json:$vHutool")
    api("cn.hutool:hutool-http:$vHutool")
    api("org.apache.commons:commons-lang3:3.11")

    api("com.squareup.okhttp3:okhttp:5.0.0-alpha.2") {
        exclude("org.jetbrains.kotlin", "kotlin-stdlib")
        exclude("org.jetbrains.kotlin", "kotlin-stdlib-common")
    }

    api("com.esotericsoftware:kryo:5.0.3")

}