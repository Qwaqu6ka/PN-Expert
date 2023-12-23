package ru.fefu.photo_tests_impl.presentation.last_photo_screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import ru.fefu.photo_test_impl.R
import ru.fefu.presentation.components.Toolbar
import ru.fefu.theme.PnExpertTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LastPhotoScreen(
    viewModel: LastPhotoScreenViewModel,
    modifier: Modifier = Modifier,
    onNavigateToCamera: () -> Unit,
    onNavigateToTest: (photoPath: String) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { Toolbar(title = "Результат", onBackPressed = { onNavigateToCamera() }) },
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
            PhotoResult(
                photoPath = viewModel.getPhotoPath(),
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CancellationButton(onNavigateToCamera)
                SuccessButton(viewModel.getPhotoPath(), onNavigateToTest)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun PhotoResult(
    photoPath: Uri,
    modifier: Modifier = Modifier
) {
    val resultPhoto = rememberAsyncImagePainter(photoPath)

    if (resultPhoto.state is AsyncImagePainter.State.Loading) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            color = PnExpertTheme.colors.mainAppColors.AppBlueColor,
            strokeWidth = 2.dp
        )
    } else {
        Card(
            modifier = modifier,
            shape = PnExpertTheme.shapes.imageShapes.imageClassic15,
            border = BorderStroke(1.dp, PnExpertTheme.colors.mainAppColors.AppBlueColor)
        ) {
            Image(
                painter = resultPhoto,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
private fun SuccessButton(
    photoPath: Uri,
    onNavigateToTest: (photoPath: String) -> Unit
) {
    Button(
        modifier = Modifier
            .size(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        colors = ButtonDefaults.buttonColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppBlueColor
        ),
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        border = BorderStroke(2.dp, PnExpertTheme.colors.mainAppColors.AppBlueColor),
        contentPadding = PaddingValues(0.dp),
        onClick = { onNavigateToTest(photoPath.toString()) }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_check_24),
            contentDescription = "check_button",
            tint = PnExpertTheme.colors.mainAppColors.AppWhiteColor
        )
    }
}

@Composable
private fun CancellationButton(
    onNavigateToCamera: () -> Unit
) {
    Button(
        modifier = Modifier
            .size(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        colors = ButtonDefaults.buttonColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppPinkDarkColor
        ),
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        contentPadding = PaddingValues(0.dp),
        onClick = { onNavigateToCamera() }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_close_24),
            contentDescription = "check_button",
            tint = PnExpertTheme.colors.mainAppColors.AppWhiteColor
        )
    }
}