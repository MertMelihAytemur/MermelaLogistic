package com.example.mermelalogistic.ui.feature.home.customer.manufacturers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mermelalogistic.core.BaseViewModel
import com.example.mermelalogistic.data.local.home.repository.FactoryRepository
import com.example.mermelalogistic.data.local.home.customer.CustomerManufacturersUiModel
import com.example.mermelalogistic.utils.ClientPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManufacturersListViewModel @Inject constructor(
    private val factoryRepository: FactoryRepository,
    private val clientPreferences: ClientPreferences
) : BaseViewModel() {

    val manufacturerList: MutableLiveData<MutableList<CustomerManufacturersUiModel>> = MutableLiveData(
        mutableListOf()
    )

    init {
        getManufacturerList()
    }
    private fun getManufacturerList() {
        viewModelScope.launch {
            kotlin.runCatching {
                isLoading.value = true
                delay(500)
                val tempList: MutableList<CustomerManufacturersUiModel> = mutableListOf()
                val response = factoryRepository.getAllManufacturers()

                response.forEach { factory ->
                    tempList.add(
                        CustomerManufacturersUiModel(
                            factory.factoryId,
                            factory.name,
                            factory.address,
                            factoryRepository.getFactoryWarehouseCount(factory.factoryId).toString()
                        )
                    )
                }
                manufacturerList.value = tempList
                isLoading.value = false
            }.onFailure {
                toastMessageState.value = "Beklenmedik bir hata oluştu."
            }

        }
    }

    fun navigateToFactoryDetail(factoryId : String){
        val action = ManufacturersListFragmentDirections.actionManufacturersListFragmentToManufacturerWarehouseDetailFragment(factoryId)
        navigate(action)
    }
}