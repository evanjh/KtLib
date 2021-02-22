dependencies {
    api(project(":ktlib-core"))

    val vExposed = "0.29.1"
    api("org.jetbrains.exposed:exposed-core:$vExposed")
    api("org.jetbrains.exposed:exposed-dao:$vExposed")
    api("org.jetbrains.exposed:exposed-jdbc:$vExposed")

    val vHutool = "5.5.8"
    api("cn.hutool:hutool-cache:$vHutool")

    api("com.zaxxer:HikariCP:4.0.2")
    api("org.xerial:sqlite-jdbc:3.34.0")
}