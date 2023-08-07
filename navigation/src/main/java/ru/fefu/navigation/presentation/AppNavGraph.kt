package ru.fefu.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.fefu.navigation.presentation.tabsscreen.InDevPlug

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = ru.fefu.navigation.presentation.navigation.BottomNavTab.Main.route
    ) {

        composable(ru.fefu.navigation.presentation.navigation.BottomNavTab.Main.route) {
            InDevPlug(
                "0"
            )
        }
        composable(ru.fefu.navigation.presentation.navigation.BottomNavTab.Profile.route) {
            InDevPlug(
                "1"
            )
        }
        composable(ru.fefu.navigation.presentation.navigation.BottomNavTab.Services.route) {
            InDevPlug(
                "2"
            )
        }
        composable(ru.fefu.navigation.presentation.navigation.BottomNavTab.History.route) {
            InDevPlug(
                "3"
            )
        }
        composable(ru.fefu.navigation.presentation.navigation.MarkedBottomNabTab.Notes.route) {
            InDevPlug(
                "4"
            )
        }
    }
}