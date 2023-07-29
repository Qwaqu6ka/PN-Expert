package ru.fefu.pnexpert.presentation.initialization.registration.navigation


sealed class RegistrationNavigationRoute(val route:String) {
    object SingUpScreen: RegistrationNavigationRoute("singUpScreen")
    object ConformPhoneScreen: RegistrationNavigationRoute("conformPhoneScreen")
    object SelectRoleScreen: RegistrationNavigationRoute("selectRoleScreen")
}
