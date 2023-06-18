package com.example.mermelalogistic.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.mermelalogistic.core.model.NavigationCommand
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel() : ViewModel() {

    private val _onNavigateState = MutableLiveData<Event<NavigationCommand>>()

    val onNavigateState: LiveData<Event<NavigationCommand>>
        get() = _onNavigateState


    var isLoading = MutableStateFlow(false)
        protected set

    var toastMessageState : MutableStateFlow<String?> = MutableStateFlow("")
        protected set

    var errorMessage : MutableStateFlow<String?> = MutableStateFlow("")
        protected set

    protected fun navigate(directions: NavDirections) {
        _onNavigateState.value = Event(NavigationCommand.ToDirection(directions))
    }
}