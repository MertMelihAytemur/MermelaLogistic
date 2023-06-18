package com.example.mermelalogistic.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 *Created by Mert Melih Aytemur on 6/15/2023.
 */
@Entity(tableName = "EXPEDITION")
data class Expedition(
    @PrimaryKey(autoGenerate = true)
    val expeditionId: Long = 0L,
    val requestId: Long,
    val fromProvince: String,
    val fromCountry: String,
    val toProvince: String,
    val toCountry: String,
    val cargoName: String,
    val parcelAmount: Int,
    val totalVolume: Double,
    val totalWeight: Double,
    val estimatedDepartureDate: Date? = null,
    val estimatedArrivalDate: Date? = null,
    val actualDepartureDate: Date? = null,
    val actualArrivalDate: Date? = null,
    val isCargoArrived: Boolean = false
)