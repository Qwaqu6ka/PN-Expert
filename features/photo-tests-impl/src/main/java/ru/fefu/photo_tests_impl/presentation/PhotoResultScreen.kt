package ru.fefu.photo_tests_impl.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.fefu.components.PNExpertToolbar
import ru.fefu.observeWithLifecycle
import ru.fefu.photo_test_impl.R
import ru.fefu.photo_tests_impl.presentation.components.Photo
import ru.fefu.theme.PnExpertTheme

@Composable
internal fun PhotoResultScreen(
    modifier: Modifier = Modifier,
    viewModel: PhotoTestsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onNavigateToTaskScreen: () -> Unit,
) {
    viewModel.photoResultScreenSideEffectFlow.observeWithLifecycle {
        when (it) {
            PhotoTestsViewModel.PhotoResultSideEffect.NavigateToTaskScreen -> onNavigateToTaskScreen()
        }
    }

    val state by viewModel.cameraScreenState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            PNExpertToolbar(title = stringResource(R.string.result), onBackPressed = onBackPressed)
        },
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = scaffoldPadding.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Photo(
                photoUri = state.tempPhotoUri, modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CancellationButton(
                    modifier = Modifier.size(PnExpertTheme.sizes.buttonSize.buttonClassic55),
                    onClick = onBackPressed
                )
                SuccessButton(
                    modifier = Modifier.size(PnExpertTheme.sizes.buttonSize.buttonClassic55),
                    onClick = viewModel::onConfirmPhotoResultScreen,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun SuccessButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppBlueColor
        ),
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        border = BorderStroke(2.dp, PnExpertTheme.colors.mainAppColors.AppBlueColor),
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_check_24),
            contentDescription = stringResource(R.string.confirm),
            tint = PnExpertTheme.colors.mainAppColors.AppWhiteColor
        )
    }
}

@Composable
private fun CancellationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppPinkDarkColor
        ),
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_close_24),
            contentDescription = stringResource(R.string.cancel),
            tint = PnExpertTheme.colors.mainAppColors.AppWhiteColor
        )
    }
}