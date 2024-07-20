package com.asteriatech.mobile.domain

import androidx.lifecycle.LiveData
import com.asteriatech.mobile.data.remote.websocket.model.WebSocketActionMessage
import com.asteriatech.mobile.data.remote.websocket.WebsocketServoEngineControllerClient
import com.asteriatech.mobile.data.remote.websocket.listeners.WebSocketActionListenerImpl
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import javax.inject.Inject

class WebSocketServoEngineControllerChannelService @Inject constructor(
    @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL) private val webSocketServoEngineControllerClient: WebsocketServoEngineControllerClient,
    private val webSocketListener: WebSocketActionListenerImpl
) {
    val webSocketActionMessages:LiveData<WebSocketActionMessage> = webSocketListener.webSocketMessages

    init {
        webSocketServoEngineControllerClient.setListener(webSocketListener)
        webSocketServoEngineControllerClient.connect()
    }

    fun sendMessage(message: WebSocketActionMessage){
        webSocketServoEngineControllerClient.sendMessage(message)
    }
}