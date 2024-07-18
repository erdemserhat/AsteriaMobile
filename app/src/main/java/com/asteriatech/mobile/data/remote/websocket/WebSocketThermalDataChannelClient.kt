package com.asteriatech.mobile.data.remote.websocket

import com.asteriatech.mobile.data.model.WebSocketMessage
import com.asteriatech.mobile.data.remote.websocket.common.WebSocketListener
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import javax.inject.Inject

class WebSocketThermalDataChannelClient @Inject constructor(
    @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL) private val request: Request,
    private val client:OkHttpClient
) {
    /**
     * listener: listener
     */
    private lateinit var listener: WebSocketListener
    private lateinit var webSocket: WebSocket

    fun setListener(listener: WebSocketListener) {
        this.listener = listener
    }

    fun connect() {
        webSocket = client.newWebSocket(request, object : okhttp3.WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                listener.onOpen()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                // process the message directly
                listener.onMessageReceived(WebSocketMessage("text", text,"100"))
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                listener.onClose()
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                listener.onError(t.message ?: "Unknown error")
            }
        })
    }

    fun sendMessage(message: WebSocketMessage) {
        if (::webSocket.isInitialized) {
            val jsonMessage = Gson().toJson(message)
            webSocket.send(jsonMessage)
        } else {
            listener.onError("WebSocket is not initialized")
        }

    }
}
