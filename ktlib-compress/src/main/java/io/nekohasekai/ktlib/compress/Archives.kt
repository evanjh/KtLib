package io.nekohasekai.ktlib.compress

import cn.hutool.core.util.CharsetUtil
import org.apache.commons.compress.archivers.tar.TarArchiveEntry
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream
import java.io.File

fun File.xz(level: Int = 9) = XZCompressorOutputStream(outputStream(), level)
fun File.tarXz(level: Int = 9) = TarArchiveOutputStream(xz(level), CharsetUtil.UTF_8)

fun TarArchiveOutputStream.writeDirectories(dir: File) {

    val canonicalDir = dir.canonicalFile

    writeDirectories(canonicalDir.parentFile ?: return)

    writeDirectory(canonicalDir.path + "/")

}

fun TarArchiveOutputStream.writeDirectory(path: String) {

    putArchiveEntry(TarArchiveEntry(
            path,
            TarArchiveEntry.LF_DIR
    ))

    closeArchiveEntry()

}

fun TarArchiveOutputStream.writeFile(path: String, file: File = File(path)) {

    putArchiveEntry(TarArchiveEntry(file, path))

    file.inputStream().use { it.copyTo(this) }

    closeArchiveEntry()

}