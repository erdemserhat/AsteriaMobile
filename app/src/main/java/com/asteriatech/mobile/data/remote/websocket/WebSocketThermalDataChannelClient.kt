package com.asteriatech.mobile.data.remote.websocket

import android.util.Log
import com.asteriatech.mobile.data.remote.websocket.model.WebSocketActionMessage
import com.asteriatech.mobile.data.remote.websocket.listeners.WebSocketThermalDataListener
import com.asteriatech.mobile.data.remote.websocket.model.WebSocketThermalDataMessage
import com.asteriatech.mobile.di.WebSocketChannel
import com.asteriatech.mobile.di.WebSocketChannels
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import org.json.JSONObject
import javax.inject.Inject

class WebSocketThermalDataChannelClient @Inject constructor(
    @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL) private val request: Request,
    private val client: OkHttpClient
) {
    /**
     * listener: listener
     */
    private lateinit var listener: WebSocketThermalDataListener
    private lateinit var webSocket: WebSocket

    fun setListener(listener: WebSocketThermalDataListener) {
        this.listener = listener
    }

    private fun parseMatrixFromJson(json: String): List<List<Double>> {
        val gson = Gson()
        return try {
            // JSON verisini düz bir liste olarak al
            val flatListType = object : TypeToken<List<Double>>() {}.type
            val flatList: List<Double> = gson.fromJson(json, flatListType)

            // Düz listeyi 24x32 matrisine dönüştür
            val rows = 24
            val cols = 32
            val matrix = mutableListOf<List<Double>>()

            for (i in 0 until rows) {
                val start = i * cols
                val end = start + cols
                matrix.add(flatList.subList(start, end))
            }

            matrix
        } catch (e: JsonSyntaxException) {
            // Hata durumunda bir boş liste döndürün veya uygun bir hata işleme yapın
            e.printStackTrace()
            emptyList()
        }
    }

    fun connect() {
        webSocket = client.newWebSocket(request, object : okhttp3.WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                listener.onOpen()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                //get purse json object from text
                // Gelen veriyi loglayarak kontrol edin
                Log.d("WebSocketMessage", text)

                val jsonObject = JSONObject(text)
                //get json keys
                val keys = jsonObject.keys()

                //get first member of the json
                val firstKey = if (keys.hasNext()) keys.next() else null
                val firstValue = firstKey?.let { jsonObject.get(it) }

                //get second member of the json
                val secondKey = if (keys.hasNext()) keys.next() else null
                val secondValue = secondKey?.let { jsonObject.get(it) }

                //process thermal data
                val thermalImageArray = firstValue?.toString()?.let { parseMatrixFromJson(it) }

                //process thermal data result
                val isDetected = secondValue?.toString().let {
                    it == "Detected"
                }

                Log.d("fsfdsfds",isDetected.toString())

                //combine both of them ( process data and resul)
                val websocketThermalDataMessage = WebSocketThermalDataMessage(
                    thermalImageArray = thermalImageArray!!,
                    isTargetDetected = isDetected

                )
                //send to listener
                Log.d("erdem1212","webSocketThermalDataMessages.toString()")
                listener.onMessageReceived(websocketThermalDataMessage)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {

                listener.onClose()
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                listener.onError(t.message ?: "Unknown error")
                Log.d("logcatTestTracing1",t.message.toString())
            }
        })
    }

}
