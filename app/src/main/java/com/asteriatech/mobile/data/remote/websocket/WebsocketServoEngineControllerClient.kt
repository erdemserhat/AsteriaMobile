package com.asteriatech.mobile.data.remote.websocket

import com.asteriatech.mobile.data.remote.websocket.model.WebSocketActionMessage
import com.asteriatech.mobile.data.remote.websocket.listeners.WebSocketActionListener
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import javax.inject.Inject

/**
 * WebsocketServoEngineControllerClient class is responsible for establishing a socket connection
 * between server and client.
 * @param request: this parameter is provided by hilt, this is a request which goes to server.
 * hilt needs websocket url to initialize a request data type.
 *
 */
class WebsocketServoEngineControllerClient @Inject constructor(
    @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL) private val request: Request,
    private val client: OkHttpClient //provided by hilt, via singleton
) {

    private lateinit var listener: WebSocketActionListener
    private lateinit var webSocket: WebSocket

    fun setListener(listener: WebSocketActionListener) {
        this.listener = listener
    }

    fun connect() {
        webSocket = client.newWebSocket(request, object : okhttp3.WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                listener.onOpen()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                // process the message directly
                listener.onMessageReceived(WebSocketActionMessage("action", text,"100"))
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                listener.onClose()
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                listener.onError(t.message ?: "Unknown error")
            }
        })
    }


    fun sendMessage(message: WebSocketActionMessage) {
        if (::webSocket.isInitialized) {
            val jsonMessage = Gson().toJson(message)
            webSocket.send(jsonMessage)
        } else {
            listener.onError("WebSocket is not initialized")


        }
    }


}


