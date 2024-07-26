package com.asteriatech.mobile.data.remote.websocket.listeners

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.asteriatech.mobile.data.remote.websocket.model.WebSocketThermalDataMessage
import javax.inject.Inject

class WebSocketThermalDataListenerImpl @Inject constructor() : WebSocketThermalDataListener {
    val webSocketThermalDataMessages: MutableLiveData<WebSocketThermalDataMessage> = MutableLiveData()

    override fun onOpen() {
        Log.d("logcatTracing1","opened")

    }

    override fun onMessageReceived(thermalMessage: WebSocketThermalDataMessage) {
        webSocketThermalDataMessages.postValue(thermalMessage)



    }

    override fun onClose() {
        //Log.d("erdem3451312","closed")

    }

    override fun onError(error: String) {
        Log.d("logcatTracing1","closed")
    }
}