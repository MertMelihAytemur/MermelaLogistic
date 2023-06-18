package com.example.mermelalogistic.data.local.home.repository

import com.example.mermelalogistic.data.local.dao.LogisticDao
import com.example.mermelalogistic.data.local.entities.*
import com.example.mermelalogistic.data.local.entities.relations.WarehouseWithStocks
import javax.inject.Inject
import kotlin.math.log

class FactoryRepository @Inject constructor(
    private val logisticDao: LogisticDao
) : FactoryRepositoryDataSource {


    override suspend fun addNewFactory(factory: Factory): Long {
        return logisticDao.addNewFactory(factory)
    }

    override suspend fun addNewWarehouse(warehouse: Warehouse): Long {
        return logisticDao.addWarehouse(warehouse)
    }

    override suspend fun addNewProduct(product: Product): Long {
        return logisticDao.addProduct(product)
    }

    override suspend fun addStock(stock: Stock): Long {
        return logisticDao.addStock(stock)
    }

    override suspend fun getCustomerWarehouseWithStockInfo(factoryId: String): List<WarehouseWithStocks> {
        return logisticDao.getCustomerWarehouseWithStockInfo(factoryId)
    }

    override suspend fun getProductById(productId: Long): Product? {
        return logisticDao.getProductById(productId)
    }

    override suspend fun getAllManufacturers(): List<Factory> {
        return logisticDao.getAllManufacturers()
    }

    override suspend fun getFactoryWarehouseCount(factoryId: String): Int {
        return logisticDao.getFactoryWarehouseCount(factoryId)
    }

    override suspend fun getManufacturerWarehouseWithStockInfo(factoryId: String): List<WarehouseWithStocks> {
        return logisticDao.getManufacturerWarehouseWithStockInfo(factoryId)
    }

    override suspend fun decreaseProductAmountFromWarehouse(
        warehouseId: Long,
        productId: Long,
        quantity: Int
    ) {
        logisticDao.decreaseProductAmountFromWarehouse(
            warehouseId,
            productId,
            quantity
        )
    }

    override suspend fun increaseProductAmountFromWarehouse(
        warehouseId: Long,
        productId: Long,
        quantity: Int
    ) {
        logisticDao.increaseProductAmountFromWarehouse(
            warehouseId,
            productId,
            quantity
        )
    }

    override suspend fun checkIfProductExist(warehouseId: Long, productId: Long): Long? {
        return logisticDao.checkIfProductExist(warehouseId,productId)
    }

    override suspend fun createProductRequest(request: Request): Long? {
        return logisticDao.createProductRequest(request)
    }

    override suspend fun fetchProductRequests(factoryId: String): List<Request> {
        return logisticDao.fetchProductRequests(factoryId)
    }

    override suspend fun getWarehouseNameById(warehouseId: Long): String {
        return logisticDao.getWarehouseNameById(warehouseId)
    }

    override suspend fun getProductNameById(productId: Long): String {
        return logisticDao.getProductNameById(productId)
    }

    override suspend fun getFactoryById(factoryId: String): Factory? {
        return logisticDao.getFactoryById(factoryId)
    }
}