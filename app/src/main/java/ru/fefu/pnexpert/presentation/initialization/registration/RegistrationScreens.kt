package ru.fefu.pnexpert.presentation.initialization.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.fefu.pnexpert.presentation.initialization.registration.navigation.RegistrationNavigation
import ru.fefu.pnexpert.presentation.initialization.registration.navigation.RegistrationNavigationRoute
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun RegistrationScreens(
    navController: NavController
) {
    val viewModel = hiltViewModel<RegistrationViewModel>()

    //painted system controllers
    val systemUiController = rememberSystemUiController()
    val barBackground = PnExpertTheme.colors.mainAppColors.AppWhiteColor

    //painted system upp & bottom panels
    SideEffect {
        systemUiController.setStatusBarColor(color = barBackground)
        systemUiController.setNavigationBarColor(color = barBackground)
    }

    val context = LocalContext.current
    LaunchedEffect(context){
        viewModel.validationEvents.collect{event->
            when(event){
                is RegistrationViewModel.ValidationEvent.Success -> {
                    viewModel.pagesNavController!!.navigate(RegistrationNavigationRoute.ConformPhoneScreen.route)
                }

                is RegistrationViewModel.ValidationEvent.Error ->{

                }
            }
        }
    }

    //Text variables
    var titleText by remember { mutableStateOf("Регистрация") }
    var pageNumber by remember { mutableStateOf(1) }

    when(viewModel.currentRegistrationPage){
        is RegistrationNavigationRoute.SingUpScreen->{
            titleText = "Регистрация"
            pageNumber = 1
        }

        is RegistrationNavigationRoute.ConformPhoneScreen ->{
            titleText = "Код из СМС"
            pageNumber = 2
        }

        is RegistrationNavigationRoute.SelectRoleScreen ->{
            titleText = "Выберите роль"
            pageNumber = 3
        }

        is RegistrationNavigationRoute.UsersAgreementScreen ->{
            titleText = "Поользовательское соглашение"
            pageNumber = 4
        }
    }

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
                text = titleText,
                style = PnExpertTheme.typography.subtitle.bold_20,
                color = PnExpertTheme.colors.textColors.FontDarkColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$pageNumber/6",
                style = PnExpertTheme.typography.subtitle.bold_20,
                color = PnExpertTheme.colors.textColors.FontDarkColor
            )
            Spacer(modifier = Modifier.height(30.dp))
            RegistrationNavigation(viewModel)
        }
    }
}