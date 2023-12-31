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
import ru.fefu.BaseViewModel
import ru.fefu.written_test_impl.domain.WrittenTestRepository
import ru.fefu.written_test_impl.entities.TestType
import ru.fefu.written_test_impl.entities.testentities.InputQuestion
import ru.fefu.written_test_impl.entities.testentities.WrittenAnswer
import ru.fefu.written_test_impl.entities.testentities.WrittenTest
import ru.fefu.written_test_impl.navigation.ARG_WRITTEN_TEST_TYPE
import javax.inject.Inject

@HiltViewModel
internal class WrittenTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: WrittenTestRepository
) : BaseViewModel() {

    private var uncompletedLeave: Boolean = true
    private val testTitle: String =
        checkNotNull(savedStateHandle[ARG_WRITTEN_TEST_TYPE]) { "Test type can not be null" }
    private val test: WrittenTest = TestType.valueOf(testTitle).test
    private val currentQuestionIndex = MutableStateFlow(0)
    private val showOldTestDialog = MutableStateFlow(false)
    private var testResult: StateFlow<List<WrittenAnswer>> = MutableStateFlow(emptyList())
    private val _answer = MutableStateFlow("")
    val answer: StateFlow<String> = _answer

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
            answer, currentQuestionIndex, showOldTestDialog,
            scope = viewModelScope, transform = ::mergeSources
        )

    override fun onCleared() {
        if (uncompletedLeave) {
            saveLocalAnswer()
        }
        super.onCleared()
    }

    fun onConfirmOldTest() {
        viewModelScope.launch {
            currentQuestionIndex.value = repository.getLastAnsweredQuestion(testTitle).first()
            changeLocalAnswer()
        }
        showOldTestDialog.value = false
    }

    fun onDismissOldTest() {
        viewModelScope.launch {
            repository.clearTest(testTitle)
        }
        showOldTestDialog.value = false
    }

    fun onBackPressed() {
        // todo
    }

    fun onNextQuestPressed() = viewModelScope.launch {
        saveLocalAnswer().join()
        if (currentQuestionIndex.value == test.questions.lastIndex) {
            repository.submitResult(testTitle)
            uncompletedLeave = false
            onBackPressed()
        } else {
            currentQuestionIndex.value++
            changeLocalAnswer()
            repository.setLastAnsweredQuestion(testTitle, currentQuestionIndex.value)
        }
    }

    fun onBackQuestPressed() = viewModelScope.launch {
        if (currentQuestionIndex.value > 0) {
            currentQuestionIndex.value--
            changeLocalAnswer()
        }
    }

    fun onLocalAnswerChange(newValue: String) {
        changeLocalAnswer(newValue)
    }

    private fun getFromTestResultOrNull(): String? {
        val list = testResult.value
        val index = currentQuestionIndex.value
        return if (index < list.size) {
            list[index].value
        } else {
            null
        }
    }

    private fun changeLocalAnswer(newValue: String? = null) {
        if (newValue == null) {
            _answer.value = getFromTestResultOrNull() ?: ""
        } else {
            _answer.value = newValue
        }

    }

    private fun saveLocalAnswer() =
        viewModelScope.launch {
            repository.saveTestResult(
                WrittenAnswer(
                    testTitle = testTitle,
                    questionNumber = currentQuestionIndex.value,
                    value = _answer.value
                )
            )
        }

    private fun mergeSources(
        answer: String,
        currentQuestionIndex: Int,
        showOldTestDialog: Boolean,
    ): TestUiState {
        val validator = (test.questions[currentQuestionIndex] as? InputQuestion)?.validator
        val isQuestionAnsweredCorrect =
            answer.isNotBlank() && validator?.let { it(answer) } != false
        return TestUiState(
            isNextQuestButtonActive = isQuestionAnsweredCorrect,
            showOldTestDialog = showOldTestDialog,
            currentQuestionIndex = currentQuestionIndex,
            test = test
        )
    }

    data class TestUiState(
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