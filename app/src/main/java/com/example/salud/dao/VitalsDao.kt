package com.example.salud.dao

import androidx.room.*
import com.example.salud.model.User
import com.example.salud.model.Vitals
import com.google.type.DateTime
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface VitalsDao {
    @Query("SELECT * FROM VITALS_TABLE ORDER BY date DESC")
    fun getVitals(): Flow<List<Vitals>>

    @Query("SELECT * FROM VITALS_TABLE WHERE date= :date")
    fun getVitals(date: String): Flow<Vitals>

    @Insert
    fun addVital(vitals: Vitals)

    @Update
    fun updateVital(vitals: Vitals)

    @Delete
    fun deleteVital(vitals: Vitals)
}