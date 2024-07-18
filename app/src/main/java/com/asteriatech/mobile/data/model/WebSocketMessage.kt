package com.asteriatech.mobile.data.model

import kotlinx.serialization.Serializable


@Serializable
data class WebSocketMessage(
    val actionType: String,
    val actionCommand: String,
    val actionMagnitude:String
)
