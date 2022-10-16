package com.example.salud.repository

import com.example.salud.model.DataOrException
import com.example.salud.model.Leaderboard
import com.example.salud.model.User
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class LeaderboardRepositoryImpl @Inject constructor(
    private var queryLeaderboardByRank: Query
):LeaderboardRepository {
    override suspend fun getLeaderboardFromFirestore(): DataOrException<List<Leaderboard>, Exception> {
        val dataOrException = DataOrException<List<Leaderboard>, Exception>()
        try {
            dataOrException.data = queryLeaderboardByRank.get().await().map { document ->
                document.toObject(Leaderboard::class.java)
            }
        } catch (e: FirebaseFirestoreException) {
            dataOrException.e = e
        }
        return dataOrException
    }
}