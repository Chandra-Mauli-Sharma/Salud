package com.example.steptracker.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class HomeScreenCard(
    val cardColor: Color,
    val cardText: String,
    val cardIcon: Int,
    val route:String
)