package com.asteriatech.mobile.di


object WebSocketChannels {
    const val THERMAL_DATA_CHANNEL = "ThermalData"
    const val SERVO_ENGINE_CONTROLLER_CHANNEL = "ServoEngineController"
}

/**
 * Enum class representing different types of communication protocols.
 *
 * This enum provides the protocol strings for HTTP, HTTPS, WS, and WSS.
 *
 * @property protocol The protocol string associated with the enum constant.
 *
 * Example usage:
 * ```
 * val protocol = CommunicationProtocolType.HTTPS
 * println(protocol.protocol) // Outputs: https://
 * ```
 */
enum class CommunicationProtocolType(val protocol: String) {
    HTTP("http://"),
    HTTPS("https://"),
    WS("ws://"),
    WSS("wss://")
}


object ServerInformation {
    const val SERVER_IP = "192.168.201.133"
    const val SERVER_PORT = "8080"

    object EndPoints {
        const val THERMAL_DATA_ENDPOINT = "/thermal-camera"
        const val SERVO_ENGINE_CONTROLLER_ENDPOINT = "/central-vision-action"

    }
}


/**
 * CustomURL class is used to build URLs using the Builder design pattern.
 * This class allows dynamic specification of URL components and constructs a URL address.
 *
 * @property address Represents the constructed URL address.
 * @constructor Instances of this class can only be created using the Builder class.
 */
class CustomURL private constructor(
    val address: String = ""
) {
    /**
     * Builder class is used to construct instances of CustomURL step by step.
     *
     * @property communicationProtocolType Specifies the communication protocol type (default: CommunicationProtocolType.WS).
     * @property serverIp Specifies the server IP address (default: ServerInformation.SERVER_IP).
     * @property serverPort Specifies the server port number (default: ServerInformation.SERVER_PORT).
     * @property endPoint Specifies the endpoint of the URL (default: empty string).
     */
    data class Builder(
        var communicationProtocolType: CommunicationProtocolType = CommunicationProtocolType.WS,
        var serverIp: String = ServerInformation.SERVER_IP,
        var serverPort: String = ServerInformation.SERVER_PORT,
        var endPoint: String = ""
    ) {
        /**
         * Sets the communication protocol type for the URL.
         *
         * @param communicationProtocolType The communication protocol type to be used.
         * @return The Builder instance with the updated communication protocol type.
         */
        fun communicationProtocolType(communicationProtocolType: CommunicationProtocolType) =
            apply { this.communicationProtocolType = communicationProtocolType }

        /**
         * Sets the server IP address for the URL.
         *
         * @param serverIp The server IP address to be used.
         * @return The Builder instance with the updated server IP address.
         */
        fun serverIp(serverIp: String) =
            apply { this.serverIp = serverIp }

        /**
         * Sets the server port number for the URL.
         *
         * @param serverPort The server port number to be used.
         * @return The Builder instance with the updated server port number.
         */
        fun serverPort(serverPort: String) =
            apply { this.serverPort = serverPort }

        /**
         * Sets the endpoint for the URL.
         *
         * @param endPoint The endpoint to be used.
         * @return The Builder instance with the updated endpoint.
         */
        fun endPoint(endPoint: String) =
            apply { this.endPoint = endPoint }

        /**
         * Builds and returns the CustomURL instance with the specified components.
         *
         * @return A string representing the constructed URL address.
         */
        fun build() = CustomURL(
            address = "${communicationProtocolType.protocol}$serverIp:$serverPort$endPoint"
        ).address
    }
}


