package ru.fefu.pnexpert

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltAndroidApp
class PnExpertApp : Application(){
    //initialization variables
    private val _isInitializationReady = MutableStateFlow(false)
    val isInitializationReady = _isInitializationReady.asStateFlow()

    //initialization setters
    fun initializationFinish(){
        _isInitializationReady.value = true
    }
    fun initializationGoBack(){
        _isInitializationReady.value = false
    }
}