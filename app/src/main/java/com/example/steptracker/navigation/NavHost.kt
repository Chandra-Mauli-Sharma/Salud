package com.example.steptracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.steptracker.view.screen.StepsScreen
import com.example.steptracker.view.screen.Home_Screen

@Composable
fun StepTrackerNavHost(navController: NavHostController){
    NavHost(navController = navController, startDestination = StepTrackerScreen.HOME_SCREEN.name){
        composable(StepTrackerScreen.HOME_SCREEN.name){
            Home_Screen(navController = navController)
        }
        composable(StepTrackerScreen.STEPS_SCREEN.name){
            StepsScreen(navController=navController)
        }
    }
}