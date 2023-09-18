package ru.fefu.video_tests_impl.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun GuideScreen(
    onNavigateToVideoScreen: () -> Unit,
    viewModel: VideoTestViewModel
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Guide Screen")
            Button(onClick = onNavigateToVideoScreen) {
                Text("To VideoScreen")
            }
        }
    }
}