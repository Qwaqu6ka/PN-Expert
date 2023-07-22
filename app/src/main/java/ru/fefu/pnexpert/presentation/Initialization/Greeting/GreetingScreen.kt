package ru.fefu.pnexpert.presentation.Initialization.Greeting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Добро пожаловать",
                    textAlign = TextAlign.Center,
                    style = PnExpertTheme.typography.title.bold_32,
                    color = PnExpertTheme.colors.textColors.FontWhiteColor
                )
                Text(
                    text = "в PN EXPERT",
                    textAlign = TextAlign.Center,
                    style = PnExpertTheme.typography.title.bold_32,
                    color = PnExpertTheme.colors.textColors.FontWhiteColor
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Программа мониторинга и сбора данных пациента!",
                    textAlign = TextAlign.Center,
                    style = PnExpertTheme.typography.subtitle.medium_18,
                    color = PnExpertTheme.colors.textColors.FontWhiteColor
                )
            }
        }
    }
}