package ru.fefu.photo_tests_impl.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.fefu.TextCardHolder
import ru.fefu.components.PNExpertTextButton
import ru.fefu.components.PNExpertToolbar
import ru.fefu.photo_test_impl.R
import ru.fefu.theme.ApplicationTheme
import ru.fefu.theme.PnExpertTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
internal fun GuideScreen(
    onNavigateToTest: () -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PhotoTestsViewModel = hiltViewModel(),
) {
    val test = remember { viewModel.test }

    Scaffold(
        topBar = {
            PNExpertToolbar(
                title = stringResource(R.string.exercise),
                onBackPressed = onBackPressed
            )
        },
        modifier = modifier,
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
    ) { scaffoldPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(scaffoldPadding)
                .padding(horizontal = 16.dp)
        ) {
            TaskAndTimeHolder(
                modifier = Modifier.fillMaxWidth(),
                taskName = stringResource(test.testTitle),
                date = remember {
                    LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                },
                time = remember { LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) }
            )
            GuidePhotosHolder(
                modifier = Modifier.fillMaxWidth(),
                guidePhotoIDs = test.testTasks.map { it.taskPhotoExample }
            )
            TextCardHolder(
                modifier = Modifier.fillMaxWidth(),
                titleText = stringResource(R.string.instruction),
                text = stringResource(test.testGuide)
            )
            Spacer(modifier = Modifier.weight(1f))
            PNExpertTextButton(
                onClick = onNavigateToTest,
                text = stringResource(R.string.start),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun TaskAndTimeHolder(
    modifier: Modifier,
    taskName: String,
    date: String,
    time: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = taskName,
            style = PnExpertTheme.typography.text.medium_14,
            color = PnExpertTheme.colors.textColors.FontDarkColor
        )
        Text(
            text = "$date / $time",
            style = PnExpertTheme.typography.text.regular_14,
            color = PnExpertTheme.colors.textColors.FontGreyColor
        )

    }
}

@Preview
@Composable
private fun TestNameHolder() {
    ApplicationTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(PnExpertTheme.colors.mainAppColors.AppWhiteColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Нарисовать часы",
                style = PnExpertTheme.typography.text.medium_14,
                color = PnExpertTheme.colors.textColors.FontDarkColor
            )
            Text(
                text = "25 мая 2022 / 14:50:04",
                style = PnExpertTheme.typography.text.regular_14,
                color = PnExpertTheme.colors.textColors.FontGreyColor
            )

        }
    }
}

@Composable
private fun GuidePhotosHolder(
    modifier: Modifier = Modifier,
    guidePhotoIDs: List<Int>,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.exercise_instruction),
            style = PnExpertTheme.typography.text.medium_16,
            color = PnExpertTheme.colors.textColors.FontDarkColor
        )
        for (id in guidePhotoIDs) {
            GuidePhoto(photoID = id)
        }
    }
}

@Composable
private fun GuidePhoto(
    modifier: Modifier = Modifier,
    @DrawableRes photoID: Int
) {
    ApplicationTheme {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = PnExpertTheme.shapes.imageShapes.imageClassic15,
            border = BorderStroke(1.dp, PnExpertTheme.colors.mainAppColors.AppDarkColor)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(PnExpertTheme.shapes.imageShapes.imageClassic15),
                painter = painterResource(id = photoID),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun GuideScreenP() {
    ApplicationTheme {
        GuideScreen(onNavigateToTest = {}, onBackPressed = {})
    }
}