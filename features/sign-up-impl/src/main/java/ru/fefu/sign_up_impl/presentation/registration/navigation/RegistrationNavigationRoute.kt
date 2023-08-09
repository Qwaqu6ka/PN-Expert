package ru.fefu.sign_up_impl.presentation.registration.navigation


sealed class RegistrationNavigationRoute(val route:String) {
    object SingUpScreen: RegistrationNavigationRoute("singUpScreen")
    object ConformPhoneScreen: RegistrationNavigationRoute("conformPhoneScreen")
    object SelectRoleScreen: RegistrationNavigationRoute("selectRoleScreen")
    object UsersAgreementScreen: RegistrationNavigationRoute("usersAgreementScreen")
    object ManualScreen: RegistrationNavigationRoute("manualScreen")
}
