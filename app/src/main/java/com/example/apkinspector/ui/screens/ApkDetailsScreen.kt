package com.example.apkinspector.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ApkDetailsScreen(
    packageName: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "APK Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Text("Package: $packageName")
        Text("APK extraction coming in Week 3.")

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}
