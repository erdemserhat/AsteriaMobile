package com.asteriatech.mobile.di

import com.asteriatech.mobile.data.model.WebSocketMessage
import com.asteriatech.mobile.data.remote.websocket.WebSocketThermalDataChannelClient
import com.asteriatech.mobile.data.remote.websocket.WebsocketServoEngineControllerClient
import com.asteriatech.mobile.data.remote.websocket.common.WebSocketListener
import com.asteriatech.mobile.data.remote.websocket.common.WebSocketListenerImpl
import com.asteriatech.mobile.data.repository.WebSocketServoEngineControllerChannelRepository
import com.asteriatech.mobile.data.repository.WebSocketThermalDataChannelRepository
import com.asteriatech.mobile.domain.WebSocketServoEngineControllerChannelService
import com.asteriatech.mobile.domain.WebSocketThermalDataChannelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request

@Module
@InstallIn(SingletonComponent::class)
object WebsocketServoEngineControllerModule {
    @Provides
    @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL)
    fun provideServoEngineControllerUrl(): String {
        val url =CommunicationProtocolType.WS + ServerInformation.SERVER_IP + ServerInformation.SERVER_PORT +ServerInformation.SERVO_ENGINE_CONTROLLER_ENDPOINT
        return url
    }

    @Provides
    @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL)
    fun provideServoEngineControllerRequest(@WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL) url: String): Request {
        return Request.Builder().url(url).build()
    }

    @Provides
    @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL)
    fun provideWebSocketServoEngineControllerClient(
        @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL) request: Request,
        client: OkHttpClient
    ): WebsocketServoEngineControllerClient {
        return WebsocketServoEngineControllerClient(request, client)
    }

    @Provides
    @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL)
    fun provideWebSocketServoEngineControllerChannelService(
        @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL) webSocketServoEngineControllerClient: WebsocketServoEngineControllerClient,
        webSocketListener: WebSocketListenerImpl
    ): WebSocketServoEngineControllerChannelService {
        return WebSocketServoEngineControllerChannelService(
            webSocketServoEngineControllerClient,
            webSocketListener

        )
    }

    @Provides
    @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL)
    fun provideWebSocketServoEngineControllerRepository(
        @WebSocketChannel(WebSocketChannels.SERVO_ENGINE_CONTROLLER_CHANNEL) webSocketServoEngineControllerChannelService: WebSocketServoEngineControllerChannelService
    ): WebSocketServoEngineControllerChannelRepository {
        return WebSocketServoEngineControllerChannelRepository(webSocketServoEngineControllerChannelService)
    }


}

