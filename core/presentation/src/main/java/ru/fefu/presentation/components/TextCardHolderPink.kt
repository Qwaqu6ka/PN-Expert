package ru.fefu.presentation.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fefu.theme.PnExpertTheme

@Composable
fun TextCardHolderPink(
    modifier: Modifier,
    titleText: String,
    text:String,
) {
    PnExpertTheme {
        Card(
            modifier = modifier,
            shape = PnExpertTheme.shapes.imageShapes.imageClassic15,
            colors = CardDefaults.cardColors(
                containerColor = PnExpertTheme.colors.mainAppColors.AppPinkLightColors
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = titleText,
                    style = PnExpertTheme.typography.text.medium_16,
                    color = PnExpertTheme.colors.textColors.FontDarkColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = text,
                    style = PnExpertTheme.typography.text.medium_14,
                    color = PnExpertTheme.colors.textColors.FontGreyColor,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewTextCardHolderPink() {
    PnExpertTheme {
        TextCardHolderPink(Modifier, "Требования", "Приложение работает с Вашими данными, поэтому для продолжения регистрации потребуется согласие с нашей политикой работы с персональными данными и конфиденциальной информацией. ")
    }
}