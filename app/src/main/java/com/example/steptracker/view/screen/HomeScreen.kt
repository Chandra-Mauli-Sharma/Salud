package com.example.steptracker.view.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.steptracker.R
import com.example.steptracker.data.HomeScreenCardList
import com.example.steptracker.view.utils.HomeScreenCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Home_Screen(navController: NavController) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        "Dashboard",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.displayLarge,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Menu, "Menu")
                    }
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.shinchan),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(50.dp)
                    )
                })
        },
    ) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.ArrowBack, "Back")
                Text("Today", fontSize = 30.sp)
                Icon(Icons.Filled.ArrowForward, "Forward")
            }
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.Center,
                columns = GridCells.Fixed(2)
            ) {
                items(items = HomeScreenCardList().list) {
                    HomeScreenCard(
                        cardColor = it.cardColor,
                        cardText = it.cardText,
                        cardIcon = painterResource(id = it.cardIcon),
                        navController = navController,
                        route = it.route
                    )
                }
            }
        }
    }
}
