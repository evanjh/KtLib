package io.nekohasekai.ktlib.stt

import io.nekohasekai.ktlib.core.*
import org.deepspeech.libdeepspeech.DeepSpeechModel
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean

object DeepSpeechLoader {

    const val version = "0.9.3"

    var loaded by AtomicBoolean()

    private fun downloadFile3(url: String, dist: File) {

        lateinit var exception: Exception

        repeat(3) {

            try {

                downloadFile(url, dist)

                return

            } catch (e: Exception) {

                exception = e

            }

        }

        throw exception

    }

    fun downloadAndLoad(cacheDir: File) {

        if (loaded) return

        runCatching {
            System.loadLibrary("deepspeech");
            System.loadLibrary("deepspeech-jni");
            loaded = true
            return
        }.onFailure {
            if (!isArm64 && !isX64) error("unsupported platform (LibDeepSpeechJni)")
        }

        val libsDir = File(cacheDir, "libs")
        val jniFile1 = File(libsDir, "libdeepspeech.so")
        val jniFile2 = File(libsDir, "deepspeech-jni.so")

        val arch = if (isX64) "x86_64" else "aarch64"

        if (!jniFile1.isFile) downloadFile3(
            "https://github.com/TdBotProject/LibDeepSpeechJni/releases/download/v$version/libdeepspeech.$arch.so",
            jniFile1
        )
        if (!jniFile2.isFile) downloadFile3(
            "https://github.com/TdBotProject/LibDeepSpeechJni/releases/download/v$version/libdeepspeech-jni.$arch.so",
            jniFile2
        )

        System.load(jniFile1.canonicalPath)
        System.load(jniFile2.canonicalPath)

    }

    fun downloadModel(cacheDir: File, language: String = ""): DeepSpeechModel {

        downloadAndLoad(cacheDir)

        val modelName = if (language.isBlank()) language else "-$language"

        val libsDir = File(cacheDir, "deepspeech")
        val modelFile = File(libsDir, "deepspeech-$version-models$modelName.pbmm")
        val scorerFile = File(libsDir, "deepspeech-$version-models$modelName.scorer")

        if (!modelFile.isFile) downloadFile3(
            "https://github.com/mozilla/DeepSpeech/releases/download/v$version/deepspeech-$version-models$modelName.pbmm",
            modelFile
        )
        if (!scorerFile.isFile) downloadFile3(
            "https://github.com/mozilla/DeepSpeech/releases/download/v$version/deepspeech-$version-models$modelName.scorer",
            scorerFile
        )

        return DeepSpeechModel(modelFile.canonicalPath).apply {
            enableExternalScorer(scorerFile.canonicalPath)
        }

    }

}