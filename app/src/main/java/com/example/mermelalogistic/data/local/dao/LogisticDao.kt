package com.example.mermelalogistic.data.local.dao

import androidx.room.*
import com.example.mermelalogistic.data.local.entities.*
import com.example.mermelalogistic.data.local.entities.relations.FactoryWithWarehouses
import com.example.mermelalogistic.data.local.entities.relations.WarehouseWithStocks

@Dao
interface LogisticDao {

    @Query("SELECT * FROM FACTORY")
    suspend fun getAllFactories(): List<Factory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewFactory(factory: Factory) : Long

    @Query("SELECT * FROM FACTORY WHERE factoryId = :factoryId")
    suspend fun getFactoryById(factoryId: String): Factory?

    @Query("SELECT * FROM FACTORY WHERE factoryId = :factoryId AND password = :password AND factoryType = :factoryType")
    suspend fun getFactoryByCredentials(factoryId: String, password: String,factoryType : String): Factory?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWarehouse(warehouse: Warehouse) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStock(stock: Stock) : Long

    @Transaction
    @Query("SELECT *FROM WAREHOUSE where factoryId = :factoryId")
    suspend fun getCustomerWarehouseWithStockInfo(factoryId : String) : List<WarehouseWithStocks>

    @Transaction
    @Query("SELECT *FROM WAREHOUSE where factoryId = :factoryId")
    suspend fun getManufacturerWarehouseWithStockInfo(factoryId : String) : List<WarehouseWithStocks>

    @Query("SELECT *FROM PRODUCT where productId = :productId")
    suspend fun getProductById(productId : Long) :Product

    @Query("SELECT productId FROM STOCK where warehouseId = :warehouseId AND productId = :productId")
    suspend fun checkIfProductExist(warehouseId: Long,productId: Long) : Long?

    @Query("SELECT *FROM FACTORY where factoryType = 'MANUFACTURER'")
    suspend fun getAllManufacturers() : List<Factory>

    @Transaction
    @Query("SELECT COUNT(*) FROM WAREHOUSE WHERE factoryId = :factoryId")
    suspend fun getFactoryWarehouseCount(factoryId: String) : Int

    @Query("UPDATE STOCK SET quantity = quantity - :quantity WHERE warehouseId = :warehouseId AND productId = :productId")
    suspend fun decreaseProductAmountFromWarehouse(warehouseId: Long, productId: Long, quantity: Int)

    @Query("UPDATE STOCK SET quantity = quantity + :quantity WHERE warehouseId = :warehouseId AND productId = :productId")
    suspend fun increaseProductAmountFromWarehouse(warehouseId: Long, productId: Long, quantity: Int)

    @Insert
    suspend fun createProductRequest(request: Request) : Long?

    @Query("SELECT *FROM REQUEST where toFactoryId = :factoryId")
    suspend fun fetchProductRequests(factoryId: String) : List<Request>

    @Query("SELECT name FROM WAREHOUSE where warehouseId = :warehouseId")
    suspend fun getWarehouseNameById(warehouseId: Long) : String

    @Query("SELECT name FROM PRODUCT where productId = :productId")
    suspend fun getProductNameById(productId: Long) : String
}