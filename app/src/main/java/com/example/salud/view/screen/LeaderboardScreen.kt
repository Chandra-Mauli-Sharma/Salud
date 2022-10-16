package com.example.salud.view.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.salud.model.Leaderboard
import com.example.salud.model.User
import com.example.salud.view.utils.LeaderboardCard
import com.example.salud.viewmodel.LeaderboardViewModel

@Composable
fun LeaderboardScreen(navController: NavController, modifier: Modifier,viewModel: LeaderboardViewModel= hiltViewModel()) {
    viewModel.getLeaderboard()
    LazyColumn(modifier = modifier) {
        items(viewModel.data.value.data as List<Leaderboard>) { item ->
            LeaderboardCard(user = item)
        }
    }

}