package com.example.steptracker.data

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.steptracker.R
import com.example.steptracker.model.HomeScreenCard
import com.example.steptracker.navigation.StepTrackerScreen

class HomeScreenCardList {
    var list = listOf(
        HomeScreenCard(
            Color(0xfffe9c47),
            "Activity",
            R.drawable.ic_outline_work_outline_24,
            StepTrackerScreen.STEPS_SCREEN.name
        ),
        HomeScreenCard(
            Color(0xff7673f1),
            "Steps",
            R.drawable.ic_baseline_fitness_center_24,
            StepTrackerScreen.STEPS_SCREEN.name
        ),
        HomeScreenCard(
            Color(0xff754ba6),
            "Sleep",
            R.drawable.ic_baseline_nights_stay_24,
            StepTrackerScreen.STEPS_SCREEN.name
        ),
        HomeScreenCard(
            Color(0xfffc67b2),
            "Pulse",
            R.drawable.ic_outline_tag_faces_24,
            StepTrackerScreen.STEPS_SCREEN.name
        )
    )
}