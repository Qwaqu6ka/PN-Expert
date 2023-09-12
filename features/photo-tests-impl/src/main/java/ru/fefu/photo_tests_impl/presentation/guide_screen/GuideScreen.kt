package ru.fefu.photo_tests_impl.presentation.guide_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.fefu.photo_tests_impl.presentation.guide_screen.elements.GuidePhotosHolder
import ru.fefu.photo_tests_impl.presentation.guide_screen.elements.TaskAndTimeHolder
import ru.fefu.presentation.components.TextCardHolderPink
import ru.fefu.presentation.components.Toolbar
import ru.fefu.theme.PnExpertTheme

@Composable
fun GuideScreen(
    modifier: Modifier,
    viewModel: GuideScreenViewModel,
) {
    Scaffold(
        topBar = { Toolbar(title = "Упражнение", onBackPressed = {}) },
        modifier = modifier,
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
        contentWindowInsets = WindowInsets(0.dp)
    ) { scaffoldPadding ->
        Surface(modifier = Modifier.padding(scaffoldPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
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
                TextCardHolderPink(
                    modifier = Modifier.fillMaxWidth(),
                    titleText = "Инструкция",
                    text = viewModel.testDataState.value.data!!.testGuide
                )
                Spacer(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
                    shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = PnExpertTheme.colors.buttonColors.ButtonNormalRedColor
                    )
                ) {
                    Text(
                        text = "Начать",
                        style = PnExpertTheme.typography.subtitle.medium_18,
                        color = PnExpertTheme.colors.textColors.FontWhiteColor
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}