package com.asteriatech.mobile.data.api

import retrofit2.http.GET
import javax.annotation.Nullable

interface ServoEngineApi {

    @GET("rotate-right")
    suspend fun rotateRight(): Void?

    @GET("rotate-left")
    suspend fun rotateLeft(): Void?
}