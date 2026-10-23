package com.example.apkinspector.data

import android.content.Context
import android.net.Uri
import java.io.File

class ApkExporter(private val context: Context) {

    fun export(sourcePath: String, destination: Uri) {
        val source = File(sourcePath)

        require(source.isFile) {
            "Source APK does not exist."
        }

        val output = context.contentResolver
            .openOutputStream(destination)
            ?: error("Unable to open destination.")

        source.inputStream().use { input ->
            output.use { outputStream ->
                input.copyTo(outputStream)
            }
        }
    }
}
