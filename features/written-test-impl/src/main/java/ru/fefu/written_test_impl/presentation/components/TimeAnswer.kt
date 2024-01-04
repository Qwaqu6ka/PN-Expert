package ru.fefu.written_test_impl.presentation.components

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.fefu.components.PNExpertTextButton
import ru.fefu.theme.PnExpertTheme
import ru.fefu.written_test_impl.R

@Composable
internal fun TimeAnswer(time: String?, onTimeChange: (String) -> Unit, modifier: Modifier = Modifier) {

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, hour: Int, minute: Int ->
            onTimeChange(intsToTime(hour, minute))
        }, 0, 0, true
    )

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.picked_time),
            style = PnExpertTheme.typography.text.medium_16
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = time ?: "?", style = PnExpertTheme.typography.text.medium_16)
        Spacer(modifier = Modifier.height(10.dp))
        PNExpertTextButton(
            onClick = { timePickerDialog.show() },
            text = stringResource(id = R.string.pick_time)
        )
    }
}

private fun intsToTime(hour: Int, minute: Int): String {
    var hourStr = hour.toString()
    if (hour < 10)
        hourStr = "0$hourStr"
    var minuteStr = minute.toString()
    if (minute < 10)
        minuteStr = "0$minuteStr"
    return "$hourStr:$minuteStr"
}