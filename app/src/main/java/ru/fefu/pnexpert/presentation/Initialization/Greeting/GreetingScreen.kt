package ru.fefu.pnexpert.presentation.Initialization.Greeting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.fefu.pnexpert.presentation.Initialization.Navigation.InitializationNavigationRoute
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun GreetingScreen(navController:NavController) {

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 71.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
                onClick = {navController.navigate(InitializationNavigationRoute.RegistrationScreen.route)},
                shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
                )
            ) {
                Text(
                    text = "Приступить",
                    style = PnExpertTheme.typography.subtitle.medium_18,
                    color = PnExpertTheme.colors.textColors.FontBlueColor
                )
            }
        }
    }
}


