package com.example.mermelalogistic.ui.feature.home.manufacturer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mermelalogistic.core.BaseViewModel
import com.example.mermelalogistic.data.local.entities.Factory
import com.example.mermelalogistic.data.local.entities.Product
import com.example.mermelalogistic.data.local.entities.Stock
import com.example.mermelalogistic.data.local.home.repository.FactoryRepository
import com.example.mermelalogistic.data.local.home.customer.CustomerWarehouseUiModel
import com.example.mermelalogistic.data.local.home.manufacturer.AddProductRequestModel
import com.example.mermelalogistic.data.local.home.manufacturer.ProductRequestUiModel
import com.example.mermelalogistic.utils.ClientPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ManufacturerHomeViewModel @Inject constructor(
    private val factoryRepository: FactoryRepository,
    private val clientPreferences: ClientPreferences
) : BaseViewModel() {

    var warehouseInfo: MutableLiveData<MutableList<CustomerWarehouseUiModel>> = MutableLiveData(
        mutableListOf()
    )

    var addProductResult: MutableLiveData<Boolean> = MutableLiveData(false)

    var productRequestList: MutableLiveData<MutableList<ProductRequestUiModel>> =
        MutableLiveData(mutableListOf())

    var factoryInfo : MutableLiveData<Factory> = MutableLiveData(null)

    init {
        clientPreferences.getMFactoryId()?.let { factoryId ->
            getFactoryInfo(factoryId)
            getWarehouseWithStockInfo(factoryId)
        }
        fetchAllProductRequests()
    }

    private fun getWarehouseWithStockInfo(factoryId: String) {
        isLoading.value = true
        viewModelScope.launch {
            delay(1000)
            kotlin.runCatching {
                val tempList: MutableList<CustomerWarehouseUiModel> = mutableListOf()
                val response = factoryRepository.getCustomerWarehouseWithStockInfo(factoryId)

                response.forEach { warehouse ->
                    warehouse.stocks.forEach { stockInfo ->
                        tempList.add(
                            CustomerWarehouseUiModel(
                                warehouseId =
                                warehouse.warehouse.warehouseId,
                                warehouseName = warehouse.warehouse.name,
                                productName = factoryRepository.getProductById(stockInfo.productId)!!.name,
                                productQty = stockInfo.quantity
                            )
                        )
                    }
                }
                warehouseInfo.value = tempList
                isLoading.value = false
            }.onFailure {
                toastMessageState.value = "Bir Hata Oluştu"
            }
        }
    }

    fun addProduct(addProductRequestModel: AddProductRequestModel) {
        viewModelScope.launch {
            val checkIfProductExist =
            kotlin.runCatching {
                val productId = factoryRepository.addNewProduct(
                    Product(
                        name = addProductRequestModel.productName
                    )
                )

                val response = factoryRepository.addStock(
                    Stock(
                        warehouseId = addProductRequestModel.warehouseId,
                        productId = productId,
                        quantity = addProductRequestModel.productAmount
                    )
                )

                if (response.toString().isNotEmpty()) {
                    addProductResult.value = true
                    clientPreferences.getMFactoryId()?.let {
                        getWarehouseWithStockInfo(it)
                    }
                }
            }.onFailure {
                toastMessageState.value = "Ürün Eklenirken Bir Hata Oluştu."
            }
        }
    }

   private fun fetchAllProductRequests() {
        viewModelScope.launch {
            kotlin.runCatching {
                val tempList: MutableList<ProductRequestUiModel> = mutableListOf()

                val response =
                    factoryRepository.fetchProductRequests(clientPreferences.getMFactoryId()!!)
                response.forEach {
                    tempList.add(
                        ProductRequestUiModel(
                            customerWarehouseName = factoryRepository.getWarehouseNameById(it.fromCustomerWarehouseId),
                            productName = factoryRepository.getProductNameById(it.productId),
                            productAmount = it.quantity,
                            requestDate = it.timestamp
                        )
                    )
                }
                Timber.i("TALEPLER $response")
                productRequestList.value = tempList
            }.onFailure {
                toastMessageState.value = "Talepler getirilemedi. Lütfen daha sonra tekrar deneyin."
            }
        }
    }

    private fun getFactoryInfo(factoryId : String){
        viewModelScope.launch {
            val factory = factoryRepository.getFactoryById(factoryId)
            factory?.let {
                factoryInfo.value = it
            }
        }
    }
}