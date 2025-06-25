package com.example.fitnesstracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.model.IntensityLevel
import com.example.fitnesstracker.data.model.Workout
import com.example.fitnesstracker.data.model.WorkoutType
import com.example.fitnesstracker.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AddWorkoutViewModel(private val repository: WorkoutRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AddWorkoutUiState())
    val uiState: StateFlow<AddWorkoutUiState> = _uiState.asStateFlow()

    fun updateWorkoutType(type: WorkoutType) {
        _uiState.value = _uiState.value.copy(selectedType = type)
    }

    fun updateSubtype(subtype: String) {
        _uiState.value = _uiState.value.copy(selectedSubtype = subtype)
    }

    fun updateDuration(duration: String) {
        _uiState.value = _uiState.value.copy(duration = duration)
    }

    fun updateIntensity(intensity: IntensityLevel) {
        _uiState.value = _uiState.value.copy(selectedIntensity = intensity)
    }

    fun updateCalories(calories: String) {
        _uiState.value = _uiState.value.copy(calories = calories)
    }

    fun updateNotes(notes: String) {
        _uiState.value = _uiState.value.copy(notes = notes)
    }

    fun saveWorkout(onSuccess: () -> Unit) {
        val state = _uiState.value

        if (state.selectedType == null || state.selectedSubtype.isBlank() ||
            state.duration.isBlank() || state.selectedIntensity == null) {
            return
        }

        val workout = Workout(
            type = state.selectedType,
            subtype = state.selectedSubtype,
            duration = state.duration.toIntOrNull() ?: 0,
            intensity = state.selectedIntensity,
            caloriesBurned = state.calories.toIntOrNull() ?: 0,
            notes = state.notes
        )

        viewModelScope.launch {
            repository.insertWorkout(workout)
            onSuccess()
        }
    }

    fun getSubtypesForWorkoutType(type: WorkoutType): List<String> {
        return when (type) {
            WorkoutType.CARDIO -> listOf("Running", "Walking", "Cycling", "Swimming", "Jumping Rope", "HIIT")
            WorkoutType.STRENGTH -> listOf("Weight Lifting", "Push-ups", "Pull-ups", "Squats", "Deadlifts", "Bench Press")
            WorkoutType.FLEXIBILITY -> listOf("Yoga", "Stretching", "Pilates", "Meditation")
            WorkoutType.SPORTS -> listOf("Basketball", "Football", "Tennis", "Badminton", "Boxing")
        }
    }
}

data class AddWorkoutUiState(
    val selectedType: WorkoutType? = null,
    val selectedSubtype: String = "",
    val duration: String = "",
    val selectedIntensity: IntensityLevel? = null,
    val calories: String = "",
    val notes: String = ""
)