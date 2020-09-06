package io.nekohasekai.ktlib.core

import org.apache.commons.lang3.SystemUtils

val isX64 = SystemUtils.OS_ARCH in arrayOf("amd64", "x86_64")
val isX86 = SystemUtils.OS_ARCH.matches("(i[3-6]86|x86)".toRegex())
val isArm64 = SystemUtils.OS_ARCH in arrayOf("aarch64", "arm64") && SystemUtils.OS_ARCH.startsWith("armv8")

val isWindows = SystemUtils.IS_OS_WINDOWS
val isWin32 = isWindows && isX86
val isWin64 = isWindows && isX64

val isLinux = SystemUtils.IS_OS_LINUX
val isMac = SystemUtils.IS_OS_MAC

val executablePrefix = when {

    isLinux -> "lib"

    else -> ""

}

val executableSuffix = when {

    isWindows -> "dll"
    isMac -> "dylib"

    else -> "so"

}