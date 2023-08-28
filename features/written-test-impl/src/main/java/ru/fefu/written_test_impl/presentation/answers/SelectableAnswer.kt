package ru.fefu.written_test_impl.presentation.answers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fefu.theme.PnExpertTheme
import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.entities.testentities.SelectableAnswer

@Composable
internal fun SelectableAnswerList(
    answers: List<SelectableAnswer>,
    chosenAnswerIndex: Int?,
    onAnswerClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for (index in answers.indices) {
            SelectableAnswerCard(
                answer = answers[index],
                onClick = { onAnswerClick(index.toString()) },
                isSelected = chosenAnswerIndex == index
            )
        }
    }
}

@Composable
private fun SelectableAnswerCard(
    answer: SelectableAnswer,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val cardColor = if (isSelected) {
        PnExpertTheme.colors.mainAppColors.AppBlueColor
    } else {
        PnExpertTheme.colors.mainAppColors.AppWhiteColor
    }

    val contentColor = if (isSelected) {
        PnExpertTheme.colors.textColors.FontWhiteColor
    } else {
        PnExpertTheme.colors.textColors.FontDarkColor
    }

    Box {
        Surface(
            onClick = onClick,
            shape = RoundedCornerShape(16.dp),
            border = if (isSelected) null else BorderStroke(
                2.dp,
                PnExpertTheme.colors.mainAppColors.AppBlueColor
            ),
            modifier = Modifier.fillMaxWidth(),
            color = cardColor,
            contentColor = contentColor
        ) {
            Text(
                text = stringResource(id = answer.text),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                style = PnExpertTheme.typography.text.medium_16,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview
@Composable
private fun CardListPreview() {
    PnExpertTheme {
        Surface(Modifier.fillMaxSize(), color = Color.Gray) {
            SelectableAnswerList(
                chosenAnswerIndex = 5,
                onAnswerClick = {},
                answers = listOf(
                    SelectableAnswer(R.string.PSQI_question5, 0),
                    SelectableAnswer(R.string.PSQI_question5, 0),
                    SelectableAnswer(R.string.PSQI_question5, 0),
                    SelectableAnswer(R.string.PSQI_question5, 0),
                    SelectableAnswer(R.string.PSQI_question5, 0),
                    SelectableAnswer(R.string.PSQI_question5, 0),
                    SelectableAnswer(R.string.PSQI_question5, 0),
                    SelectableAnswer(R.string.PSQI_question5, 0),
                )
            )
        }
    }
}

@Preview
@Composable
private fun CardPreview() {
    PnExpertTheme {
        SelectableAnswerCard(answer = SelectableAnswer(R.string.PSQI_question5, 0), false) {}
    }
}

@Preview
@Composable
private fun SelectedCardPreview() {
    PnExpertTheme {
        SelectableAnswerCard(answer = SelectableAnswer(R.string.PSQI_question5, 0), true) {}
    }
}