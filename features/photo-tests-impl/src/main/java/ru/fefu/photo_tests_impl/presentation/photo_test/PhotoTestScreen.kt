package ru.fefu.photo_tests_impl.presentation.photo_test

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.fefu.presentation.components.Toolbar
import ru.fefu.theme.PnExpertTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhotoTestScreen(
    modifier: Modifier,
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(top = scaffoldPadding.calculateTopPadding())
        ) {
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
                enabled = false,
                shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor,
                    disabledContainerColor = PnExpertTheme.colors.buttonColors.ButtonInactiveColor
                )
            ) {
                Text(
                    text = "Продолжить",
                    style = PnExpertTheme.typography.subtitle.medium_18,
                    color = PnExpertTheme.colors.textColors.FontWhiteColor
                )
            }
        }
    }
}