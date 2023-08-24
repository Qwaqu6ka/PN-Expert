package ru.fefu.photo_tests_impl.presentation.guide_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.fefu.photo_tests_impl.presentation.PhotoTestsViewModel
import ru.fefu.photo_tests_impl.presentation.guide_screen.elements.GuidePhotosHolder
import ru.fefu.photo_tests_impl.presentation.guide_screen.elements.TaskAndTimeHolder
import ru.fefu.presentation.Toolbar
import ru.fefu.theme.PnExpertTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GuideScreen(viewModel: PhotoTestsViewModel) {
    Scaffold(
        topBar = { Toolbar(title = "Упражнение") },
    ) {scaffoldPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PnExpertTheme.colors.mainAppColors.AppGreyLightColor)
                .verticalScroll(rememberScrollState())
                .padding(vertical = scaffoldPadding.calculateTopPadding())
                .padding(horizontal = 16.dp)
        ) {
            TaskAndTimeHolder(
                modifier = Modifier
                    .fillMaxWidth(),
                taskName = viewModel.testDataState.value.data!!.testName,
                date = "25 мая 2022",
                time = "14:50:04"
            )
            Spacer(modifier = Modifier.height(20.dp))
            GuidePhotosHolder(
                modifier = Modifier.fillMaxWidth(),
                guidePhotos = viewModel.testDataState.value.data!!.testGuidePhotos
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = viewModel.testDataState.value.data!!.testGuide,
                style = PnExpertTheme.typography.text.medium_14,
                color = PnExpertTheme.colors.textColors.FontGreyColor
            )
        }
    }
}



