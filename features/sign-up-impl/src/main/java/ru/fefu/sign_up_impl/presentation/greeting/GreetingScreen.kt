package ru.fefu.sign_up_impl.presentation.greeting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.fefu.theme.PnExpertTheme

@Composable
fun GreetingScreen(onRegisterNavigate: () -> Unit) {

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.statusBarDarkContentEnabled = false
        systemUiController.navigationBarDarkContentEnabled = false
    }

    Surface(
        color = PnExpertTheme.colors.mainAppColors.AppBlueColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
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
                .windowInsetsPadding(WindowInsets.safeDrawing),
            contentAlignment = Alignment.BottomCenter
        ) {
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
                onClick = onRegisterNavigate,
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


