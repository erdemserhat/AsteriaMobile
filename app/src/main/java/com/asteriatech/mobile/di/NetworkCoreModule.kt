package com.asteriatech.mobile.di

//import com.asteriatech.mobile.data.remote.websocket.EchoWebSocketListener
import com.asteriatech.mobile.data.remote.websocket.listeners.WebSocketActionListenerImpl
import com.asteriatech.mobile.data.remote.websocket.listeners.WebSocketThermalDataListenerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkCoreModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(45, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(
                CustomURL.Builder()
                    .communicationProtocolType(CommunicationProtocolType.HTTP)
                    .build()
            )
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideWebSocketListenerImpl(): WebSocketActionListenerImpl {
        return WebSocketActionListenerImpl()
    }

    @Provides
    fun provideWebSocketThermalDataListenerImpl(): WebSocketThermalDataListenerImpl {
        return WebSocketThermalDataListenerImpl()
    }








}