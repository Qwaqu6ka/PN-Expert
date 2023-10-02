package ru.fefu.photo_tests_impl.presentation.photo_test_screen

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import ru.fefu.photo_tests_impl.domain.models.PhotoTestItem
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.domain.use_cases.GetTestDataItem


class PhotoTestScreenViewModel @AssistedInject constructor(
    private val getTestDataItem: GetTestDataItem,
    @Assisted private val testType: PhotoTestType
):ViewModel() {
    private val _photoPath = mutableStateOf(Uri.EMPTY)
    val photoPath: State<Uri> = _photoPath

    private val _testPage = mutableStateOf<Int?>(null)
    val testPage: State<Int?> = _testPage

    private var  _testData = mutableStateOf<PhotoTestItem?>(null)
    val testData:State<PhotoTestItem?> = _testData

    fun setPhotoPath(photoPath:Uri){
        _photoPath.value = photoPath
    }

    fun setTestPage(testPage:Int){
        _testPage.value = testPage
        _testData.value = getTestData(testType, _testPage.value!!)
    }

    private fun getTestData(testType:PhotoTestType, testNumber:Int): PhotoTestItem {
        return getTestDataItem(testType, testNumber)
    }

    @AssistedFactory
    interface Factory {
        fun create(testType: PhotoTestType): PhotoTestScreenViewModel
    }
}