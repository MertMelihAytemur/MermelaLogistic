package com.example.mermelalogistic.data.local.home.customer

data class RequestProductRequestModel(
    val factoryId : String,
    val factoryWarehouseId : Long,
    val customerWarehouseId : Long,
    val productName : String,
    val productId : Long,
    val productAmount : Int
)