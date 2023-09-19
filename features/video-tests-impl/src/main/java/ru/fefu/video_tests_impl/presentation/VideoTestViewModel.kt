package ru.fefu.video_tests_impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.fefu.video_tests_impl.entities.TestType
import ru.fefu.video_tests_impl.navigation.TEST_TYPE_KEY
import javax.inject.Inject

@HiltViewModel
internal class VideoTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val test: TestType =
        TestType.valueOf(checkNotNull(savedStateHandle[TEST_TYPE_KEY]) { "Test type can not be null" })



}