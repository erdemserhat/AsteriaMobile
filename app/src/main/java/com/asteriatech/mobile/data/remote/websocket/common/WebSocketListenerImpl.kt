package com.asteriatech.mobile.data.remote.websocket.common

import androidx.lifecycle.MutableLiveData
import com.asteriatech.mobile.data.model.WebSocketMessage
import javax.inject.Inject

class WebSocketListenerImpl @Inject constructor() : WebSocketListener {
    val webSocketMessages: MutableLiveData<WebSocketMessage> = MutableLiveData()

    override fun onOpen() {
        // Bağlantı açıldığında yapılacak işlemler
    }

    override fun onMessageReceived(message: WebSocketMessage) {
        webSocketMessages.postValue(message)
    }

    override fun onClose() {
        // Bağlantı kapandığında yapılacak işlemler
    }

    override fun onError(error: String) {
        // Hata oluştuğunda yapılacak işlemler
    }
}
