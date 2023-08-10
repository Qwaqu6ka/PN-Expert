package ru.fefu.sign_up_impl.presentation.registration.sing_up

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fefu.sign_up_impl.R
import ru.fefu.sign_up_impl.presentation.registration.navigation.RegistrationNavigationRoute
import ru.fefu.sign_up_impl.presentation.registration.RegistrationViewModel
import ru.fefu.theme.PnExpertTheme


private val CURRENT_PAGE = RegistrationNavigationRoute.SingUpScreen

@SuppressLint("RememberReturnType")
@Composable
fun SingUpScreen(
    viewModel: RegistrationViewModel
) {
    viewModel.changeRegistrationPage(CURRENT_PAGE)

    // Creating a values and variables to remember
    // focus requester, manager and state
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        SingUpInputFields(focusRequester, viewModel)
        Spacer(modifier = Modifier.height(40.dp))
        AlternativeSingUp()
        Spacer(modifier = Modifier.height(50.dp))
        SingInText()
        Spacer(modifier = Modifier.weight(1f))
        RegistrationButton(focusManager, viewModel)
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
private fun RegistrationButton(
    focusManager: FocusManager,
    viewModel: RegistrationViewModel,
) {
    TextButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        onClick = {
            focusManager.clearFocus()
            viewModel.inputDataEvent(SingUpFormEvent.Submit)
        },
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        colors = ButtonDefaults.textButtonColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppBlueColor,
        )
    ) {
        Text(
            text = "Регистрация",
            style = PnExpertTheme.typography.subtitle.medium_18,
            color = PnExpertTheme.colors.textColors.FontWhiteColor
        )
    }
}

@Composable
private fun SingInText() {
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
private fun AlternativeSingUp() {

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

@Composable
private fun ErrorField(error: String) {
    Spacer(modifier = Modifier.height(8.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55)
            .background(
                PnExpertTheme.colors.mainAppColors.AppPinkLightColors,
                shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
            )
            .border(
                width = 1.dp,
                color = PnExpertTheme.colors.buttonColors.ButtonPressedRedColor,
                shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = error,
            color = PnExpertTheme.colors.buttonColors.ButtonPressedRedColor,
            style = PnExpertTheme.typography.text.regular_16,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SingUpInputFields(
    focusRequester: FocusRequester,
    viewModel: RegistrationViewModel
) {

    //input data variables
    val inputDataState = viewModel.inputDataState

    println(inputDataState.phoneNumber)

    //All fields variables
    val fieldBackground = PnExpertTheme.colors.mainAppColors.AppWhiteColor
    val fieldShadow = 6.dp

    //Phone fields variables
    var phoneNumber by remember { mutableStateOf("") }
    var statusDropDown by remember { mutableStateOf(false) }
    val listCountryCode = listOf("+7", "+1", "+4", "+5")
    var selectedCountryCodeItem by remember { mutableStateOf(listCountryCode[0]) }

    //Password fields variables
    var passwordVisibility by remember { mutableStateOf(false) }
    val iconPassword = if (passwordVisibility) {
        painterResource(id = R.drawable.baseline_visibility_off_24)
    } else {
        painterResource(id = R.drawable.baseline_visibility_24)
    }

    var passwordRepeatVisibility by remember { mutableStateOf(false) }
    val iconPasswordRepeat = if (passwordRepeatVisibility) {
        painterResource(id = R.drawable.baseline_visibility_off_24)
    } else {
        painterResource(id = R.drawable.baseline_visibility_24)
    }

    Column(
        modifier = Modifier
    ) {
        Text(
            text = "Введите номер телефона и пин код чтобы зарегестироваться.",
            style = PnExpertTheme.typography.text.regular_16,
            color = PnExpertTheme.colors.textColors.FontGreyColor,
//            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ExposedDropdownMenuBox(
                modifier = Modifier.weight(2f),
                expanded = statusDropDown,
                onExpandedChange = { statusDropDown = !statusDropDown }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .menuAnchor()
                        .shadow(fieldShadow, PnExpertTheme.shapes.mainShapes.appDefault10)
                        .focusRequester(focusRequester),
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
                        unfocusedBorderColor = Color.Transparent,
                        containerColor = fieldBackground,
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
                                viewModel.inputDataEvent(
                                    SingUpFormEvent.PhoneNumberChanged(
                                        selectedCountryCodeItem + phoneNumber
                                    )
                                )
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
                    .shadow(fieldShadow, PnExpertTheme.shapes.mainShapes.appDefault10)
                    .focusRequester(focusRequester),
                shape = PnExpertTheme.shapes.mainShapes.appDefault10,
                value = phoneNumber,
                placeholder = {
                    Text(
                        text = "(000)000-00-00",
                        style = PnExpertTheme.typography.text.regular_16,
                        color = PnExpertTheme.colors.textColors.FontGreyColor
                    )
                },
                onValueChange = {
                    phoneNumber = it
                    viewModel.inputDataEvent(
                        SingUpFormEvent.PhoneNumberChanged(
                            selectedCountryCodeItem + it
                        )
                    )
                },
                isError = inputDataState.phoneNumberError != null,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            phoneNumber = ""
                            viewModel.inputDataEvent(
                                SingUpFormEvent.PhoneNumberChanged(
                                    selectedCountryCodeItem + phoneNumber
                                )
                            )
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.error_mark_icon),
                            contentDescription = "calendar icon",
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = PnExpertTheme.colors.mainAppColors.AppBlueColor,
                    unfocusedBorderColor = Color.Transparent,
                    containerColor = fieldBackground
                )
            )

        }
        if (inputDataState.phoneNumberError != null) {
            ErrorField(error = inputDataState.phoneNumberError)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(fieldShadow, PnExpertTheme.shapes.mainShapes.appDefault10)
                .focusRequester(focusRequester),
            shape = PnExpertTheme.shapes.mainShapes.appDefault10,
            value = inputDataState.password,
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
            onValueChange = {
                viewModel.inputDataEvent(SingUpFormEvent.PasswordChanged(it))
            },
            isError = inputDataState.passwordError != null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = PnExpertTheme.colors.mainAppColors.AppBlueColor,
                unfocusedBorderColor = Color.Transparent,
                containerColor = fieldBackground
            )
        )

        if (inputDataState.passwordError != null) {
            ErrorField(error = inputDataState.passwordError)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(fieldShadow, PnExpertTheme.shapes.mainShapes.appDefault10)
                .focusRequester(focusRequester),
            shape = PnExpertTheme.shapes.mainShapes.appDefault10,
            value = inputDataState.repeatPassword,
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
            onValueChange = {
                viewModel.inputDataEvent(SingUpFormEvent.RepeatPasswordChanged(it))
            },
            isError = inputDataState.repeatPasswordError != null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordRepeatVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = PnExpertTheme.colors.mainAppColors.AppBlueColor,
                unfocusedBorderColor = Color.Transparent,
                containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
            )
        )

        if (inputDataState.repeatPasswordError != null) {
            ErrorField(error = inputDataState.repeatPasswordError)
        }
    }
}
