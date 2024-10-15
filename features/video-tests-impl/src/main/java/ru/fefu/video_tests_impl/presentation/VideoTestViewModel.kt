package ru.fefu.video_tests_impl.presentation

import android.app.Application
import androidx.camera.view.PreviewView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.fefu.camera.presentation.GraphicOverlay
import ru.fefu.video_tests_impl.domain.CameraXRepository
import ru.fefu.video_tests_impl.domain.TestType
import ru.fefu.video_tests_impl.navigation.ARG_TEST_TYPE
import ru.fefu.video_tests_impl.utils.PoseDetectorProcessor
import kotlin.random.Random

internal class VideoTestViewModel(
    private val application: Application,
    savedStateHandle: SavedStateHandle,
    private val cameraXRepository: CameraXRepository
) : AndroidViewModel(application) {

    val test: TestType =
        TestType.valueOf(checkNotNull(savedStateHandle[ARG_TEST_TYPE]) { "Test type can not be null" })


    private val _permissionKey = MutableStateFlow(Random.nextLong())
    val permissionKey: StateFlow<Long> = _permissionKey

//    private val TAG = "CameraXViewModel"
//    private var cameraProviderLiveData: MutableLiveData<ProcessCameraProvider>? = null
//
//    /**
//     * Create an instance which interacts with the camera service via the given application context.
//     */
//
//    fun getProcessCameraProvider(): LiveData<ProcessCameraProvider> {
//        if (cameraProviderLiveData == null) {
//            cameraProviderLiveData = MutableLiveData()
//            val cameraProviderFuture =
//                ProcessCameraProvider.getInstance(getApplication<Application>())
//            cameraProviderFuture.addListener(
//                {
//                    try {
//                        cameraProviderLiveData!!.setValue(cameraProviderFuture.get())
//                    } catch (e: ExecutionException) {
//                        // Handle any errors (including cancellation) here.
//                        Log.e(TAG, "Unhandled exception", e)
//                    } catch (e: InterruptedException) {
//                        Log.e(TAG, "Unhandled exception", e)
//                    }
//                },
//                ContextCompat.getMainExecutor(getApplication<Application>())
//            )
//        }
//        return cameraProviderLiveData!!
//    }

    fun launchPermissionRequest() {
        _permissionKey.value = Random.nextLong()
    }

    fun clearCamera() = cameraXRepository.clearCamera()

    fun bindPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner) =
        cameraXRepository.bindPreview(previewView, lifecycleOwner)

    fun bindGraphicOverlay(graphicOverlay: GraphicOverlay) {
        cameraXRepository.setGraphicOverlay(graphicOverlay)
        cameraXRepository.addPoseAnalyzer(
            PoseDetectorProcessor(
                context = application.applicationContext,
                options = PoseDetectorOptions.Builder()
                    .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
                    .build(),
                showInFrameLikelihood = true,
                visualizeZ = true,
                rescaleZForVisualization = true,
                runClassification = false,
                isStreamMode = true
            )
        )
    }

    fun flipCameraFacing() = cameraXRepository.changeCameraFacing()
}