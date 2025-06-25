package com.example.fitnesstracker.data.model

enum class WorkoutType(val displayName: String, val color: Long) {
    CARDIO("Cardio", 0xFF2196F3),
    STRENGTH("Strength", 0xFFF44336),
    FLEXIBILITY("Flexibility", 0xFF9C27B0),
    SPORTS("Sports", 0xFF4CAF50)
}

enum class IntensityLevel(val displayName: String) {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High")
}