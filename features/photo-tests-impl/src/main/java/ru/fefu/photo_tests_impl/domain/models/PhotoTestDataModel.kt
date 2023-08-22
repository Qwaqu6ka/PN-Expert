package ru.fefu.photo_tests_impl.domain.models

import androidx.compose.ui.graphics.painter.Painter

internal data class PhotoTestDataModel(
    val testName: String,
    val testGuide: String,
    val testGuidePhoto: List<Painter>,
    val testTasks: List<PhotoTestTask>
)

internal data class PhotoTestTask(
    val taskName: String,
    val taskMaskPhoto: Painter?
)