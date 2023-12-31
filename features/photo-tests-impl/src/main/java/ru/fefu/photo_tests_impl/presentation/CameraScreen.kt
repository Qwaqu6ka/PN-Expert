package ru.fefu.photo_tests_impl.presentation

import android.Manifest
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.launch
import ru.fefu.TextCardHolder
import ru.fefu.photo_test_impl.R
import ru.fefu.theme.PnExpertTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun CameraScreen(
    onNavigateToPhotoResult: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PhotoTestsViewModel = hiltViewModel()
) {
    val state by viewModel.cameraScreenState.collectAsState()

    val permission = if (Build.VERSION.SDK_INT <= 28) {
        listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    } else listOf(Manifest.permission.CAMERA)

    val permissionState = rememberMultiplePermissionsState(permissions = permission)

    if (!permissionState.allPermissionsGranted) {
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    if (state.tempPhotoUri != Uri.EMPTY) {
        onNavigateToPhotoResult()
    }

    Scaffold(
        modifier = modifier,
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (permissionState.allPermissionsGranted) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        TextCardHolder(
                            modifier = Modifier.padding(16.dp),
                            text = stringResource(R.string.camera_hint)
                        )
                    }
                    AndroidView(
                        factory = {
                            val previewView = PreviewView(it)
                            viewModel.showCameraPreview(previewView, lifecycleOwner)
                            previewView
                        },
                        modifier = Modifier
                            .weight(4f)
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        IconButton(
                            modifier = Modifier
                                .padding(bottom = 40.dp)
                                .size(64.dp),
                            onClick = {
                                if (permissionState.allPermissionsGranted) {
                                    if (state.isPhotoButtonEnabled) {
                                        viewModel.captureAndSave(context)
                                    } else {
                                        scope.launch {
                                            snackBarHostState.showSnackbar(
                                                "Qr код не был распознан"
                                            )
                                        }
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please accept permission in app settings",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = if (state.isPhotoButtonEnabled)
                                    PnExpertTheme.colors.mainAppColors.AppBlueColor
                                else
                                    PnExpertTheme.colors.buttonColors.ButtonInactiveColor,
                            )
                        ) {
                            Icon(
                                modifier = Modifier.size(64.dp),
                                painter = painterResource(id = R.drawable.baseline_camera_24),
                                contentDescription = stringResource(R.string.make_photo),
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}