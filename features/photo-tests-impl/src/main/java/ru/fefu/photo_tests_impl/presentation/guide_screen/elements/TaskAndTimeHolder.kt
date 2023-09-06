package ru.fefu.photo_tests_impl.presentation.guide_screen.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.fefu.theme.PnExpertTheme


@Composable
internal fun TaskAndTimeHolder(
    modifier:Modifier,
    taskName:String,
    date:String,
    time:String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = taskName,
            style = PnExpertTheme.typography.text.medium_14,
            color = PnExpertTheme.colors.textColors.FontDarkColor
        )
        Text(
            text = "$date / $time",
            style = PnExpertTheme.typography.text.regular_14,
            color = PnExpertTheme.colors.textColors.FontGreyColor
        )

    }
}

@Preview
@Composable
private fun TestNameHolder() {
    PnExpertTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(PnExpertTheme.colors.mainAppColors.AppWhiteColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Нарисовать часы",
                style = PnExpertTheme.typography.text.medium_14,
                color = PnExpertTheme.colors.textColors.FontDarkColor
            )
            Text(
                text = "25 мая 2022 / 14:50:04",
                style = PnExpertTheme.typography.text.regular_14,
                color = PnExpertTheme.colors.textColors.FontGreyColor
            )

        }
    }
}