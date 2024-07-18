package com.asteriatech.mobile.di

object WebSocketChannels{
    const val THERMAL_DATA_CHANNEL = "ThermalData"
    const val SERVO_ENGINE_CONTROLLER_CHANNEL="ServoEngineController"
}

object CommunicationProtocolType {
    const val HTTP = "http://"
    const val HTTPS = "https://"
    const val WS = "ws://"
    const val WSS = "wss://"
}

object ServerInformation{

    const val SERVER_IP = "192.168.18.207"
    const val SERVER_PORT = ":8080"


    const val THERMAL_DATA_ENDPOINT = "/thermal-camera"
    const val SERVO_ENGINE_CONTROLLER_ENDPOINT = "/central-vision-action"
}