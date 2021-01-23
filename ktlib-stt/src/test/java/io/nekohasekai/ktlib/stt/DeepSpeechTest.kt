package io.nekohasekai.ktlib.stt

import cn.hutool.core.io.IoUtil
import cn.hutool.core.util.RuntimeUtil
import org.deepspeech.libdeepspeech.DeepSpeechModel
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder


object DeepSpeechTest {

    fun stt(self: DeepSpeechModel, audioFile: File) {

        val inferenceStartTime = System.currentTimeMillis()

        val process = RuntimeUtil.exec(
            "ffmpeg",
            "-hide_banner",
            "-nostats",
            "-loglevel", "fatal",
            "-i", audioFile.canonicalPath,
            "-vn",
            "-acodec", "pcm_s16le",
            "-ac", "1",
            "-ar", "${self.sampleRate()}",
            "-f", "s16le",
            "pipe:"
        )

        val frame = ByteArray(IoUtil.DEFAULT_LARGE_BUFFER_SIZE)
        val input = process.inputStream
        var proc = 0L
        var ctx = self.createStream()

        while (true) {

            val readCount = input.read(frame)
            if (readCount == -1) break
            proc += readCount

            val shorts = ShortArray(readCount / 2)
            ByteBuffer.wrap(frame).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts)
            self.feedAudioContent(ctx, shorts, shorts.size)

            if (proc >= 128 * 1024) {
                val result = self.finishStream(ctx)
                println(result)
                ctx = self.createStream()
                proc = 0L
            }
        }

        val result = self.finishStream(ctx)
        println(result)

        val inferenceExecTime = System.currentTimeMillis() - inferenceStartTime
        println((inferenceExecTime / 1000).toString() + "s")

    }

    @JvmStatic
    fun main(args: Array<String>) {

        val model = DeepSpeechLoader.downloadModel(File("../cache"), "zh-CN")

        stt(model, File("/home/sekai/下载/8388794398_884432.mp3"))

        model.freeModel()

    }

}