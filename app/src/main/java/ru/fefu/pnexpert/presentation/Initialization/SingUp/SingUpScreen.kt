package ru.fefu.pnexpert.presentation.Initialization.SingUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
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
    val listCountryCode = listOf("+7", "+1", "+4", "+5")
    var selectedCountryCodeItem by remember { mutableStateOf(listCountryCode[0]) }

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
                        .fillMaxWidth()
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
        }
    }
}
