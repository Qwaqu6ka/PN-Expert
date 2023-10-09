package ru.fefu.photo_tests_impl.domain.use_cases

import android.net.Uri
import ru.fefu.photo_tests_impl.data.InternalPhotoTestsRepository
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsRepository
import javax.inject.Inject

class AddUserAnswerUseCase @Inject constructor(
    private val repository: PhotoTestsRepository
) {
    operator fun invoke(photo: Uri, testNumber:Int){
        repository.newUserAnswer(photo, testNumber)
    }
}