package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.data.model.Workout
import com.example.fitnesstracker.data.repository.WorkoutRepository
import java.util.*

@Composable
fun ProgressScreen(repository: WorkoutRepository) {
    val workouts by repository.getAllWorkouts().collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Progress Analysis",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            WeeklyStatsCard(workouts)
        }

        item {
            MonthlyStatsCard(workouts)
        }

        item {
            WorkoutTypeProgressCard(workouts)
        }
    }
}

@Composable
private fun WeeklyStatsCard(workouts: List<Workout>) {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_MONTH, -7)
    val weekAgo = calendar.timeInMillis

    val weeklyWorkouts = workouts.filter { it.date >= weekAgo }
    val totalCalories = weeklyWorkouts.sumOf { it.caloriesBurned }
    val totalDuration = weeklyWorkouts.sumOf { it.duration }
    val workoutCount = weeklyWorkouts.size

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "This Week",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProgressStatItem("Workouts", "$workoutCount")
                ProgressStatItem("Calories", "$totalCalories")
                ProgressStatItem("Duration", "${totalDuration}m")
            }
        }
    }
}

@Composable
private fun MonthlyStatsCard(workouts: List<Workout>) {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_MONTH, -30)
    val monthAgo = calendar.timeInMillis

    val monthlyWorkouts = workouts.filter { it.date >= monthAgo }
    val totalCalories = monthlyWorkouts.sumOf { it.caloriesBurned }
    val totalDuration = monthlyWorkouts.sumOf { it.duration }
    val workoutCount = monthlyWorkouts.size
    val avgCaloriesPerWorkout = if (workoutCount > 0) totalCalories / workoutCount else 0

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "This Month",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProgressStatItem("Workouts", "$workoutCount")
                ProgressStatItem("Total Cal", "$totalCalories")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProgressStatItem("Duration", "${totalDuration}m")
                ProgressStatItem("Avg Cal", "$avgCaloriesPerWorkout")
            }
        }
    }
}

@Composable
private fun WorkoutTypeProgressCard(workouts: List<Workout>) {
    val typeStats = workouts.groupBy { it.type }.mapValues { entry ->
        val typeWorkouts = entry.value
        Triple(
            typeWorkouts.size,
            typeWorkouts.sumOf { it.caloriesBurned },
            typeWorkouts.sumOf { it.duration }
        )
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Progress by Type",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            if (typeStats.isEmpty()) {
                Text(
                    text = "No workout data available",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                typeStats.forEach { (type, stats) ->
                    val (count, calories, duration) = stats

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = type.displayName,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "$count workouts",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "$calories cal • ${duration}m",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    if (typeStats.keys.last() != type) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
private fun ProgressStatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}