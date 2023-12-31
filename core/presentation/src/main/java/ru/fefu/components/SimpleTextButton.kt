package ru.fefu.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.fefu.theme.PnExpertTheme

@Composable
fun SimpleTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        colors = ButtonDefaults.buttonColors(
            containerColor = PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor
        )
    ) {
        Text(text = text)
    }
}