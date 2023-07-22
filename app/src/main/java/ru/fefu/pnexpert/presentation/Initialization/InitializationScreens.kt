package ru.fefu.pnexpert.presentation.Initialization

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.fefu.pnexpert.presentation.Initialization.Greeting.GreetingScreen
import ru.fefu.pnexpert.presentation.Initialization.Navigation.InitializationNavigationRoute
import ru.fefu.pnexpert.presentation.Initialization.Registration.RegistrationScreens
import ru.fefu.pnexpert.presentation.Initialization.Registration.SingUp.SingUpScreen

@Composable
fun InitializationScreens() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = InitializationNavigationRoute.GreetingScreen.route){
        composable(route = InitializationNavigationRoute.GreetingScreen.route){
            GreetingScreen(navController)
        }
        composable(InitializationNavigationRoute.RegistrationScreen.route){
            RegistrationScreens(navController)
        }
    }

}