package com.example.mermelalogistic.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "WAREHOUSE",
    foreignKeys = [
        ForeignKey(entity = Factory::class, parentColumns = ["factoryId"], childColumns = ["factoryId"])
    ],
    indices = [Index(value = ["factoryId"])]
)
data class Warehouse(
    @PrimaryKey(autoGenerate = true)
    val warehouseId: Long = 0L,
    val factoryId: String,
    val name: String,
    val address: String
)