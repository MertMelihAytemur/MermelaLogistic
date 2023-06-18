package com.example.mermelalogistic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mermelalogistic.data.local.LogisticDatabase
import com.example.mermelalogistic.data.local.entities.Factory
import com.example.mermelalogistic.data.local.entities.Product
import com.example.mermelalogistic.data.local.entities.Stock
import com.example.mermelalogistic.data.local.entities.Warehouse
import com.example.mermelalogistic.data.local.home.repository.FactoryRepository
import com.example.mermelalogistic.ext.hideKeyBoard
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factoryRepository : FactoryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manufacturer1 = Factory(factoryId = "123", name = "TIRSAN", address = "TR,ISTANBUL", password = "123", factoryType = "MANUFACTURER")
        val manufacturer2 = Factory(factoryId = "1234", name = "MERDECES-BENZ", address = "GER,MUNICH", password = "1234", factoryType = "MANUFACTURER")
        val manufacturer3 = Factory(factoryId = "1235", name = "TUSAŞ", address = "TR,ANKARA", password = "1235", factoryType = "MANUFACTURER")

        val customer1 = Factory(factoryId = "789", name = "IVECO", address = "TR,BURSA", password = "789", factoryType = "CUSTOMER")

        val manufacturer1warehouse = Warehouse(factoryId = "123", name = "TIRSAN DEPO 1", address = "TR")
        val manufacturer1warehouse2 = Warehouse(factoryId = "123", name = "TIRSAN DEPO 2", address = "EU")
        val manufacturer2warehouse = Warehouse(factoryId = "1234", name = "MERCEDES DEPO 1", address = "EU")
        val manufacturer3warehouse = Warehouse(factoryId = "1235", name = "TUSAS DEPO 1", address = "TR")
        val customer1warehouse = Warehouse(factoryId = "789", name = "IVECO DEPO 1", address = "TR")

        val product1 = Product(name = "Şaft")
        val product2 = Product(name = "Mil Kolu")
        val product3 = Product(name = "Rulman")
        val product4 = Product(name = "Dişli")


        lifecycleScope.launch {
            factoryRepository.addNewFactory(manufacturer1)
            factoryRepository.addNewFactory(manufacturer2)
            factoryRepository.addNewFactory(manufacturer3)
            factoryRepository.addNewFactory(customer1)

            val id = factoryRepository.addNewWarehouse(manufacturer1warehouse)
            val id2 = factoryRepository.addNewWarehouse(manufacturer1warehouse2)
            val id3 = factoryRepository.addNewWarehouse(manufacturer2warehouse)
            val id4 = factoryRepository.addNewWarehouse(manufacturer3warehouse)
            val id5 = factoryRepository.addNewWarehouse(customer1warehouse)

            val pId = factoryRepository.addNewProduct(product1)
            val pId2 = factoryRepository.addNewProduct(product2)
            val pId3 = factoryRepository.addNewProduct(product3)
            val pId4 = factoryRepository.addNewProduct(product4)

            factoryRepository.addStock(Stock( warehouseId = id, productId = pId, quantity = 800))
            factoryRepository.addStock(Stock(warehouseId = id, productId = pId2, quantity = 300))
            factoryRepository.addStock(Stock( warehouseId = id2, productId = pId3, quantity = 900))
            factoryRepository.addStock(Stock( warehouseId = id3, productId = pId3, quantity = 800))
            factoryRepository.addStock(Stock( warehouseId = id3, productId = pId2, quantity = 800))
            factoryRepository.addStock(Stock( warehouseId = id3, productId = pId, quantity = 800))
            factoryRepository.addStock(Stock( warehouseId = id4, productId = pId, quantity = 800))
            factoryRepository.addStock(Stock( warehouseId = id4, productId = pId2, quantity = 800))
            factoryRepository.addStock(Stock( warehouseId = id4, productId = pId3, quantity = 800))
            factoryRepository.addStock(Stock( warehouseId = id5, productId = pId2, quantity = 800))
            factoryRepository.addStock(Stock( warehouseId = id5, productId = pId4, quantity = 800))
            factoryRepository.addStock(Stock( warehouseId = id5, productId = pId3, quantity = 800))
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            hideKeyBoard()
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onDestroy() {
        this.deleteDatabase("logistic_db")
        super.onDestroy()
    }

}