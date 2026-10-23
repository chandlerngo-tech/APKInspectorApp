
package com.example.apkinspector.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apkinspector.viewmodel.AppListViewModel

@Composable
fun AppListScreen(
    onNavigateToDetails: (String) -> Unit,
    onNavigateToHistory: () -> Unit,
    appViewModel: AppListViewModel = viewModel()
) {
    val apps by appViewModel.apps.collectAsStateWithLifecycle()
    val isLoading by appViewModel.isLoading.collectAsStateWithLifecycle()

    var searchQuery by remember { mutableStateOf("") }

    val filteredApps = remember(apps, searchQuery) {
        apps.filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
                    it.packageName.contains(searchQuery, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "APK Inspector",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search applications") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedButton(onClick = onNavigateToHistory) {
            Text("Analysis History")
        }

        when {
            isLoading -> CircularProgressIndicator()

            filteredApps.isEmpty() ->
                Text("No matching applications found.")

            else -> LazyColumn {
                items(
                    items = filteredApps,
                    key = { it.packageName }
                ) { app ->
                    ListItem(
                        headlineContent = { Text(app.name) },
                        supportingContent = {
                            Text(app.packageName)
                        },
                        modifier = Modifier.clickable {
                            onNavigateToDetails(app.packageName)
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
