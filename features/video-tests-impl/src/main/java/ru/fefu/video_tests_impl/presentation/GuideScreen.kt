package ru.fefu.video_tests_impl.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.fefu.TextCardHolder
import ru.fefu.components.GifImage
import ru.fefu.components.PNExpertTextButton
import ru.fefu.components.PNExpertToolbar
import ru.fefu.theme.PnExpertTheme
import ru.fefu.video_tests_impl.R

@Composable
internal fun GuideScreen(
    onNavigateToVideoScreen: () -> Unit,
    viewModel: VideoTestViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            PNExpertToolbar(title = stringResource(R.string.exercise),
                onBackPressed = { })
        },
        bottomBar = {
            PNExpertTextButton(
                onClick = onNavigateToVideoScreen,
                text = stringResource(id = R.string.start),
                alternativeColor = true,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
            )
        },
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(viewModel.test.titleRes),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = PnExpertTheme.typography.text.medium_14
                )
                Spacer(Modifier.height(21.dp))
                Text(
                    text = stringResource(id = R.string.instruction_to_exercise) + ":",
                    style = PnExpertTheme.typography.text.medium_16
                )
                GifImage(
                    data = R.drawable.exercise,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
                TextCardHolder(
                    titleText = stringResource(id = R.string.instruction),
                    text = stringResource(id = viewModel.test.instructionRes)
                )
            }
        }
    }
}