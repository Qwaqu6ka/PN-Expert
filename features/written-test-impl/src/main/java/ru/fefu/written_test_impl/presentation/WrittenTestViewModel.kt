package ru.fefu.written_test_impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.fefu.presentation.BaseViewModel
import ru.fefu.written_test_impl.domain.CompleteTestUseCase
import ru.fefu.written_test_impl.domain.HaveOldTestUseCase
import ru.fefu.written_test_impl.domain.LeaveUncompletedTestUseCase
import ru.fefu.written_test_impl.entities.TestType
import ru.fefu.written_test_impl.entities.testentities.Question
import ru.fefu.written_test_impl.entities.testentities.WrittenTest
import javax.inject.Inject

@HiltViewModel
internal class WrittenTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val completeTestUseCase: CompleteTestUseCase,
    private val leaveUncompletedTestUseCase: LeaveUncompletedTestUseCase,
    private val haveOldTestUseCase: HaveOldTestUseCase
) : BaseViewModel() {

    private val testTitle: String = savedStateHandle["testType"]
        ?: throw IllegalArgumentException("Test type can not be null")
    private val testType: TestType
        get() = TestType.valueOf(testTitle)
    private val test: WrittenTest
        get() = testType.test

    private val currentQuestionIndex = MutableStateFlow(0)
    private val testResult = MutableStateFlow(List<String?>(test.questions.size) { null })

    /**
     *  value for [ru.fefu.written_test_impl.presentation.answers.SelectableAnswerList]
     */
    private val chosenSelectableAnswerIndex = MutableStateFlow<Int?>(null)

    /**
     * value for [ru.fefu.written_test_impl.presentation.answers.SelectableAnswerList]
     * and [ru.fefu.written_test_impl.presentation.answers.SelectableAnswerList]
     */
    private val inputAnswerValue = MutableStateFlow<String?>(null)

    val testUiState: StateFlow<TestUiState> =
        combineState(
            currentQuestionIndex, chosenSelectableAnswerIndex, inputAnswerValue, testResult,
            scope = viewModelScope, transform = ::mergeSources
        )

    init {  //todo
        viewModelScope.launch {
            val haveOldTest = haveOldTestUseCase.isTestCompleted(testType)
            if (haveOldTest) {
                // launch oldTest dialog
            }
        }
    }

    /**
     *  function for [ru.fefu.written_test_impl.presentation.answers.SelectableAnswerList]
     */
    fun onChooseSelectableAnswer(selectedAnsIndex: Int) {
        chosenSelectableAnswerIndex.value = selectedAnsIndex
        saveTestResult(currentQuestionIndex.value, selectedAnsIndex.toString())
    }

    /**
     *  function for [ru.fefu.written_test_impl.presentation.answers.SelectableAnswerList]
     *  and [ru.fefu.written_test_impl.presentation.answers.SelectableAnswerList]
     */
    fun onInputChange(newValue: String) {
        inputAnswerValue.value = newValue
        saveTestResult(currentQuestionIndex.value, newValue.ifBlank { null })
    }

    fun onBackPressed() {
        // todo
    }

    fun onNextQuestPressed() = viewModelScope.launch {
        if (currentQuestionIndex.value == test.questions.lastIndex) {
            completeTestUseCase.submitResult(testType, testResult.value)
            // todo nav to other page
        } else {
            currentQuestionIndex.value++
            chosenSelectableAnswerIndex.value = null
            inputAnswerValue.value = null
        }
    }

    fun onBackQuestPressed() = viewModelScope.launch {
        if (currentQuestionIndex.value == 0) return@launch
        currentQuestionIndex.value--
    }

    private fun saveTestResult(questionIndex: Int, value: String?) {
        val newList = testResult.value.toMutableList()
        newList[questionIndex] = value
        testResult.value = newList
    }

    private fun mergeSources(
        currentQuestionIndex: Int,
        chosenSelectableAnswerIndex: Int?,
        inputAnswerValue: String?,
        testResult: List<String?>
    ): TestUiState {
        return TestUiState(
            isPreviousQuestButtonActive = currentQuestionIndex > 0,
            chosenSelectableAnswerIndex = chosenSelectableAnswerIndex,
            inputAnswerValue = inputAnswerValue,
            isNextQuestButtonActive = testResult[currentQuestionIndex]?.isNotBlank() ?: false,
            currentQuestion = test.questions[currentQuestionIndex],
            isQuestionLast = currentQuestionIndex < test.questions.lastIndex
        )
    }

    data class TestUiState(
        val currentQuestion: Question,
        val chosenSelectableAnswerIndex: Int?,
        val inputAnswerValue: String?,
        val isPreviousQuestButtonActive: Boolean,
        val isNextQuestButtonActive: Boolean,
        private val isQuestionLast: Boolean
    ) {
        val replaceNextButtonWithDone = isQuestionLast
    }
}
