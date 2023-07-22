package ru.fefu.pnexpert.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

val shapesPalette = PnExpertShapes().apply {
    this.buttonShapes =
        PnExpertShapes.ButtonShapes(
            buttonClassic10 = RoundedCornerShape(10.dp)
        )
}