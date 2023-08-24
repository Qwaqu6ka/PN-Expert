package ru.fefu.written_test_impl.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.fefu.presentation.Toolbar
import ru.fefu.theme.PnExpertTheme
import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.entities.testentities.ChoiceQuestion
import ru.fefu.written_test_impl.entities.testentities.InputQuestion
import ru.fefu.written_test_impl.entities.testentities.TimeQuestion
import ru.fefu.written_test_impl.presentation.answers.SelectableAnswerList

@Composable
internal fun WrittenTest(
    modifier: Modifier = Modifier,
    testViewModel: WrittenTestViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            Toolbar(
                title = stringResource(id = R.string.test),
                onBackPressed = { testViewModel.onBackPressed() })
        },
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor
    ) { innerPadding ->

        TestContent(testViewModel = testViewModel, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
internal fun TestContent(
    testViewModel: WrittenTestViewModel,
    modifier: Modifier = Modifier
) {

    val uiState by testViewModel.testUiState.collectAsState()

    Surface(modifier = modifier) {
        Column(modifier = Modifier.padding(10.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(
                    rememberScrollState()
                )
            ) {
                Text(text = stringResource(id = uiState.currentQuestion.text))
                when (val question = uiState.currentQuestion) {
                    is ChoiceQuestion -> SelectableAnswerList(
                        answers = question.answers,
                        chosenAnswerIndex = uiState.chosenSelectableAnswerIndex,
                        onAnswerClick = testViewModel::onChooseSelectableAnswer
                    )

                    is TimeQuestion -> Text("Time")
                    is InputQuestion -> Text("input")
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
    Row(modifier = modifier) {
        Button(onClick = onPreviousQuestion, enabled = previousButtonIsActive) {
            Text(text = stringResource(id = R.string.back))
        }
        Button(onClick = onNextQuestion, enabled = nextButtonIsActive) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}