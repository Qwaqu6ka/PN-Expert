package ru.fefu.photo_tests_impl.presentation.result_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.fefu.photo_tests_impl.domain.models.PhotoTestAnswerForReading
import ru.fefu.photo_tests_impl.domain.use_cases.GetUserAnswersUseCase
import javax.inject.Inject

@HiltViewModel
class ResultScreenViewModel @Inject constructor(
    private val getUserAnswersUseCase: GetUserAnswersUseCase
):ViewModel() {

    private var _answers = mutableStateOf<PhotoTestAnswerForReading?>(null)
    val answers: State<PhotoTestAnswerForReading?> = _answers

    init {
        getAnswer()
    }

    private fun getAnswer(){
        _answers.value = getUserAnswersUseCase()
    }
}