package com.asteriatech.mobile.data.repository

import androidx.lifecycle.LiveData
import com.asteriatech.mobile.data.remote.websocket.model.WebSocketActionMessage
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import com.asteriatech.mobile.domain.WebSocketServoEngineControllerChannelService
import javax.inject.Inject

class WebSocketServoEngineControllerChannelRepository @Inject constructor(
    @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL) private val webSocketServoEngineControllerService: WebSocketServoEngineControllerChannelService
) {
    fun observeWebSocketMessages(): LiveData<WebSocketActionMessage> {
        return webSocketServoEngineControllerService.webSocketActionMessages

    }

    fun sendMessage(webSocketActionMessage: WebSocketActionMessage){
        webSocketServoEngineControllerService.sendMessage(webSocketActionMessage)
    }


}
