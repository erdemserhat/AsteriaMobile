package com.asteriatech.mobile.data.remote.websocket.common

import com.asteriatech.mobile.data.model.WebSocketMessage

interface WebSocketListener {
    fun onOpen()
    fun onMessageReceived(message: WebSocketMessage)
    fun onClose()
    fun onError(error: String)
}
