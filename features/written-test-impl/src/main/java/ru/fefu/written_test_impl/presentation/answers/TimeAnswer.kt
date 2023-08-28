package ru.fefu.written_test_impl.presentation.answers

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import ru.fefu.written_test_impl.R

@Composable
fun TimeAnswer(time: String?, onTimeChange: (String) -> Unit, modifier: Modifier = Modifier) {

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, hour: Int, minute: Int ->
            onTimeChange("$hour:$minute")
        }, 0, 0, true
    )

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.picked_time))
        Text(text = time ?: "?")
        Button(onClick = { timePickerDialog.show() }) {
            Text(text = stringResource(id = R.string.pick_time))
        }
    }
}