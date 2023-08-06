package ru.fefu.pnexpert.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel:ViewModel() {
    //splash screen variables
    private val _isUiReady = MutableStateFlow(false)
    val isUiReady = _isUiReady.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _isUiReady.value = true
        }
    }

}