package com.example.salud.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.salud.converter.DateConverter
import com.example.salud.dao.VitalsDao
import com.example.salud.model.User
import com.example.salud.model.Vitals

@Database(entities = [Vitals::class], version = 3, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class VitalsDb:RoomDatabase() {
    abstract fun vitalsDao():VitalsDao
}