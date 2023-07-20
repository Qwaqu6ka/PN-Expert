package ru.fefu.pnexpert.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import ru.fefu.pnexpert.ui.theme.PnExpertTheme.LocalPnExpertColors

@Composable
fun PnExpertTheme(
    content: @Composable () -> Unit
){
    val colors = baseAppPalette


    CompositionLocalProvider (
        LocalPnExpertColors provides colors,
        content = content
    )
}