package ru.fefu.photo_tests_impl.presentation.photo_test_screen

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class PhotoTestScreenViewModel:ViewModel() {
    private val _photoPath = mutableStateOf(Uri.EMPTY)
    val photoPath: State<Uri> = _photoPath

    private val _testPage = mutableIntStateOf(0)
    val testPage: State<Int> = _testPage

    fun setPhotoPath(photoPath:Uri){
        _photoPath.value = photoPath
    }

    fun setTestPage(testPage:Int){
        _testPage.intValue = testPage
    }
}