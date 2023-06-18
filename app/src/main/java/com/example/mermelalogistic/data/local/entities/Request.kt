package com.example.mermelalogistic.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Timestamp

@Entity(
    tableName = "REQUEST",
    foreignKeys = [
        ForeignKey(entity = Warehouse::class, parentColumns = ["warehouseId"], childColumns = ["fromCustomerWarehouseId"]),
        ForeignKey(entity = Factory::class, parentColumns = ["factoryId"], childColumns = ["toFactoryId"]),
        ForeignKey(entity = Product::class, parentColumns = ["productId"], childColumns = ["productId"])
    ],
    indices = [
        Index(value = ["fromCustomerWarehouseId"]),
        Index(value = ["toFactoryId"]),
        Index(value = ["productId"])
    ]
)
data class Request(
    @PrimaryKey(autoGenerate = true)
    val requestId: Long = 0L,
    val fromCustomerWarehouseId: Long,
    val toFactoryId: String,
    val productId: Long,
    val quantity: Int,
    val timestamp: Date = Date(System.currentTimeMillis()),
    val isApproved: Boolean = false,
    val departureDate: Date? = null,
    val arrivalDate: Date? = null,
    val isCargoArrived: Boolean = false
)