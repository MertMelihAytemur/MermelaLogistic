package com.example.mermelalogistic.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FACTORY")
data class Factory(
    @PrimaryKey(autoGenerate = false)
    val factoryId: String,
    val name: String,
    val address: String,
    val password : String,
    val factoryType : String
)