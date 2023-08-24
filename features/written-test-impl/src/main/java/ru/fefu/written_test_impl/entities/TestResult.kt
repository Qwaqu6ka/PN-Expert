package ru.fefu.written_test_impl.entities

import kotlinx.coroutines.flow.MutableStateFlow

data class TestResult(
    val answers: MutableStateFlow<List<String?>> = MutableStateFlow<List<String?>>(emptyList())
)