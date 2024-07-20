package com.asteriatech.mobile.data.remote.websocket.model

import kotlinx.serialization.Serializable


@Serializable
data class WebSocketActionMessage(
    val actionType: String,
    val actionCommand: String,
    val actionMagnitude:String
)
