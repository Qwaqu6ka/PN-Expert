package ru.fefu.photo_tests_impl.domain.use_cases

import ru.fefu.photo_tests_impl.domain.models.PhotoTestDataModel
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsRepository
import javax.inject.Inject

class GetTestDataUseCase @Inject constructor(
    private val repository: PhotoTestsRepository
) {
    operator fun invoke(testType: PhotoTestType): PhotoTestDataModel{
        return repository.getPhotoTest(testType)
    }
}