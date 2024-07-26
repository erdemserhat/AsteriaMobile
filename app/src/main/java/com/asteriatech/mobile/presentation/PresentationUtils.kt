package com.asteriatech.mobile.presentation

import androidx.compose.ui.graphics.Color


fun getColorFromTemperature(value: Double): Color {
    return when {
        value in 0.0..32.0 -> Color.Green
        value in 31.0..32.0 -> Color.Yellow
        value in 33.0..34.5 -> Color(0xFFFFA500) // Orange
        value in 34.5..40.0 -> Color.Red
        else -> Color.Gray // Default color for values outside the specified ranges
    }
}