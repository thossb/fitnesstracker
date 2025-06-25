package com.example.fitnesstracker.data.repository

import com.example.fitnesstracker.data.database.WorkoutDao
import com.example.fitnesstracker.data.model.Workout
import kotlinx.coroutines.flow.Flow
import java.util.*

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    fun getAllWorkouts(): Flow<List<Workout>> = workoutDao.getAllWorkouts()

    fun getWorkoutsByDateRange(startDate: Long, endDate: Long): Flow<List<Workout>> =
        workoutDao.getWorkoutsByDateRange(startDate, endDate)

    suspend fun insertWorkout(workout: Workout) = workoutDao.insertWorkout(workout)

    suspend fun updateWorkout(workout: Workout) = workoutDao.updateWorkout(workout)

    suspend fun deleteWorkout(workout: Workout) = workoutDao.deleteWorkout(workout)

    suspend fun getTodayWorkouts(): List<Workout> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDay = calendar.timeInMillis

        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val endOfDay = calendar.timeInMillis - 1

        return workoutDao.getTodayWorkouts(startOfDay, endOfDay)
    }
}