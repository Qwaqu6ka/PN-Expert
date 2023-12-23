package ru.fefu.pnexpert.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.fefu.feature_api.register
import ru.fefu.pnexpert.presentation.BottomTabs
import ru.fefu.pnexpert.presentation.InDevPlug

@Composable
fun AppNavGraph(
    navController: NavHostController,
    featureApiHolder: FeatureApiHolder,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
//        startDestination = BottomTabs.Main.route
//        startDestination = featureApiHolder.signUpApi.route
        startDestination = featureApiHolder.photoTestsApi.route
    ) {

        register(
            featureApi = featureApiHolder.signUpApi,
            navController = navController,
            modifier = modifier
        )

        register(
            featureApi = featureApiHolder.photoTestsApi,
            navController = navController,
            modifier = modifier
        )



        navigation(
            route = BottomTabs.Main.route,
            startDestination = featureApiHolder.mainPageApi.route
        ) {
            register(
                featureApi = featureApiHolder.mainPageApi,
                navController = navController,
                modifier = modifier
            )
        }


        navigation(
            route = BottomTabs.Profile.route,
            startDestination = "testProfile"
        ) {
            composable("testProfile") {
                InDevPlug("History") {
                    navController.navigate(featureApiHolder.photoTestsApi.route)
                }
            }
        }

        navigation(
            route = BottomTabs.History.route,
            startDestination = "testHistory"
        ) {

            composable("testHistory") {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    InDevPlug("Updrs1") {
                        navController.navigate(featureApiHolder.writtenTestApi.testUpdrs1Route)
                    }
                    InDevPlug("Updrs2") {
                        navController.navigate(featureApiHolder.writtenTestApi.testUpdrs2Route)
                    }
                    InDevPlug("Updrs3") {
                        navController.navigate(featureApiHolder.writtenTestApi.testUpdrs3Route)
                    }
                    InDevPlug("Updrs4") {
                        navController.navigate(featureApiHolder.writtenTestApi.testUpdrs4Route)
                    }
                    InDevPlug("Psqi") {
                        navController.navigate(featureApiHolder.writtenTestApi.testPsqiRoute)
                    }
                    InDevPlug("Hads") {
                        navController.navigate(featureApiHolder.writtenTestApi.testHadsRoute)
                    }
                    InDevPlug("Fab") {
                        navController.navigate(featureApiHolder.writtenTestApi.testFabRoute)
                    }
                    InDevPlug("HoehnYahr") {
                        navController.navigate(featureApiHolder.writtenTestApi.testHoehnYahrRoute)
                    }
                    InDevPlug("Pdq39") {
                        navController.navigate(featureApiHolder.writtenTestApi.testPdq39Route)
                    }
                    InDevPlug("SchwabEngland") {
                        navController.navigate(featureApiHolder.writtenTestApi.testSchwabEnglandRoute)
                    }
                }
            }
            register(
                featureApi = featureApiHolder.writtenTestApi,
                navController = navController,
                modifier = modifier
            )
        }
        navigation(
            route = BottomTabs.Notes.route,
            startDestination = featureApiHolder.calendarApi.route
        ) {
            register(
                featureApi = featureApiHolder.calendarApi,
                navController = navController,
                modifier = modifier
            )
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