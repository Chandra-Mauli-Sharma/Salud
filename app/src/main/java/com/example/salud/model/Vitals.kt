package com.example.salud.model

import androidx.room.*
import com.google.type.DateTime
import java.util.*

@Entity(tableName = "VITALS_TABLE")
data class Vitals(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String = "0",
    val uid: String = "uid",
    val steps: Int = 0,
    val distance: Float = 5.5f,
    val calorie: Float = 4.8f

)