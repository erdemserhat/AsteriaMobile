package com.asteriatech.mobile.dto

import kotlinx.serialization.Serializable



// The @Serializable annotation indicates that the Location class is serializable.
// This allows it to be converted to formats like JSON and back.
@Serializable
data class Location(
    val latitude: Double,  // Latitude value, e.g., 37.7749
    val longitude: Double, // Longitude value, e.g., -122.4194
    val altitude: Double   // Altitude value, e.g., 100.0
)