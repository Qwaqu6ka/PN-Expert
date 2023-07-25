package ru.fefu.pnexpert.presentation.Initialization.Registration.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.fefu.pnexpert.presentation.Initialization.Greeting.GreetingScreen
import ru.fefu.pnexpert.presentation.Initialization.Navigation.InitializationNavigationRoute
import ru.fefu.pnexpert.presentation.Initialization.Registration.ConformPhone.ConformNumberPage
import ru.fefu.pnexpert.presentation.Initialization.Registration.RegistrationScreens
import ru.fefu.pnexpert.presentation.Initialization.Registration.RegistrationViewModel
import ru.fefu.pnexpert.presentation.Initialization.Registration.SingUp.SingUpScreen

@Composable
fun RegistrationNavigation(viewModel: RegistrationViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RegistrationNavigationRoute.SingUpScreen.route){
        composable(route = RegistrationNavigationRoute.SingUpScreen.route){
            SingUpScreen(viewModel, navController)
        }
        composable(RegistrationNavigationRoute.ConformPhoneScreen.route){
            ConformNumberPage()
        }
    }
}