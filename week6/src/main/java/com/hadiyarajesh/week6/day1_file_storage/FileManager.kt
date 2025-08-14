package com.hadiyarajesh.week6.day1_file_storage

import android.content.Context
import java.io.File

enum class DirectoryType {
    Cache,
    Files
}

enum class StorageType {
    Internal,
    External
}

object FileManager {
    private fun getBaseDirectory(
        directoryType: DirectoryType,
        storageType: StorageType,
        context: Context
    ): File? {
        return when (directoryType) {
            DirectoryType.Cache -> {
                when (storageType) {
                    StorageType.Internal -> {
                        context.cacheDir
                    }

                    StorageType.External -> {
                        context.externalCacheDir
                    }
                }
            }

            DirectoryType.Files -> {
                when (storageType) {
                    StorageType.Internal -> {
                        context.filesDir
                    }

                    StorageType.External -> {
                        context.getExternalFilesDir(null)
                    }
                }
            }
        }
    }

    fun writeToFile(
        context: Context,
        directoryType: DirectoryType,
        storageType: StorageType,
        fileName: String,
        content: String,
        onSuccess: () -> Unit = {}
    ) {
        val baseDirectory = getBaseDirectory(directoryType, storageType, context)
        val file = File(baseDirectory, fileName)
        file.writeText(content)
        onSuccess()
    }

    fun readFromFile(
        directoryType: DirectoryType,
        storageType: StorageType,
        fileName: String,
        context: Context
    ): String {
        val baseDirectory = getBaseDirectory(directoryType, storageType, context)
        val file = File(baseDirectory, fileName)
        return file.readText()
    }
}
