package com.asteriatech.mobile.data.remote.websocket.model

data class WebSocketThermalDataMessage(
    val thermalImageArray: List<List<Double>>,
    val isTargetDetected:Boolean
)