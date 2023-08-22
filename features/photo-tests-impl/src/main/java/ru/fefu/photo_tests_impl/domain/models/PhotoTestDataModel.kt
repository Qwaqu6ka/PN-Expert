package ru.fefu.photo_tests_impl.domain.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.painter.Painter

internal data class PhotoTestDataModel(
    val testName: String,
    val testGuide: String,
    val testGuidePhotos: List<TestPhoto>,
    val testTasks: List<PhotoTestTask>
)

internal data class TestPhoto(
    @get:DrawableRes val drawableId: Int
)

internal data class PhotoTestTask(
    val taskName: String,
    val taskMaskPhoto: TestPhoto?
)