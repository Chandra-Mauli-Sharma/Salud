package com.example.salud.repository

import com.example.salud.model.DataOrException
import com.example.salud.model.Leaderboard
import com.example.salud.model.User
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import dagger.Component
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface LeaderboardRepository{
    suspend fun getLeaderboardFromFirestore(): DataOrException<List<Leaderboard>, Exception>
}