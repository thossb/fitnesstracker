package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesstracker.data.model.IntensityLevel
import com.example.fitnesstracker.data.model.WorkoutType
import com.example.fitnesstracker.data.repository.WorkoutRepository
import com.example.fitnesstracker.ui.viewmodels.AddWorkoutUiState
import com.example.fitnesstracker.ui.viewmodels.AddWorkoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWorkoutScreen(
    repository: WorkoutRepository,
    onWorkoutSaved: () -> Unit,
    viewModel: AddWorkoutViewModel = viewModel { AddWorkoutViewModel(repository) }
) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Add New Workout",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            WorkoutTypeSelection(
                selectedType = uiState.selectedType,
                onTypeSelected = viewModel::updateWorkoutType
            )
        }

        if (uiState.selectedType != null) {
            item {
                SubtypeSelection(
                    selectedType = uiState.selectedType!!,
                    selectedSubtype = uiState.selectedSubtype,
                    onSubtypeSelected = viewModel::updateSubtype,
                    getSubtypes = viewModel::getSubtypesForWorkoutType
                )
            }

            item {
                WorkoutDetailsForm(
                    uiState = uiState,
                    onDurationChange = viewModel::updateDuration,
                    onIntensityChange = viewModel::updateIntensity,
                    onCaloriesChange = viewModel::updateCalories,
                    onNotesChange = viewModel::updateNotes
                )
            }

            item {
                Button(
                    onClick = { viewModel.saveWorkout(onWorkoutSaved) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = uiState.selectedType != null &&
                            uiState.selectedSubtype.isNotBlank() &&
                            uiState.duration.isNotBlank() &&
                            uiState.selectedIntensity != null
                ) {
                    Text("Save Workout")
                }
            }
        }
    }
}

@Composable
private fun WorkoutTypeSelection(
    selectedType: WorkoutType?,
    onTypeSelected: (WorkoutType) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Select Workout Type",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            WorkoutType.values().forEach { type ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedType == type,
                            onClick = { onTypeSelected(type) }
                        )
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedType == type,
                        onClick = { onTypeSelected(type) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = type.displayName)
                }
            }
        }
    }
}

@Composable
private fun SubtypeSelection(
    selectedType: WorkoutType,
    selectedSubtype: String,
    onSubtypeSelected: (String) -> Unit,
    getSubtypes: (WorkoutType) -> List<String>
) {
    val subtypes = getSubtypes(selectedType)

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Select Activity",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            subtypes.forEach { subtype ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedSubtype == subtype,
                            onClick = { onSubtypeSelected(subtype) }
                        )
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedSubtype == subtype,
                        onClick = { onSubtypeSelected(subtype) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = subtype)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WorkoutDetailsForm(
    uiState: AddWorkoutUiState,
    onDurationChange: (String) -> Unit,
    onIntensityChange: (IntensityLevel) -> Unit,
    onCaloriesChange: (String) -> Unit,
    onNotesChange: (String) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Workout Details",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = uiState.duration,
                onValueChange = onDurationChange,
                label = { Text("Duration (minutes)") },
                modifier = Modifier.fillMaxWidth()
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Intensity Level",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )

                IntensityLevel.values().forEach { intensity ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = uiState.selectedIntensity == intensity,
                                onClick = { onIntensityChange(intensity) }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = uiState.selectedIntensity == intensity,
                            onClick = { onIntensityChange(intensity) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = intensity.displayName)
                    }
                }
            }

            OutlinedTextField(
                value = uiState.calories,
                onValueChange = onCaloriesChange,
                label = { Text("Calories Burned") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.notes,
                onValueChange = onNotesChange,
                label = { Text("Notes (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
        }
    }
}