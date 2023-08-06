package ru.fefu.pnexpert.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel:ViewModel() {
    private val _isUiReady = MutableStateFlow(false)
    val isUiReady = _isUiReady.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _isUiReady.value = true
        }
    }
}