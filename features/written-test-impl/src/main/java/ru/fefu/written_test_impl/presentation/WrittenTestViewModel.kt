package ru.fefu.written_test_impl.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.fefu.presentation.BaseViewModel
import ru.fefu.written_test_impl.domain.repositories.WrittenTestRepository
import ru.fefu.written_test_impl.entities.TestType
import ru.fefu.written_test_impl.entities.testentities.Question
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
        Log.d("debug", "init ViewModel")
        viewModelScope.launch {
            testResult = repository.getTestResults(testTitle)
                .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

            val haveOldTest = repository.isTestCompleted(testTitle, test.questions.size)
            if (haveOldTest) {
                showOldTestDialog.value = true
            }
        }
    }

    override fun onCleared() {
        Log.d("debug", "onCleared ViewModel")
        super.onCleared()
    }

    val testUiState: StateFlow<TestUiState> =
        combineState(
            currentQuestionIndex, testResult, showOldTestDialog,
            scope = viewModelScope, transform = ::mergeSources
        )

    fun onConfirmOldTest() {
        showOldTestDialog.value = false
        Log.d("debug", "confirm clicked")
    }

    fun onDismissOldTest() {
        viewModelScope.launch {
            repository.clearTest(testTitle)
        }
        showOldTestDialog.value = false
        Log.d("debug", "disable clicked")
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
        val isQuestionAnswered = answer != null
//    todo    val isCurrentAnswerCorrect =
        Log.d("debug", "mergeSources: currentQuestIndex: $currentQuestionIndex")
        Log.d("debug", "mergeSources: testResult: ${testResult.joinToString { it.toString() }}")
        Log.d("debug", "mergeSources: showOldDialog: $showOldTestDialog")
        Log.d("debug", "_______________________________________________")
        return TestUiState(
            isPreviousQuestButtonActive = currentQuestionIndex > 0,
            isNextQuestButtonActive = isQuestionAnswered,
            currentQuestion = test.questions[currentQuestionIndex],
            answer = answer?.value,
            showOldTestDialog = showOldTestDialog,
            isQuestionLast = currentQuestionIndex == test.questions.lastIndex
        )
    }

    data class TestUiState(
        val currentQuestion: Question,
        val answer: String?,
        val showOldTestDialog: Boolean,
        val isPreviousQuestButtonActive: Boolean,
        val isNextQuestButtonActive: Boolean,
        private val isQuestionLast: Boolean
    ) {
        val replaceNextButtonWithDone = isQuestionLast
    }
}
