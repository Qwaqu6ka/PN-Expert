package ru.fefu.pnexpert.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import java.time.format.TextStyle

//PN_Expert tokens
data class PnExpertColors(
    //MainAppColors
    val AppDarkColor: Color,
    val AppBlueColor: Color,
    val AppPinkLightColors: Color,
    val AppGreyLightColor: Color,
    val AppGreyDarkColor: Color,
    val AppBlueLightColor: Color,
    val AppWhiteColor: Color,
    val AppPinkDarkColor: Color,

    //ButtonsColors
    val ButtonNormalBlueColor: Color,
    val ButtonNormalRedColor: Color,

    val ButtonHoverBlueColor: Color,
    val ButtonHoverRedColor: Color,
    val ButtonHoverLightColor: Color,

    val ButtonPressedBlueColor: Color,
    val ButtonPressedRedColor: Color,
    val ButtonPressedLightColor: Color,

    val ButtonInactiveColor: Color,

    //FontColors
    val FontDarkColor: Color,
    val FontBlueColor: Color,
    val FontGreyColor: Color,
    val FontWhiteColor: Color,

    //IconColors
    val IconBlueColor: Color,
    val IconBlueLightColor: Color,
    val IconRedColor: Color,
    val IconPinkColor: Color,
    val IconGreenColor: Color,
    val IconOrangeColor: Color,

    //Gradients
    val GradientPink: Brush,
    val GradientBlue: Brush,
)

data class PnExpertTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val toolbar: TextStyle,
)



object PnExpertTheme{
    val colors:PnExpertColors
        @Composable
        get() = LocalPnExpertColors.current

    val typography:PnExpertTypography
        @Composable
        get() = LocalPnExpertTypography.current



    val LocalPnExpertColors = staticCompositionLocalOf<PnExpertColors> {
        error("No colors provided")
    }

    val LocalPnExpertTypography = staticCompositionLocalOf<PnExpertTypography> {
        error("No typography provided")
    }
}