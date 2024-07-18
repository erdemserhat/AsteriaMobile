package com.asteriatech.mobile.domain

import androidx.lifecycle.LiveData
import com.asteriatech.mobile.data.model.WebSocketMessage
import com.asteriatech.mobile.data.remote.websocket.WebsocketServoEngineControllerClient
import com.asteriatech.mobile.data.remote.websocket.common.WebSocketListenerImpl
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import javax.inject.Inject

class WebSocketServoEngineControllerChannelService @Inject constructor(
    @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL) private val webSocketServoEngineControllerClient: WebsocketServoEngineControllerClient,
    private val webSocketListener: WebSocketListenerImpl
) {
    val webSocketMessages:LiveData<WebSocketMessage> = webSocketListener.webSocketMessages

    init {
        webSocketServoEngineControllerClient.setListener(webSocketListener)
        webSocketServoEngineControllerClient.connect()
    }

    fun sendMessage(message: WebSocketMessage){
        webSocketServoEngineControllerClient.sendMessage(message)
    }
}