package com.asteriatech.mobile.di

import com.asteriatech.mobile.data.remote.websocket.WebSocketThermalDataChannelClient
import com.asteriatech.mobile.data.remote.websocket.common.WebSocketListenerImpl
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
        val url =CommunicationProtocolType.WS + ServerInformation.SERVER_IP + ServerInformation.SERVER_PORT +ServerInformation.THERMAL_DATA_ENDPOINT
        return url
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
        webSocketListener: WebSocketListenerImpl
    ): WebSocketThermalDataChannelService {
        return WebSocketThermalDataChannelService(
            webSocketThermalDataChannelClient,
            webSocketListener
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