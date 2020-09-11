@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.utils

import io.nekohasekai.ktlib.core.mkLog
import io.nekohasekai.ktlib.td.core.TdHandler
import io.nekohasekai.ktlib.td.core.raw.downloadFile
import kotlinx.coroutines.delay
import java.io.File

private val log = mkLog("FileDownloader")

suspend fun TdHandler.download(file: td.TdApi.File): File {

    var path: String

    if (!file.local.isDownloadingCompleted) {

        var times = 0

        while (true) {

            times++

            try {

                log.trace("Downloading ${file.remote.id}")

                path = downloadFile(file.id, 1, 0, 0, true).local.path!!

                break

            } catch (e: Exception) {

                if (times == 3) {

                    throw e

                } else {

                    log.warn(e, "Download failed, waiting for retry.")

                }

            }

            delay(1000L)

        }

    } else {

        path = file.local.path!!

    }

    return File(path)

}