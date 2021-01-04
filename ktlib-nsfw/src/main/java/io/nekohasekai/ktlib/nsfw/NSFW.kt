@file:Suppress("unused")

package io.nekohasekai.ktlib.nsfw

import ai.djl.inference.Predictor
import ai.djl.modality.Classifications
import ai.djl.modality.cv.BufferedImageFactory
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

    const val DRAWINGS = "drawings"
    const val HENTAI = "hentai"
    const val NEUTRAL = "neutral"
    const val PORN = "porn"
    const val SEXY = "sexy"

    val classNames = listOf(DRAWINGS, HENTAI, NEUTRAL, PORN, SEXY)

    lateinit var model: ZooModel<Image, Classifications>
    lateinit var predictor: Predictor<Image, Classifications>

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

            cacheFile.delete()

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

        predictor = model.newPredictor()

    }

    fun predict(vararg image: File): List<Map<String, Double>> {
        if (!::model.isInitialized) error("Init first")
        val images = image.map { BufferedImageFactory.getInstance().fromFile(it.toPath()) }
        return predictor.batchPredict(images).map { classifications ->
            mapOf(* classifications.items<Classifications.Classification>().map {
                it.className to it.probability
            }.toTypedArray())
        }
    }

}