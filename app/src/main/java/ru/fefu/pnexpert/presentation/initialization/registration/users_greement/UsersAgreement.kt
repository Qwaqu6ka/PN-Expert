package ru.fefu.pnexpert.presentation.initialization.registration.users_greement

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ru.fefu.pnexpert.R
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun UsersAgreement() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        DescriptionText()
        Spacer(modifier = Modifier.height(24.dp))
        AgreementImage()
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun AgreementImage() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .clip(PnExpertTheme.shapes.imageShapes.imageClassic15),
        painter = painterResource(id = R.drawable.user_agreement_img),
        contentDescription = "userAgreementImg",
        contentScale = ContentScale.FillWidth
    )
}

@Composable
private fun DescriptionText() {
    Text(
        text = buildAnnotatedString {
            append("Приложение работает с Вашими данными, поэтому для продолжения регистрации потребуется согласие с нашей")
            withStyle(style = SpanStyle(color = PnExpertTheme.colors.mainAppColors.AppBlueColor)){
                append(" политикой работы с персональными данными ")
            }
            append("и конфиденциальной информацией.")
        },
        style = PnExpertTheme.typography.text.regular_16,
        color = PnExpertTheme.colors.textColors.FontGreyColor,
        textAlign = TextAlign.Center
    )
}


