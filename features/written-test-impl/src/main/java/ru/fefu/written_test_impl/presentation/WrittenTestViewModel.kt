package ru.fefu.written_test_impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.fefu.written_test_impl.entities.TestType
import ru.fefu.written_test_impl.entities.testentities.WrittenTest
import javax.inject.Inject

@HiltViewModel
internal class WrittenTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val testTitle: String = savedStateHandle["testType"]
        ?: throw IllegalArgumentException("Test type can not be null")

    val test: WrittenTest
        get() = TestType.valueOf(testTitle).test

    fun onBackPressed() {

    }
}