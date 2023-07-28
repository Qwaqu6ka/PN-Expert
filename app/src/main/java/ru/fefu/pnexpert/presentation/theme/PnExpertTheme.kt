package ru.fefu.pnexpert.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme.LocalPnExpertColors
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme.LocalPnExpertShapes
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme.LocalPnExpertSizes
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme.LocalPnExpertTypography

@Composable
fun PnExpertTheme(
    content: @Composable () -> Unit
){
    val colors = baseAppPalette
    val typography = typographyPalette
    val shapes = shapesPalette
    val sizes = sizesPalette

    CompositionLocalProvider (
        LocalPnExpertColors provides colors,
        LocalPnExpertTypography provides typography,
        LocalPnExpertShapes provides  shapes,
        LocalPnExpertSizes provides  sizes,
        content = content
    )
}