package com.asteriatech.mobile.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WebSocketChannel(val value: String = "")

@Qualifier
annotation class Constant()

