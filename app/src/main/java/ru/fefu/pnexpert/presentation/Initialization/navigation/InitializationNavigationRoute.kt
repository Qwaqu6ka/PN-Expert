package ru.fefu.pnexpert.presentation.Initialization.navigation

sealed class InitializationNavigationRoute(val route:String) {
    object GreetingScreen: InitializationNavigationRoute("greetingScreen")
    object RegistrationScreen: InitializationNavigationRoute("registrationScreen")
}