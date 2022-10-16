package com.example.salud.data

import androidx.compose.ui.graphics.Color
import com.example.salud.R
import com.example.salud.model.HomeScreenCard
import com.example.salud.navigation.SaludScreen

class HomeScreenCardList {
    var list = listOf(
        HomeScreenCard(
            Color(0xfffe9c47),
            "Activity",
            R.drawable.ic_outline_work_outline_24,
            SaludScreen.ACTIVITY_SCREEN.name
        ),
        HomeScreenCard(
            Color(0xff7673f1),
            "Steps",
            R.drawable.ic_baseline_fitness_center_24,
            SaludScreen.STEPS_SCREEN.name
        ),
        HomeScreenCard(
            Color(0xff754ba6),
            "Sleep",
            R.drawable.ic_baseline_nights_stay_24,
            SaludScreen.STEPS_SCREEN.name
        ),
        HomeScreenCard(
            Color(0xfffc67b2),
            "Running",
            R.drawable.ic_outline_tag_faces_24,
            SaludScreen.RUNNING_SCREEN.name
        )
    )
}