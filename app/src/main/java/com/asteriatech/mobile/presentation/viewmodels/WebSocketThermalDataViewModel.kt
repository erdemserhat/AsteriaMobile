package com.asteriatech.mobile.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.asteriatech.mobile.data.remote.websocket.model.WebSocketThermalDataMessage
import com.asteriatech.mobile.data.repository.WebSocketThermalDataChannelRepository
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebSocketThermalDataViewModel @Inject constructor(
    @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL) private val repository: WebSocketThermalDataChannelRepository
) : ViewModel() {
    val webSocketThermalMessages: LiveData<WebSocketThermalDataMessage> =
        repository.observeWebSocketMessages()
}