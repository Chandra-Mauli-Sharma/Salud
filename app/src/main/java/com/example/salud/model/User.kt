package com.example.salud.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class User(
    val uid: String? =null,
    val rank: Int? = null,
    val name: String?=null,
    val calorie: Float?=null,
    val distance: Float?=null
)