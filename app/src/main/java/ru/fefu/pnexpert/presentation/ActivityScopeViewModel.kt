package ru.fefu.pnexpert.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityScopeViewModel : ViewModel() {
    private val _isUiReady = MutableLiveData(false)
    val isUiReady: LiveData<Boolean> = _isUiReady

    init {
        _isUiReady.value = true
    }
}