package com.asteriatech.mobile.di

import com.asteriatech.mobile.data.remote.websocket.WebSocketThermalDataChannelClient
import com.asteriatech.mobile.data.remote.websocket.listeners.WebSocketThermalDataListenerImpl
import com.asteriatech.mobile.data.repository.WebSocketThermalDataChannelRepository
import com.asteriatech.mobile.domain.WebSocketThermalDataChannelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request

@Module
@InstallIn(SingletonComponent::class)
object WebSocketThermalDataChannelModule {
    @Provides
    @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL)
    fun provideThermalDataUrl(): String {
        val customURL = CustomURL.Builder()
            .communicationProtocolType(CommunicationProtocolType.WS)
            .serverIp(ServerInformation.SERVER_IP)
            .serverPort(ServerInformation.SERVER_PORT)
            .endPoint(ServerInformation.EndPoints.THERMAL_DATA_ENDPOINT)
            .build()

        return customURL
    }

    @Provides
    @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL)
    fun provideWebSocketThermalDataRequest(@WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL) url: String): Request {
        return Request.Builder().url(url).build()
    }

    @Provides
    @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL)
    fun provideWebSocketThermalDataClient(
        @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL) request: Request,
        client: OkHttpClient

    ): WebSocketThermalDataChannelClient {
        return WebSocketThermalDataChannelClient(request, client)
    }


    @Provides
    @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL)
    fun provideWebSocketThermalDataService(
        @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL) webSocketThermalDataChannelClient: WebSocketThermalDataChannelClient,
        webSocketThermalDataListener: WebSocketThermalDataListenerImpl
    ): WebSocketThermalDataChannelService {
        return WebSocketThermalDataChannelService(
            webSocketThermalDataChannelClient,
            webSocketThermalDataListener
        )
    }

    @Provides
    @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL)
    fun provideWebSocketThermalDataRepository(
        @WebSocketChannel(WebSocketChannels.THERMAL_DATA_CHANNEL) webSocketThermalDataChannelService: WebSocketThermalDataChannelService
    ): WebSocketThermalDataChannelRepository {
        return WebSocketThermalDataChannelRepository(webSocketThermalDataChannelService)
    }


}