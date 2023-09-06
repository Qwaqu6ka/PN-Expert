package ru.fefu.photo_tests_impl.data

import ru.fefu.photo_test_impl.R
import ru.fefu.photo_tests_impl.domain.models.PhotoTestDataModel
import ru.fefu.photo_tests_impl.domain.models.PhotoTestTask
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.domain.models.TestPhoto
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsRepository
import javax.inject.Inject

internal class InternalPhotoTestsRepository @Inject constructor():PhotoTestsRepository {

    private val clockPhotoTest: PhotoTestDataModel = PhotoTestDataModel(
        testName = "Нарисвать часы",
        testGuide = "Нарисйте на бумаге часы, на которых будет указано время (необходимое время будет дано при начале теста), сфотографируйте и отправьте полученный результат",
        testGuidePhotos = listOf(
            TestPhoto(R.drawable.photo_test_clock)
        ),
        testTasks = listOf(
            PhotoTestTask(
                taskName = "Нарисовать часы",
                taskMaskPhoto = TestPhoto(R.drawable.photo_test_clock)
            )
        )
    )

    override fun getPhotoTest(photoTestType: PhotoTestType): PhotoTestDataModel {
        return when (photoTestType) {
            is PhotoTestType.ClockPhotoTest -> clockPhotoTest
            is PhotoTestType.FacePhotoTest -> clockPhotoTest
            is PhotoTestType.FullLengthPhotoTest -> clockPhotoTest
            is PhotoTestType.HandwritingPhotoTest -> clockPhotoTest
        }
    }

}