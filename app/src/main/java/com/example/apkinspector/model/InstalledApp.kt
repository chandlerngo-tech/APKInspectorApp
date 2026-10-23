package com.example.apkinspector.model

data class InstalledApp(
    val name: String,
    val packageName: String,
    val versionName: String,
    val apkPath: String
)
