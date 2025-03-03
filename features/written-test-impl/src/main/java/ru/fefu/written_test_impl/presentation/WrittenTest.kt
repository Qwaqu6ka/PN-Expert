package ru.fefu.written_test_impl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.fefu.components.PNExpertAlertDialog
import ru.fefu.components.PNExpertTextButton
import ru.fefu.components.PNExpertToolbar
import ru.fefu.observeWithLifecycle
import ru.fefu.theme.PnExpertTheme
import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.presentation.components.InputAnswer
import ru.fefu.written_test_impl.presentation.components.SelectableAnswerList
import ru.fefu.written_test_impl.presentation.components.TimeAnswer
import ru.fefu.written_test_impl.presentation.entities.ChoiceQuestion
import ru.fefu.written_test_impl.presentation.entities.InputQuestion
import ru.fefu.written_test_impl.presentation.entities.TimeQuestion

@Composable
internal fun WrittenTest(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WrittenTestViewModel = koinViewModel(),
) {
    viewModel.sideEffectFlow.observeWithLifecycle { sideEffect ->
        when (sideEffect) {
            is WrittenTestViewModel.WrittenTestSideEffect.NavigateBack -> onNavigateBack()
        }
    }

    val uiState by viewModel.testUiState.collectAsState()

    if (uiState.showOldTestDialog) {
        PNExpertAlertDialog(
            onDismissRequest = viewModel::onDismissOldTest,
            onConfirmation = viewModel::onConfirmOldTest,
            dialogTitle = stringResource(id = R.string.old_test_dialog_title),
            dialogText = stringResource(id = R.string.old_test_dialog_text),
            confirmButtonText = stringResource(id = R.string.yes),
            dismissButtonText = stringResource(id = R.string.no)
        )
    }

    Scaffold(
        topBar = {
            PNExpertToolbar(
                title = stringResource(id = R.string.test),
                onBackPressed = viewModel::onBackPressed
            )
        },
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
        contentWindowInsets = WindowInsets(0.dp),
        modifier = modifier
    ) { innerPadding ->
        TestContent(
            testViewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun TestContent(
    testViewModel: WrittenTestViewModel,
    modifier: Modifier = Modifier
) {

    val uiState by testViewModel.testUiState.collectAsState()

    Surface(
        modifier = modifier,
        color = PnExpertTheme.colors.mainAppColors.AppWhiteColor
    ) {
        val scrollState = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .verticalScroll(scrollState)
        ) {
            val currentQuestion = uiState.currentQuestion
            val hintRes = if (currentQuestion is InputQuestion) {
                currentQuestion.hint
            } else {
                null
            }
            val inputValidator = if (currentQuestion is InputQuestion) {
                currentQuestion.validator
            } else {
                null
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "${uiState.currentQuestionNumber}/${uiState.amountOfQuestions}",
                textAlign = TextAlign.Center,
                style = PnExpertTheme.typography.text.medium_16
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = uiState.currentQuestion.text),
                textAlign = TextAlign.Justify,
                style = PnExpertTheme.typography.text.medium_16
            )
            Spacer(modifier = Modifier.height(15.dp))

            val answer by testViewModel.answer.collectAsState()
            when (val question = uiState.currentQuestion) {
                is ChoiceQuestion -> SelectableAnswerList(
                    answers = question.answers,
                    chosenAnswerIndex = if (answer.isNotBlank()) answer.toInt() else null,
                    onAnswerClick = testViewModel::onLocalAnswerChange
                )

                is TimeQuestion -> TimeAnswer(
                    time = answer,
                    onTimeChange = testViewModel::onLocalAnswerChange,
                    modifier = Modifier.fillMaxSize()
                )

                is InputQuestion -> InputAnswer(
                    inputValue = answer,
                    onInputChange = testViewModel::onLocalAnswerChange,
                    hintRes = hintRes,
                    validator = inputValidator,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(15.dp))

            val coroutineScope = rememberCoroutineScope()
            ManageButtons(
                onPreviousQuestion = testViewModel::onBackQuestPressed,
                onNextQuestion = testViewModel::onNextQuestPressed,
                scrollToStart = { coroutineScope.launch { scrollState.scrollTo(0) } },
                previousButtonIsActive = uiState.isPreviousQuestButtonActive,
                nextButtonIsActive = uiState.isNextQuestButtonActive,
                replaceNextButtonWithDone = uiState.replaceNextButtonWithDone,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
private fun ManageButtons(
    onPreviousQuestion: () -> Unit,
    onNextQuestion: () -> Unit,
    scrollToStart: () -> Unit,
    previousButtonIsActive: Boolean,
    nextButtonIsActive: Boolean,
    replaceNextButtonWithDone: Boolean,
    modifier: Modifier
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
        PNExpertTextButton(
            onClick = {
                onPreviousQuestion()
                scrollToStart()
            },
            text = stringResource(id = R.string.back),
            enabled = previousButtonIsActive
        )
        PNExpertTextButton(
            onClick = {
                onNextQuestion()
                scrollToStart()
            },
            text = if (replaceNextButtonWithDone)
                stringResource(id = R.string.complete)
            else
                stringResource(id = R.string.next),
            enabled = nextButtonIsActive
        )
    }
}