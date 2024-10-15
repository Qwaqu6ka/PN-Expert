package ru.fefu.photo_tests_impl.presentation

import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import ru.fefu.TextCardHolder
import ru.fefu.components.PNExpertTextButton
import ru.fefu.components.PNExpertToolbar
import ru.fefu.observeWithLifecycle
import ru.fefu.photo_test_impl.R
import ru.fefu.photo_tests_impl.presentation.components.Photo
import ru.fefu.theme.PnExpertTheme

private const val FILE_FORMAT_IMAGE = "image/*"

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun TaskScreen(
    modifier: Modifier = Modifier,
    viewModel: PhotoTestsViewModel,
    onPopBackStack: () -> Unit,
    onNavigateToCamera: () -> Unit,
    onNavigateToTestResult: () -> Unit
) {
    BackHandler(onBack = viewModel::onBackPressedTaskScreen)

    val context = LocalContext.current
    viewModel.taskScreenSideEffectFlow.observeWithLifecycle { sideEffect ->
        when (sideEffect) {
            is PhotoTestsViewModel.TaskScreenSideEffect.NavigateToGuideScreen -> onPopBackStack()
            is PhotoTestsViewModel.TaskScreenSideEffect.NavigateToTestResult -> onNavigateToTestResult()
            is PhotoTestsViewModel.TaskScreenSideEffect.ShowToastRes ->
                Toast.makeText(
                    context,
                    sideEffect.resId,
                    Toast.LENGTH_SHORT
                ).show()
        }
    }
    val state by viewModel.taskScreenState.collectAsState()

    val permissionState =
        rememberMultiplePermissionsState(permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE))

    if (!permissionState.allPermissionsGranted) {
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }

    Scaffold(
        topBar = {
            PNExpertToolbar(
                title = stringResource(R.string.make_photo),
                onBackPressed = viewModel::onBackPressedTaskScreen
            )
        },
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TextCardHolder(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(state.activeTask.taskDescriprion)
            )
            Photo(
                photoUri = state.activeTaskAnswer,
                placeholderResId = state.activeTask.taskPhotoExample,
                modifier = Modifier.height(500.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PhotoButton(onNavigateToCamera)

                val launcher =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                        viewModel.setPhotoFromFiles(photoPath = uri ?: Uri.EMPTY, context = context)
                    }
                LoadFromFilesButton {
                    if (permissionState.allPermissionsGranted) {
                        launcher.launch(FILE_FORMAT_IMAGE)
                    } else {
                        Toast.makeText(context, R.string.permission_required, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            PNExpertTextButton(
                onClick = viewModel::onNextTaskClick,
                text = stringResource(R.string.continue_),
                enabled = state.isNextTaskButtonActive,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun LoadFromFilesButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        border = BorderStroke(2.dp, PnExpertTheme.colors.mainAppColors.AppBlueColor),
        colors = ButtonDefaults.textButtonColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
            contentColor = PnExpertTheme.colors.mainAppColors.AppBlueColor
        )
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = stringResource(R.string.load_photo),
                style = PnExpertTheme.typography.subtitle.medium_18
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = stringResource(R.string.load_photo)
            )
        }
    }
}

@Composable
private fun PhotoButton(onNavigateToCamera: () -> Unit) {
    Button(
        onClick = onNavigateToCamera,
        modifier = Modifier
            .size(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        colors = ButtonDefaults.buttonColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppBlueColor
        ),
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        border = BorderStroke(2.dp, PnExpertTheme.colors.mainAppColors.AppBlueColor),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add_photo),
            contentDescription = stringResource(R.string.make_photo),
            tint = PnExpertTheme.colors.mainAppColors.AppWhiteColor
        )
    }
}
