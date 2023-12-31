package ru.fefu.photo_tests_impl.domain.repositories

import android.net.Uri
import kotlinx.coroutines.flow.StateFlow
import ru.fefu.photo_tests_impl.domain.models.PhotoTestModel
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType

interface PhotoTestsRepository {
    fun getPhotoTest(photoTestType: PhotoTestType): PhotoTestModel
    fun getAnswers(): StateFlow<List<Uri>>
    fun setAnswer(answerIndex: Int, photo: Uri)
}