package ru.fefu.video_tests_impl.presentation

import android.app.Application
import android.util.Log
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.fefu.video_tests_impl.domain.TestType
import ru.fefu.video_tests_impl.navigation.ARG_TEST_TYPE
import java.util.concurrent.ExecutionException
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
internal class VideoTestViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    val test: TestType =
        TestType.valueOf(checkNotNull(savedStateHandle[ARG_TEST_TYPE]) { "Test type can not be null" })

    private val _permissionKey = MutableStateFlow(Random.nextLong())
    val permissionKey: StateFlow<Long> = _permissionKey

    private val TAG = "CameraXViewModel"
    private var cameraProviderLiveData: MutableLiveData<ProcessCameraProvider>? = null

    /**
     * Create an instance which interacts with the camera service via the given application context.
     */

    fun getProcessCameraProvider(): LiveData<ProcessCameraProvider> {
        if (cameraProviderLiveData == null) {
            cameraProviderLiveData = MutableLiveData()
            val cameraProviderFuture =
                ProcessCameraProvider.getInstance(getApplication<Application>())
            cameraProviderFuture.addListener(
                {
                    try {
                        cameraProviderLiveData!!.setValue(cameraProviderFuture.get())
                    } catch (e: ExecutionException) {
                        // Handle any errors (including cancellation) here.
                        Log.e(TAG, "Unhandled exception", e)
                    } catch (e: InterruptedException) {
                        Log.e(TAG, "Unhandled exception", e)
                    }
                },
                ContextCompat.getMainExecutor(getApplication<Application>())
            )
        }
        return cameraProviderLiveData!!
    }

    fun launchPermissionRequest() {
        _permissionKey.value = Random.nextLong()
    }
}