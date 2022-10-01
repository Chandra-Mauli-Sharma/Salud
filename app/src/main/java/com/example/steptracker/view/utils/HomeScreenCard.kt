package com.example.steptracker.view.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.steptracker.navigation.StepTrackerScreen

@Composable
fun HomeScreenCard(
    cardColor: Color,
    cardText:String,
    cardIcon: Painter,
    navController: NavController,
    route:String
) {
    Card(
        Modifier
            .size(width = 180.dp, height = 250.dp)
            .padding(10.dp).clickable { navController.navigate(route) },
        colors = CardDefaults.cardColors(cardColor),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(60.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = cardText, color = Color.White)
            Icon(painter = cardIcon, "Icon", tint = Color.White)
        }
    }
}