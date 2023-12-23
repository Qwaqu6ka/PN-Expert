package ru.fefu.photo_tests_impl.domain.use_cases

import ru.fefu.photo_tests_impl.domain.models.PhotoTestGuide
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsRepository
import javax.inject.Inject

class GetGuideDataUseCase @Inject constructor(
    private val repository: PhotoTestsRepository
) {
    operator fun invoke(testType: PhotoTestType): PhotoTestGuide{
        return repository.getPhotoTestGuide(testType)
    }
}