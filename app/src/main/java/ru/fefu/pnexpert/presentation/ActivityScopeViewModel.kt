package ru.fefu.pnexpert.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.fefu.calendar_api.CalendarApi
import ru.fefu.main_api.MainPageApi
import ru.fefu.photo_tests_api.PhotoTestsApi
import ru.fefu.pnexpert.navigation.FeatureApiHolder
import ru.fefu.sign_up_api.SignUpApi
import ru.fefu.video_tests_api.VideoTestApi
import ru.fefu.written_test_api.WrittenTestApi

class ActivityScopeViewModel(
    override val signUpApi: SignUpApi,
    override val mainPageApi: MainPageApi,
    override val writtenTestApi: WrittenTestApi,
    override val photoTestsApi: PhotoTestsApi,
    override val videoTestsApi: VideoTestApi,
    override val calendarApi: CalendarApi,
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