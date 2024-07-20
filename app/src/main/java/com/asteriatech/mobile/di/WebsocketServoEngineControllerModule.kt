package com.asteriatech.mobile.di

import com.asteriatech.mobile.data.remote.websocket.WebsocketServoEngineControllerClient
import com.asteriatech.mobile.data.remote.websocket.listeners.WebSocketActionListenerImpl
import com.asteriatech.mobile.data.repository.WebSocketServoEngineControllerChannelRepository
import com.asteriatech.mobile.domain.WebSocketServoEngineControllerChannelService
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
        val customURL = CustomURL.Builder()
            .communicationProtocolType(CommunicationProtocolType.WS)
            .serverIp(ServerInformation.SERVER_IP)
            .serverPort(ServerInformation.SERVER_PORT)
            .endPoint(ServerInformation.EndPoints.SERVO_ENGINE_CONTROLLER_ENDPOINT)
            .build()
        return customURL
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
        webSocketListener: WebSocketActionListenerImpl
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
        return WebSocketServoEngineControllerChannelRepository(
            webSocketServoEngineControllerChannelService
        )
    }


}

