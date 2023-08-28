package ru.fefu.written_test_impl.presentation.answers

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import ru.fefu.written_test_impl.entities.testentities.InputQuestionValidator

@Composable
fun InputAnswer(
    inputValue: String?,
    onInputChange: (String) -> Unit,
    @StringRes hintRes: Int?,
    validator: InputQuestionValidator?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = inputValue ?: "",
            onValueChange = onInputChange,
            label = { if (hintRes != null) Text(text = stringResource(id = hintRes)) },
            isError =
            if (inputValue == null || inputValue == "") false
            else if (validator != null) !validator(inputValue)
            else false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
    }
}