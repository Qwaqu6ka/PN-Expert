package ru.fefu.pnexpert.presentation.Initialization.registration.navigation


sealed class RegistrationNavigationRoute(val route:String) {
    object SingUpScreen: RegistrationNavigationRoute("singUpScreen")
    object ConformPhoneScreen: RegistrationNavigationRoute("conformPhoneScreen")
}
