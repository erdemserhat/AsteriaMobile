package com.asteriatech.mobile.data.repository

import androidx.lifecycle.LiveData
import com.asteriatech.mobile.data.remote.websocket.model.WebSocketActionMessage
import com.asteriatech.mobile.data.remote.websocket.model.WebSocketThermalDataMessage
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import com.asteriatech.mobile.domain.WebSocketThermalDataChannelService
import javax.inject.Inject

class WebSocketThermalDataChannelRepository @Inject constructor(
    @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL)private val webSocketThermalDataChannelService: WebSocketThermalDataChannelService
) {
    fun observeWebSocketMessages(): LiveData<WebSocketThermalDataMessage> {
        return webSocketThermalDataChannelService.webSocketThermalDataMessage
    }
}
