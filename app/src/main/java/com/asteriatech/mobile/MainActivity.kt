package com.asteriatech.mobile

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
//import com.asteriatech.mobile.data.EchoWebSocketListener
import com.asteriatech.mobile.di.ExampleDependency
import com.asteriatech.mobile.ui.theme.AsteriaMobileTheme
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var webSocket: WebSocket

    @Inject
    lateinit var exampleDependency: ExampleDependency

   // @Inject
    //lateinit var echoWebSocketListener: EchoWebSocketListener



    private val viewModel: MainViewModel by viewModels()
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Log.d("asdsada",webSocket.......)
        // Sensör yöneticisini başlat
       // sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
       // accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)


        webSocket.request()
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



        Log.d("Asteria Test", "Test Message")
        try {
            //exampleDependency.a()
            webSocket.send("Serhat")
        } catch (e: Exception) {
            Log.d("errorTag", e.localizedMessage!!)
        }


        //  try {
        //    webSocket.send("dasdsa")
        //}catch (e:Exception){
        //     println(e.printStackTrace())
        // }

        // WebSocket bağlantısı başlatılır
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocket.close(1000, "Activity Destroyed")
    }

    @Composable
    fun Greeting(
        viewModel: MainViewModel = hiltViewModel()

    ) {
       // val matrix by viewModel.matrix.collectAsState()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                viewModel.rotateLeft()


            }) {
                Text(text = "Rotate Left")
            }

            Button(onClick = { viewModel.rotateRight() }) {
                Text(text = "Rotate Left")
            }
            //Text(text = matrix)

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AsteriaMobileTheme {


        }
    }
}