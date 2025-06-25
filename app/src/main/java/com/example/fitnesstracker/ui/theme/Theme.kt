package com.example.fitnesstracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Warna Simple yang Pasti Work
private val FitnessPrimary = Color(0xFFFF6B35)
private val FitnessOnPrimary = Color(0xFFFFFFFF)
private val FitnessSecondary = Color(0xFF2196F3)

// Color Scheme Simple
private val LightColorScheme = lightColorScheme(
    primary = FitnessPrimary,
    onPrimary = FitnessOnPrimary,
    secondary = FitnessSecondary,
    tertiary = Color(0xFF9C27B0)
)

@Composable
fun FitnessTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Gunakan light theme saja, jangan dark theme dulu
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}