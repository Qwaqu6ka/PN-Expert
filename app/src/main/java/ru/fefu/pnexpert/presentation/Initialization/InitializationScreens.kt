package ru.fefu.pnexpert.presentation.Initialization

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.fefu.pnexpert.presentation.Initialization.greeting.GreetingScreen
import ru.fefu.pnexpert.presentation.Initialization.navigation.InitializationNavigationRoute
import ru.fefu.pnexpert.presentation.Initialization.registration.RegistrationScreens

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