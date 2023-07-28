package ru.fefu.pnexpert.presentation.Initialization.registration.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.fefu.pnexpert.presentation.Initialization.registration.conform_phone.ConformNumberPage
import ru.fefu.pnexpert.presentation.Initialization.registration.RegistrationViewModel
import ru.fefu.pnexpert.presentation.Initialization.registration.sing_up.SingUpScreen

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