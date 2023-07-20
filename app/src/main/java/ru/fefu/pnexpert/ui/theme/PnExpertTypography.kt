package ru.fefu.pnexpert.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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

                fontWeight = FontWeight(600),
            ),
            medium_32 = TextStyle(
                fontSize = 32.sp,
                lineHeight = 43.2.sp,

                fontWeight = FontWeight(500),
                color = Color(0xFF393939),
            )
        )



}