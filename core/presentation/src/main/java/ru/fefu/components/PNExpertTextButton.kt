package ru.fefu.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.fefu.theme.PnExpertTheme

@Composable
fun PNExpertTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    alternativeColor: Boolean = false,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        colors =
        if (alternativeColor) {
            ButtonDefaults.buttonColors(
                containerColor = PnExpertTheme.colors.buttonColors.ButtonNormalRedColor,
                disabledContainerColor = PnExpertTheme.colors.buttonColors.ButtonNormalRedColor.copy(alpha = 0.5f),
                contentColor = PnExpertTheme.colors.textColors.FontWhiteColor,
                disabledContentColor = PnExpertTheme.colors.textColors.FontWhiteColor
            )
        } else {
            ButtonDefaults.buttonColors(
                containerColor = PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor,
                disabledContainerColor = PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor.copy(alpha = 0.5f),
                contentColor = PnExpertTheme.colors.textColors.FontWhiteColor,
                disabledContentColor = PnExpertTheme.colors.textColors.FontWhiteColor
            )
        }
    ) {
        Text(
            text = text,
            style = PnExpertTheme.typography.subtitle.medium_18,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }
}