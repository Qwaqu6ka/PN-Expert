package ru.fefu.video_tests_impl.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.fefu.video_tests_impl.navigation.TEST_TYPE_KEY
import javax.inject.Inject

@HiltViewModel
internal class VideoTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val testTitle: String =
        checkNotNull(savedStateHandle[TEST_TYPE_KEY]) { "Test type can not be null" }

    init {
        Log.d("debug", "init ViewModel $testTitle")
    }

    override fun onCleared() {
        Log.d("debug", "onCleared ViewModel $testTitle")
        super.onCleared()
    }
}