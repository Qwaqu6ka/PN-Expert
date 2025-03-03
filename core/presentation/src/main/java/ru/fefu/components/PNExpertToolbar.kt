package ru.fefu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import ru.fefu.presentation.R
import ru.fefu.theme.PnExpertTheme
import ru.fefu.theme.ApplicationTheme

@Composable
fun PNExpertToolbar(
    title: String,
    isInverseColor: Boolean = false,
    onBackPressed: (() -> Unit)? = null
) {

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
            .background(PnExpertTheme.colors.mainAppColors.AppWhiteColor)
            .padding(horizontal = 16.dp)
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
                .padding(bottom = 6.dp)
        )
    }
}

@Preview
@Composable
fun ToolbarPreviewBack() {
    ApplicationTheme {
        Surface(color = Color.White, modifier = Modifier.fillMaxWidth()) {
            PNExpertToolbar(
                title = "Программtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr"
            ) {}
        }
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    ApplicationTheme {
        Surface(color = Color.White, modifier = Modifier.fillMaxWidth()) {
            PNExpertToolbar(
                title = "Программtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr",
            )
        }
    }
}

@Preview
@Composable
fun ToolbarDarkPreviewBack() {
    ApplicationTheme {
        Surface(color = Color.Black, modifier = Modifier.fillMaxWidth()) {
            PNExpertToolbar(title = "Мероприятия", isInverseColor = true) {}
        }
    }
}

@Preview
@Composable
fun ToolbarDarkPreview() {
    ApplicationTheme {
        Surface(color = Color.Black, modifier = Modifier.fillMaxWidth()) {
            PNExpertToolbar(title = "Мероприятия", isInverseColor = true)
        }
    }
}