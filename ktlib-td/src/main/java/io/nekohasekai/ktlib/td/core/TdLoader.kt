package io.nekohasekai.ktlib.td.core

import io.nekohasekai.ktlib.core.*
import java.io.File
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.system.exitProcess

object TdLoader {

    private val log = mkLog("TdLoader")

    const val version = "1.7.0"

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

    private fun tryLoadZLib() {

        val exceptions = LinkedList<Throwable>()

        runCatching {

            System.loadLibrary("zlib1")

            return

        }.onFailure {

            exceptions.add(it)

        }


        System.getenv("PATH").split(";").forEach { path ->

            if (!File(path, "git.exe").isFile) return@forEach

            val mingwBin = File(path, "../${if (isX64) "mingw64" else "mingw32"}/bin").canonicalFile

            runCatching {

                System.load("$mingwBin\\zlib1.dll")

                return

            }.onFailure {

                exceptions.add(it)

            }

        }

        exceptions.forEach { it.printStackTrace() }

        defaultLog.error("zlib not found in your system classpath, please install Git for Windows.")
        defaultLog.error("Check out https://gitforwindows.org/")

        exitProcess(100)

    }

    private fun tryLoadOpenSSL() {

        val (libcrypto, libssl) = if (isX64) {

            arrayOf("libcrypto-1_1-x64", "libssl-1_1-x64")

        } else {

            arrayOf("libcrypto-1_1", "libssl-1_1")

        }

        val exceptions = LinkedList<Throwable>()

        runCatching {

            System.loadLibrary(libcrypto)
            System.loadLibrary(libssl)

        }.onFailure {

            exceptions.add(it)

        }

        System.getenv("PATH").split(";").forEach { path ->

            if (!File(path, "git.exe").isFile) return@forEach

            val mingwBin = File(path, "../${if (isX64) "mingw64" else "mingw32"}/bin").canonicalFile

            runCatching {

                System.load("$mingwBin\\$libcrypto.dll")
                System.load("$mingwBin\\$libssl.dll")

                return

            }.onFailure {

                exceptions.add(it)

            }

        }

        exceptions.forEach { it.printStackTrace() }

        defaultLog.error("openssl not found in your system classpath, please install Git for Windows.")
        defaultLog.error("Check out https://gitforwindows.org/")


    }

    private fun checkVCRuntime() {

        runCatching {

            System.loadLibrary("vcruntime140")

        }.onFailure {

            defaultLog.error(it)

            defaultLog.error("Visual C++ Runtime 2015 not installed.")
            defaultLog.error("Check out https://github.com/abbodi1406/vcredist/releases")

            exitProcess(100)

        }

    }

    /**
     * 尝试加载 TDLib
     *
     * @param libsDir 从加载的目录
     * @param allowDownload 允许下载, 支持 Windows 32 64 / Linux x86 x86_64 arm64v8
     */
    fun tryLoad(libsDir: File = File("cache/libs"), allowDownload: Boolean = true) {

        if (loaded) return

        if (isWindows) {

            checkVCRuntime()

            tryLoadZLib()
            tryLoadOpenSSL()

        }

        var errMsg = ""

        var jniFile = File("$libsDir/${executablePrefix}tdjni.${executableSuffix}")

        runCatching<Unit> {

            System.loadLibrary("tdjni")

            return

        }.recoverCatching {

            errMsg = it.message ?: it.javaClass.simpleName

            if (!jniFile.isFile) error("$jniFile not found.")

            System.load(jniFile.path)

            loaded = true

            return

        }.recoverCatching {

            errMsg += "\n\n" + it.message

        }

        jniFile = File("$libsDir/${executablePrefix}tdjni.$version.${executableSuffix}")

        fun jniLoad() {

            runCatching {

                System.load(jniFile.canonicalPath)

            }.onFailure {

                errMsg += "\n\n" + it.message

                error(errMsg)

            }

        }

        if (jniFile.isFile || !allowDownload) {

            jniLoad()

            return

        }

        runCatching {

            val url = "https://github.com/TdBotProject/LibTDJni/releases/download/td@$version/" + when {

                isLinux -> "libtdjni." + when {
                    isX64 -> "x86_64"
                    isX86 -> "x86"
                    isArm64 -> "arm64v8"
                    else -> error("unsupported")
                } + ".so"

                isWin32 -> "tdjni.x86.dll"
                isWin64 -> "tdjni.x86_64.dll"

                else -> error("unsupported")
            }

            // 删除旧版本
            jniFile.parentFile?.listFiles()?.filter { it.name.contains("tdjni") }?.forEach { it.delete() }

            log.info("下载 TDLib 二进制文件: $url")

            downloadFile3(url, jniFile)

        }.onFailure {

            errMsg += "\n\n" + it.parse()

            error(errMsg)

        }

        jniLoad()

    }

}