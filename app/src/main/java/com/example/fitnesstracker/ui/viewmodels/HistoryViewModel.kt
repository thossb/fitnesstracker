package com.example.fitnesstracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.model.Workout
import com.example.fitnesstracker.data.model.WorkoutType
import com.example.fitnesstracker.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: WorkoutRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init {
        loadWorkouts()
    }

    private fun loadWorkouts() {
        viewModelScope.launch {
            repository.getAllWorkouts().collect { workouts ->
                _uiState.value = _uiState.value.copy(
                    workouts = workouts,
                    filteredWorkouts = applyFilters(workouts)
                )
            }
        }
    }

    fun updateFilter(filter: WorkoutType?) {
        _uiState.value = _uiState.value.copy(selectedFilter = filter)
        _uiState.value = _uiState.value.copy(
            filteredWorkouts = applyFilters(_uiState.value.workouts)
        )
    }

    private fun applyFilters(workouts: List<Workout>): List<Workout> {
        val filter = _uiState.value.selectedFilter
        return if (filter != null) {
            workouts.filter { it.type == filter }
        } else {
            workouts
        }
    }

    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch {
            repository.deleteWorkout(workout)
        }
    }
}

data class HistoryUiState(
    val workouts: List<Workout> = emptyList(),
    val filteredWorkouts: List<Workout> = emptyList(),
    val selectedFilter: WorkoutType? = null
)