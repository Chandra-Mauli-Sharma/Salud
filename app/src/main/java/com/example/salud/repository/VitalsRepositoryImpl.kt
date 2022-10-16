package com.example.salud.repository

import com.example.salud.dao.VitalsDao
import com.example.salud.model.Vitals
import com.google.type.DateTime
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Singleton

@Singleton
class VitalsRepositoryImpl(private val vitalsDao: VitalsDao):VitalsRepository {
    override suspend fun getVitalsFromRoom(): Flow<List<Vitals>> = vitalsDao.getVitals()

    override suspend fun getVitalFromRoom(dateTime: String): Flow<Vitals> = vitalsDao.getVitals(dateTime)

    override suspend fun addVitalToRoom(vitals: Vitals) = vitalsDao.addVital(vitals)

    override suspend fun updateVitalInRoom(vitals: Vitals) = vitalsDao.updateVital(vitals)

    override suspend fun deleteVitalFromRoom(vitals: Vitals) = vitalsDao.updateVital(vitals)
}