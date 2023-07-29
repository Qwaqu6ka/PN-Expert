package ru.fefu.pnexpert.presentation.initialization.registration.manual

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.fefu.pnexpert.R
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun ManualScreen(){
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        ManualCard()
        Spacer(modifier = Modifier.height(100.dp))
        NextButton()
    }
}

@Composable
fun NextButton() {
    TextButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55)
            .border(1.dp, PnExpertTheme.colors.textColors.FontBlueColor, PnExpertTheme.shapes.buttonShapes.buttonClassic10 )
        ,
        onClick = {

        },
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.Transparent,
            disabledContentColor = PnExpertTheme.colors.buttonColors.ButtonInactiveColor,
        )
    ) {
        Text(
            text = "Пропустить шаг",
            style = PnExpertTheme.typography.subtitle.medium_18,
            color = PnExpertTheme.colors.textColors.FontBlueColor
        )
    }
}

@Composable
private fun ManualCard() {

    val fieldShadow = 6.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(fieldShadow, PnExpertTheme.shapes.imageShapes.imageClassic15),
        shape = PnExpertTheme.shapes.mainShapes.appDefault10,
        colors = CardDefaults.cardColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor
        )
    ) {
        Column() {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(15f / 10f)
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
                painter = painterResource(id = R.drawable.user_agreement_img),
                contentDescription = "userAgreementImg",
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Политика персональных данных",
                    style = PnExpertTheme.typography.subtitle.bold_18,
                    color = PnExpertTheme.colors.textColors.FontDarkColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Приложение работает с Вашими данными, поэтому для продолжения регистрации потребуется согласие с нашей политикой работы с персональными данными и конфиденциальной информацией.",
                    style = PnExpertTheme.typography.text.regular_14,
                    color = PnExpertTheme.colors.textColors.FontGreyColor
                )
            }
            
        }
    }
}
