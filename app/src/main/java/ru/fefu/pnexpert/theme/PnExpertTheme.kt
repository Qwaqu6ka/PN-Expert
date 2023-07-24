package ru.fefu.pnexpert.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import ru.fefu.pnexpert.theme.PnExpertTheme.LocalPnExpertColors
import ru.fefu.pnexpert.theme.PnExpertTheme.LocalPnExpertTypography

@Composable
fun PnExpertTheme(
    content: @Composable () -> Unit
){
    val colors = baseAppPalette
    val typography = typographyPalette

    CompositionLocalProvider (
        LocalPnExpertColors provides colors,
        LocalPnExpertTypography provides typography,
        content = content
    )
}