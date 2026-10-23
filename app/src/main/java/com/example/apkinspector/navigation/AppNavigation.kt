package com.example.apkinspector.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.apkinspector.ui.screens.AppListScreen
import com.example.apkinspector.ui.screens.ApkDetailsScreen
import com.example.apkinspector.ui.screens.HistoryScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "apps"
    ) {
        composable("apps") {
            AppListScreen(
                onNavigateToDetails = {
                    navController.navigate("details")
                },
                onNavigateToHistory = {
                    navController.navigate("history")
                }
            )
        }

        composable("details") {
            ApkDetailsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("history") {
            HistoryScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}