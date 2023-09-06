package ru.fefu.photo_tests_impl.domain.repositories

import ru.fefu.photo_tests_impl.domain.models.PhotoTestDataModel
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType

interface PhotoTestsRepository {
    fun getPhotoTest(photoTestType: PhotoTestType): PhotoTestDataModel
}