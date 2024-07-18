package com.asteriatech.mobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.asteriatech.mobile.data.model.WebSocketMessage
import com.asteriatech.mobile.data.repository.WebSocketServoEngineControllerChannelRepository
import com.asteriatech.mobile.data.repository.WebSocketThermalDataChannelRepository
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebSocketServoEngineControllerViewModel @Inject constructor(
    @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL) private val repository: WebSocketServoEngineControllerChannelRepository

) : ViewModel() {
    val webSocketMessages: LiveData<WebSocketMessage> = repository.observeWebSocketMessages()


    fun sendMessage(message: WebSocketMessage) {
        repository.sendMessage(message)
    }
}