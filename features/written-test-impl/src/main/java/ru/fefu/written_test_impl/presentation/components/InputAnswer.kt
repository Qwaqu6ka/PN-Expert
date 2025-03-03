package ru.fefu.written_test_impl.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import ru.fefu.theme.PnExpertTheme
import ru.fefu.written_test_impl.presentation.entities.InputQuestionValidator

@Composable
internal fun InputAnswer(
    modifier: Modifier = Modifier,
    inputValue: String = "",
    onInputChange: (String) -> Unit,
    @StringRes hintRes: Int?,
    validator: InputQuestionValidator?,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        val textBoxContainerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor
        val textBoxTextColor = PnExpertTheme.colors.textColors.FontDarkColor
        val textBoxMainColor = PnExpertTheme.colors.mainAppColors.AppBlueColor

        OutlinedTextField(
            value = inputValue,
            onValueChange = onInputChange,
            label = { if (hintRes != null) Text(text = stringResource(id = hintRes)) },
            isError =
            if (inputValue == "") false
            else if (validator != null) !validator(inputValue)
            else false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = textBoxContainerColor,
                errorContainerColor = textBoxContainerColor,
                unfocusedContainerColor = textBoxContainerColor,
                cursorColor = textBoxMainColor,
                focusedTextColor = textBoxTextColor,
                unfocusedTextColor = textBoxTextColor,
                focusedIndicatorColor = textBoxMainColor,
                focusedLabelColor = textBoxMainColor
            )
        )
    }
}