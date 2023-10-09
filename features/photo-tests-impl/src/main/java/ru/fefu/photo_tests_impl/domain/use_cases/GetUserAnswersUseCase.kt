package ru.fefu.photo_tests_impl.domain.use_cases

import android.net.Uri
import ru.fefu.photo_tests_impl.domain.models.PhotoTestAnswerForReading
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsRepository
import javax.inject.Inject

class GetUserAnswersUseCase @Inject constructor(
    private val repository: PhotoTestsRepository
) {
    operator fun invoke():PhotoTestAnswerForReading{
        return repository.getUserAnswers()
    }
}