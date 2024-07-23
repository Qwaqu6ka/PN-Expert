package ru.fefu.video_tests_impl.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.fefu.video_tests_api.VideoTestApi
import ru.fefu.video_tests_impl.domain.TestType
import ru.fefu.video_tests_impl.presentation.GuideScreen
import ru.fefu.video_tests_impl.presentation.VideoScreen
import ru.fefu.video_tests_impl.presentation.VideoTestViewModel
import javax.inject.Inject
import javax.inject.Singleton

internal const val ARG_TEST_TYPE = "videoTestType"
private const val VIDEO_TESTS_ROOT_ROUTE = "videoTests"
private const val GRAPH_ROUTE = "$VIDEO_TESTS_ROOT_ROUTE/{$ARG_TEST_TYPE}"
private const val GUIDE_SCREEN_ROUTE = "videoTests_guideScreen"
private const val VIDEO_SCREEN_ROUTE = "videoTests_cameraScreen"

@Singleton
class VideoTestsImpl @Inject constructor() : VideoTestApi {

    override val fingersTappingTestRoute = "$VIDEO_TESTS_ROOT_ROUTE/${TestType.FingersTapping.name}"
    override val brushMovementsTestRoute = "$VIDEO_TESTS_ROOT_ROUTE/${TestType.BrushMovements.name}"
    override val footTappingTestRoute = "$VIDEO_TESTS_ROOT_ROUTE/${TestType.FootTapping.name}"
    override val legsMobilityTestRoute = "$VIDEO_TESTS_ROOT_ROUTE/${TestType.LegsMobility.name}"
    override val gaitTestRoute = "$VIDEO_TESTS_ROOT_ROUTE/${TestType.Gait.name}"
    override val gettingUpFromChairTestRoute =
        "$VIDEO_TESTS_ROOT_ROUTE/${TestType.GettingUpFromChair.name}"
    override val posturalHandsTremorTestRoute =
        "$VIDEO_TESTS_ROOT_ROUTE/${TestType.PosturalHandsTremor.name}"
    override val kineticHandsTremorTestRoute =
        "$VIDEO_TESTS_ROOT_ROUTE/${TestType.KineticHandsTremor.name}"
    override val facialExpressivenessTestRoute =
        "$VIDEO_TESTS_ROOT_ROUTE/${TestType.FacialExpressiveness.name}"
    override val brushPronationSupinationTestRoute =
        "$VIDEO_TESTS_ROOT_ROUTE/${TestType.BrushPronationSupination.name}"
    override val amplitudeOfRestingTremorTestRoute =
        "$VIDEO_TESTS_ROOT_ROUTE/${TestType.AmplitudeOfRestingTremor.name}"

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

                GuideScreen(
                    viewModel = hiltViewModel<VideoTestViewModel>(parentEntry),
                    onNavigateToVideoScreen = { navController.navigate(VIDEO_SCREEN_ROUTE) },
                    modifier = modifier
                )
            }
            composable(route = VIDEO_SCREEN_ROUTE) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(GRAPH_ROUTE)
                }

                VideoScreen(
                    viewModel = hiltViewModel<VideoTestViewModel>(parentEntry),
                    modifier = modifier
                )
            }
        }
    }
}