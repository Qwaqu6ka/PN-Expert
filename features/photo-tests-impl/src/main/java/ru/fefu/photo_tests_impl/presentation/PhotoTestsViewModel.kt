package ru.fefu.photo_tests_impl.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.fefu.photo_tests_impl.domain.models.PhotoTestDataModel
import ru.fefu.photo_tests_impl.domain.use_cases.GetTestDataUseCase
import ru.fefu.photo_tests_impl.presentation.models.TestDataState
import javax.inject.Inject

@HiltViewModel
internal class PhotoTestsViewModel @Inject constructor(
    private val getTestDataUseCase: GetTestDataUseCase
):ViewModel() {
    private var testDataState = mutableStateOf(TestDataState())
}