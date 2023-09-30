package ru.fefu.photo_tests_impl.domain.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.painter.Painter

data class PhotoTestDataModel(
    val testName: String,
    val testGuide: String,
    val testGuidePhotos: List<TestPhoto>,
    val testTasks: List<PhotoTestTask>
){
    fun receivedPhotoTest(testNumber: Int): PhotoTestItem{
        if (testNumber < 1 || testNumber > testTasks.size)
            throw IllegalArgumentException()

        return PhotoTestItem(
            testGuidePhoto = this.testGuidePhotos[testNumber-1],
            testTask = this.testTasks[testNumber-1]
        )
    }

    fun receivedTestGuide():PhotoTestGuide{
        return PhotoTestGuide(
            testName = this.testName,
            testGuide = this.testGuide,
            testGuidePhotos = this.testGuidePhotos
        )
    }
}

data class PhotoTestItem(
    val testGuidePhoto: TestPhoto,
    val testTask: PhotoTestTask
)

data class PhotoTestGuide(
    val testName: String,
    val testGuide: String,
    val testGuidePhotos: List<TestPhoto>,
)

data class TestPhoto(
    @get:DrawableRes val drawableId: Int
)

data class PhotoTestTask(
    val taskName: String,
    val taskMaskPhoto: TestPhoto?
)