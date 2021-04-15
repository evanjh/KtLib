repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    api(project(":ktlib-core"))
    api(project(":ktlib-compress"))

    api("net.java.dev.jna:jna:5.8.0")
    api("org.bytedeco:javacpp:1.5.5")
    api("org.bytedeco:javacpp:1.5.5:linux-x86_64")
    api("ai.djl.tensorflow:tensorflow-model-zoo:0.10.0")
    api("ai.djl.tensorflow:tensorflow-native-cpu:2.3.1")

}