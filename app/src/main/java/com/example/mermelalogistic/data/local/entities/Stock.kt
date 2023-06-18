package com.example.mermelalogistic.data.local.entities

import androidx.room.*

@Entity(
    tableName = "STOCK",
    foreignKeys = [
        ForeignKey(entity = Warehouse::class, parentColumns = ["warehouseId"], childColumns = ["warehouseId"]),
        ForeignKey(entity = Product::class, parentColumns = ["productId"], childColumns = ["productId"])
    ],
    indices = [
        Index(value = ["warehouseId"]),
        Index(value = ["productId"])
    ]
)
data class Stock(
    @PrimaryKey(autoGenerate = true)
    val stockId: Long = 0L,
    val warehouseId: Long,
    val productId: Long,
    val quantity: Int
)







