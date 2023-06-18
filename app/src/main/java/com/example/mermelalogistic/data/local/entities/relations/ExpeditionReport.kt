package com.example.mermelalogistic.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mermelalogistic.data.local.entities.Expedition
import com.example.mermelalogistic.data.local.entities.Factory
import com.example.mermelalogistic.data.local.entities.Request
import com.example.mermelalogistic.data.local.entities.Warehouse

/**
 *Created by Mert Melih Aytemur on 6/15/2023.
 */
data class ExpeditionReport(
    @Embedded val expedition: Expedition,
    @Relation(
        parentColumn = "requestId",
        entityColumn = "requestId"
    )
    val request: Request,
    @Relation(
        parentColumn = "toFactoryId",
        entityColumn = "factoryId"
    )
    val factory: Factory,
    @Relation(
        parentColumn = "fromCustomerWarehouseId",
        entityColumn = "warehouseId"
    )
    val warehouse: Warehouse
)