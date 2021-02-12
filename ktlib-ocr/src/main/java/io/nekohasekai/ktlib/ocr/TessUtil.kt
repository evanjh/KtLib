@file:Suppress("unused")

package io.nekohasekai.ktlib.ocr

import cn.hutool.core.util.CharsetUtil
import cn.hutool.core.util.RuntimeUtil
import io.nekohasekai.ktlib.core.defaultLog
import net.sourceforge.tess4j.ITessAPI
import net.sourceforge.tess4j.TessAPI1
import net.sourceforge.tess4j.util.ImageIOHelper
import java.io.File
import java.io.InputStream
import javax.imageio.ImageIO
import kotlin.math.ceil

object TessUtil {

    lateinit var handle: ITessAPI.TessBaseAPI
    var failed = false

    fun initTess(): Boolean {

        if (::handle.isInitialized) return true

        if (!failed) try {
            handle = TessAPI1.TessBaseAPICreate()
            RuntimeUtil.addShutdownHook { TessAPI1.TessBaseAPIDelete(handle) }
            TessAPI1.TessBaseAPIInit3(handle, null, "chi_sim+chi_tra+eng")
        } catch (e: Exception) {
            failed = true
            defaultLog.error(e, "Init tesseract failed: ")
        }

        return !failed

    }

    fun doOcr(imagePath: String) = doOcr(File(imagePath))
    fun doOcr(imageFile: File) = doOcr(imageFile.inputStream())

    fun doOcr(inputStream: InputStream): String {

        initTess()

        val image = ImageIO.read(inputStream)
        val buf = ImageIOHelper.convertImageData(image)
        val bytesPerPixel = image.colorModel.pixelSize / 8
        val bytesPerLine = ceil(image.width * image.colorModel.pixelSize / 8.0).toInt()
        TessAPI1.TessBaseAPISetImage(handle, buf, image.width, image.height, bytesPerPixel, bytesPerLine)
        TessAPI1.TessBaseAPISetRectangle(handle, 0, 0, image.width, image.height)
        val utf8Text = TessAPI1.TessBaseAPIGetUTF8Text(handle)
        val result = utf8Text.getString(0, CharsetUtil.UTF_8)
        TessAPI1.TessDeleteText(utf8Text)

        return result

    }

}