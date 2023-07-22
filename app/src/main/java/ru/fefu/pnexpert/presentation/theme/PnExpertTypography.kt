package ru.fefu.pnexpert.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.fefu.pnexpert.R

private val SfProDisplay = FontFamily(
    Font(R.font.sf_pro_display_semibold, FontWeight.W600),
    Font(R.font.sf_pro_display_medium, FontWeight.W500),
    Font(R.font.sf_pro_display_regular, FontWeight.W400),
)

val typographyPalette = PnExpertTypography().apply {

    this.title =
        PnExpertTypography.Title(
            bold_32 = TextStyle(
                fontSize = 32.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(600),
            ),
            medium_32 = TextStyle(
                fontSize = 32.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(500),
            )
        )

    this.subtitle =
        PnExpertTypography.Subtitle(
            medium_24 = TextStyle(
                fontSize = 24.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(500),
            ),
            bold_20 = TextStyle(
                fontSize = 20.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(600),
            ),
            bold_18 = TextStyle(
                fontSize = 18.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(600),
            ),
            medium_18 = TextStyle(
                fontSize = 18.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(500),
            ),
        )

    this.text =
        PnExpertTypography.Text(
            medium_16 = TextStyle(
                fontSize = 16.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(500),
            ),
            regular_16 = TextStyle(
                fontSize = 16.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(400),
            ),
            medium_14 = TextStyle(
                fontSize = 14.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(500),
            ),
            regular_14 = TextStyle(
                fontSize = 16.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(400),
            ),
            medium_12 = TextStyle(
                fontSize = 12.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(500),
            ),
            regular_12 = TextStyle(
                fontSize = 12.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(400),
            ),
            medium_11 = TextStyle(
                fontSize = 11.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight(500),
            ),
        )
}