package ru.fefu.video_tests_impl.presentation

import android.Manifest
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.widget.Toast
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.COORDINATE_SYSTEM_ORIGINAL
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.mlkit.common.MlKitException
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import ru.fefu.presentation.components.SimpleTextButton
import ru.fefu.theme.PnExpertTheme
import ru.fefu.video_tests_impl.R
import ru.fefu.video_tests_impl.preferences.PreferenceUtils
import ru.fefu.video_tests_impl.visionutils.GraphicOverlay
import ru.fefu.video_tests_impl.visionutils.VisionImageProcessor
import ru.fefu.video_tests_impl.visionutils.posedetector.PoseDetectorProcessor

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun VideoScreen(
    viewModel: VideoTestViewModel,
    modifier: Modifier = Modifier
) {
    val permission =
        mutableListOf(
            Manifest.permission.CAMERA
        ).apply {
            if (Build.VERSION.SDK_INT <= 28) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toList()

    val permissionState = rememberMultiplePermissionsState(permissions = permission)

    if (!permissionState.allPermissionsGranted) {
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (permissionState.allPermissionsGranted) {
            SimpleCameraPreview(viewModel)
        } else {
            RequirePermissionCard(
                onRequirePermission = {
                    SideEffect {
                        permissionState.launchMultiplePermissionRequest()
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun SimpleCameraPreview(
    viewModel: VideoTestViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val poseDetectorUtils = remember { PoseDetectionUtils }

    previewComponent()

    poseDetectorUtils.cameraSelector = CameraSelector.Builder().requireLensFacing(poseDetectorUtils.lensFacing).build()

    viewModel.getProcessCameraProvider().observe(lifecycleOwner) {  provider: ProcessCameraProvider? ->
        poseDetectorUtils.cameraProvider = provider
        bindAllCameraUseCases(poseDetectorUtils, context, lifecycleOwner)

    }

}

private fun bindAllCameraUseCases(poseDetectionUtils: PoseDetectionUtils, context: Context, lifecycleOwner: LifecycleOwner) {
    if (poseDetectionUtils.cameraProvider != null) {
        // As required by CameraX API, unbinds all use cases before trying to re-bind any of them.
        poseDetectionUtils.cameraProvider!!.unbindAll()
        bindPreviewUseCase(poseDetectionUtils, context, lifecycleOwner)
        bindAnalysisUseCase(poseDetectionUtils, context, lifecycleOwner)
    }
}

private fun bindPreviewUseCase(poseDetectionUtils: PoseDetectionUtils, context: Context, lifecycleOwner: LifecycleOwner) {
    if (!PreferenceUtils.isCameraLiveViewportEnabled(context)) {
        return
    }
    if (poseDetectionUtils.cameraProvider == null) {
        return
    }
    if (poseDetectionUtils.previewUseCase != null) {
        poseDetectionUtils.cameraProvider!!.unbind(poseDetectionUtils.previewUseCase)
    }

    val builder = Preview.Builder()
    val targetResolution = PreferenceUtils.getCameraXTargetResolution(context, poseDetectionUtils.lensFacing)
    if (targetResolution != null) {
        builder.setTargetResolution(targetResolution)
    }
    poseDetectionUtils.previewUseCase = builder.build()
    poseDetectionUtils.previewUseCase!!.setSurfaceProvider(poseDetectionUtils.previewView!!.getSurfaceProvider())
    poseDetectionUtils.camera =
        poseDetectionUtils.cameraProvider!!.bindToLifecycle(/* lifecycleOwner= */ lifecycleOwner, poseDetectionUtils.cameraSelector!!, poseDetectionUtils.previewUseCase)
}

private fun bindAnalysisUseCase(poseDetectionUtils: PoseDetectionUtils, context: Context, lifecycleOwner: LifecycleOwner) {
    if (poseDetectionUtils.cameraProvider == null) {
        return
    }
    if (poseDetectionUtils.analysisUseCase != null) {
        poseDetectionUtils.cameraProvider!!.unbind(poseDetectionUtils.analysisUseCase)
    }
    if (poseDetectionUtils.imageProcessor != null) {
        poseDetectionUtils.imageProcessor!!.stop()
    }
    try {

        val poseDetectorOptions = PreferenceUtils.getPoseDetectorOptionsForLivePreview(context)
        val shouldShowInFrameLikelihood =
            PreferenceUtils.shouldShowPoseDetectionInFrameLikelihoodLivePreview(context)
        val visualizeZ = PreferenceUtils.shouldPoseDetectionVisualizeZ(context)
        val rescaleZ = PreferenceUtils.shouldPoseDetectionRescaleZForVisualization(context)
        val runClassification = PreferenceUtils.shouldPoseDetectionRunClassification(context)

        poseDetectionUtils.imageProcessor = PoseDetectorProcessor(
            context,
            poseDetectorOptions,
            shouldShowInFrameLikelihood,
            visualizeZ,
            rescaleZ,
            runClassification,
            /* isStreamMode = */ true
        )
    } catch (e: Exception) {
        Log.e(VideoScreenKeys.TAG, "Can not create image processor: ", e)
        return
    }

    val builder = ImageAnalysis.Builder()
    val targetResolution = PreferenceUtils.getCameraXTargetResolution(context, poseDetectionUtils.lensFacing)
    if (targetResolution != null) {
        builder.setTargetResolution(targetResolution)
    }
    poseDetectionUtils.analysisUseCase = builder.build()

    poseDetectionUtils.needUpdateGraphicOverlayImageSourceInfo = true

    poseDetectionUtils.analysisUseCase?.setAnalyzer(
        // imageProcessor.processImageProxy will use another thread to run the detection underneath,
        // thus we can just runs the analyzer itself on main thread.
        ContextCompat.getMainExecutor(context),
        ImageAnalysis.Analyzer { imageProxy: ImageProxy ->
            if (poseDetectionUtils.needUpdateGraphicOverlayImageSourceInfo) {
                val isImageFlipped = poseDetectionUtils.lensFacing == CameraSelector.LENS_FACING_FRONT
                val rotationDegrees = imageProxy.imageInfo.rotationDegrees
                if (rotationDegrees == 0 || rotationDegrees == 180) {
                    poseDetectionUtils.graphicOverlay!!.setImageSourceInfo(imageProxy.width, imageProxy.height, isImageFlipped)
                } else {
                    poseDetectionUtils.graphicOverlay!!.setImageSourceInfo(imageProxy.height, imageProxy.width, isImageFlipped)
                }
                poseDetectionUtils.needUpdateGraphicOverlayImageSourceInfo = false
            }
            try {
                poseDetectionUtils.imageProcessor!!.processImageProxy(imageProxy, poseDetectionUtils.graphicOverlay)
            } catch (e: MlKitException) {
                Log.e(VideoScreenKeys.TAG, "Failed to process image. Error: " + e.localizedMessage)
                Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    )
    poseDetectionUtils.cameraProvider!!.bindToLifecycle(/* lifecycleOwner= */ lifecycleOwner, poseDetectionUtils.cameraSelector!!, poseDetectionUtils.analysisUseCase)
}

@Composable
private fun previewComponent() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        AndroidView(factory = {
            val previewView = PreviewView(it)
            PoseDetectionUtils.previewView = previewView

            previewView
        })
        AndroidView(factory = {
            val graphicOverlay = GraphicOverlay(it, null)
            PoseDetectionUtils.graphicOverlay = graphicOverlay

            graphicOverlay
        })
    }

}

@Composable
private fun RequirePermissionCard(
    onRequirePermission: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppGreyLightColor
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.permission_need_text),
                style = PnExpertTheme.typography.subtitle.medium_18,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            SimpleTextButton(
                onClick = { onRequirePermission },
                text = stringResource(id = R.string.give_permission)
            )
        }
    }
}

object PoseDetectionUtils {
    var previewView: PreviewView? = null
    var graphicOverlay: GraphicOverlay? = null
    var cameraProvider: ProcessCameraProvider? = null
    var camera: Camera? = null
    var previewUseCase: Preview? = null
    var analysisUseCase: ImageAnalysis? = null
    var imageProcessor: VisionImageProcessor? = null
    var needUpdateGraphicOverlayImageSourceInfo = false
    var lensFacing = CameraSelector.LENS_FACING_BACK
    var cameraSelector: CameraSelector? = null
}

private object VideoScreenKeys {
    const val TAG = "CameraXLivePreview"
}
