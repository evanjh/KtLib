@file:Suppress("unused")

package io.nekohasekai.ktlib.nsfw

import ai.djl.modality.Classifications
import ai.djl.modality.cv.Image
import ai.djl.modality.cv.transform.Resize
import ai.djl.modality.cv.transform.ToTensor
import ai.djl.modality.cv.translator.ImageClassificationTranslator
import ai.djl.repository.zoo.Criteria
import ai.djl.repository.zoo.ModelZoo
import ai.djl.repository.zoo.ZooModel
import io.nekohasekai.ktlib.compress.tar
import io.nekohasekai.ktlib.compress.xz
import io.nekohasekai.ktlib.core.downloadFile
import java.io.File

object NSFW {

    val classNames = listOf("drawings", "hentai", "neutral", "porn", "sexy")

    lateinit var model: ZooModel<Image, Classifications>

    fun loadModel(cachePath: File) {

        if (::model.isInitialized) return

        val modelPath = File(cachePath, "mobilenet_v2_140_224")

        if (!File(modelPath, "saved_model.pb").isFile) {

            val cacheFile = File(cachePath, "mobilenet_v2_140_224.tar.xz")

            downloadFile(
                "https://github.com/nekohasekai/KtLib/releases/download/nsfw_model/mobilenet_v2_140_224.tar.xz",
                cacheFile
            )

            val archive = cacheFile.inputStream().xz().tar()
            while (archive.nextEntry != null) {
                val currentEntry = archive.currentEntry
                if (currentEntry.isDirectory) {
                    File(modelPath, currentEntry.name).mkdirs()
                } else {
                    File(modelPath, currentEntry.name).writeBytes(archive.readBytes())
                }
            }

        }

        model = ModelZoo.loadModel(
            Criteria.builder()
                .setTypes(Image::class.java, Classifications::class.java)
                .optTranslator(
                    ImageClassificationTranslator.builder()
                        .addTransform(Resize(224, 224))
                        .addTransform(ToTensor())
                        .optSynset(classNames)
                        .build()
                )
                .optModelPath(modelPath.toPath())
                .build()
        )

    }

}