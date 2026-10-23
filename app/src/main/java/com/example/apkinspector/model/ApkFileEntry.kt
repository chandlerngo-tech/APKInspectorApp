package com.example.apkinspector.model

data class ApkFileEntry(
    val name: String,
    val size: Long,
    val compressedSize: Long,
    val isDirectory: Boolean
)
