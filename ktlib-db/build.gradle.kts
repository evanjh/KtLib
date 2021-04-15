dependencies {
    api(project(":ktlib-core"))

    val vExposed = "0.30.2"
    api("org.jetbrains.exposed:exposed-core:$vExposed")
    api("org.jetbrains.exposed:exposed-dao:$vExposed")
    api("org.jetbrains.exposed:exposed-jdbc:$vExposed")

    val vHutool = "5.6.3"
    api("cn.hutool:hutool-cache:$vHutool")

    api("com.zaxxer:HikariCP:4.0.3")
    api("org.xerial:sqlite-jdbc:3.34.0")
}