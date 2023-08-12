package ru.fefu.sign_up_impl.presentation.registration.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.fefu.sign_up_impl.presentation.registration.RegistrationViewModel
import ru.fefu.sign_up_impl.presentation.registration.conform_phone.ConformNumberPage
import ru.fefu.sign_up_impl.presentation.registration.manual.ManualScreen
import ru.fefu.sign_up_impl.presentation.registration.role_selector.RoleSelectorScreen
import ru.fefu.sign_up_impl.presentation.registration.sing_up.SingUpScreen
import ru.fefu.sign_up_impl.presentation.registration.users_agreement.UsersAgreementScreen

@Composable
fun RegistrationNavigation(
    viewModel: RegistrationViewModel
) {
    val navController = rememberNavController()
    viewModel.initPagesNavController(navController)
    NavHost(
        navController = navController,
        startDestination = RegistrationNavigationRoute.SingUpScreen.route
    ) {
        composable(route = RegistrationNavigationRoute.SingUpScreen.route) {
            SingUpScreen(viewModel)
        }
        composable(RegistrationNavigationRoute.ConformPhoneScreen.route) {
            ConformNumberPage(viewModel)
        }
        composable(RegistrationNavigationRoute.SelectRoleScreen.route) {
            RoleSelectorScreen(viewModel)
        }
        composable(RegistrationNavigationRoute.UsersAgreementScreen.route) {
            UsersAgreementScreen(viewModel)
        }
        composable(RegistrationNavigationRoute.ManualScreen.route) {
            ManualScreen(viewModel)
        }
    }
}