package ru.fefu.photo_tests_impl.domain.repositories

import ru.fefu.photo_tests_impl.domain.models.PhotoTestDataModel
import ru.fefu.photo_tests_impl.domain.models.PhotoTestGuide
import ru.fefu.photo_tests_impl.domain.models.PhotoTestItem
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType

interface PhotoTestsRepository {
    fun getPhotoTestItem(photoTestType: PhotoTestType, testNumber: Int): PhotoTestItem

    fun getPhotoTestGuide(photoTestType: PhotoTestType): PhotoTestGuide

}