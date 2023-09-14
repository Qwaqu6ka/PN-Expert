package ru.fefu.video_tests_impl.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class VideoTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val testTitle: String = savedStateHandle["testType"]
        ?: throw IllegalArgumentException("Test type can not be null")

    // TODO: getTestTitle from navigation and share this viewModel to 2 screens

    init {
        Log.d("debug", testTitle)
    }
}