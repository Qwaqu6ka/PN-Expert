package ru.fefu.photo_tests_impl.presentation.photo_test_screen

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ru.fefu.photo_tests_impl.presentation.models.PhotoTestAnswerEntities


class PhotoTestScreenViewModel:ViewModel() {
    private val _photoPath = mutableStateOf(Uri.EMPTY)
    val photoPath: State<Uri> = _photoPath

    private val userAnswers: PhotoTestAnswerEntities = PhotoTestAnswerEntities()

    fun setPhotoPath(photoPath:Uri){
        _photoPath.value = photoPath
    }


}