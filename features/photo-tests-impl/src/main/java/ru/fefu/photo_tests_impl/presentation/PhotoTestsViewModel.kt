package ru.fefu.photo_tests_impl.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.domain.use_cases.GetTestDataUseCase
import ru.fefu.photo_tests_impl.presentation.models.TestDataState
import javax.inject.Inject

@HiltViewModel
class PhotoTestsViewModel @Inject constructor(
    private val testType: PhotoTestType,
    private val getTestDataUseCase: GetTestDataUseCase
):ViewModel() {
    private var _testDataState = mutableStateOf(TestDataState())
    val testDataState: State<TestDataState> = _testDataState

    @AssistedFactory
    interface Factory {
        fun create(testType: PhotoTestType): PhotoTestsViewModel
    }
}