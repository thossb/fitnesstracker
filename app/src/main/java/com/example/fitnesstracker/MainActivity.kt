package com.example.fitnesstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fitnesstracker.data.database.WorkoutDatabase
import com.example.fitnesstracker.data.repository.WorkoutRepository
import com.example.fitnesstracker.navigation.FitnessTrackerApp
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = WorkoutDatabase.getDatabase(applicationContext)
        val repository = WorkoutRepository(database.workoutDao())

        setContent {
            FitnessTrackerTheme {
                FitnessTrackerApp(repository)
            }
        }
    }
}