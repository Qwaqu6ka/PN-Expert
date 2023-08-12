package ru.fefu.sign_up_impl.presentation.registration.manual

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import ru.fefu.sign_up_impl.R
import ru.fefu.sign_up_impl.presentation.registration.RegistrationViewModel
import ru.fefu.sign_up_impl.presentation.registration.navigation.RegistrationNavigationRoute
import kotlinx.coroutines.launch
import ru.fefu.theme.PnExpertTheme

private val CURRENT_PAGE = RegistrationNavigationRoute.ManualScreen

data class ManualData(
    val imageResource: Int,
    val imageTitle: String,
    val imageDescription: String,
)


@Composable
fun ManualScreen(
    viewModel: RegistrationViewModel,
){

    viewModel.changeRegistrationPage(CURRENT_PAGE)

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        ManualCardHolder(pagerState)
        Spacer(modifier = Modifier.height(10.dp))
        NextButton(viewModel, pagerState)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun NextButton(viewModel: RegistrationViewModel, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TextButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55)
            .border(
                1.dp,
                PnExpertTheme.colors.textColors.FontBlueColor,
                PnExpertTheme.shapes.buttonShapes.buttonClassic10
            )
        ,
        onClick = {
            if (pagerState.currentPage != pagerState.pageCount-1){
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1, 0f)
                }
            }
            else
                viewModel.registrationSuccess()
        },
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.Transparent,
            disabledContentColor = PnExpertTheme.colors.buttonColors.ButtonInactiveColor,
        )
    ) {
        Text(
            text = if (pagerState.currentPage != pagerState.pageCount-1){
                "Пропустить шаг"
            }
            else{
                "Завершить регистрацию"
            },
            style = PnExpertTheme.typography.subtitle.medium_18,
            color = PnExpertTheme.colors.textColors.FontBlueColor
        )
    }
}


@Composable
fun ManualCardHolder(
    pagerState: PagerState
){

    val manualDataList = listOf(
        ManualData(R.drawable.manual_1, "Здравствуйте!", "Уважаемый пациент. Дальнейшие этапы регистрации обязательны и очень важны, для быстрого прохождения регистрации вам потребуется подготовка. Ознакомьтесь с данной инструкцией, которая поможет вам сделать процедуру регистрации простой и легкой."),
        ManualData(R.drawable.user_agreement_img, "Политика персональных данных", "Приложение работает с Вашими данными, поэтому для продолжения регистрации потребуется согласие с нашей политикой работы с персональными данными и конфиденциальной информацией."),
        ManualData(R.drawable.manual_3, "Привязка родственника или опекуна", "Вы можете привязать к своему профилю родственника или опекуна. Привязка осуществляется по номеру мобильного телефона, который можно указать из контактов в телефоне. Ваш родственник/опекун должен быть зарегистрирован в системе, если приложение его не находит, то отправьте ему приглашение на регистрацию в приложении."),
        ManualData(R.drawable.manual_4, "Привязка специалиста", "Если вы знаете своего специалиста, можно отправить ему запрос на присоединение к Вашему профилю. Поиск осуществляется по ФИО специалиста или клинике, в которой он работает. Подготовьте эту информацию заранее."),
        ManualData(R.drawable.manual_6, "«Защита входа»", "Вы можете защитить вход в приложение по пин-коду или отпечатку пальца. Пожалуйста используйте код, который совпадает с кодом доступа на ваш мобильный телефон или другим кодом, который вы точно никогда не забудите."),
        ManualData(R.drawable.manual_7, "«Первичный сбор данных»", "Для оформления и ведения персональной истории болезни, приложению потребуется исходная информация, а именно ФИО, Дата рождения, Пол, Город проживания"),
        ManualData(R.drawable.manual_8, "«Первичный сбор данных»", "Также вы пройдете первичный опрос, который займет не более 5 минут"),
        ManualData(R.drawable.manual_9, "«Первичный сбор данных»", "Также вы пройдете первичный опрос, который займет не более 5 минут"),
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            count = manualDataList.size,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) {id->
            ManualCard(manualDataList[id])
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalPagerIndicator(pagerState = pagerState)
    }
}

@Composable
private fun ManualCard(manualData:ManualData) {

    val fieldShadow = 6.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 12.dp)
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
                painter = painterResource(id = manualData.imageResource),
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
                    text = manualData.imageTitle,
                    style = PnExpertTheme.typography.subtitle.bold_18,
                    color = PnExpertTheme.colors.textColors.FontDarkColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = manualData.imageDescription,
                    style = PnExpertTheme.typography.text.regular_14,
                    color = PnExpertTheme.colors.textColors.FontGreyColor
                )
            }

        }
    }
}
