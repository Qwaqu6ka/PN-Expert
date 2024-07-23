package ru.fefu.video_tests_impl.presentation

import android.Manifest
import android.os.Build
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import ru.fefu.camera.presentation.GraphicOverlay
import ru.fefu.components.PNExpertTextButton
import ru.fefu.theme.ApplicationTheme
import ru.fefu.theme.PnExpertTheme
import ru.fefu.video_tests_impl.R

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
        viewModel.launchPermissionRequest()
    }

    LaunchedEffect(key1 = viewModel.permissionKey.collectAsState()) {
        permissionState.launchMultiplePermissionRequest()
    }
    DisposableEffect(key1 = Unit) {
        onDispose { viewModel.clearCamera() }
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (permissionState.allPermissionsGranted) {
            SimpleCameraPreview(viewModel)
        } else {
            RequirePermissionCard(
                onRequirePermission = viewModel::launchPermissionRequest,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
private fun SimpleCameraPreview(
    viewModel: VideoTestViewModel,
    modifier: Modifier = Modifier
) {
//    val poseDetectorUtils = remember { PoseDetectionUtils }

    Column(modifier = modifier) {
        CameraPreviewComponent(
            onBindPreview = viewModel::bindPreview,
            onBindGraphicOverlay = viewModel::bindGraphicOverlay,
            modifier = Modifier.weight(1f)
        )
        ControlPanel(
            onStartRecording = { /*TODO*/ },
            onStopRecording = { /*TODO*/ },
            onCameraFlip = viewModel::flipCameraFacing,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
        )
    }

//    poseDetectorUtils.cameraSelector =
//        CameraSelector.Builder().requireLensFacing(poseDetectorUtils.lensFacing).build()

//    viewModel.getProcessCameraProvider()
//        .observe(lifecycleOwner) { provider: ProcessCameraProvider? ->
//            poseDetectorUtils.cameraProvider = provider
//            bindAllCameraUseCases(poseDetectorUtils, context, lifecycleOwner)
//
//        }

}

//private fun bindAllCameraUseCases(
//    poseDetectionUtils: PoseDetectionUtils,
//    context: Context,
//    lifecycleOwner: LifecycleOwner
//) {
//    if (poseDetectionUtils.cameraProvider != null) {
//        // As required by CameraX API, unbinds all use cases before trying to re-bind any of them.
//        poseDetectionUtils.cameraProvider!!.unbindAll()
//        bindPreviewUseCase(poseDetectionUtils, context, lifecycleOwner)
//        bindAnalysisUseCase(poseDetectionUtils, context, lifecycleOwner)
//    }
//}
//
//private fun bindPreviewUseCase(
//    poseDetectionUtils: PoseDetectionUtils,
//    context: Context,
//    lifecycleOwner: LifecycleOwner
//) {
//    if (poseDetectionUtils.cameraProvider == null) {
//        return
//    }
//    if (poseDetectionUtils.previewUseCase != null) {
//        poseDetectionUtils.cameraProvider!!.unbind(poseDetectionUtils.previewUseCase)
//    }
//
//    val builder = Preview.Builder()
//    poseDetectionUtils.previewUseCase = builder.build()
//    poseDetectionUtils.previewUseCase!!.setSurfaceProvider(poseDetectionUtils.previewView!!.getSurfaceProvider())
//    poseDetectionUtils.camera =
//        poseDetectionUtils.cameraProvider!!.bindToLifecycle(/* lifecycleOwner= */ lifecycleOwner,
//            poseDetectionUtils.cameraSelector!!,
//            poseDetectionUtils.previewUseCase
//        )
//}
//
//private fun bindAnalysisUseCase(
//    poseDetectionUtils: PoseDetectionUtils,
//    context: Context,
//    lifecycleOwner: LifecycleOwner
//) {
//    if (poseDetectionUtils.cameraProvider == null) {
//        return
//    }
//    if (poseDetectionUtils.analysisUseCase != null) {
//        poseDetectionUtils.cameraProvider!!.unbind(poseDetectionUtils.analysisUseCase)
//    }
//    if (poseDetectionUtils.imageProcessor != null) {
//        poseDetectionUtils.imageProcessor!!.stop()
//    }
//    try {
//
//        val poseDetectorOptions =
//            PreferenceUtils.getPoseDetectorOptionsForLivePreview(context) // fast(1) gpu/stream
//        val shouldShowInFrameLikelihood =
//            PreferenceUtils.shouldShowPoseDetectionInFrameLikelihoodLivePreview(context)    // true
//        val visualizeZ = PreferenceUtils.shouldPoseDetectionVisualizeZ(context) // true
//        val rescaleZ = PreferenceUtils.shouldPoseDetectionRescaleZForVisualization(context) // true
//        val runClassification =
//            PreferenceUtils.shouldPoseDetectionRunClassification(context)   //false
//
//        poseDetectionUtils.imageProcessor = PoseDetectorProcessor(
//            context,
//            poseDetectorOptions,
//            shouldShowInFrameLikelihood,
//            visualizeZ,
//            rescaleZ,
//            runClassification,
//            /* isStreamMode = */ true
//        )
//    } catch (e: Exception) {
//        Log.e(VideoScreenKeys.TAG, "Can not create image processor: ", e)
//        return
//    }
//
//    val builder = ImageAnalysis.Builder()
//    poseDetectionUtils.analysisUseCase = builder.build()
//
//    poseDetectionUtils.needUpdateGraphicOverlayImageSourceInfo = true
//
//    poseDetectionUtils.analysisUseCase?.setAnalyzer(
//        // imageProcessor.processImageProxy will use another thread to run the detection underneath,
//        // thus we can just runs the analyzer itself on main thread.
//        ContextCompat.getMainExecutor(context),
//        ImageAnalysis.Analyzer { imageProxy: ImageProxy ->
//            if (poseDetectionUtils.needUpdateGraphicOverlayImageSourceInfo) {
//                val isImageFlipped =
//                    poseDetectionUtils.lensFacing == CameraSelector.LENS_FACING_FRONT
//                val rotationDegrees = imageProxy.imageInfo.rotationDegrees
//                if (rotationDegrees == 0 || rotationDegrees == 180) {
//                    poseDetectionUtils.graphicOverlay!!.setImageSourceInfo(
//                        imageProxy.width,
//                        imageProxy.height,
//                        isImageFlipped
//                    )
//                } else {
//                    poseDetectionUtils.graphicOverlay!!.setImageSourceInfo(
//                        imageProxy.height,
//                        imageProxy.width,
//                        isImageFlipped
//                    )
//                }
//                poseDetectionUtils.needUpdateGraphicOverlayImageSourceInfo = false
//            }
//            try {
//                poseDetectionUtils.imageProcessor!!.processImageProxy(
//                    imageProxy,
//                    poseDetectionUtils.graphicOverlay
//                )
//            } catch (e: MlKitException) {
//                Log.e(VideoScreenKeys.TAG, "Failed to process image. Error: " + e.localizedMessage)
//                Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
//            }
//        }
//    )
//    poseDetectionUtils.cameraProvider!!.bindToLifecycle(/* lifecycleOwner= */ lifecycleOwner,
//        poseDetectionUtils.cameraSelector!!,
//        poseDetectionUtils.analysisUseCase
//    )
//}

@Composable
private fun CameraPreviewComponent(
    onBindPreview: (preview: PreviewView, lifecycleOwner: LifecycleOwner) -> Unit,
    onBindGraphicOverlay: (graphicOverlay: GraphicOverlay) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        val lifecycleOwner = LocalLifecycleOwner.current
        AndroidView(factory = { context ->
            val previewView = PreviewView(context)
            onBindPreview(previewView, lifecycleOwner)
            previewView
        })
        AndroidView(factory = { context ->
            val graphicOverlay = GraphicOverlay(context, null)
            onBindGraphicOverlay(graphicOverlay)
            graphicOverlay
        })
    }
}

@Composable
private fun ControlPanel(
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit,
    onCameraFlip: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(color = PnExpertTheme.colors.mainAppColors.AppWhiteColor),
        contentAlignment = Alignment.Center
    ) {
        Row {
            IconButton(onClick = onStartRecording, modifier = Modifier.weight(3f)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_record),
                    contentDescription = stringResource(id = R.string.start_recording)
                )
            }
//            IconButton(onClick = onStopRecording) {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_stop_record),
//                    contentDescription = stringResource(id = R.string.stop_recording)
//                )
//            }
            IconButton(onClick = onCameraFlip, modifier = Modifier.weight(1f)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_flip_camera),
                    contentDescription = stringResource(id = R.string.flip_camera)
                )
            }
        }
    }
}

@Composable
private fun RequirePermissionCard(
    onRequirePermission: () -> Unit,
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
            PNExpertTextButton(
                onClick = onRequirePermission,
                text = stringResource(id = R.string.give_permission)
            )
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun PControlPanel() {
    ApplicationTheme {
        ControlPanel(
            onStartRecording = {},
            onStopRecording = {},
            onCameraFlip = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

//object PoseDetectionUtils {
//    var previewView: PreviewView? = null
//    var graphicOverlay: GraphicOverlay? = null
//    var cameraProvider: ProcessCameraProvider? = null
//    var camera: Camera? = null
//    var previewUseCase: Preview? = null
//    var analysisUseCase: ImageAnalysis? = null
//    var imageProcessor: ru.fefu.camera.VisionImageProcessor? = null
//    var needUpdateGraphicOverlayImageSourceInfo = false
//    var lensFacing = CameraSelector.LENS_FACING_BACK
//    var cameraSelector: CameraSelector? = null
//}
//
//private object VideoScreenKeys {
//    const val TAG = "CameraXLivePreview"
//}
