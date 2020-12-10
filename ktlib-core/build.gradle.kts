dependencies {

    val vHutool = "5.5.2"
    api("cn.hutool:hutool-core:$vHutool")
    api("cn.hutool:hutool-log:$vHutool")
    api("cn.hutool:hutool-json:$vHutool")
    api("cn.hutool:hutool-http:$vHutool")
    api("org.apache.commons:commons-lang3:3.9")

    api("com.squareup.okhttp3:okhttp:4.10.0-RC1") {
        exclude("org.jetbrains.kotlin", "kotlin-stdlib")
        exclude("org.jetbrains.kotlin", "kotlin-stdlib-common")
    }

    api("com.esotericsoftware:kryo:5.0.2")

}