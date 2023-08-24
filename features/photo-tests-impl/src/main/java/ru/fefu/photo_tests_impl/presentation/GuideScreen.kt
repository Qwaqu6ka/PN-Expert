package ru.fefu.photo_tests_impl.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun GuideScreen(viewModel: PhotoTestsViewModel) {
    Text(
        text = viewModel.testDataState.toString()
    )
}