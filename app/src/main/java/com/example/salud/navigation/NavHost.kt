package com.example.salud.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.salud.view.screen.*
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepTrackerNavHost(navController: NavHostController,modifier: Modifier){

    NavHost( navController = navController, startDestination = SaludScreen.HOME_SCREEN.name){
        composable(SaludScreen.ONBOARDING_SCREEN.name){
            OnboardingScreen(navController = navController)
        }
        composable(SaludScreen.ACTIVITY_SCREEN.name){
            ActivityScreen()
        }
        composable(SaludScreen.LOGIN_SCREEN.name){
            LoginScreen(navController = navController)
        }
        composable(SaludScreen.HOME_SCREEN.name){
            Home_Screen(navController = navController, modifier = modifier)
        }
        composable(SaludScreen.STEPS_SCREEN.name){
            StepsScreen(navController=navController, modifier)
        }
        composable(SaludScreen.ONBOARDING_SCREEN.name){
            OnboardingScreen(navController = navController)
        }
        composable(SaludScreen.RUNNING_SCREEN.name){
            RunningScreen(navController = navController)
        }
        composable(SaludScreen.LEADERBOARD_SCREEN.name){
            LeaderboardScreen(navController = navController,modifier)
        }
    }
}