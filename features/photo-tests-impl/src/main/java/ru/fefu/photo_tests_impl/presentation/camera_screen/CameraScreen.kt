package ru.fefu.photo_tests_impl.presentation.camera_screen

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.launch
import ru.fefu.photo_test_impl.R
import ru.fefu.photo_tests_impl.presentation.last_photo_screen.LastPhotoScreen
import ru.fefu.presentation.TextCardHolder
import ru.fefu.theme.PnExpertTheme

@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    modifier: Modifier,
    onNavigateToPhotoResult: (String) -> Unit,
    viewModel: CameraScreenViewModel = hiltViewModel()
) {
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
    var previewView: PreviewView

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val isPhotoButtonEnabled = viewModel.isBarcodeDetected.collectAsState()


    if (viewModel.photoUri.value != Uri.EMPTY) {
        val uri = viewModel.photoUri.value
        viewModel.cleanPhotoUri()
        onNavigateToPhotoResult(uri.toString())
    }

    Scaffold(
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
        snackbarHost = {SnackbarHost(snackBarHostState)}
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (permissionState.allPermissionsGranted){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.Center
                    ){
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ){
                            TextCardHolder(
                                modifier = Modifier.padding(16.dp),
                                text = "Сфотографирйте ваш результат так, чтобы был виден Qr код"
                            )
                        }
                    }
                    AndroidView(
                        factory = {
                            previewView = PreviewView(it)
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
                                    if (isPhotoButtonEnabled.value){
                                        viewModel.captureAndSave(context)
                                    }
                                    else{
                                        scope.launch {
                                            snackBarHostState.showSnackbar(
                                                "Qr код не был распознан или его не видно"
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
                                containerColor = if(isPhotoButtonEnabled.value)
                                    PnExpertTheme.colors.mainAppColors.AppBlueColor
                                else
                                    PnExpertTheme.colors.buttonColors.ButtonInactiveColor,
                            )
                        ) {
                            Icon(
                                modifier = Modifier.size(64.dp),
                                painter = painterResource(id = R.drawable.baseline_camera_24),
                                contentDescription = "cameraButton",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}