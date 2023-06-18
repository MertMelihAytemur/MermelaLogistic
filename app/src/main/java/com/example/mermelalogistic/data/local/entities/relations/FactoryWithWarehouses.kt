package com.example.mermelalogistic.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mermelalogistic.data.local.entities.Factory
import com.example.mermelalogistic.data.local.entities.Warehouse

data class FactoryWithWarehouses(
    @Embedded val factory: Factory,
    @Relation(
        parentColumn = "factoryId",
        entityColumn = "factoryId"
    )
    val warehouses: List<Warehouse>
)