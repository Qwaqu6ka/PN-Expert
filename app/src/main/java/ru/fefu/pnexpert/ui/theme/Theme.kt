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
    val AppDarkColor: Color = Color(0xFF393939),
    val AppBlueColor: Color = Color(0xFF5668E8),
    val AppPinkLightColors: Color = Color(0xFFF9C9DC),
    val AppGreyLightColor: Color = Color(0xFFF4F4F4),
    val AppGreyDarkColor: Color = Color(0xFF8B8B8B),
    val AppBlueLightColor:Color = Color(0xFF6AEBEB),
    val AppWhiteColor:Color = Color(0xFFFFFFFF),
    val AppPinkDarkColor:Color = Color(0xFFF25F8E),

    //ButtonsColors
    val ButtonNormalBlueColor:Color = Color(0xFF5668E8),
    val ButtonNormalRedColor:Color = Color(0xFFF25F8E),

    val ButtonHoverBlueColor:Color = Color(0xFF3E50CE),
    val ButtonHoverRedColor:Color = Color(0xFFEB4E80),
    val ButtonHoverLightColor:Color = Color(0xFFE5E9FF),

    val ButtonPressedBlueColor:Color = Color(0xFF2C3CAC),
    val ButtonPressedRedColor:Color = Color(0xFFD6376A),
    val ButtonPressedLightColor: Color = Color(0xFFC7CEFF),

    val ButtonInactiveColor:Color = Color(0xFF929FFF),

    //FontColors
    val FontDarkColor:Color = Color(0xFF393939),
    val FontBlueColor: Color = Color(0xFF5668E8),
    val FontGreyColor: Color = Color(0xFF8B8B8B),
    val FontWhiteColor:Color = Color(0xFFFFFFFF),

    //IconColors
    val IconBlueColor: Color = Color(0xFF5668E8),
    val IconBlueLightColor: Color = Color(0xFF5FABF2),
    val IconRedColor:Color = Color(0xFFF24726),
    val IconPinkColor:Color = Color(0xFFF25F8E),
    val IconGreenColor:Color = Color(0xFF149D17),
    val IconOrangeColor:Color = Color(0xFFFA8E10),
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