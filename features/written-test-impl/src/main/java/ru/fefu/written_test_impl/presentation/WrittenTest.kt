package ru.fefu.written_test_impl.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ru.fefu.presentation.Toolbar
import ru.fefu.theme.PnExpertTheme
import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.entities.testentities.WrittenTest

@Composable
internal fun WrittenTest(
    modifier: Modifier = Modifier,
    viewModel: WrittenTestViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            Toolbar(
                title = stringResource(id = R.string.test),
                onBackPressed = { viewModel.onBackPressed() })
        },
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor
    ) { innerPadding ->

        TestContent(test = viewModel.test, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
internal fun TestContent(
    test: WrittenTest,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        LazyColumn {
            items(25) {
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
                Text(text = stringResource(id = test.title))
            }
        }
    }
}