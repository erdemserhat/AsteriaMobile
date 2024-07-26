package com.asteriatech.mobile.data.remote.websocket.listeners

import com.asteriatech.mobile.data.remote.websocket.model.WebSocketThermalDataMessage

interface WebSocketThermalDataListener {
    fun onOpen()
    fun onMessageReceived(thermalMessage: WebSocketThermalDataMessage)
    fun onClose()
    fun onError(error: String)
}