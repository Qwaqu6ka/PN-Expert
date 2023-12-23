package ru.fefu.photo_tests_impl.presentation.photo_test_screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v4.os.IResultReceiver._Parcel
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.Coil
import coil.ImageLoader
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.fefu.photo_tests_impl.domain.models.PhotoTestItem
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.domain.use_cases.AddUserAnswerUseCase
import ru.fefu.photo_tests_impl.domain.use_cases.GetTestDataItem
import ru.fefu.photo_tests_impl.domain.use_cases.GetUserAnswersUseCase
import ru.fefu.photo_tests_impl.utils.PhotoBarcodeScanner

private const val SUCCESS_BARCODE_VALUE = "testValue"

class PhotoTestScreenViewModel @AssistedInject constructor(
    private val getTestDataItem: GetTestDataItem,
    private val addUserAnswerUseCase: AddUserAnswerUseCase,
    private val getUserAnswerUseCase: GetUserAnswersUseCase,
    private val qrScanner: PhotoBarcodeScanner,
    @Assisted private val testType: PhotoTestType
):ViewModel() {
    private val _photoPath = mutableStateOf(Uri.EMPTY)
    val photoPath: State<Uri> = _photoPath

    private val _testPage = mutableStateOf<Int?>(null)
    val testPage: State<Int?> = _testPage

    private var  _testData = mutableStateOf<PhotoTestItem?>(null)
    val testData:State<PhotoTestItem?> = _testData

    private var _isLastTest =  mutableStateOf<Boolean?>(null)
    val isLastTest: State<Boolean?> = _isLastTest

    private var _isQrSuccess = mutableStateOf<Boolean?>(null)
    val isQrSuccess: State<Boolean?> = _isQrSuccess

    private var uriBitmap = mutableStateOf<Bitmap?>(null)

    fun setPhotoPath(photoPath:Uri){
        _photoPath.value = photoPath
    }

    fun addAnswer(){
        println(getUserAnswerUseCase().userAnswer)
        addUserAnswerUseCase(_testData.value!!.testTask.taskName, _photoPath.value, _testPage.value!!)
    }

    fun setTestPage(testPage:Int){
        _testPage.value = testPage
        _testData.value = getTestData(testType, _testPage.value!!)
        _isLastTest.value = _testData.value!!.isLastTask
    }

    fun photoIsSuccess(context: Context){
        if (_photoPath.value != Uri.EMPTY){
            uriToBitmap(_photoPath.value, context)
            if (uriBitmap.value != null)
                _isQrSuccess.value = checkQrCode(uriBitmap.value!!)
        }
    }

    private fun checkQrCode(bitmap: Bitmap): Boolean{
        return when(qrScanner.readQRCode(bitmap)){
            SUCCESS_BARCODE_VALUE -> true
            else -> false
        }
    }

    private fun uriToBitmap(
        imageURI: Uri,
        context: Context,
    ) {
        var bitmap: Bitmap? = null
        val loadBitmap = viewModelScope.launch(Dispatchers.IO) {
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageURI)
                .allowHardware(false)
                .build()
            val result = loader.execute(request)
            if (result is SuccessResult) {
                bitmap = (result.drawable as BitmapDrawable).bitmap
            } else if (result is ErrorResult) {
                cancel(result.throwable.localizedMessage ?: "ErrorResult", result.throwable)
            }
        }
        loadBitmap.invokeOnCompletion { throwable ->
            bitmap?.let {
                uriBitmap.value = it
            } ?: throwable?.let {
                uriBitmap.value = null
            }
        }
    }

    private fun getTestData(testType:PhotoTestType, testNumber:Int): PhotoTestItem {
        return getTestDataItem(testType, testNumber)
    }


    @AssistedFactory
    interface Factory {
        fun create(testType: PhotoTestType): PhotoTestScreenViewModel
    }
}