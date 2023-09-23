package ru.fefu.photo_tests_impl.presentation.camera_screen

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.fefu.photo_tests_impl.data.CustomCameraRepository
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsCameraRepository
import javax.inject.Inject

@HiltViewModel
class CameraScreenViewModel @Inject constructor(
    private val cameraRepository: PhotoTestsCameraRepository
):ViewModel() {

    private val _photoUri = mutableStateOf(Uri.EMPTY)
    val photoUri:MutableState<Uri> = _photoUri

    private val _barcodeData = MutableStateFlow("")
    val barcodeData:StateFlow<String> = _barcodeData


    fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ){
        viewModelScope.launch {
            cameraRepository.showCameraPreview(
                previewView,
                lifecycleOwner,
                true
            ) { barcodeScanner: BarcodeScanner, imageProxy: ImageProxy ->
                processImageProxyListener(barcodeScanner, imageProxy)
            }
        }
    }

    fun captureAndSave(context: Context){
        viewModelScope.launch {
            cameraRepository.captureAndSaveImage(context, _photoUri)
        }
    }

    fun cleanPhotoUri(){
        _photoUri.value = Uri.EMPTY
    }

    @OptIn(ExperimentalGetImage::class)
    private fun processImageProxyListener(
        barcodeScanner: BarcodeScanner,
        imageProxy: ImageProxy
    ) {
        val inputImage =
            InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)

        barcodeScanner.process(inputImage)
            .addOnSuccessListener { barcodes ->
                barcodes.forEach { barcode ->
                    val barcodeValue = barcode.rawValue

                    if (barcodeValue != null){
                        _barcodeData.value = barcodeValue
                        println(barcodeValue)
                    }
                    else {
                        _barcodeData.value = ""
                        println("------------NO BARCODE------------")
                    }
                }
            }
            .addOnFailureListener {
                Log.e(ContentValues.TAG, it.message ?: it.toString())
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }

}