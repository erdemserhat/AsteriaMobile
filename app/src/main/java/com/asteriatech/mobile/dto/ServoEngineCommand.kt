package com.asteriatech.mobile.dto

import com.asteriatech.mobile.data.remote.websocket.model.WebSocketActionMessage
import com.asteriatech.mobile.di.Constant


@Constant
object ServoEngineCommand {
    val ROTATE_LEFT = WebSocketActionMessage(
        actionType="SERVO_ACTION",
        actionCommand="ROTATE_LEFT",
        actionMagnitude="-"
    )

    val ROTATE_RIGHT = WebSocketActionMessage(
        actionType="SERVO_ACTION",
        actionCommand="ROTATE_RIGHT",
        actionMagnitude="-"
    )
}