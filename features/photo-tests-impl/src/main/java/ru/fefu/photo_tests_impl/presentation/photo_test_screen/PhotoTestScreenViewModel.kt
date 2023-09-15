package ru.fefu.photo_tests_impl.presentation.photo_test_screen

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PhotoTestScreenViewModel:ViewModel() {
    private val _photoPath = mutableStateOf(Uri.EMPTY)
    val photoPath: State<Uri> = _photoPath

    fun setPhotoPath(photoPath:Uri){
        _photoPath.value = photoPath
    }
}