package ru.fefu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fefu.theme.PnExpertTheme
import ru.fefu.theme.ApplicationTheme

@Composable
fun TextCardHolder(
    modifier: Modifier = Modifier,
    text: String,
    titleText: String = "",
    background: Color = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
    borderColor: Color = PnExpertTheme.colors.mainAppColors.AppBlueColor,
    titleTextColor: Color = PnExpertTheme.colors.textColors.FontBlueColor,
    textColor: Color = PnExpertTheme.colors.textColors.FontBlueColor,
) {
    Card(
        modifier = modifier,
        shape = PnExpertTheme.shapes.imageShapes.imageClassic15,
        border = BorderStroke(1.dp, borderColor),
        colors = CardDefaults.cardColors(
            containerColor = background
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (titleText.isNotBlank()) {
                Text(
                    text = titleText,
                    style = PnExpertTheme.typography.subtitle.medium_18,
                    color = titleTextColor
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            Text(
                text = text,
                style = PnExpertTheme.typography.text.medium_14,
                color = textColor,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTextCardHolderPink() {
    ApplicationTheme {
        TextCardHolder(
            titleText = "Требования",
            text = "Приложение работает с Вашими данными, поэтому для продолжения регистрации потребуется согласие с нашей политикой работы с персональными данными и конфиденциальной информацией. ",
        )
    }
}