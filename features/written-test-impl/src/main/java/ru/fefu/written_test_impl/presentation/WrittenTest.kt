package ru.fefu.written_test_impl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.fefu.presentation.components.SimpleAlertDialog
import ru.fefu.presentation.components.SimpleTextButton
import ru.fefu.presentation.components.Toolbar
import ru.fefu.theme.PnExpertTheme
import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.entities.testentities.ChoiceQuestion
import ru.fefu.written_test_impl.entities.testentities.InputQuestion
import ru.fefu.written_test_impl.entities.testentities.TimeQuestion
import ru.fefu.written_test_impl.presentation.answers.InputAnswer
import ru.fefu.written_test_impl.presentation.answers.SelectableAnswerList
import ru.fefu.written_test_impl.presentation.answers.TimeAnswer

@Composable
internal fun WrittenTest(
    modifier: Modifier = Modifier,
    testViewModel: WrittenTestViewModel = hiltViewModel(),
) {
    val uiState by testViewModel.testUiState.collectAsState()

    if (uiState.showOldTestDialog) {
        SimpleAlertDialog(
            onDismissRequest = testViewModel::onDismissOldTest,
            onConfirmation = testViewModel::onConfirmOldTest,
            dialogTitle = stringResource(id = R.string.old_test_dialog_title),
            dialogText = stringResource(id = R.string.old_test_dialog_text),
            confirmButtonText = stringResource(id = R.string.yes),
            dismissButtonText = stringResource(id = R.string.no)
        )
    }

    Scaffold(
        topBar = {
            Toolbar(
                title = stringResource(id = R.string.test),
                onBackPressed = { testViewModel.onBackPressed() })
        },
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
        modifier = modifier
    ) { innerPadding ->
        TestContent(
            testViewModel = testViewModel,
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
        )
    }
}

@Composable
internal fun TestContent(
    testViewModel: WrittenTestViewModel,
    modifier: Modifier = Modifier
) {

    val uiState by testViewModel.testUiState.collectAsState()

    Surface(modifier = modifier) {
        Column(modifier = Modifier.padding(20.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .weight(1f)
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



                Text(
                    text = stringResource(id = uiState.currentQuestion.text),
                    textAlign = TextAlign.Justify,
                    style = PnExpertTheme.typography.text.medium_16
                )
                Spacer(modifier = Modifier.height(10.dp))

                when (val question = uiState.currentQuestion) {
                    is ChoiceQuestion -> SelectableAnswerList(
                        answers = question.answers,
                        chosenAnswerIndex = uiState.answer?.toInt(),
                        onAnswerClick = testViewModel::onAnswerChange
                    )

                    is TimeQuestion -> TimeAnswer(
                        time = uiState.answer,
                        onTimeChange = testViewModel::onAnswerChange,
                        modifier = Modifier.fillMaxSize()
                    )

                    is InputQuestion -> InputAnswer(
                        inputValue = uiState.answer,
                        onInputChange = testViewModel::onAnswerChange,
                        hintRes = hintRes,
                        validator = inputValidator,
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }
            ManageButtons(
                onPreviousQuestion = testViewModel::onBackQuestPressed,
                onNextQuestion = testViewModel::onNextQuestPressed,
                previousButtonIsActive = uiState.isPreviousQuestButtonActive,
                nextButtonIsActive = uiState.isNextQuestButtonActive,
                replaceNextButtonWithDone = uiState.replaceNextButtonWithDone,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@Composable
private fun ManageButtons(
    onPreviousQuestion: () -> Unit,
    onNextQuestion: () -> Unit,
    previousButtonIsActive: Boolean,
    nextButtonIsActive: Boolean,
    replaceNextButtonWithDone: Boolean,
    modifier: Modifier
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
        SimpleTextButton(
            onClick = onPreviousQuestion,
            text = stringResource(id = R.string.back),
            enabled = previousButtonIsActive
        )
        SimpleTextButton(
            onClick = onNextQuestion,
            text = if (replaceNextButtonWithDone)
                stringResource(id = R.string.complete)
            else
                stringResource(id = R.string.next),
            enabled = nextButtonIsActive
        )
    }
}