package com.example.steptracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.steptracker.navigation.StepTrackerNavHost
import com.example.steptracker.ui.theme.StepTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StepTrackerApp()
                }
            }
        }
    }
}

@Composable
fun StepTrackerApp(){
    val navController = rememberNavController()
    StepTrackerNavHost(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StepTrackerTheme {
        StepTrackerApp()
    }
}