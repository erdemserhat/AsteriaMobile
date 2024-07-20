package com.asteriatech.mobile.dto

import kotlinx.serialization.Serializable

@Serializable
sealed class DroneCommand {
    @Serializable
    data class TakeOff(val speed:Speed) : DroneCommand() //Kalkış

    @Serializable
    data class Landing(val speed:Speed) : DroneCommand() //İniş

    @Serializable
    data class MoveTo(val location: Location, val speed: Speed) : DroneCommand() //Belirli bir konuma gitme

    @Serializable
    data class ReturnToHome(val speed:Speed) : DroneCommand() //Eve Dönüş

    @Serializable
    data class SetSpeed(val speed: Speed) : DroneCommand() //Hızı Ayarla
}