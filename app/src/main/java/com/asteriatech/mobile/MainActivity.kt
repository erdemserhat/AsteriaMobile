package com.asteriatech.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.asteriatech.mobile.data.model.WebSocketMessage
//import com.asteriatech.mobile.data.remote.websocket.EchoWebSocketListener
import com.asteriatech.mobile.ui.theme.AsteriaMobileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsteriaMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }


    @Composable
    fun Greeting(
        thermalVM: WebSocketThermalDataViewModel = hiltViewModel(),
        servoVM:WebSocketServoEngineControllerViewModel = hiltViewModel()
    ) {
        // LiveData'yı State olarak gözlemleyin
        val thermalMessages by thermalVM.webSocketMessages.observeAsState()
        val servoMessages by servoVM.webSocketMessages.observeAsState()

        Column(
            modifier = Modifier.fillMaxSize(),

        ) {

            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.3f),
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { servoVM.sendMessage(WebSocketMessage("Servo Action","rotate_right","100")) }) {
                    Text(text = "rotate_right")
                }

                Button(onClick = { servoVM.sendMessage(WebSocketMessage("Servo Action","rotate_left","100")) }) {
                    Text(text = "rotate_left")
                }


            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                // WebSocketMessage'ı metin olarak gösterin
                thermalMessages?.let {
                    Text(text = it.actionCommand)
                } ?: Text(text = "No message received")

                // WebSocketMessage'ı metin olarak gösterin
                servoMessages?.let {
                    Text(text = it.actionCommand)
                } ?: Text(text = "No message received")

            }






        }
    }

}