package ru.fefu.pnexpert.presentation.Initialization.SingUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fefu.pnexpert.R
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun SingUpScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = PnExpertTheme.colors.mainAppColors.AppGreyLightColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            SingUpInputFields()
            Spacer(modifier = Modifier.height(30.dp))
            AlternativeSingUp()
            Spacer(modifier = Modifier.height(80.dp))
            SingInText()
            Spacer(modifier = Modifier.height(30.dp))
            RegistrationButton()
        }
    }
}

@Composable
private fun RegistrationButton(){
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
            text = "регистрация",
            style = PnExpertTheme.typography.subtitle.medium_18,
            color = PnExpertTheme.colors.textColors.FontWhiteColor
        )
    }
}

@Composable
private fun SingInText(){
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Уже есть акаунт?",
            style = PnExpertTheme.typography.text.regular_16,
            color = PnExpertTheme.colors.textColors.FontGreyColor,
        )
        TextButton(
            onClick = {}
        ) {
            Text(
                text = "Войти",
                style = PnExpertTheme.typography.text.regular_16,
                color = PnExpertTheme.colors.textColors.FontBlueColor,
            )
        }
    }
}

@Composable
private fun AlternativeSingUp(){

    val buttonShadow = 4.dp

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Войти через социальные сети",
            style = PnExpertTheme.typography.text.regular_16,
            color = PnExpertTheme.colors.textColors.FontGreyColor,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {}
            ) {
                Image(
                    modifier = Modifier.shadow(buttonShadow, CircleShape),
                    painter = painterResource(id = R.drawable.ok_icon),
                    contentDescription = "okSingUpIcon"
                )
            }
            IconButton(
                onClick = {}
            ) {
                Image(
                    modifier = Modifier.shadow(buttonShadow, CircleShape),
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = "okSingUpIcon"
                )
            }
            IconButton(
                onClick = {}
            ) {
                Image(
                    modifier = Modifier.shadow(buttonShadow, CircleShape),
                    painter = painterResource(id = R.drawable.whatsapp_icon),
                    contentDescription = "okSingUpIcon"
                )
            }
            IconButton(
                onClick = {}
            ) {
                Image(
                    modifier = Modifier.shadow(buttonShadow, CircleShape),
                    painter = painterResource(id = R.drawable.telegram_icon),
                    contentDescription = "okSingUpIcon"
                )
            }
        }
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SingUpInputFields() {
    //All fields variables
    val fieldBackground = PnExpertTheme.colors.mainAppColors.AppWhiteColor
    val fieldShadow = 4.dp

    //Phone fields variables
    var statusDropDown by remember { mutableStateOf(false) }
    val listCountryCode = listOf("+7", "+1", "+4", "+5")
    var selectedCountryCodeItem by remember { mutableStateOf(listCountryCode[0]) }

    //Password fields variables
    var passwordVisibility by remember { mutableStateOf(false) }
    val iconPassword = if(passwordVisibility){
        painterResource(id = R.drawable.baseline_visibility_off_24)
    }else{
        painterResource(id = R.drawable.baseline_visibility_24)
    }

    var passwordRepeatVisibility by remember { mutableStateOf(false) }
    val iconPasswordRepeat = if(passwordRepeatVisibility){
        painterResource(id = R.drawable.baseline_visibility_off_24)
    }else{
        painterResource(id = R.drawable.baseline_visibility_24)
    }

    Column(
        modifier = Modifier
    ) {
        Text(
            text = "Введите номер телефона и пин код чтобы зарегестироваться.",
            style = PnExpertTheme.typography.text.regular_16,
            color = PnExpertTheme.colors.textColors.FontGreyColor,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ExposedDropdownMenuBox(
                modifier = Modifier.weight(2f),
                expanded = statusDropDown,
                onExpandedChange = {statusDropDown = !statusDropDown}
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .menuAnchor()
                        .shadow(fieldShadow, PnExpertTheme.shapes.mainShapes.appDefault10),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = statusDropDown
                        )
                    },
                    shape = PnExpertTheme.shapes.mainShapes.appDefault10,
                    value = selectedCountryCodeItem,
                    onValueChange = {},
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = PnExpertTheme.colors.mainAppColors.AppBlueColor,
                        textColor = PnExpertTheme.colors.textColors.FontDarkColor,
                        containerColor = fieldBackground
                    )
                )
                DropdownMenu(
                    modifier = Modifier
                        .exposedDropdownSize()
                        .background(PnExpertTheme.colors.mainAppColors.AppWhiteColor),
                    offset = DpOffset(0.dp, 6.dp),
                    expanded = statusDropDown,
                    onDismissRequest = { statusDropDown = false }
                ) {
                    listCountryCode.forEach { selectStatus ->
                        DropdownMenuItem(
                            modifier = Modifier
                                .background(PnExpertTheme.colors.mainAppColors.AppWhiteColor),
                            text = {
                                Text(
                                    text = selectStatus,
                                    color = PnExpertTheme.colors.textColors.FontGreyColor
                                )
                            },
                            onClick = {
                                selectedCountryCodeItem = selectStatus
                                statusDropDown = false
                            },
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .weight(5f)
                    .shadow(fieldShadow, PnExpertTheme.shapes.mainShapes.appDefault10),
                shape = PnExpertTheme.shapes.mainShapes.appDefault10,
                value = "",
                placeholder = {
                    Text(
                        text = "(000)000-00-00",
                        style = PnExpertTheme.typography.text.regular_16,
                        color = PnExpertTheme.colors.textColors.FontGreyColor
                    )
                },
                onValueChange = {},
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.error_mark_icon),
                        contentDescription = "calendar icon",
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = PnExpertTheme.colors.mainAppColors.AppBlueColor,
                    textColor = PnExpertTheme.colors.textColors.FontDarkColor,
                    containerColor = fieldBackground
                )
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(fieldShadow, PnExpertTheme.shapes.mainShapes.appDefault10),
            shape = PnExpertTheme.shapes.mainShapes.appDefault10,
            value = "adwdawd",
            placeholder = {
                Text(
                    text = "Пароль",
                    style = PnExpertTheme.typography.text.regular_16,
                    color = PnExpertTheme.colors.textColors.FontGreyColor
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = iconPassword,
                        contentDescription = null
                    )
                }
            },
            onValueChange = {},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = PnExpertTheme.colors.mainAppColors.AppBlueColor,
                textColor = PnExpertTheme.colors.textColors.FontDarkColor,
                containerColor = fieldBackground
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(fieldShadow, PnExpertTheme.shapes.mainShapes.appDefault10),
            shape = PnExpertTheme.shapes.mainShapes.appDefault10,
            value = "adwdawd",
            placeholder = {
                Text(
                    text = "Повторите пароль",
                    style = PnExpertTheme.typography.text.regular_16,
                    color = PnExpertTheme.colors.textColors.FontGreyColor
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordRepeatVisibility = !passwordRepeatVisibility }) {
                    Icon(
                        painter = iconPasswordRepeat,
                        contentDescription = null
                    )
                }
            },
            onValueChange = {},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if(passwordRepeatVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = PnExpertTheme.colors.mainAppColors.AppBlueColor,
                textColor = PnExpertTheme.colors.textColors.FontDarkColor,
                containerColor = fieldBackground
            )
        )
    }
}
