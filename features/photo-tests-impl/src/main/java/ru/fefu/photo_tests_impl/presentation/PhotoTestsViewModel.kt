package ru.fefu.photo_tests_impl.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.domain.use_cases.GetTestDataUseCase
import ru.fefu.photo_tests_impl.presentation.models.TestDataState
import javax.inject.Inject


class PhotoTestsViewModel @AssistedInject constructor(
    private val getTestDataUseCase: GetTestDataUseCase,
    @Assisted private val testType: PhotoTestType
    ):ViewModel() {
    private var _testDataState = mutableStateOf(TestDataState())
    val testDataState: State<TestDataState> = _testDataState

    init {
        getTestData(testType)
    }

    private fun getTestData(testType: PhotoTestType){
        val testData = getTestDataUseCase(testType)
        _testDataState.value = TestDataState(data = testData)
    }

    @AssistedFactory
    interface Factory {
        fun create(testType: PhotoTestType): PhotoTestsViewModel
    }
}