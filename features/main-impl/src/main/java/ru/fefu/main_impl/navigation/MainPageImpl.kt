package ru.fefu.main_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.fefu.main_api.MainPageApi
import ru.fefu.main_impl.presentation.MainTab

private const val MAIN_PAGE_ROUTE = "mainPage"

class MainPageImpl : MainPageApi {

    override val route: String = MAIN_PAGE_ROUTE

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = MAIN_PAGE_ROUTE) {
            MainTab(modifier = modifier)
        }
    }
}