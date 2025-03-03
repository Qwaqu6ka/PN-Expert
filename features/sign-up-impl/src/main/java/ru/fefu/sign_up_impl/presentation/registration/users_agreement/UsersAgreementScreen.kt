package ru.fefu.sign_up_impl.presentation.registration.users_agreement

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ru.fefu.sign_up_impl.R
import ru.fefu.sign_up_impl.presentation.registration.RegistrationViewModel
import ru.fefu.sign_up_impl.presentation.registration.navigation.RegistrationNavigationRoute
import ru.fefu.theme.PnExpertTheme

private val CURRENT_PAGE = RegistrationNavigationRoute.UsersAgreementScreen

@Composable
fun UsersAgreementScreen(viewModel: RegistrationViewModel) {

    val acceptedTerms = remember { mutableStateOf(false) }

    viewModel.changeRegistrationPage(CURRENT_PAGE)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),

    ) {
        DescriptionText()
        Spacer(modifier = Modifier.height(24.dp))
        AgreementImage()
        Spacer(modifier = Modifier.height(14.dp))
        UserTerms(acceptedTerms)
        Spacer(modifier = Modifier.weight(1f))
        NextButton(viewModel, acceptedTerms)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun NextButton(
    viewModel: RegistrationViewModel,
    acceptedTerms:MutableState<Boolean>
) {

    TextButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        enabled = acceptedTerms.value,
        onClick = {
            if (acceptedTerms.value){
                viewModel.pagesNavController!!.navigate(RegistrationNavigationRoute.ManualScreen.route)
            }
        },
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        colors = ButtonDefaults.textButtonColors(
            disabledContainerColor = PnExpertTheme.colors.buttonColors.ButtonInactiveColor,
            containerColor = PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor,
            disabledContentColor = PnExpertTheme.colors.buttonColors.ButtonInactiveColor,
        )
    ) {
        Text(
            text = "Далее",
            style = PnExpertTheme.typography.subtitle.medium_18,
            color = PnExpertTheme.colors.textColors.FontWhiteColor
        )
    }
}

@Composable
private fun UserTerms(
    acceptedTerms:MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ){
        Checkbox(
            checked = acceptedTerms.value,
            onCheckedChange = {
                acceptedTerms.value = !acceptedTerms.value
            },
        )

        Row() {
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = buildAnnotatedString {
                    append("я принимаю условия")
                    withStyle(
                        style = SpanStyle(
                            color = PnExpertTheme.colors.textColors.FontDarkColor,
                            textDecoration = TextDecoration.Underline
                        )
                    ){
                        append(" пользовательского соглашения и политики конфиденциальности")
                    }
                },
                style = PnExpertTheme.typography.text.regular_14,
                color = PnExpertTheme.colors.textColors.FontGreyColor,
            )
        }
    }
}

@Composable
private fun AgreementImage() {
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


