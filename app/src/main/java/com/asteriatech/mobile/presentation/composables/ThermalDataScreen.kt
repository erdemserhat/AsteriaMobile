package com.asteriatech.mobile.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asteriatech.mobile.presentation.viewmodels.WebSocketServoEngineControllerViewModel
import com.asteriatech.mobile.presentation.viewmodels.WebSocketThermalDataViewModel

@Composable
fun ThermalDataScreen(
    thermalVM: WebSocketThermalDataViewModel = hiltViewModel(),
    servoVM: WebSocketServoEngineControllerViewModel = hiltViewModel()
) {
    val thermalMessages by thermalVM.webSocketThermalMessages.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {


        if (thermalMessages == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Loading..."
                )

            }

        } else{


            Column {

                Box(modifier = Modifier.fillMaxSize()) {


                    Row(modifier = Modifier.align(Alignment.Center)) {
                        Box {

                            ThermalImageContent(
                                matrix = thermalMessages!!.thermalImageArray,
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .fillMaxHeight()
                            )


                            Text(
                                text = "Thermal Video", modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(10.dp), color = Color.White
                            )

                        }

                        Box {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight()
                                    .background(Color.Green)
                            ) {
                                VideoPlayerScreen("http://192.168.142.97:8080/video")

                            }

                            Text(
                                text = "Thermal Video", modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(10.dp), color = Color.White
                            )


                        }


                    }



                    Button(
                        onClick = {
                            servoVM.rotateLeft()
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(40.dp)


                    ) {
                        Text(text = "Rotate Left")

                    }

                    Button(
                        onClick = {
                           servoVM.rotateRight()
                        },
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(40.dp)

                    ) {
                        Text(text = "Rotate Right")

                    }

                }


            }

            if(thermalMessages!!.isTargetDetected){

                Text(text = "DETECTED !", fontSize = 25.sp, color = Color.Red, modifier = Modifier.align(
                    Alignment.Center), fontWeight = FontWeight.Bold
                )
            }


        }
    }

    }
