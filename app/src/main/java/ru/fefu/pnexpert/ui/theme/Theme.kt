package ru.fefu.pnexpert.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

//PN_Expert tokens
data class PnExpertColors(
    //MainAppColors
    val AppDarkColor: Color,
    val AppBlueColor: Color,
    val AppPinkLightColors: Color,
    val AppGreyLightColor: Color ,
    val AppGreyDarkColor: Color,
    val AppBlueLightColor:Color,
    val AppWhiteColor:Color,
    val AppPinkDarkColor:Color,

    //ButtonsColors
    val ButtonNormalBlueColor:Color,
    val ButtonNormalRedColor:Color,

    val ButtonHoverBlueColor:Color,
    val ButtonHoverRedColor:Color,
    val ButtonHoverLightColor:Color,

    val ButtonPressedBlueColor:Color,
    val ButtonPressedRedColor:Color,
    val ButtonPressedLightColor: Color,

    val ButtonInactiveColor:Color,

    //FontColors
    val FontDarkColor:Color,
    val FontBlueColor: Color,
    val FontGreyColor: Color,
    val FontWhiteColor:Color,

    //IconColors
    val IconBlueColor: Color,
    val IconBlueLightColor: Color,
    val IconRedColor:Color,
    val IconPinkColor:Color,
    val IconGreenColor:Color,
    val IconOrangeColor:Color,

    //Gradients
    val GradientPink:Brush,
    val GradientBlue:Brush,
)

val baseAppPalette = PnExpertColors(
    //MainAppColors
    AppDarkColor= Color(0xFF393939),
    AppBlueColor= Color(0xFF5668E8),
    AppPinkLightColors = Color(0xFFF9C9DC),
    AppGreyLightColor= Color(0xFFF4F4F4),
    AppGreyDarkColor= Color(0xFF8B8B8B),
    AppBlueLightColor= Color(0xFF6AEBEB),
    AppWhiteColor = Color(0xFFFFFFFF),
    AppPinkDarkColor = Color(0xFFF25F8E),

    //ButtonsColors
    ButtonNormalBlueColor = Color(0xFF5668E8),
    ButtonNormalRedColor = Color(0xFFF25F8E),

    ButtonHoverBlueColor = Color(0xFF3E50CE),
    ButtonHoverRedColor = Color(0xFFEB4E80),
    ButtonHoverLightColor = Color(0xFFE5E9FF),

    ButtonPressedBlueColor = Color(0xFF2C3CAC),
    ButtonPressedRedColor= Color(0xFFD6376A),
    ButtonPressedLightColor = Color(0xFFC7CEFF),

    ButtonInactiveColor = Color(0xFF929FFF),

    //FontColors
    FontDarkColor= Color(0xFF393939),
    FontBlueColor = Color(0xFF5668E8),
    FontGreyColor = Color(0xFF8B8B8B),
    FontWhiteColor = Color(0xFFFFFFFF),

    //IconColors
    IconBlueColor = Color(0xFF5668E8),
    IconBlueLightColor = Color(0xFF5FABF2),
    IconRedColor = Color(0xFFF24726),
    IconPinkColor = Color(0xFFF25F8E),
    IconGreenColor = Color(0xFF149D17),
    IconOrangeColor = Color(0xFFFA8E10),

    //Gradients
    GradientPink = Brush.linearGradient(
        listOf(Color(0xFF5668E8), Color(0xFFF9C9DC)),
        start = Offset(0.5f, 0f),
        end = Offset(0.5f, 1f)
    ),
    GradientBlue = Brush.linearGradient(
        listOf(Color(0xFF5668E8), Color(0xFF6AEBEB)),
        start = Offset(0f, 0.5f),
        end = Offset(1f, 0.5f)
    )
)

@Composable
fun PNExpertTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

}