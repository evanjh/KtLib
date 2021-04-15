dependencies {
    api(project(":ktlib-td"))

    api("com.github.pengrad:java-telegram-bot-api:5.1.0") {
        exclude("com.squareup.okhttp3")
        exclude("com.google.code.gson", "gson")
    }

    api("com.google.code.gson:gson:2.8.6")
}