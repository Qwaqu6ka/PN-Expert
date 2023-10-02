package ru.fefu.photo_tests_impl.presentation.photo_test_screen

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.domain.use_cases.GetTestDataItem


class PhotoTestScreenViewModel @AssistedInject constructor(
    private val getTestDataItem: GetTestDataItem,
    @Assisted private val testType: PhotoTestType
):ViewModel() {
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

    @AssistedFactory
    interface Factory {
        fun create(testType: PhotoTestType): PhotoTestScreenViewModel
    }
}