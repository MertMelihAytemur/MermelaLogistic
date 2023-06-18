package com.example.mermelalogistic.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "PRODUCT")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val productId: Long = 0L,
    val name: String,
)