import org.gradle.internal.jvm.Jvm
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30"
    id("com.github.ben-manes.versions") version "0.36.0"
}

group = "io.nekohasekai"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        jcenter()
        google()
    }

    apply(plugin = "com.github.ben-manes.versions")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
            noReflect = false
            noStdlib = false
            useIR = true
        }
    }
}