package com.example.main_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.main_api.MainPageApi
import com.example.main_impl.presentation.MainTab
import javax.inject.Inject
import javax.inject.Singleton

private const val GRAPH_ROUTE = "mainPageGraph"
private const val MAIN_PAGE_ROUTE = "mainPageRoute"

@Singleton
class MainPageImpl @Inject constructor(): MainPageApi {

    override val route: String = GRAPH_ROUTE

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = route,
            startDestination = MAIN_PAGE_ROUTE
        ){
            composable(MAIN_PAGE_ROUTE){
                MainTab()
            }
        }
    }

}