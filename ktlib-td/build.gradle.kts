plugins {
    application
}

dependencies {

    api(project(":ktlib-core"))
    api(project(":ktlib-db"))
    api("org.yaml:snakeyaml:1.26")

}

application {
    applicationName = project.name
    mainClass.set("io.nekohasekai.ktlib.td.i18n.LocaleDefGeneratorKt")
}