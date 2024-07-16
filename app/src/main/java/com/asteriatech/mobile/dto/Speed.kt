package com.asteriatech.mobile.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Speed(private val value: Int) {
    init {
        require(value in 0..255) { "Speed must be between 0 and 255" }
    }

    @Transient
    val speed: Int = value
}
