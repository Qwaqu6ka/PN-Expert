package ru.fefu.pnexpert.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

val shapesPalette = PnExpertShapes().apply {
    this.mainShapes =
        PnExpertShapes.MainShapes(
            appDefault10 = RoundedCornerShape(10.dp)
        )

    this.buttonShapes =
        PnExpertShapes.ButtonShapes(
            buttonClassic10 = RoundedCornerShape(10.dp)
        )
}