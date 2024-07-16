package com.asteriatech.mobile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.asteriatech.mobile.data.EchoWebSocketListener
import com.asteriatech.mobile.data.api.ServoEngineApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //private val echoWebSocketListener: EchoWebSocketListener,
    private val servoEngineApi: ServoEngineApi
) : ViewModel() {

  //  val matrix: StateFlow<String> = echoWebSocketListener.matrix

    fun rotateLeft() {
       // Log.d("MainViewModel", "rotateLeft: ${echoWebSocketListener.matrix}")
        viewModelScope.launch {
            try {
                servoEngineApi.rotateLeft()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error in rotateLeft", e)
            }
        }
    }

    fun rotateRight() {
        viewModelScope.launch {
            try {
                servoEngineApi.rotateRight()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error in rotateRight", e)
            }
        }
    }
}
