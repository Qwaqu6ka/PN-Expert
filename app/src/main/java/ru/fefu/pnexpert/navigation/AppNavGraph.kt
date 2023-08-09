package ru.fefu.pnexpert.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.fefu.pnexpert.presentation.BottomTabs
import ru.fefu.pnexpert.presentation.InDevPlug

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = BottomTabs.Main.route
    ) {

        navigation(
            route = BottomTabs.Main.route,
            startDestination = "testMain"
        ) {
            composable("testMain") {
                InDevPlug("Main")
            }
        }
        navigation(
            route = BottomTabs.Profile.route,
            startDestination = "testProfile"
        ) {
            composable("testProfile") {
                InDevPlug("Profile")
            }
        }
        navigation(
            route = BottomTabs.History.route,
            startDestination = "testHistory"
        ) {
            composable("testHistory") {
                InDevPlug("History")
            }
        }
        navigation(
            route = BottomTabs.Notes.route,
            startDestination = "testNotes"
        ) {
            composable("testNotes") {
                InDevPlug("Notes")
            }
        }
        navigation(
            route = BottomTabs.Services.route,
            startDestination = "testServices"
        ) {
            composable("testServices") {
                InDevPlug("Services")
            }
        }
    }
}