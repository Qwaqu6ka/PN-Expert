package ru.fefu.written_test_impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.fefu.presentation.BaseViewModel
import ru.fefu.written_test_impl.domain.WrittenTestRepository
import ru.fefu.written_test_impl.entities.TestType
import ru.fefu.written_test_impl.entities.testentities.InputQuestion
import ru.fefu.written_test_impl.entities.testentities.WrittenAnswer
import ru.fefu.written_test_impl.entities.testentities.WrittenTest
import javax.inject.Inject

@HiltViewModel
internal class WrittenTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: WrittenTestRepository
) : BaseViewModel() {

    private val testTitle: String = savedStateHandle["testType"]
        ?: throw IllegalArgumentException("Test type can not be null")
    private val test: WrittenTest
        get() = TestType.valueOf(testTitle).test
    private val currentQuestionIndex = MutableStateFlow(0)
    private val showOldTestDialog = MutableStateFlow(false)
    private var testResult: StateFlow<List<WrittenAnswer>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            testResult = repository.getTestResults(testTitle)
                .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

            val haveOldTest = repository.isTestUncompleted(testTitle)
            if (haveOldTest) {
                showOldTestDialog.value = true
            }
        }
    }

    val testUiState: StateFlow<TestUiState> =
        combineState(
            currentQuestionIndex, testResult, showOldTestDialog,
            scope = viewModelScope, transform = ::mergeSources
        )

    fun onConfirmOldTest() {
        viewModelScope.launch {
            currentQuestionIndex.value = repository.getLastAnsweredQuestion(testTitle).first()
        }
        showOldTestDialog.value = false
    }

    fun onDismissOldTest() {
        viewModelScope.launch {
            repository.clearTest(testTitle)
        }
        showOldTestDialog.value = false
    }

    fun onAnswerChange(newValue: String) {
        viewModelScope.launch {
            repository.saveTestResult(
                WrittenAnswer(
                    testTitle = testTitle,
                    questionNumber = currentQuestionIndex.value,
                    value = newValue
                )
            )
        }
    }

    fun onBackPressed() {
        // todo
    }

    fun onNextQuestPressed() = viewModelScope.launch {
        if (currentQuestionIndex.value == test.questions.lastIndex) {
            repository.submitResult(testTitle)
            // todo nav to other page
        } else {
            currentQuestionIndex.value++
            repository.setLastAnsweredQuestion(testTitle, currentQuestionIndex.value)
        }
    }

    fun onBackQuestPressed() = viewModelScope.launch {
        if (currentQuestionIndex.value > 0)
            currentQuestionIndex.value--
    }

    private fun mergeSources(
        currentQuestionIndex: Int,
        testResult: List<WrittenAnswer>,
        showOldTestDialog: Boolean,
    ): TestUiState {
        val answer = testResult.find { it.questionNumber == currentQuestionIndex }
        val validator = (test.questions[currentQuestionIndex] as? InputQuestion)?.validator
        val isQuestionAnsweredCorrect = answer != null &&
                validator?.let { it(answer.value) } != false &&
                answer.value.isNotBlank()
        return TestUiState(
            isNextQuestButtonActive = isQuestionAnsweredCorrect,
            answer = answer?.value,
            showOldTestDialog = showOldTestDialog,
            currentQuestionIndex = currentQuestionIndex,
            test = test
        )
    }

    data class TestUiState(
        val answer: String?,
        val showOldTestDialog: Boolean,
        val isNextQuestButtonActive: Boolean,
        private val currentQuestionIndex: Int,
        private val test: WrittenTest
    ) {
        val replaceNextButtonWithDone = currentQuestionIndex == test.questions.lastIndex
        val currentQuestion = test.questions[currentQuestionIndex]
        val isPreviousQuestButtonActive = currentQuestionIndex > 0
        val amountOfQuestions = test.questions.size
        val currentQuestionNumber = currentQuestionIndex + 1
    }
}

/**
 * TODO: inputAnswer bug where cursor puts in start after print first symbol
 */
