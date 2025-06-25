package com.example.fitnesstracker.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.data.repository.WorkoutRepository
import com.example.fitnesstracker.ui.components.BottomNavigationBar
import com.example.fitnesstracker.ui.screens.AddWorkoutScreen
import com.example.fitnesstracker.ui.screens.DashboardScreen
import com.example.fitnesstracker.ui.screens.HistoryScreen
import com.example.fitnesstracker.ui.screens.ProgressScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessTrackerApp(repository: WorkoutRepository) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("dashboard") {
                DashboardScreen(repository = repository)
            }
            composable("add_workout") {
                AddWorkoutScreen(
                    repository = repository,
                    onWorkoutSaved = {
                        navController.navigate("dashboard") {
                            popUpTo("dashboard") { inclusive = true }
                        }
                    }
                )
            }
            composable("history") {
                HistoryScreen(repository = repository)
            }
            composable("progress") {
                ProgressScreen(repository = repository)
            }
        }
    }
}