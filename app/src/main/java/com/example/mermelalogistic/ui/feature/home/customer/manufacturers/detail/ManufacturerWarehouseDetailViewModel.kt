package com.example.mermelalogistic.ui.feature.home.customer.manufacturers.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mermelalogistic.core.BaseViewModel
import com.example.mermelalogistic.data.local.entities.Factory
import com.example.mermelalogistic.data.local.entities.Request
import com.example.mermelalogistic.data.local.entities.Stock
import com.example.mermelalogistic.data.local.home.customer.RequestProductRequestModel
import com.example.mermelalogistic.data.local.home.repository.FactoryRepository
import com.example.mermelalogistic.data.local.home.manufacturer.ManufacturerDetailUiModel
import com.example.mermelalogistic.utils.ClientPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManufacturerWarehouseDetailViewModel @Inject constructor(
    private val factoryRepository: FactoryRepository,
    private val clientPreferences: ClientPreferences
) : BaseViewModel() {

    var warehouseInfo: MutableLiveData<MutableList<ManufacturerDetailUiModel>> = MutableLiveData(
        mutableListOf()
    )

    var factoryInfo : MutableLiveData<Factory> = MutableLiveData(null)

    fun getFactoryInfo(factoryId : String){
        viewModelScope.launch {
            val factory = factoryRepository.getFactoryById(factoryId)
            factory?.let {
                factoryInfo.value = it
            }
        }
    }

    fun getWarehouseWithStockInfo(factoryId: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                val tempList: MutableList<ManufacturerDetailUiModel> = mutableListOf()
                val response = factoryRepository.getManufacturerWarehouseWithStockInfo(factoryId)

                response.forEach { warehouse ->
                    warehouse.stocks.forEach { stockInfo ->
                        tempList.add(
                            ManufacturerDetailUiModel(
                                factoryId = warehouse.warehouse.factoryId,
                                warehouseId = warehouse.warehouse.warehouseId,
                                warehouseName = warehouse.warehouse.name,
                                productId = stockInfo.productId,
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
                isLoading.value = false
            }
        }
    }

    fun requestProduct(requestProductRequestModel: RequestProductRequestModel) {
        viewModelScope.launch {
            kotlin.runCatching {
                val response = factoryRepository.createProductRequest(Request(
                    fromCustomerWarehouseId = requestProductRequestModel.customerWarehouseId,
                    toFactoryId = requestProductRequestModel.factoryId,
                    productId = requestProductRequestModel.productId,
                    quantity = requestProductRequestModel.productAmount
                ))

                if(response != null){
                    toastMessageState.value = "Ürün Talebiniz Başarıyla Oluşturuldu."
                }
            }.onFailure {
                toastMessageState.value =
                    "Beklenmedik bir hata oluştu. Lütfen girilen bilgileri kontrol ediniz."
                isLoading.value = false
            }
        }
    }

    fun fetchProductFromWarehouse(requestProductRequestModel: RequestProductRequestModel) {
        viewModelScope.launch {
            kotlin.runCatching {
                val checkIfProductExist = factoryRepository.checkIfProductExist(requestProductRequestModel.customerWarehouseId,requestProductRequestModel.productId)

                if(checkIfProductExist != null){
                    factoryRepository.decreaseProductAmountFromWarehouse(
                        warehouseId = requestProductRequestModel.factoryWarehouseId,
                        productId = requestProductRequestModel.productId,
                        quantity = requestProductRequestModel.productAmount
                    )

                    factoryRepository.increaseProductAmountFromWarehouse(
                        warehouseId = requestProductRequestModel.customerWarehouseId,
                        productId = requestProductRequestModel.productId,
                        quantity = requestProductRequestModel.productAmount
                    )
                }else{
                    factoryRepository.decreaseProductAmountFromWarehouse(
                        warehouseId = requestProductRequestModel.factoryWarehouseId,
                        productId = requestProductRequestModel.productId,
                        quantity = requestProductRequestModel.productAmount
                    )
                     addProduct(requestProductRequestModel)
                }

                toastMessageState.value = "Ürün Başarıyla Tedarik Edildi."
                navigateToCustomerWarehouse()
            }.onFailure {
                "Beklenmedik bir hata oluştu. Lütfen girilen bilgileri kontrol ediniz."
                isLoading.value = false
            }
        }
    }

    private fun addProduct(requestProductRequestModel: RequestProductRequestModel) {
        viewModelScope.launch {
            kotlin.runCatching {
                val response = factoryRepository.addStock(
                    Stock(
                        warehouseId = requestProductRequestModel.customerWarehouseId,
                        productId = requestProductRequestModel.productId,
                        quantity = requestProductRequestModel.productAmount
                    )
                )

                if(response.toString().isNotEmpty()){
                    getWarehouseWithStockInfo(clientPreferences.getMFactoryId()!!)
                }
            }.onFailure {
                toastMessageState.value = "Ürün Eklenirken Bir Hata Oluştu."
                isLoading.value = false
            }
        }
    }

    private fun navigateToCustomerWarehouse(){
        val action = ManufacturerWarehouseDetailFragmentDirections.actionManufacturerWarehouseDetailFragmentToCustomerHomeFragment()
        navigate(action)
    }
}