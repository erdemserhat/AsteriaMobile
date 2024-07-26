package com.asteriatech.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.asteriatech.mobile.presentation.composables.ThermalDataScreen
//import com.asteriatech.mobile.data.remote.websocket.EchoWebSocketListener
import com.asteriatech.mobile.ui.theme.AsteriaMobileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        //sendGetRequest("http://192.168.142.172:8000/rotate-right")
        super.onCreate(savedInstanceState)
        setContent {

            AsteriaMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ThermalDataScreen()
                }
            }
        }
    }

}

