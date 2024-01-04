package ru.fefu.video_tests_impl.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.fefu.video_tests_api.VideoTestApi
import ru.fefu.video_tests_impl.entities.TestType
import ru.fefu.video_tests_impl.presentation.GuideScreen
import ru.fefu.video_tests_impl.presentation.VideoScreen
import ru.fefu.video_tests_impl.presentation.VideoTestViewModel
import javax.inject.Inject
import javax.inject.Singleton

private const val VIDEO_BASE_ROUTE = "video_test"
private const val GUIDE_SCREEN_ROUTE = "video_guide_screen"
private const val VIDEO_SCREEN_ROUTE = "video_camera_screen"
internal const val TEST_TYPE_KEY = "testType"
private const val GRAPH_ROUTE = "$VIDEO_BASE_ROUTE/{$TEST_TYPE_KEY}"

@Singleton
class VideoTestsImpl @Inject constructor() : VideoTestApi {

    override val fingersTappingTestRoute = VIDEO_BASE_ROUTE + "/" + TestType.FingersTapping.name
    override val brushMovementsTestRoute = VIDEO_BASE_ROUTE + "/" + TestType.BrushMovements.name
    override val footTappingTestRoute = VIDEO_BASE_ROUTE + "/" + TestType.FootTapping.name
    override val legsMobilityTestRoute = VIDEO_BASE_ROUTE + "/" + TestType.LegsMobility.name
    override val gettingUpFromChairTestRoute =
        VIDEO_BASE_ROUTE + "/" + TestType.GettingUpFromChair.name
    override val gaitTestRoute = VIDEO_BASE_ROUTE + "/" + TestType.Gait.name
    override val posturalHandsTremorTestRoute =
        VIDEO_BASE_ROUTE + "/" + TestType.PosturalHandsTremor.name
    override val kineticHandsTremorTestRoute =
        VIDEO_BASE_ROUTE + "/" + TestType.KineticHandsTremor.name
    override val facialExpressivenessTestRoute =
        VIDEO_BASE_ROUTE + "/" + TestType.FacialExpressiveness.name
    override val brushPronationSupinationTestRoute =
        VIDEO_BASE_ROUTE + "/" + TestType.BrushPronationSupination.name
    override val amplitudeOfRestingTremorTestRoute =
        VIDEO_BASE_ROUTE + "/" + TestType.AmplitudeOfRestingTremor.name

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = GUIDE_SCREEN_ROUTE,
            route = GRAPH_ROUTE
        ) {
            composable(route = GUIDE_SCREEN_ROUTE) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(GRAPH_ROUTE)
                }
                val parentViewModel = hiltViewModel<VideoTestViewModel>(parentEntry)

                GuideScreen(
                    viewModel = parentViewModel,
                    onNavigateToVideoScreen = { navController.navigate(VIDEO_SCREEN_ROUTE) },
                    modifier = modifier
                )
            }
            composable(route = VIDEO_SCREEN_ROUTE) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(GRAPH_ROUTE)
                }
                val parentViewModel = hiltViewModel<VideoTestViewModel>(parentEntry)

                VideoScreen(viewModel = parentViewModel, modifier = modifier.fillMaxSize())
            }
        }
    }
}