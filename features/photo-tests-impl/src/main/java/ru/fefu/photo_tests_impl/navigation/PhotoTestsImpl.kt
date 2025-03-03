package ru.fefu.photo_tests_impl.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.koin.androidx.compose.koinViewModel
import ru.fefu.photo_tests_api.PhotoTestsApi
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.presentation.CameraScreen
import ru.fefu.photo_tests_impl.presentation.GuideScreen
import ru.fefu.photo_tests_impl.presentation.PhotoResultScreen
import ru.fefu.photo_tests_impl.presentation.TaskScreen
import ru.fefu.photo_tests_impl.presentation.TestResultScreen
import javax.inject.Inject
import javax.inject.Singleton

internal const val ARG_PHOTO_TEST_TYPE = "photoTestType"
private const val GRAPH_ROOT_ROUTE = "photoTests"
private const val GRAPH_ROUTE = "$GRAPH_ROOT_ROUTE/{$ARG_PHOTO_TEST_TYPE}"
private const val GUIDE_SCREEN_ROUTE = "photoTests_guideScreen"
private const val TASK_SCREEN_ROUTE = "photoTests_taskScreen"
private const val CAMERA_SCREEN_ROUTE = "photoTests_cameraScreen"
private const val PHOTO_RESULT_SCREEN_ROUTE = "photoTests_photoResultScreen"
private const val TEST_RESULT_SCREEN_ROUTE = "photoTests_testResultScreen"

@Singleton
class PhotoTestsImpl @Inject constructor() : PhotoTestsApi {

    override val clockPhotoTestRoute = "$GRAPH_ROOT_ROUTE/${PhotoTestType.Clock.name}"
    override val facePhotoTestRoute = "$GRAPH_ROOT_ROUTE/${PhotoTestType.Face.name}"
    override val fullLengthPhotoTestRoute = "$GRAPH_ROOT_ROUTE/${PhotoTestType.FullLength.name}"
    override val handwritingPhotoTestRoute = "$GRAPH_ROOT_ROUTE/${PhotoTestType.Handwriting.name}"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = GRAPH_ROUTE,
            startDestination = GUIDE_SCREEN_ROUTE
        ) {

            composable(GUIDE_SCREEN_ROUTE) {
                val parentEntry = remember(it) { navController.getBackStackEntry(GRAPH_ROUTE) }
                GuideScreen(
                    modifier = modifier,
                    onBackPressed = { navController.popBackStack() },
                    onNavigateToTest = { navController.navigate(TASK_SCREEN_ROUTE) },
                    viewModel = koinViewModel(viewModelStoreOwner = parentEntry)
                )
            }

            composable(TASK_SCREEN_ROUTE) {
                val parentEntry = remember(it) { navController.getBackStackEntry(GRAPH_ROUTE) }
                TaskScreen(
                    modifier = modifier,
                    onPopBackStack = { navController.popBackStack() },
                    onNavigateToCamera = { navController.navigate(CAMERA_SCREEN_ROUTE) },
                    onNavigateToTestResult = { navController.navigate(TEST_RESULT_SCREEN_ROUTE) },
                    viewModel = koinViewModel(viewModelStoreOwner = parentEntry)
                )
            }

            composable(CAMERA_SCREEN_ROUTE) {
                val parentEntry = remember(it) { navController.getBackStackEntry(GRAPH_ROUTE) }
                CameraScreen(
                    modifier = modifier,
                    onNavigateToPhotoResult = { navController.navigate(PHOTO_RESULT_SCREEN_ROUTE) },
                    viewModel =koinViewModel(viewModelStoreOwner = parentEntry)
                )
            }

            composable(PHOTO_RESULT_SCREEN_ROUTE) {
                val parentEntry = remember(it) { navController.getBackStackEntry(GRAPH_ROUTE) }
                PhotoResultScreen(
                    modifier = modifier,
                    onBackPressed = { navController.popBackStack() },
                    onNavigateToTaskScreen = {
                        navController.navigate(route = TASK_SCREEN_ROUTE) {
                            popUpTo(TASK_SCREEN_ROUTE)
                        }
                    },
                    viewModel = koinViewModel(viewModelStoreOwner = parentEntry)
                )
            }

            composable(TEST_RESULT_SCREEN_ROUTE) {
                val parentEntry = remember(it) { navController.getBackStackEntry(GRAPH_ROUTE) }
                TestResultScreen(
                    modifier = modifier,
                    viewModel = koinViewModel(viewModelStoreOwner = parentEntry)
                )
            }
        }
    }
}