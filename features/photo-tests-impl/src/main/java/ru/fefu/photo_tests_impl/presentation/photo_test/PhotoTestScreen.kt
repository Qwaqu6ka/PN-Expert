package ru.fefu.photo_tests_impl.presentation.photo_test

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.fefu.presentation.components.Toolbar
import ru.fefu.theme.PnExpertTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhotoTestScreen(
    modifier: Modifier = Modifier,
    onNavigateToGuide: () -> Unit
) {
    Scaffold(
        topBar = { Toolbar(title = "Сделайте фото", onBackPressed = {onNavigateToGuide()}) },
        modifier = modifier,
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
    ) {scaffoldPadding ->
        Column(
            modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = scaffoldPadding.calculateTopPadding())
        ) {

        }
    }
}