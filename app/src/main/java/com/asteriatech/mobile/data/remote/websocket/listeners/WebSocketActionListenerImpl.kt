package com.asteriatech.mobile.data.remote.websocket.listeners

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.asteriatech.mobile.data.remote.websocket.model.WebSocketActionMessage
import javax.inject.Inject

class WebSocketActionListenerImpl @Inject constructor() : WebSocketActionListener {
    val webSocketMessages: MutableLiveData<WebSocketActionMessage> = MutableLiveData()

    override fun onOpen() {
        // Bağlantı açıldığında yapılacak işlemler

    }

    override fun onMessageReceived(message: WebSocketActionMessage) {
        webSocketMessages.postValue(message)
    }

    override fun onClose() {
        // Bağlantı kapandığında yapılacak işlemler
    }

    override fun onError(error: String) {
        // Hata oluştuğunda yapılacak işlemler
    }
}
