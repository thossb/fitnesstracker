package com.example.fitnesstracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.model.Workout
import com.example.fitnesstracker.data.model.WorkoutType
import com.example.fitnesstracker.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: WorkoutRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            // Get today's workouts
            val todayWorkouts = repository.getTodayWorkouts()

            // Get recent workouts
            repository.getAllWorkouts()
                .take(5)
                .collect { allWorkouts ->
                    val recentWorkouts = allWorkouts.take(5)

                    _uiState.value = _uiState.value.copy(
                        todayCalories = todayWorkouts.sumOf { it.caloriesBurned },
                        todayDuration = todayWorkouts.sumOf { it.duration },
                        todayWorkouts = todayWorkouts.size,
                        recentWorkouts = recentWorkouts,
                        workoutDistribution = calculateWorkoutDistribution(allWorkouts)
                    )
                }
        }
    }

    private fun calculateWorkoutDistribution(workouts: List<Workout>): Map<WorkoutType, Int> {
        return workouts.groupBy { it.type }.mapValues { it.value.size }
    }
}

data class DashboardUiState(
    val todayCalories: Int = 0,
    val todayDuration: Int = 0,
    val todayWorkouts: Int = 0,
    val todaySteps: Int = 0,
    val recentWorkouts: List<Workout> = emptyList(),
    val workoutDistribution: Map<WorkoutType, Int> = emptyMap()
)