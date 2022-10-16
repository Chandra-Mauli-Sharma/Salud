package com.example.salud.view.screen

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavController
import com.example.salud.R
import com.example.salud.model.Vitals
import com.example.salud.viewmodel.VitalsViewModel
import kotlinx.datetime.Instant
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepsScreen(
    navController: NavController,
    modifier: Modifier,
    viewModel: VitalsViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current

    val sensorManager by remember { mutableStateOf(ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager) }

    val stepCounterSensor: Sensor? by remember {
        mutableStateOf(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER))
    }

    var sensorStatus by remember {
        mutableStateOf(0.0f)
    }

    val currentTime: Date = Calendar.getInstance().time
    viewModel.getVitals()
    viewModel.getVitals(currentTime.date.toString())


    val animatedProgress = animateFloatAsState(
        targetValue = (viewModel.vital?.steps?.div(10000.0f)!!),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    val stepCounterSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_STEP_COUNTER) {
                sensorStatus = event.values[0]

                if (viewModel.vitals.isEmpty()) {
                    viewModel.addVital(
                        Vitals(
                            date = currentTime.date.toString(),
                            steps = (sensorStatus).toInt(),
                            calorie = ((((sensorStatus).toInt()) / 1000.0f) * 0.4f),
                            distance = ((sensorStatus).toInt() * 0.762f)
                        )
                    )
                } else {
                    if ((viewModel.vitals.first().date).toInt() < currentTime.date) {
                        val newSteps = (sensorStatus).toInt() - viewModel.vitals[1].steps
                        viewModel.updateVital(
                            Vitals(
                                date = currentTime.date.toString(),
                                steps = newSteps,
                                calorie = (((newSteps) / 1000.0f) * 0.4f),
                                distance = (newSteps * 0.762f)
                            )
                        )
                    } else viewModel.updateVital(
                        Vitals(
                            date = (viewModel.vitals.first().date).toInt().toString(),
                            steps = viewModel.vital?.steps!!,
                            calorie = (((viewModel.vital?.steps)?.div(1000.0f))?.times(0.4f))!!,
                            distance = (viewModel.vital?.steps?.times(0.762f))!!
                        )
                    )
                }

            }
        }
    }

    LaunchedEffect(Unit) {
        sensorManager.registerListener(
            stepCounterSensorEventListener,
            stepCounterSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }



    DisposableEffect(Unit) {
        onDispose() {
            sensorManager.unregisterListener(stepCounterSensorEventListener)
        }

    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(250.dp),
                progress = animatedProgress,
                strokeWidth = 20.dp
            )
            Text(
                text = viewModel.vital?.steps.toString(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                fontSize = 40.sp, modifier = Modifier.padding(5.dp)
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Card(
                modifier = Modifier
                    .width(120.dp)
                    .height(70.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_local_fire_department_24),
                        contentDescription = "Calorie Icon",
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = viewModel.vital?.calorie?.toInt().toString(),
                        color = Color.White
                    )
                }
            }
        }
    }

}
