package com.example.apkinspector.data

import com.example.apkinspector.model.ApkFileEntry
import java.io.File
import java.util.zip.ZipFile

class ApkAnalyzer {

    fun analyze(apkPath: String): List<ApkFileEntry> {
        val file = File(apkPath)

        require(file.exists()) {
            "APK file does not exist."
        }

        require(file.isFile) {
            "Selected path is not a file."
        }

        ZipFile(file).use { zip ->
            return zip.entries().asSequence()
                .take(10000)
                .map { entry ->
                    ApkFileEntry(
                        name = entry.name,
                        size = entry.size,
                        compressedSize = entry.compressedSize,
                        isDirectory = entry.isDirectory
                    )
                }
                .toList()
        }
    }
}
