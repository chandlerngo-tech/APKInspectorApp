package com.example.apkinspector.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ApkDetailsScreen(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "APK Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Text("Application metadata will appear here.")

        Text("Permissions and APK information will appear here.")

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}