package com.example.apkinspector.ui.screens

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apkinspector.viewmodel.ApkDetailsViewModel
import com.example.apkinspector.viewmodel.ApkDetailsUiState

@Composable
fun ApkDetailsScreen(
    packageName: String,
    onBack: () -> Unit,
    appViewModel: ApkDetailsViewModel = viewModel()
) {
    val context = LocalContext.current
    val state by appViewModel.uiState.collectAsStateWithLifecycle()

    val exporter = remember(context) {
        com.example.apkinspector.data.ApkExporter(context)
    }

    var exportMessage by remember {
        mutableStateOf<String?>(null)
    }

    val exportLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument(
            "application/vnd.android.package-archive"
        )
    ) { uri ->
        if (uri != null) {
            appViewModel.exportApk(uri, exporter) {
                exportMessage = it
            }
        }
    }

    LaunchedEffect(packageName) {
        appViewModel.loadApk(packageName)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(onClick = onBack) {
            Text("Back")
        }

        Text(
            "APK Details",
            style = MaterialTheme.typography.headlineMedium
        )

        when (val current = state) {
            is ApkDetailsUiState.Loading -> {
                CircularProgressIndicator()
            }

            is ApkDetailsUiState.Error -> {
                Text(
                    current.message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            is ApkDetailsUiState.Success -> {
                Text("App: ${current.appName}")
                Text("Package: ${current.packageName}")
                Text("Version: ${current.versionName}")
                Text("APK Size: ${current.apkSize / 1024} KB")

                Button(
                    onClick = {
                        exportLauncher.launch(
                            "${current.packageName}.apk"
                        )
                    }
                ) {
                    Text("Extract APK")
                }

                Text(
                    "APK File Structure",
                    style = MaterialTheme.typography.titleLarge
                )

                LazyColumn(
                    verticalArrangement =
                        Arrangement.spacedBy(4.dp)
                ) {
                    items(current.files) { file ->
                        ListItem(
                            headlineContent = {
                                Text(file.name)
                            },
                            supportingContent = {
                                Text(
                                    "Size: ${file.size} bytes"
                                )
                            }
                        )
                        HorizontalDivider()
                    }
                }
            }
        }

        exportMessage?.let {
            Text(it)
        }
    }
}
