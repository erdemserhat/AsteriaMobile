package com.asteriatech.mobile.data

import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class EchoWebSocketListener @Inject constructor() : WebSocketListener() {

    private val _matrix = MutableStateFlow("")
    val matrix: StateFlow<String> = _matrix


    // onOpen, WebSocket bağlantısı açıldığında çağrılır.
    override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
        // Bağlantı açıldığında yapılacak işlemler
    }

    // onMessage, sunucudan bir metin mesajı alındığında çağrılır.
    override fun onMessage(webSocket: WebSocket, text: String) {
        _matrix.value = text
        //Log.d("das",matrix.value)
    }

    // onMessage, sunucudan bir ikili mesaj alındığında çağrılır.
    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Log.d("WebSocket", "Receiving bytes: ${bytes.hex()}")
    }

    // onClosing, sunucu bağlantıyı kapatmak istediğinde çağrılır.
    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Log.d("WebSocket", "Closing: $code / $reason")
    }

    // onFailure, bağlantı hatası meydana geldiğinde çağrılır.
    override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
        Log.e("WebSocket", "Error: ${t.message}")
    }


}
