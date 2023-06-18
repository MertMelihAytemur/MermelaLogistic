package com.example.mermelalogistic.ui.feature.login

import androidx.lifecycle.viewModelScope
import com.example.mermelalogistic.core.BaseViewModel
import com.example.mermelalogistic.data.local.login.repository.LoginRepository
import com.example.mermelalogistic.utils.ClientPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val clientPreferences: ClientPreferences
) : BaseViewModel() {

    fun loginManufacturer(factoryId: String, password: String) {
        viewModelScope.launch {
            isLoading.value = true
            kotlin.runCatching {
                val response = repository.login(factoryId,password,"MANUFACTURER")
                if (response != null) {
                    clientPreferences.setMFactoryId(response.factoryId)
                    navigateToManufacturerHomePage()
                    isLoading.value = false
                } else {
                    toastMessageState.value = "Kullanıcı Bulunamadı"
                    isLoading.value = false
                }
            }.onFailure {
                toastMessageState.value = "Kullanıcı Bulunamadı"
                isLoading.value = false
            }
        }
    }

    fun loginCustomer(factoryId: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                isLoading.value = true
                val response = repository.login(factoryId,password,"CUSTOMER")
                if (response != null) {
                    clientPreferences.setCFactoryId(response.factoryId)
                    navigateToCustomerHomePage()
                    isLoading.value = false
                } else {
                    toastMessageState.value = "Kullanıcı bulunamadı."
                    isLoading.value = false
                }
            }.onFailure {
                toastMessageState.value = "Kullanıcı bulunamadı."
                isLoading.value = false
            }
        }
    }

    private fun navigateToManufacturerHomePage() {
        val action = LoginFragmentDirections.actionLoginFragmentToManufacturerHomeFragment()
        navigate(action)
    }

    private fun navigateToCustomerHomePage() {
        val action = LoginFragmentDirections.actionLoginFragmentToCustomerHomeFragment()
        navigate(action)
    }
}