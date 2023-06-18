package com.example.mermelalogistic.ui.feature.home.customer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mermelalogistic.core.BaseViewModel
import com.example.mermelalogistic.data.local.entities.Factory
import com.example.mermelalogistic.data.local.home.repository.FactoryRepository
import com.example.mermelalogistic.data.local.home.customer.CustomerWarehouseUiModel
import com.example.mermelalogistic.utils.ClientPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerHomeViewModel @Inject constructor(
    private val factoryRepository: FactoryRepository,
    private val clientPreferences: ClientPreferences
) : BaseViewModel() {

    var warehouseInfo: MutableLiveData<MutableList<CustomerWarehouseUiModel>> = MutableLiveData(
        mutableListOf()
    )

    var factoryInfo : MutableLiveData<Factory> = MutableLiveData(null)

    init {
        clientPreferences.getCFactoryId()?.let { factoryId ->
            getFactoryInfo(factoryId)
            getWarehouseWithStockInfo(factoryId)
        }
    }

    private fun getWarehouseWithStockInfo(factoryId: String) {
        viewModelScope.launch {
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
                isLoading.value  = false
            }.onFailure {
                toastMessageState.value = "Bir Hata Oluştu"
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


    fun navigateToManufacturersInfoFragment() {
        val action =
            CustomerHomeFragmentDirections.actionCustomerHomeFragmentToManufacturersListFragment()
        navigate(action)
    }
}