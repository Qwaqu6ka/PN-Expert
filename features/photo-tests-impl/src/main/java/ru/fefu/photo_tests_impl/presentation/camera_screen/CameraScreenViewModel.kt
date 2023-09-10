package ru.fefu.photo_tests_impl.presentation.camera_screen

import android.content.Context
import android.net.Uri
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.fefu.photo_tests_impl.data.CustomCameraRepository
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsCameraRepository
import javax.inject.Inject

@HiltViewModel
class CameraScreenViewModel @Inject constructor(
    private val cameraRepository: PhotoTestsCameraRepository
):ViewModel() {

    private var _photoUri = mutableStateOf(Uri.EMPTY)
    val photoUri:MutableState<Uri> = _photoUri

    fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ){
        viewModelScope.launch {
            cameraRepository.showCameraPreview(
                previewView,
                lifecycleOwner
            )
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

}