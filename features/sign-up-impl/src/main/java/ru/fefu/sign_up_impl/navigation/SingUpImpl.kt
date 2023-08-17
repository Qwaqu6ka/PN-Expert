package ru.fefu.sign_up_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.fefu.sign_up_api.SignUpApi
import ru.fefu.sign_up_impl.presentation.greeting.GreetingScreen
import ru.fefu.sign_up_impl.presentation.registration.RegistrationScreens
import ru.fefu.sign_up_impl.presentation.registration.RegistrationViewModel
import ru.fefu.viewModelCreator
import javax.inject.Inject
import javax.inject.Singleton

private const val GRAPH_ROUTE = "registerGraph"
private const val GREETING_ROUTE = "greetingScreen"
private const val REGISTER_ROUTE = "registrationScreen"

@Singleton
class SingUpImpl @Inject constructor(
    private val signUpRouter: SignUpRouter
) : SignUpApi {

    @Inject lateinit var registerVMFactory: RegistrationViewModel.Factory

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
                    navController.navigate(mainTabRoute){
                        popUpTo(0)
                    }
                }
                val viewModel = viewModelCreator {
                    registerVMFactory.create(launchMainTab)
                }
                RegistrationScreens(viewModel)
            }
        }
    }
}