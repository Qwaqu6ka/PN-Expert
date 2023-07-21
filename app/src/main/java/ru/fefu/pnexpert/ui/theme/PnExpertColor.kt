package ru.fefu.pnexpert.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val baseAppPalette = PnExpertColors().apply {
    this.mainAppColors =
        PnExpertColors.MainAppColors(
            AppDarkColor = Color(0xFF393939),
            AppBlueColor = Color(0xFF5668E8),
            AppPinkLightColors = Color(0xFFF9C9DC),
            AppGreyLightColor = Color(0xFFF4F4F4),
            AppGreyDarkColor = Color(0xFF8B8B8B),
            AppBlueLightColor = Color(0xFF6AEBEB),
            AppWhiteColor = Color(0xFFFFFFFF),
            AppPinkDarkColor = Color(0xFFF25F8E),
        )
    this.buttonColors =
        PnExpertColors.ButtonColors(
            ButtonNormalRedColor = Color(0xFFF25F8E),
            ButtonNormalBlueColor = Color(0xFF5668E8),

            ButtonHoverBlueColor = Color(0xFF3E50CE),
            ButtonHoverRedColor = Color(0xFFEB4E80),
            ButtonHoverLightColor = Color(0xFFE5E9FF),

            ButtonPressedBlueColor = Color(0xFF2C3CAC),
            ButtonPressedRedColor = Color(0xFFD6376A),
            ButtonPressedLightColor = Color(0xFFC7CEFF),

            ButtonInactiveColor = Color(0xFF929FFF),
        )
    this.textColors =
        PnExpertColors.TextColors(
            FontDarkColor = Color(0xFF393939),
            FontBlueColor = Color(0xFF5668E8),
            FontGreyColor = Color(0xFF8B8B8B),
            FontWhiteColor = Color(0xFFFFFFFF),
        )
    this.iconColors =
        PnExpertColors.IconColors(
            IconBlueColor = Color(0xFF5668E8),
            IconBlueLightColor = Color(0xFF5FABF2),
            IconRedColor = Color(0xFFF24726),
            IconPinkColor = Color(0xFFF25F8E),
            IconGreenColor = Color(0xFF149D17),
            IconOrangeColor = Color(0xFFFA8E10),
        )
    this.gradients =
        PnExpertColors.Gradients(
            GradientPink = Brush.verticalGradient(
                listOf(Color(0xFF5668E8), Color(0xFFF9C9DC)),
                startY = 0.0f,
                endY = 700.0f
            ),
            GradientBlue = Brush.horizontalGradient(
                listOf(Color(0xFF5668E8), Color(0xFF6AEBEB)),
            )
        )
}
