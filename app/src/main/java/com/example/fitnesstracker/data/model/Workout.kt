package com.example.fitnesstracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val type: WorkoutType,
    val subtype: String,
    val duration: Int, // minutes
    val intensity: IntensityLevel,
    val caloriesBurned: Int,
    val notes: String = "",
    val date: Long = System.currentTimeMillis()
)