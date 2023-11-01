package ru.fefu.video_tests_impl.presentation

import android.Manifest
import android.os.Build
import androidx.camera.core.ImageAnalysis.COORDINATE_SYSTEM_ORIGINAL
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import ru.fefu.presentation.components.SimpleTextButton
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
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (permissionState.allPermissionsGranted) {
            SimpleCameraPreview()
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
private fun SimpleCameraPreview() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val cameraController = remember { LifecycleCameraController(context) }

    AndroidView(
        factory = { ctx ->
            val options = AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build()
            val poseDetector = PoseDetection.getClient(options)
            val executor = ContextCompat.getMainExecutor(ctx)

            // view to show
            val previewView = PreviewView(ctx)
            previewView.controller = cameraController

            cameraController.bindToLifecycle(lifecycleOwner)
            cameraController.setImageAnalysisAnalyzer(
                executor,
                MlKitAnalyzer(
                    listOf(poseDetector),
                    COORDINATE_SYSTEM_ORIGINAL,
                    executor
                ) { result ->
                    val pose = result.getValue(poseDetector)
                    if (pose == null || pose.allPoseLandmarks.isEmpty()) {
                        previewView.overlay.clear()
                        return@MlKitAnalyzer
                    }


//                    val poseGraphic = PoseGraphic(
//                        context = ctx,
//                        attrs = null,
//                        pose = pose,
//                        showInFrameLikelihood = false,
//                        visualizeZ = true,
//                        rescaleZForVisualization = true
//                    )
//                    previewView.bitmap?.width?.let {
//                        poseGraphic.setImageSourceInfo(
//                            imageWidth = it,
//                            imageHeight = previewView.bitmap?.height!!,
//                            isFlipped = false
//                        )
//                    }
//t
//                    previewView.overlay.clear()
//                    previewView.overlay.add(poseGraphic)


                    val poseDrawable = PoseDrawable(
                        pose = pose,
                        showInFrameLikelihood = true,
                        visualizeZ = true,
                        rescaleZForVisualization = true
                    )

                    previewView.overlay.clear()
                    previewView.overlay.add(poseDrawable)
                }
            )
            previewView
        },
        modifier = Modifier.fillMaxSize()
    )
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
