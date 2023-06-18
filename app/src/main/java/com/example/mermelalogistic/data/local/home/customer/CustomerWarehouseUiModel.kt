package com.example.mermelalogistic.data.local.home.customer

import com.example.mermelalogistic.data.local.entities.Stock
import com.example.mermelalogistic.data.local.entities.Warehouse

class CustomerWarehouseUiModel(
    val warehouseId : Long,
    val warehouseName : String,
    val productName : String,
    val productQty : Int
)