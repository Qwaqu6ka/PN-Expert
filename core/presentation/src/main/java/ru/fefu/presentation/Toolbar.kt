package ru.fefu.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.fefu.theme.PnExpertTheme

@Composable
fun Toolbar(title: String, isInverseColor: Boolean = false, onBackPressed: (() -> Unit)? = null) {

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.statusBarDarkContentEnabled = true
    }

    val textColor = if (isInverseColor) PnExpertTheme.colors.textColors.FontWhiteColor
    else PnExpertTheme.colors.textColors.FontDarkColor
    val iconColor = if (isInverseColor) PnExpertTheme.colors.mainAppColors.AppWhiteColor
    else PnExpertTheme.colors.mainAppColors.AppBlueColor
    Box(
        Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(horizontal = 16.dp)
            .windowInsetsPadding(WindowInsets.statusBars)
            .defaultMinSize(minHeight = 50.dp)
    ) {
        if (onBackPressed != null) {
            IconButton(onClick = onBackPressed) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(id = R.string.back_button),
                    tint = iconColor
                )
            }
        }
        Text(
            text = title,
            style = PnExpertTheme.typography.subtitle.bold_18,
            color = textColor,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 55.dp)
        )
    }
}

@Preview
@Composable
fun ToolbarPreviewBack() {
    PnExpertTheme {
        Surface(color = Color.White, modifier = Modifier.fillMaxWidth()) {
            Toolbar(
                title = "Программtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr",
                onBackPressed = {}
            )
        }
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    PnExpertTheme {
        Surface(color = Color.White, modifier = Modifier.fillMaxWidth()) {
            Toolbar(
                title = "Программtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr",
            )
        }
    }
}

@Preview
@Composable
fun ToolbarDarkPreviewBack() {
    PnExpertTheme {
        Surface(color = Color.Black, modifier = Modifier.fillMaxWidth()) {
            Toolbar(title = "Мероприятия", isInverseColor = true, onBackPressed = {})
        }
    }
}

@Preview
@Composable
fun ToolbarDarkPreview() {
    PnExpertTheme {
        Surface(color = Color.Black, modifier = Modifier.fillMaxWidth()) {
            Toolbar(title = "Мероприятия", isInverseColor = true)
        }
    }
}