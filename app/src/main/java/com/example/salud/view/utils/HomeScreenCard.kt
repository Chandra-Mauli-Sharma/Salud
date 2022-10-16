package com.example.salud.view.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

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
            .size(200.dp)
            .padding(10.dp).clickable { navController.navigate(route) },
        colors = CardDefaults.cardColors(cardColor),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = cardText, color = Color.White, fontSize = 30.sp)
            Icon(painter = cardIcon, "Icon", tint = Color.White, modifier = Modifier.fillMaxWidth().size(50.dp))
        }
    }
}