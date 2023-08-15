package ru.fefu.written_test_impl.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.fefu.written_test_impl.entities.TestType
import javax.inject.Inject

@HiltViewModel
internal class WrittenTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val testTitle: String = savedStateHandle["testType"]
        ?: throw IllegalArgumentException("Test type can not be null")

    private val testType: TestType
        get() = TestType.valueOf(testTitle)

    init {
        Log.d("debug", testType.toString())
    }

    override fun onCleared() {
        Log.d("debug", "viewModel is dead")
        super.onCleared()
    }
}