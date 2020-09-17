package io.nekohasekai.ktlib.td.core

import td.TdApi.*
import java.io.File

class TdOptions {

    var useTestDc = false
    var databaseDirectory: String = File("data").canonicalPath
    var filesDirectory: String = File("cache/files").canonicalPath
    var useFileDatabase = true
    var useChatInfoDatabase = true
    var useMessageDatabase = true
    var useSecretChats = false
    var apiId = 21724 // TGX appId
    var apiHash = "3e0cb5efcd52300aec5994fdfc5bdc16" // TGX appHash
    var systemLanguageCode = "en"
    var deviceModel = "KtLib@td-${TdLoader.version}"
    var systemVersion = "1.0"
    var applicationVersion = "1.0"
    var enableStorageOptimizer = true
    var ignoreFileNames = true

    fun build(): TdlibParameters {

        return TdlibParameters(useTestDc, databaseDirectory, filesDirectory, useFileDatabase, useChatInfoDatabase, useMessageDatabase, useSecretChats, apiId, apiHash, systemLanguageCode, deviceModel, systemVersion, applicationVersion, enableStorageOptimizer, ignoreFileNames)

    }

    infix fun useTestDc(useTestDc: Boolean): TdOptions {
        this.useTestDc = useTestDc
        return this
    }

    infix fun databaseDirectory(databaseDirectory: String): TdOptions {
        this.databaseDirectory = File(databaseDirectory).apply { mkdirs() }.canonicalPath
        return this
    }

    infix fun useFileDatabase(useFileDatabase: Boolean): TdOptions {
        this.useFileDatabase = useFileDatabase
        return this
    }

    infix fun filesDirectory(filesDirectory: String): TdOptions {
        this.filesDirectory = File(filesDirectory).apply { mkdirs() }.canonicalPath
        return this
    }

    infix fun useChatInfoDatabase(useChatInfoDatabase: Boolean): TdOptions {
        this.useChatInfoDatabase = useChatInfoDatabase
        return this
    }

    infix fun useMessageDatabase(useMessageDatabase: Boolean): TdOptions {
        this.useMessageDatabase = useMessageDatabase
        return this
    }

    infix fun useSecretChats(useSecretChats: Boolean): TdOptions {
        this.useSecretChats = useSecretChats
        return this
    }

    infix fun apiId(apiId: Int): TdOptions {
        this.apiId = apiId
        return this
    }

    infix fun apiHash(apiHash: String): TdOptions {
        this.apiHash = apiHash
        return this
    }

    infix fun systemLanguageCode(systemLanguageCode: String): TdOptions {
        this.systemLanguageCode = systemLanguageCode
        return this
    }

    infix fun deviceModel(deviceModel: String): TdOptions {
        this.deviceModel = deviceModel
        return this
    }

    infix fun systemVersion(systemVersion: String): TdOptions {
        this.systemVersion = systemVersion
        return this
    }

    infix fun applicationVersion(applicationVersion: String): TdOptions {
        this.applicationVersion = applicationVersion
        return this
    }

    infix fun enableStorageOptimizer(enableStorageOptimizer: Boolean): TdOptions {
        this.enableStorageOptimizer = enableStorageOptimizer
        return this
    }

    infix fun ignoreFileNames(ignoreFileNames: Boolean): TdOptions {
        this.ignoreFileNames = ignoreFileNames
        return this
    }
}