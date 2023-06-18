package com.example.mermelalogistic.data.local.home.repository

import com.example.mermelalogistic.data.local.LogisticDatabase
import com.example.mermelalogistic.data.local.entities.*
import com.example.mermelalogistic.data.local.entities.relations.FactoryWithWarehouses
import com.example.mermelalogistic.data.local.entities.relations.WarehouseWithStocks

interface FactoryRepositoryDataSource {

    suspend fun addNewFactory(factory: Factory) : Long

    suspend fun addNewWarehouse(warehouse: Warehouse) : Long

    suspend fun addNewProduct(product: Product) : Long

    suspend fun addStock(stock: Stock) : Long

    suspend fun getCustomerWarehouseWithStockInfo(factoryId : String) : List<WarehouseWithStocks>

    suspend fun getProductById(productId : Long) : Product?

    suspend fun getAllManufacturers() : List<Factory>

    suspend fun getFactoryWarehouseCount(factoryId: String) : Int

    suspend fun getManufacturerWarehouseWithStockInfo(factoryId : String) : List<WarehouseWithStocks>

    suspend fun decreaseProductAmountFromWarehouse(warehouseId: Long, productId: Long, quantity: Int)

    suspend fun increaseProductAmountFromWarehouse(warehouseId: Long, productId: Long, quantity: Int)

    suspend fun checkIfProductExist(warehouseId: Long,productId: Long) : Long?

    suspend fun createProductRequest(request: Request) : Long?

    suspend fun fetchProductRequests(factoryId: String) : List<Request>

    suspend fun getWarehouseNameById(warehouseId: Long) : String

    suspend fun getProductNameById(productId: Long) : String

    suspend fun getFactoryById(factoryId : String) : Factory?
}