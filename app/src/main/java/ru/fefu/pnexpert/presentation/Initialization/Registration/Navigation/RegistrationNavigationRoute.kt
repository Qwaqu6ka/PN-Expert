package ru.fefu.pnexpert.presentation.Initialization.Registration.Navigation


sealed class RegistrationNavigationRoute(val route:String) {
    object SingUpScreen: RegistrationNavigationRoute("singUpScreen")
    object ConformPhoneScreen: RegistrationNavigationRoute("conformPhoneScreen")
}
