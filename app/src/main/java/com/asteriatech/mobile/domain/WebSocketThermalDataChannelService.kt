package com.asteriatech.mobile.domain

import androidx.lifecycle.LiveData
import com.asteriatech.mobile.data.remote.websocket.model.WebSocketActionMessage
import com.asteriatech.mobile.data.remote.websocket.WebSocketThermalDataChannelClient
import com.asteriatech.mobile.data.remote.websocket.listeners.WebSocketThermalDataListenerImpl
import com.asteriatech.mobile.data.remote.websocket.model.WebSocketThermalDataMessage
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import javax.inject.Inject

class WebSocketThermalDataChannelService @Inject constructor(
    @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL) private val webSocketThermalDataChannelClient: WebSocketThermalDataChannelClient,
    private val webSocketThermalDataListener: WebSocketThermalDataListenerImpl
) {
    val webSocketThermalDataMessage: LiveData<WebSocketThermalDataMessage> = webSocketThermalDataListener.webSocketThermalDataMessages

    init {
        webSocketThermalDataChannelClient.setListener(webSocketThermalDataListener)
        webSocketThermalDataChannelClient.connect()
    }


}
