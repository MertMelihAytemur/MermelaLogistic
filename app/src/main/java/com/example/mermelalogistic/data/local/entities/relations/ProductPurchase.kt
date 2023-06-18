package com.example.mermelalogistic.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mermelalogistic.data.local.entities.Product
import com.example.mermelalogistic.data.local.entities.Request
import com.example.mermelalogistic.data.local.entities.Warehouse

/**
 *Created by Mert Melih Aytemur on 6/15/2023.
 */
data class ProductPurchase(
    @Embedded val request: Request,
    @Relation(
        parentColumn = "productId",
        entityColumn = "productId"
    )
    val product: Product,
    @Relation(
        parentColumn = "fromCustomerWarehouseId",
        entityColumn = "warehouseId"
    )
    val warehouse: Warehouse
)
