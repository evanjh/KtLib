package net.sourceforge.tess4j

import io.nekohasekai.ktlib.ocr.TessUtil

object TessTest {

    @JvmStatic
    fun main(args: Array<String>) {

        println(TessUtil.doOcr("/home/sekai/下载/Telegram Desktop/IMG_4919.JPG"))

    }

}