package com.asteriatech.mobile.data.remote.websocket.listeners

import com.asteriatech.mobile.data.remote.websocket.model.WebSocketActionMessage

interface WebSocketActionListener {
    fun onOpen()
    fun onMessageReceived(message: WebSocketActionMessage)
    fun onClose()
    fun onError(error: String)
}

