repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    api(project(":ktlib-core"))

    api("org.bytedeco:ffmpeg:4.3.1-1.5.5-SNAPSHOT")
    api("org.bytedeco:ffmpeg:4.3.1-1.5.5-SNAPSHOT:linux-x86_64-gpl")
}