package com.example.steptracker.view.screen

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.steptracker.R
import com.example.steptracker.StepTrackerApp
import com.example.steptracker.ui.theme.StepTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepsScreen(navController: NavController) {
    val ctx = LocalContext.current

    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    val stepCounterSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    val sensorStatus = remember {
        mutableStateOf(0.0f)
    }
    val animatedProgress = animateFloatAsState(
        targetValue = 1.0f,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    val stepCounterSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_STEP_COUNTER) {
                sensorStatus.value = event.values[0]
            }
        }
    }

    sensorManager.registerListener(
        stepCounterSensorEventListener,
        stepCounterSensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        "Steps",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.displayLarge,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, "Menu")
                    }
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.shinchan),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(50.dp)
                    )
                })
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(200.dp),
                    progress = animatedProgress,
                    strokeWidth = 20.dp
                )
                Text(
                    text = ((sensorStatus.value).toInt()).toString(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    fontSize = 40.sp, modifier = Modifier.padding(5.dp)
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Card(
                    modifier = Modifier
                        .width(120.dp)
                        .height(70.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_local_fire_department_24),
                            contentDescription = "Calorie Icon"
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = ((((sensorStatus.value).toInt())/1000.0f)*0.4).toString())
                    }
                }
            }
        }
    }



}
