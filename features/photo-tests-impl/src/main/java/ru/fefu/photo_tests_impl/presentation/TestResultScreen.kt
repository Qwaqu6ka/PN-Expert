package ru.fefu.photo_tests_impl.presentation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.fefu.TextCardHolder
import ru.fefu.components.PNExpertTextButton
import ru.fefu.components.PNExpertToolbar
import ru.fefu.photo_test_impl.R
import ru.fefu.photo_tests_impl.presentation.components.Photo

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun TestResultScreen(
    modifier: Modifier = Modifier,
    viewModel: PhotoTestsViewModel
) {
    val state by viewModel.testResultScreenState.collectAsState()

    Scaffold(
        modifier = modifier,
        containerColor = Color.White,
        topBar = { PNExpertToolbar(title = stringResource(R.string.test_results)) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            for ((taskIndex, photoAnswer) in state.answers.withIndex()) {
                ResultItem(
                    taskDescription = stringResource(state.tasks[taskIndex].taskDescriprion),
                    taskNumber = taskIndex + 1,
                    photoAnswer = photoAnswer
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            PNExpertTextButton(
                onClick = viewModel::completeTest,
                text = stringResource(R.string.complete_test),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ResultItem(
    taskDescription: String,
    taskNumber: Int,
    photoAnswer: Uri,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextCardHolder(
            modifier = Modifier.fillMaxWidth(),
            text = taskDescription,
            titleText = stringResource(R.string.task_number, taskNumber)
        )
        Photo(photoAnswer)
    }
}
