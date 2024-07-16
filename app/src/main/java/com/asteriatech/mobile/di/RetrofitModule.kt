package com.asteriatech.mobile.di

//import com.asteriatech.mobile.data.EchoWebSocketListener
import com.asteriatech.mobile.data.EchoWebSocketListener
import com.asteriatech.mobile.data.api.ServoEngineApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

val baseURL = "http://192.168.76.171:8000"
val websocketURL = "ws://192.168.40.171:8000/ws"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

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
            .baseUrl(baseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun provideWebSocketRequest(): Request {
        return Request.Builder()
            .url(websocketURL)
            .build()
    }

    @Provides
    @Singleton
    fun provideWebSocket(client: OkHttpClient, request: Request, listener: WebSocketListener): WebSocket {
        return client.newWebSocket(request, listener)
    }

    @Provides
    @Singleton
    fun provideWebSocketListener(): WebSocketListener {
        return EchoWebSocketListener()
    }

    @Provides
    @Singleton
    fun provideExampleDependency(): ExampleDependency {
        return ExampleDependency()
    }

    @Provides
    @Singleton
    fun provideServoEngineApiService(retrofit: Retrofit):ServoEngineApi {
        return retrofit.create(ServoEngineApi::class.java)
    }



}