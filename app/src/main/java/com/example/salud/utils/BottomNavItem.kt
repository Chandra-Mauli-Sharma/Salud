package com.example.salud.utils

import com.example.salud.R
import com.example.salud.navigation.SaludScreen

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String) {
    object Home : BottomNavItem("Home", R.drawable.ic_baseline_home_24,SaludScreen.HOME_SCREEN.name)
    object LeaderBoard : BottomNavItem("LeaderBoard", R.drawable.ic_baseline_leaderboard_24,SaludScreen.LEADERBOARD_SCREEN.name)
}