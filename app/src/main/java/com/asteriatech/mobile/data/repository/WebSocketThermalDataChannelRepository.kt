package com.asteriatech.mobile.data.repository

import androidx.lifecycle.LiveData
import com.asteriatech.mobile.data.model.WebSocketMessage
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import com.asteriatech.mobile.domain.WebSocketThermalDataChannelService
import javax.inject.Inject

class WebSocketThermalDataChannelRepository @Inject constructor(
    @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL)private val webSocketThermalDataChannelService: WebSocketThermalDataChannelService
) {
    fun observeWebSocketMessages(): LiveData<WebSocketMessage> {
        return webSocketThermalDataChannelService.webSocketMessages
    }

    fun sendMessage(message: WebSocketMessage) {
        webSocketThermalDataChannelService.sendMessage(message)
    }
}
