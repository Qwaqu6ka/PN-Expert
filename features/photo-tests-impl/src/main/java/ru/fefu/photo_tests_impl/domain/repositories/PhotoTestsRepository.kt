package ru.fefu.photo_tests_impl.domain.repositories

import android.net.Uri
import ru.fefu.photo_tests_impl.domain.models.PhotoTestAnswer
import ru.fefu.photo_tests_impl.domain.models.PhotoTestAnswerForReading
import ru.fefu.photo_tests_impl.domain.models.PhotoTestDataModel
import ru.fefu.photo_tests_impl.domain.models.PhotoTestGuide
import ru.fefu.photo_tests_impl.domain.models.PhotoTestItem
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType

interface PhotoTestsRepository {
    fun getPhotoTestItem(photoTestType: PhotoTestType, testNumber: Int): PhotoTestItem

    fun getPhotoTestGuide(photoTestType: PhotoTestType): PhotoTestGuide

    fun getUserAnswers():PhotoTestAnswerForReading

    fun newUserAnswer(testTask: String, photo: Uri, answerNumber: Int)

}