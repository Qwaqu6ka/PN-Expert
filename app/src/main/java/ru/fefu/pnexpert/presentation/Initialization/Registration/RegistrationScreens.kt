package ru.fefu.pnexpert.presentation.Initialization.Registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.fefu.pnexpert.presentation.Initialization.Registration.SingUp.SingUpScreen
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun RegistrationScreens(
    navController: NavController,
    viewModel: RegistrationViewModel = viewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = PnExpertTheme.colors.mainAppColors.AppWhiteColor
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Регистрация",
                style = PnExpertTheme.typography.subtitle.bold_20,
                color = PnExpertTheme.colors.textColors.FontDarkColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "1/6",
                style = PnExpertTheme.typography.subtitle.bold_20,
                color = PnExpertTheme.colors.textColors.FontDarkColor
            )
            Spacer(modifier = Modifier.height(30.dp))
            SingUpScreen(viewModel)
        }
    }
}