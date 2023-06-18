package com.example.mermelalogistic.data.local.home.manufacturer

import java.sql.Date

data class ProductRequestUiModel(
    val customerWarehouseName : String,
    val productName : String,
    val productAmount : Int,
    val requestDate : Date
)
