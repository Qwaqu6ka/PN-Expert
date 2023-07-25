package ru.fefu.pnexpert.presentation.Initialization.Registration.ConformPhone

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fefu.pnexpert.presentation.Initialization.Registration.RegistrationViewModel
import ru.fefu.pnexpert.presentation.Initialization.Registration.SingUp.SingUpFormEvent
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun ConformNumberPage() {
    Column(
        modifier = Modifier
        .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        InputCodeFields()
        Spacer(modifier = Modifier.height(80.dp))
        MessageTimer()
        Spacer(modifier = Modifier.height(80.dp))
        TextRepeatCode()
        Spacer(modifier = Modifier.height(50.dp))
        ConformButton()
    }
}

@Composable
fun ConformButton() {
    TextButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        onClick = {},
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
fun TextRepeatCode() {
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
        TextButton(onClick = {}) {
            Text(
                text = "Отправить код повторно",
                textAlign = TextAlign.Center,
                style = PnExpertTheme.typography.text.regular_16,
                color = PnExpertTheme.colors.textColors.FontBlueColor
            )
        }
    }
}

@Composable
private fun MessageTimer() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "00:30",
        textAlign = TextAlign.Center,
        style = PnExpertTheme.typography.title.medium_32,
        color = PnExpertTheme.colors.mainAppColors.AppPinkDarkColor
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputCodeFields() {
    val fieldBackground = PnExpertTheme.colors.mainAppColors.AppWhiteColor

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedTextField(
            modifier = Modifier
                .size(64.dp, 90.dp)
                .border(
                    1.dp,
                    PnExpertTheme.colors.mainAppColors.AppGreyDarkColor,
                    PnExpertTheme.shapes.buttonShapes.buttonClassic10
                ),
            shape = PnExpertTheme.shapes.mainShapes.appDefault10,
            value = "",
            onValueChange = {},
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
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .size(64.dp, 90.dp)
                .border(
                    1.dp,
                    PnExpertTheme.colors.mainAppColors.AppGreyDarkColor,
                    PnExpertTheme.shapes.buttonShapes.buttonClassic10
                ),
            shape = PnExpertTheme.shapes.mainShapes.appDefault10,
            value = "",
            onValueChange = {},
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
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .size(64.dp, 90.dp)
                .border(
                    1.dp,
                    PnExpertTheme.colors.mainAppColors.AppGreyDarkColor,
                    PnExpertTheme.shapes.buttonShapes.buttonClassic10
                ),
            shape = PnExpertTheme.shapes.mainShapes.appDefault10,
            value = "",
            onValueChange = {},
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
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .size(64.dp, 90.dp)
                .border(
                    1.dp,
                    PnExpertTheme.colors.mainAppColors.AppGreyDarkColor,
                    PnExpertTheme.shapes.buttonShapes.buttonClassic10
                ),
            shape = PnExpertTheme.shapes.mainShapes.appDefault10,
            value = "",
            onValueChange = {},
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
            )
        )
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
