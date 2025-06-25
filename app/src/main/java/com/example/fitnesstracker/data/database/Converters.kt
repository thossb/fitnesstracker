package com.example.fitnesstracker.data.database

import androidx.room.TypeConverter
import com.example.fitnesstracker.data.model.IntensityLevel
import com.example.fitnesstracker.data.model.WorkoutType

class Converters {
    @TypeConverter
    fun fromWorkoutType(type: WorkoutType): String = type.name

    @TypeConverter
    fun toWorkoutType(name: String): WorkoutType = WorkoutType.valueOf(name)

    @TypeConverter
    fun fromIntensityLevel(level: IntensityLevel): String = level.name

    @TypeConverter
    fun toIntensityLevel(name: String): IntensityLevel = IntensityLevel.valueOf(name)
}