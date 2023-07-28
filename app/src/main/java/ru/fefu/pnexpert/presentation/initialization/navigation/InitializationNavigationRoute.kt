package ru.fefu.pnexpert.presentation.initialization.navigation

sealed class InitializationNavigationRoute(val route:String) {
    object GreetingScreen: InitializationNavigationRoute("greetingScreen")
    object RegistrationScreen: InitializationNavigationRoute("registrationScreen")
}