package ru.fefu.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp


class PnExpertColors{
    lateinit var mainAppColors: MainAppColors
    lateinit var buttonColors: ButtonColors
    lateinit var textColors: TextColors
    lateinit var iconColors: IconColors
    lateinit var gradients: Gradients

    //MainAppColors
    data class MainAppColors(
        val AppDarkColor: Color,
        val AppBlueColor: Color,
        val AppPinkLightColors: Color,
        val AppGreyLightColor: Color,
        val AppGreyDarkColor: Color,
        val AppBlueLightColor: Color,
        val AppWhiteColor: Color,
        val AppPinkDarkColor: Color,
    )

    //ButtonsColors
    data class ButtonColors(
        val ButtonNormalBlueColor: Color,
        val ButtonNormalRedColor: Color,

        val ButtonHoverBlueColor: Color,
        val ButtonHoverRedColor: Color,
        val ButtonHoverLightColor: Color,

        val ButtonPressedBlueColor: Color,
        val ButtonPressedRedColor: Color,
        val ButtonPressedLightColor: Color,

        val ButtonInactiveColor: Color,
    )

    data class TextColors(
        val FontDarkColor: Color,
        val FontBlueColor: Color,
        val FontGreyColor: Color,
        val FontWhiteColor: Color,
    )

    data class IconColors(
        val IconBlueColor: Color,
        val IconBlueLightColor: Color,
        val IconRedColor: Color,
        val IconPinkColor: Color,
        val IconGreenColor: Color,
        val IconOrangeColor: Color,
    )

    data class Gradients(
        val GradientPink: Brush,
        val GradientBlue: Brush,
    )
}


class PnExpertTypography{
    lateinit var title: Title
    lateinit var subtitle: Subtitle
    lateinit var text: Text

    data class Title(
        val bold_32: TextStyle,
        val medium_32:TextStyle
    )

    data class Subtitle(
        val medium_24: TextStyle,
        val bold_20:TextStyle,
        val bold_18:TextStyle,
        val medium_18: TextStyle
    )

    data class Text(
        val medium_16: TextStyle,
        val regular_16:TextStyle,
        val medium_14: TextStyle,
        val regular_14:TextStyle,
        val medium_12: TextStyle,
        val regular_12:TextStyle,
        val medium_11: TextStyle,
    )
}

class PnExpertSizes{
    lateinit var buttonSize: ButtonSize

    data class ButtonSize(
        val buttonClassic55: Dp
    )
}

class PnExpertShapes{
    lateinit var buttonShapes: ButtonShapes
    lateinit var mainShapes: MainShapes
    lateinit var imageShapes: ImageShapes

    data class MainShapes(
        val appDefault10:Shape
    )

    data class ButtonShapes(
        val buttonClassic10: Shape
    )

    data class ImageShapes(
        val imageClassic15: Shape
    )
}


object PnExpertTheme{
    val colors: PnExpertColors
        @Composable
        get() = LocalPnExpertColors.current

    val typography: PnExpertTypography
        @Composable
        get() = LocalPnExpertTypography.current

    val shapes: PnExpertShapes
        @Composable
        get() = LocalPnExpertShapes.current

    val sizes: PnExpertSizes
        @Composable
        get() = LocalPnExpertSizes.current



    val LocalPnExpertColors = staticCompositionLocalOf<PnExpertColors> {
        error("No colors provided")
    }

    val LocalPnExpertTypography = staticCompositionLocalOf<PnExpertTypography> {
        error("No typography provided")
    }

    val LocalPnExpertShapes = staticCompositionLocalOf<PnExpertShapes> {
        error("No shapes provided")
    }

    val LocalPnExpertSizes = staticCompositionLocalOf<PnExpertSizes> {
        error("No sizes provided")
    }
}