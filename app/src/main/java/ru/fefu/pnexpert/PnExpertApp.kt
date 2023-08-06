package ru.fefu.pnexpert

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
@HiltAndroidApp
class PnExpertApp @Inject constructor() : Application(){
    //initialization variables
    private val _isInitializationReady = mutableStateOf(false)
    val isInitializationReady: State<Boolean> = _isInitializationReady

    //initialization setters
    fun initializationFinish(){
        _isInitializationReady.value = true
    }
    fun initializationGoBack(){
        _isInitializationReady.value = false
    }
}