package ru.fefu.pnexpert.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import ru.fefu.feature_api.FeatureApi
import ru.fefu.pnexpert.presentation.navigation.tabsscreen.entities.BottomNavTab

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = BottomNavTab.Main.route
    ) {
        navigation()
    }
}

fun NavGraphBuilder.register(
    featureApi: FeatureApi,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    featureApi.registerGraph(
        navGraphBuilder = this,
        navController = navController,
        modifier = modifier
    )
}