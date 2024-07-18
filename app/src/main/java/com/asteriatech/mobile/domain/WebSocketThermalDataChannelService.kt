package com.asteriatech.mobile.domain

import androidx.lifecycle.LiveData
import com.asteriatech.mobile.data.model.WebSocketMessage
import com.asteriatech.mobile.data.remote.websocket.WebSocketThermalDataChannelClient
import com.asteriatech.mobile.data.remote.websocket.common.WebSocketListenerImpl
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import javax.inject.Inject

class WebSocketThermalDataChannelService @Inject constructor(
    @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL) private val webSocketThermalDataChannelClient: WebSocketThermalDataChannelClient,
    private val webSocketListener: WebSocketListenerImpl
) {
    val webSocketMessages: LiveData<WebSocketMessage> = webSocketListener.webSocketMessages

    init {
        webSocketThermalDataChannelClient.setListener(webSocketListener)
        webSocketThermalDataChannelClient.connect()
    }

    fun sendMessage(message: WebSocketMessage) {
        webSocketThermalDataChannelClient.sendMessage(message)
    }
}
