package ru.fefu.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import ru.fefu.theme.PnExpertTheme.LocalPnExpertColors
import ru.fefu.theme.PnExpertTheme.LocalPnExpertShapes
import ru.fefu.theme.PnExpertTheme.LocalPnExpertSizes
import ru.fefu.theme.PnExpertTheme.LocalPnExpertTypography

@Composable
fun ApplicationTheme(
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