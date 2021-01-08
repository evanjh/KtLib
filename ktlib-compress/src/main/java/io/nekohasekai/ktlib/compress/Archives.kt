@file:Suppress("unused")

package io.nekohasekai.ktlib.compress

import org.apache.commons.compress.archivers.tar.TarArchiveEntry
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream
import java.io.File
import java.io.InputStream
import java.io.OutputStream

fun InputStream.xz() = XZCompressorInputStream(this)
fun InputStream.tar() = TarArchiveInputStream(this)
fun OutputStream.xz(level: Int = 9) = XZCompressorOutputStream(this, level)
fun OutputStream.tar() = TarArchiveOutputStream(this)

fun TarArchiveOutputStream.writeDirectories(dir: File) {

    val canonicalDir = dir.canonicalFile

    writeDirectories(canonicalDir.parentFile ?: return)

    writeDirectory(canonicalDir.path + "/")

}

fun TarArchiveOutputStream.writeDirectory(path: String) {

    putArchiveEntry(
        TarArchiveEntry(
            path,
            TarArchiveEntry.LF_DIR
        )
    )

    closeArchiveEntry()

}

fun TarArchiveOutputStream.writeFile(path: String, file: File = File(path)) {

    putArchiveEntry(TarArchiveEntry(file, path))

    file.inputStream().use { it.copyTo(this) }

    closeArchiveEntry()

}

fun TarArchiveOutputStream.writeFile(path: String, input: String) {
    val bytes = input.toByteArray()
    writeFile(path, bytes.inputStream(), bytes.size.toLong())
}

fun TarArchiveOutputStream.writeFile(path: String, input: InputStream, length: Long) {

    putArchiveEntry(TarArchiveEntry(path).apply {
        size = length
    })

    input.copyTo(this)

    closeArchiveEntry()

}