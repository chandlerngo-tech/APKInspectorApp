package com.example.apkinspector.data

import android.content.Context
import android.content.pm.PackageManager
import com.example.apkinspector.model.InstalledApp

class InstalledAppRepository(
    private val context: Context
) {
    fun getInstalledApps(): List<InstalledApp> {
        val pm = context.packageManager

        val packages = pm.getInstalledPackages(
            PackageManager.PackageInfoFlags.of(0)
        )

        return packages.mapNotNull { info ->
            val appInfo = info.applicationInfo
                ?: return@mapNotNull null

            InstalledApp(
                name = pm.getApplicationLabel(appInfo).toString(),
                packageName = info.packageName,
                versionName = info.versionName ?: "Unknown",
                apkPath = appInfo.sourceDir
            )
        }.sortedBy { it.name.lowercase() }
    }
}
