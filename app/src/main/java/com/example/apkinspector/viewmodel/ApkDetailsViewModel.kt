package com.example.apkinspector.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.apkinspector.data.ApkAnalyzer
import com.example.apkinspector.data.ApkExporter
import com.example.apkinspector.model.ApkFileEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed interface ApkDetailsUiState {
    data object Loading : ApkDetailsUiState

    data class Success(
        val appName: String,
        val packageName: String,
        val versionName: String,
        val apkSize: Long,
        val apkPath: String,
        val files: List<ApkFileEntry>
    ) : ApkDetailsUiState

    data class Error(
        val message: String
    ) : ApkDetailsUiState
}

class ApkDetailsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val _uiState =
        MutableStateFlow<ApkDetailsUiState>(
            ApkDetailsUiState.Loading
        )

    val uiState: StateFlow<ApkDetailsUiState> =
        _uiState

    fun loadApk(packageName: String) {
        viewModelScope.launch {
            _uiState.value = ApkDetailsUiState.Loading

            try {
                val result = withContext(Dispatchers.IO) {
                    val pm = getApplication<Application>()
                        .packageManager

                    val info = pm.getPackageInfo(
                        packageName,
                        0
                    )

                    val appInfo = info.applicationInfo
                        ?: error("Application information missing")

                    val path = appInfo.sourceDir
                    val apkFile = java.io.File(path)

                    val files = ApkAnalyzer().analyze(path)

                    ApkDetailsUiState.Success(
                        appName = pm.getApplicationLabel(
                            appInfo
                        ).toString(),
                        packageName = packageName,
                        versionName =
                            info.versionName ?: "Unknown",
                        apkSize = apkFile.length(),
                        apkPath = path,
                        files = files
                    )
                }

                _uiState.value = result

            } catch (e: Exception) {
                _uiState.value = ApkDetailsUiState.Error(
                    e.message ?: "Unable to analyze APK"
                )
            }
        }
    }

    fun exportApk(
        destination: Uri,
        exporter: ApkExporter,
        onResult: (String) -> Unit
    ) {
        val current = _uiState.value

        if (current !is ApkDetailsUiState.Success) {
            onResult("No APK selected")
            return
        }

        viewModelScope.launch {
            val message = try {
                withContext(Dispatchers.IO) {
                    exporter.export(
                        current.apkPath,
                        destination
                    )
                }
                "APK exported successfully!"
            } catch (e: Exception) {
                "Export failed: ${e.message}"
            }

            onResult(message)
        }
    }
}
