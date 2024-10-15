package ru.fefu.sign_up_impl.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.koin.compose.koinInject
import ru.fefu.sign_up_api.SignUpApi
import ru.fefu.sign_up_impl.presentation.greeting.GreetingScreen
import ru.fefu.sign_up_impl.presentation.registration.RegistrationScreens
import ru.fefu.sign_up_impl.presentation.registration.RegistrationViewModel
import ru.fefu.sign_up_impl.utils.singUpValidation.PasswordValidator
import ru.fefu.sign_up_impl.utils.singUpValidation.PhoneNumberValidator
import ru.fefu.sign_up_impl.utils.singUpValidation.RepeatPasswordValidator

private const val GRAPH_ROUTE = "registerGraph"
private const val GREETING_ROUTE = "greetingScreen"
private const val REGISTER_ROUTE = "registrationScreen"

class SingUpImpl(private val signUpRouter: SignUpRouter) : SignUpApi {

    override val route = GRAPH_ROUTE

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = route,
            startDestination = GREETING_ROUTE
        ) {
            composable(GREETING_ROUTE) {
                GreetingScreen { navController.navigate(REGISTER_ROUTE) }
            }
            composable(REGISTER_ROUTE) {
                val mainTabRoute = signUpRouter.provideMainTabRoute()
                val launchMainTab = {
                    navController.navigate(mainTabRoute) {
                        popUpTo(0)
                    }
                }
                val phoneNumberValidator: PhoneNumberValidator = koinInject()
                val passwordValidator: PasswordValidator = koinInject()
                val repeatPasswordValidator: RepeatPasswordValidator = koinInject()
                val viewModel = viewModel {
                    RegistrationViewModel(
                        phoneNumberValidator = phoneNumberValidator,
                        passwordValidator = passwordValidator,
                        repeatPasswordValidator = repeatPasswordValidator,
                        launchMainTab = launchMainTab
                    )
                }
                RegistrationScreens(viewModel)
            }
        }
    }
}