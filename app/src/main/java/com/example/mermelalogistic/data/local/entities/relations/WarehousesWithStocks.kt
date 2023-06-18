package com.example.mermelalogistic.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mermelalogistic.data.local.entities.Stock
import com.example.mermelalogistic.data.local.entities.Warehouse

data class WarehouseWithStocks(
    @Embedded val warehouse: Warehouse,
    @Relation(
        parentColumn = "warehouseId",
        entityColumn = "warehouseId"
    )
    val stocks: List<Stock>
)