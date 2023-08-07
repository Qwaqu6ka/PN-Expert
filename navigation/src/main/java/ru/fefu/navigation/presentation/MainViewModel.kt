package ru.fefu.navigation.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel : ViewModel() {
    private val _isUiReady = MutableLiveData(false)
    val isUiReady: LiveData<Boolean> = _isUiReady

    init {
        _isUiReady.value = true
    }
}