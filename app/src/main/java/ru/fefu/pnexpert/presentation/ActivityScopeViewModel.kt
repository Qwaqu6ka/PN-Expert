package ru.fefu.pnexpert.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.fefu.main_api.MainPageApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.fefu.pnexpert.navigation.FeatureApiHolder
import ru.fefu.sign_up_api.SignUpApi
import javax.inject.Inject

@HiltViewModel
class ActivityScopeViewModel @Inject constructor(
    override val signUpApi: SignUpApi,
    override val mainPageApi: MainPageApi,
) : ViewModel(), FeatureApiHolder {

    private val _isUiReady = MutableLiveData(false)
    val isUiReady: LiveData<Boolean> = _isUiReady

    init {
        viewModelScope.launch {
            delay(1000L)
            _isUiReady.value = true
        }
    }
}