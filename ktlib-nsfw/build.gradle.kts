repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    api(project(":ktlib-core"))
    api(project(":ktlib-compress"))

    implementation("net.java.dev.jna:jna:5.6.0")
    implementation("org.bytedeco:javacpp:1.5.4")
    implementation("org.bytedeco:javacpp:1.5.4:linux-x86_64")
    implementation("ai.djl.tensorflow:tensorflow-model-zoo:0.9.0")
    implementation("ai.djl.tensorflow:tensorflow-native-cpu:2.3.1")

}