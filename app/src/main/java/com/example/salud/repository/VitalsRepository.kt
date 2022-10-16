package com.example.salud.repository

import com.example.salud.model.User
import com.example.salud.model.Vitals
import com.google.type.DateTime
import kotlinx.coroutines.flow.Flow
import java.util.*

interface VitalsRepository {
    suspend fun getVitalsFromRoom(): Flow<List<Vitals>>

    suspend fun getVitalFromRoom(dateTime: String): Flow<Vitals>

    suspend fun addVitalToRoom(vitals: Vitals)

    suspend fun updateVitalInRoom(vitals: Vitals)

    suspend fun deleteVitalFromRoom(vitals: Vitals)
}