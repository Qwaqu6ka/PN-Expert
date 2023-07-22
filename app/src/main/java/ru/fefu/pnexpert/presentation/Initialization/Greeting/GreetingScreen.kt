package ru.fefu.pnexpert.presentation.Initialization.Greeting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun GreetingScreen() {

    //painted system controllers
    val systemUiController = rememberSystemUiController()
    val barBackground = PnExpertTheme.colors.mainAppColors.AppBlueColor

    //painted system upp & bottom panels
    SideEffect {
        systemUiController.setStatusBarColor(color = barBackground)
        systemUiController.setNavigationBarColor(color = barBackground)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = PnExpertTheme.colors.mainAppColors.AppBlueColor
    ) {

    }
}