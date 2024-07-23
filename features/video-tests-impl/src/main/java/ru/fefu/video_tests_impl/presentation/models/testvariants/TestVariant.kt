package ru.fefu.video_tests_impl.presentation.models.testvariants

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface TestVariant {
    @get:StringRes
    val titleRes: Int

    @get:StringRes
    val instructionRes: Int

    @get:DrawableRes
    val exampleDrawableRes: Int

    val shouldUsePoseAnalyzer: Boolean
}