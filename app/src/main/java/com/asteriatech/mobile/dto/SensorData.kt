package com.asteriatech.mobile.dto

import kotlinx.serialization.Serializable


// The @Serializable annotation indicates that the SensorData class is serializable.
// This allows it to be converted to formats like JSON and back.
@Serializable
data class SensorData(
    val temperature: Double,  // Temperature value, e.g., 25.5 degrees
    val humidity: Double,     // Humidity percentage, e.g., 60%
    val pressure: Double,     // Pressure value, e.g., 1013.25 hPa
    val gps: Location,        // GPS location information, of type Location data class
    val batteryLevel: Int     // Battery level as a percentage, e.g., 85
)
