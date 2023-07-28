package ru.fefu.pnexpert.presentation.initialization

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.fefu.pnexpert.presentation.initialization.greeting.GreetingScreen
import ru.fefu.pnexpert.presentation.initialization.navigation.InitializationNavigationRoute
import ru.fefu.pnexpert.presentation.initialization.registration.RegistrationScreens

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