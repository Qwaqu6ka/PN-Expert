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
        startDestination = BottomTabs.Main.route
//        startDestination = featureApiHolder.signUpApi.route
//        startDestination = featureApiHolder.photoTestsApi.route
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

        register(
            featureApi = featureApiHolder.videoTestsApi,
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
                Column {
                    InDevPlug(testStr = "ClockPhotoTest") {
                        navController.navigate(featureApiHolder.photoTestsApi.clockPhotoTestRoute)
                    }
                    InDevPlug(testStr = "FacePhotoTest") {
                        navController.navigate(featureApiHolder.photoTestsApi.facePhotoTestRoute)
                    }
                    InDevPlug(testStr = "FullLengthPhotoTest") {
                        navController.navigate(featureApiHolder.photoTestsApi.fullLengthPhotoTestRoute)
                    }
                    InDevPlug(testStr = "HandwritingPhotoTest") {
                        navController.navigate(featureApiHolder.photoTestsApi.handwritingPhotoTestRoute)
                    }
                }
            }
        }

        navigation(
            route = BottomTabs.History.route,
            startDestination = "testHistory"
        ) {

            composable("testHistory") {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    InDevPlug(testStr = "Updrs1") {
                        navController.navigate(featureApiHolder.writtenTestApi.testUpdrs1Route)
                    }
                    InDevPlug(testStr = "Updrs2") {
                        navController.navigate(featureApiHolder.writtenTestApi.testUpdrs2Route)
                    }
                    InDevPlug(testStr = "Updrs3") {
                        navController.navigate(featureApiHolder.writtenTestApi.testUpdrs3Route)
                    }
                    InDevPlug(testStr = "Updrs4") {
                        navController.navigate(featureApiHolder.writtenTestApi.testUpdrs4Route)
                    }
                    InDevPlug(testStr = "Psqi") {
                        navController.navigate(featureApiHolder.writtenTestApi.testPsqiRoute)
                    }
                    InDevPlug(testStr = "Hads") {
                        navController.navigate(featureApiHolder.writtenTestApi.testHadsRoute)
                    }
                    InDevPlug(testStr = "Fab") {
                        navController.navigate(featureApiHolder.writtenTestApi.testFabRoute)
                    }
                    InDevPlug(testStr = "HoehnYahr") {
                        navController.navigate(featureApiHolder.writtenTestApi.testHoehnYahrRoute)
                    }
                    InDevPlug(testStr = "Pdq39") {
                        navController.navigate(featureApiHolder.writtenTestApi.testPdq39Route)
                    }
                    InDevPlug(testStr = "SchwabEngland") {
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
                InDevPlug(testStr = "Services")
            }
        }
    }
}