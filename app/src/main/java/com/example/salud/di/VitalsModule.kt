package com.example.salud.di

import android.content.Context
import androidx.room.Room
import com.example.salud.dao.VitalsDao
import com.example.salud.db.VitalsDb
import com.example.salud.model.Vitals
import com.example.salud.repository.VitalsRepository
import com.example.salud.repository.VitalsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class VitalsModule {
    @Provides
    fun provideVitalsDb(
        @ApplicationContext
        context : Context
    ) = Room.databaseBuilder(
        context,
        VitalsDb::class.java,
        "VITALS_TABLE"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideVitalsDao(
        vitalsDb: VitalsDb
    ) = vitalsDb.vitalsDao()

    @Provides
    fun provideVitalsRepository(
        vitalsDao: VitalsDao
    ): VitalsRepository = VitalsRepositoryImpl(vitalsDao)
}