package ru.fefu.pnexpert.presentation.Initialization.SingUp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun SingUpScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = PnExpertTheme.colors.mainAppColors.AppWhiteColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            SingUpInputFields()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpInputFields() {

    var statusDropDown by remember { mutableStateOf(false) }

    Column() {
        Text(
            text = "Введите номер телефона и пин код чтобы зарегестироваться.",
            style = PnExpertTheme.typography.text.regular_16,
            color = PnExpertTheme.colors.textColors.FontGreyColor,
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ExposedDropdownMenuBox(
                expanded = statusDropDown,
                onExpandedChange = {statusDropDown = !statusDropDown}
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.90f)
                        .menuAnchor(),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = genderDropDown
                        )
                    },
                    shape = RoundedCornerShape(13.dp),
                    value = when(inputDataState.status){
                        "student"->"Студент"
                        "guest"->"Гость"
                        "employee"->"Сотрудник"
                        else->""
                    },
                    onValueChange = {
                    },
                    isError = inputDataState.statusError != null,
                    placeholder = {
                        Text(
                            text = "Не выбрано",
                            fontSize = 18.sp,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight(200),
                            color = FefuFitTheme.color.textColor.mainTextColor,
                        )
                    },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 16.sp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                        focusedBorderColor = FefuFitTheme.color.mainAppColors.appBlueColor,
                        textColor = FefuFitTheme.color.textColor.mainTextColor
                    )
                )
                DropdownMenu(
                    modifier = Modifier
                        .exposedDropdownSize()
                        .background(FefuFitTheme.color.mainAppColors.appCardColor),
                    offset = DpOffset(0.dp, 6.dp),
                    expanded = statusDropDown,
                    onDismissRequest = { statusDropDown = false }
                ) {
                    listStatus.forEach { selectStatus ->
                        DropdownMenuItem(
                            modifier = Modifier
                                .background(FefuFitTheme.color.mainAppColors.appCardColor),
                            text = {
                                Text(
                                    text = selectStatus,
                                    color = FefuFitTheme.color.textColor.mainTextColor
                                )
                            },
                            onClick = {
                                selectedStatusItem = selectStatus
                                val shortStatus = when(selectedStatusItem){
                                    "Студент"-> "student"
                                    "Гость"-> "guest"
                                    else -> "employee"
                                }
                                viewModel.inputDataEvent(SingUpFirstFormEvent.StatusChanged(shortStatus))
                                statusDropDown = false
                            },
                        )
                    }
                }
            }
        }
    }
}
