package com.example.salud.view.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salud.R
import com.example.salud.model.Leaderboard
import com.example.salud.model.User

@Composable
fun LeaderboardCard(user: Leaderboard) {
    Card(
        elevation = CardDefaults.cardElevation(20.dp),
        modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 15.dp),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "user.rank.toString()",
                        modifier = Modifier
                            .background(color = Color.Black, shape = CircleShape)
                            .padding(20.dp),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(user.Name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.size(20.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .width(120.dp)
                            .height(70.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Black)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_local_fire_department_24),
                                contentDescription = "Calorie Icon",
                                tint = Color.Red
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = user.Calorie.toString(),
                                color = Color.White
                            )
                        }
                    }

                    Card(
                        modifier = Modifier
                            .width(120.dp)
                            .height(70.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xffa760f0))
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.distance),
                                contentDescription = "Distance Icon",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = user.Distance.toString(),
                                color = Color.White
                            )
                        }
                    }
                }
            }


        }
    }
}

@Preview
@Composable
fun Preview() {
    MaterialTheme {
//        LeaderboardCard(User("1", 0, "Chandra Mauli Sharma", 4.4f, 5.5f))
    }
}