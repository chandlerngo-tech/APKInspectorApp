package com.example.apkinspector.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HistoryScreen(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Analysis History",
            style = MaterialTheme.typography.headlineMedium
        )

        Text("No APKs have been analyzed yet.")

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}