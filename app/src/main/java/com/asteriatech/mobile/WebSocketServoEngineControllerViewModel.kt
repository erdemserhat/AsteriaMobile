package com.asteriatech.mobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.asteriatech.mobile.data.remote.websocket.model.WebSocketActionMessage
import com.asteriatech.mobile.data.repository.WebSocketServoEngineControllerChannelRepository
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import com.asteriatech.mobile.dto.ServoEngineCommand
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebSocketServoEngineControllerViewModel @Inject constructor(
    @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL) private val repository: WebSocketServoEngineControllerChannelRepository

) : ViewModel() {
    fun rotateLeft() {
        repository.sendMessage(ServoEngineCommand.ROTATE_LEFT)
    }

    fun rotateRight() {
        repository.sendMessage(ServoEngineCommand.ROTATE_RIGHT)
    }

}