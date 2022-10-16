package com.example.salud.view.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.salud.R

@Composable
fun GradientButton(
    text: String,
    textColor: Color,
    gradient: Brush,
    onClick: () -> Unit,
    isIconActive:Boolean
) {
    Button(
        modifier = Modifier.padding(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    elevation = ButtonDefaults.buttonElevation(5.dp))
    {
        Box(
            modifier = Modifier
                .background(gradient)
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .size(150.dp, 30.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Text(text = text, color = textColor)
                if(isIconActive) {
                    Spacer(modifier = Modifier.size(width = 7.dp, height = 0.dp))
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google Icon"
                    )
                }
            }
        }

    }
}