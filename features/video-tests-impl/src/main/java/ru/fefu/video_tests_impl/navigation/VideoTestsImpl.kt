package ru.fefu.video_tests_impl.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.fefu.video_tests_api.VideoTestApi
import ru.fefu.video_tests_impl.entities.TestType
import ru.fefu.video_tests_impl.presentation.GuideScreen
import ru.fefu.video_tests_impl.presentation.VideoScreen
import ru.fefu.video_tests_impl.presentation.VideoTestViewModel
import javax.inject.Inject
import javax.inject.Singleton

private const val VIDEO_TESTS_ROUTE = "video_test"
private const val GUIDE_SCREEN_ROUTE = "guide"
private const val VIDEO_SCREEN_ROUTE = "video"

@Singleton
class VideoTestsImpl @Inject constructor() : VideoTestApi {

    override val facialExpressivenessTestRoute =
        "$VIDEO_TESTS_ROUTE/${TestType.FacialExpressiveness}"
    override val fingersTappingTestRoute = "$VIDEO_TESTS_ROUTE/${TestType.FingersTapping}"
    override val brushMovementsTestRoute = "$VIDEO_TESTS_ROUTE/${TestType.BrushMovements}"
    override val brushPronationSupinationTestRoute =
        "$VIDEO_TESTS_ROUTE/${TestType.BrushPronationSupination}"
    override val footTappingTestRoute = "$VIDEO_TESTS_ROUTE/${TestType.FootTapping}"
    override val legsMobilityTestRoute = "$VIDEO_TESTS_ROUTE/${TestType.LegsMobility}"
    override val gettingUpFromChairTestRoute = "$VIDEO_TESTS_ROUTE/${TestType.GettingUpFromChair}"
    override val gaitTestRoute = "$VIDEO_TESTS_ROUTE/${TestType.Gait}"
    override val posturalHandsTremorTestRoute = "$VIDEO_TESTS_ROUTE/${TestType.PosturalHandsTremor}"
    override val kineticHandsTremorTestRoute = "$VIDEO_TESTS_ROUTE/${TestType.KineticHandsTremor}"
    override val amplitudeOfRestingTremorTestRoute =
        "$VIDEO_TESTS_ROUTE/${TestType.AmplitudeOfRestingTremor}"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = GUIDE_SCREEN_ROUTE,
            route = "$VIDEO_TESTS_ROUTE/{testType}"
        ) {
            val arguments = listOf(navArgument("testType") { type = NavType.StringType })

            composable(route = GUIDE_SCREEN_ROUTE, arguments = arguments) {
                val viewModel: VideoTestViewModel = hiltViewModel()
                GuideScreen(viewModel)
            }
            composable(route = VIDEO_SCREEN_ROUTE, arguments = arguments) {
                VideoScreen()
            }
        }
    }
}