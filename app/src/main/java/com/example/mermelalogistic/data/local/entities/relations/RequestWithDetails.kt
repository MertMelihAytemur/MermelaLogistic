package com.example.mermelalogistic.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mermelalogistic.data.local.entities.Factory
import com.example.mermelalogistic.data.local.entities.Product
import com.example.mermelalogistic.data.local.entities.Request
import com.example.mermelalogistic.data.local.entities.Warehouse

data class RequestWithDetails(
    @Embedded val request: Request,
    @Relation(
        parentColumn = "fromWarehouseId",
        entityColumn = "warehouseId"
    )
    val fromWarehouse: Warehouse,
    @Relation(
        parentColumn = "toFactoryId",
        entityColumn = "factoryId"
    )
    val toFactory: Factory,
    @Relation(
        parentColumn = "productId",
        entityColumn = "productId"
    )
    val product: Product
)