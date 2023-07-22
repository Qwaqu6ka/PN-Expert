package ru.fefu.pnexpert.presentation.Initialization.SingUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
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
    val listCountryCode = listOf("+7", "+1", "+4", "+5")
    var selectedCountryCodeItem by remember { mutableStateOf(listCountryCode[0]) }

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
                        .menuAnchor(),
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
                        textColor = PnExpertTheme.colors.textColors.FontGreyColor
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
                    .weight(5f),
                shape = PnExpertTheme.shapes.mainShapes.appDefault10,
                value = "(000)000-00-00",
                onValueChange = {
//            viewModel.inputDataEvent(SingInFormEvent.EmailChanged(it))
                },
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
                    focusedBorderColor = PnExpertTheme.colors.mainAppColors.AppBlueColor
                )
            )

        }
    }
}
