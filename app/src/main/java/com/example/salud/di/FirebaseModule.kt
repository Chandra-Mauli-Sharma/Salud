package com.example.salud.di

import com.example.salud.repository.LeaderboardRepository
import com.example.salud.repository.LeaderboardRepositoryImpl
import com.example.salud.utils.Constants.LEADERBOARD_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class,ViewModelComponent::class)
object FirebaseModule {

    @Provides
    fun provideQueryLeaderboardByRank(db: FirebaseFirestore): Query =
        db.collection(LEADERBOARD_COLLECTION)


    @Provides
    fun provideLeaderboardRepository(queryLeaderboardByRank: Query): LeaderboardRepository =
        LeaderboardRepositoryImpl(queryLeaderboardByRank = queryLeaderboardByRank)

}