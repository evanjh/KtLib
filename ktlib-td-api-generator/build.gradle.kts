plugins {
    application
}

dependencies {
    api(project(":ktlib-core"))
}

application {
    mainClass.set("io.nekohasekai.ktlib.td.generator.TdApiGenerator")
}