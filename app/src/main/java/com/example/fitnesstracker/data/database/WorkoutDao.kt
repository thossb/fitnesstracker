package com.example.fitnesstracker.data.database

import androidx.room.*
import com.example.fitnesstracker.data.model.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workouts ORDER BY date DESC")
    fun getAllWorkouts(): Flow<List<Workout>>

    @Query("SELECT * FROM workouts WHERE date >= :startDate AND date <= :endDate ORDER BY date DESC")
    fun getWorkoutsByDateRange(startDate: Long, endDate: Long): Flow<List<Workout>>

    @Insert
    suspend fun insertWorkout(workout: Workout)

    @Update
    suspend fun updateWorkout(workout: Workout)

    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Query("SELECT * FROM workouts WHERE date >= :todayStart AND date <= :todayEnd")
    suspend fun getTodayWorkouts(todayStart: Long, todayEnd: Long): List<Workout>
}