package ru.fefu.photo_tests_impl.domain.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class PhotoTestModel(
    @StringRes val testTitle: Int,
    @StringRes val testGuide: Int,
    val testTasks: List<PhotoTestTask>
)

@Immutable
data class PhotoTestTask(
    @StringRes val taskDescriprion: Int,
    @DrawableRes val taskPhotoExample: Int
)