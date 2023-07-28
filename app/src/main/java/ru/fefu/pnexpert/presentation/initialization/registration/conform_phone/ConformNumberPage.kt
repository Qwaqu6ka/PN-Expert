package ru.fefu.pnexpert.presentation.initialization.registration.conform_phone

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ru.fefu.pnexpert.presentation.initialization.registration.RegistrationViewModel
import ru.fefu.pnexpert.presentation.initialization.registration.navigation.RegistrationNavigationRoute
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme
import java.util.EnumSet.range

private val CURRENT_PAGE = RegistrationNavigationRoute.ConformPhoneScreen

@Composable
fun ConformNumberPage(viewModel: RegistrationViewModel) {

    viewModel.changeRegistrationPage(CURRENT_PAGE)

    val timerValue = remember {
        mutableStateOf(100L * 310L)
    }

    Column(
        modifier = Modifier
        .padding(horizontal = 16.dp),

    ) {
        Spacer(modifier = Modifier.height(30.dp))
        InputCodeFields()
        Spacer(modifier = Modifier.height(80.dp))
        MessageTimer(totalTime = timerValue,)
        Spacer(modifier = Modifier.height(80.dp))
        TextRepeatCode(timerValue)
        Spacer(modifier = Modifier.height(50.dp))
        ConformButton()
    }
}

@Composable
fun ConformButton(){
    TextButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        onClick = {
        },
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        colors = ButtonDefaults.textButtonColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppBlueColor,
        )
    ) {
        Text(
            text = "Подтвердить",
            style = PnExpertTheme.typography.subtitle.medium_18,
            color = PnExpertTheme.colors.textColors.FontWhiteColor
        )
    }
}

@Composable
fun TextRepeatCode(totalTime: MutableState<Long>) {

    var enabledButton by remember {
        mutableStateOf(false)
    }

    enabledButton = totalTime.value == 0L

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Eсли в течении 30 секунд вам не пришел код",
            textAlign = TextAlign.Center,
            style = PnExpertTheme.typography.text.regular_16,
            color = PnExpertTheme.colors.textColors.FontGreyColor
        )
        TextButton(
            enabled = enabledButton,
            onClick = {
                totalTime.value = 100L * 300L
            },
        ) {
            Text(
                text = "Отправить код повторно",
                textAlign = TextAlign.Center,
                style = PnExpertTheme.typography.text.regular_16,
                color = if(enabledButton)
                            PnExpertTheme.colors.textColors.FontBlueColor
                        else
                            PnExpertTheme.colors.textColors.FontGreyColor

            )
        }
    }
}

@Composable
private fun MessageTimer(
    totalTime: MutableState<Long>,
) {

    LaunchedEffect(key1 = totalTime.value) {
        if(totalTime.value > 0) {
            delay(100L)
            totalTime.value -= 100L
        }
    }
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = if ((totalTime.value / 1000L) >= 10)
                    "00:${totalTime.value / 1000L}"
               else
                    "00:0${totalTime.value / 1000L}",
        textAlign = TextAlign.Center,
        style = PnExpertTheme.typography.title.medium_32,
        color = PnExpertTheme.colors.mainAppColors.AppPinkDarkColor
    )
}

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputCodeFields() {
    val fieldBackground = PnExpertTheme.colors.mainAppColors.AppWhiteColor
    var fieldsValue =mutableListOf<MutableState<String>>()

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in 0..3){
            fieldsValue.add(remember { mutableStateOf("") })

            OutlinedTextField(
                modifier = Modifier
                    .size(64.dp, 90.dp)
                    .border(
                        1.dp,
                        PnExpertTheme.colors.mainAppColors.AppGreyDarkColor,
                        PnExpertTheme.shapes.buttonShapes.buttonClassic10
                    ),
                shape = PnExpertTheme.shapes.mainShapes.appDefault10,
                value = fieldsValue[i].value,
                onValueChange = {
                    if (fieldsValue[i].value.isEmpty() || it.isEmpty()) fieldsValue[i].value = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                textStyle = TextStyle(
                    color = PnExpertTheme.colors.textColors.FontGreyColor,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = PnExpertTheme.colors.mainAppColors.AppBlueColor,
                    textColor = PnExpertTheme.colors.textColors.FontDarkColor,
                    unfocusedBorderColor = Color.Transparent,
                    containerColor = fieldBackground
                ),
                maxLines = 1,
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Введите код из смс мы отправили его на номер",
        style = PnExpertTheme.typography.text.regular_16,
        color = PnExpertTheme.colors.textColors.FontGreyColor,
        textAlign = TextAlign.Center
    )
}
