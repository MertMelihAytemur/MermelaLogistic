package com.example.mermelalogistic.data.local.home.manufacturer

data class AddProductRequestModel(
    val productName : String,
    val warehouseId : Long,
    val productAmount : Int
)