package ru.fefu.pnexpert.main.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.fefu.pnexpert.main.presentation.screens.InDevPlug
import ru.fefu.pnexpert.main.presentation.screens.MainTab

@Composable
fun MainScreenNavGraph(navController: NavHostController, navHostPadding: PaddingValues) {
    NavHost(
        navController,
        startDestination = BottomNavTab.Main.route,
        modifier = Modifier.padding(navHostPadding)
    ) {
        composable(BottomNavTab.Main.route) { MainTab() }
        composable(BottomNavTab.Profile.route) { InDevPlug("1") }
        composable(BottomNavTab.Services.route) { InDevPlug("2") }
        composable(BottomNavTab.History.route) { InDevPlug("3") }
        composable(MarkedBottomNabTab.Notes.route) { InDevPlug("4") }
    }
}