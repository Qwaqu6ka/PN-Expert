package ru.fefu.photo_tests_impl.navigation

import android.net.Uri
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.fefu.photo_tests_api.PhotoTestsApi
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.presentation.camera_screen.CameraScreen
import ru.fefu.photo_tests_impl.presentation.guide_screen.GuideScreen
import ru.fefu.photo_tests_impl.presentation.guide_screen.GuideScreenViewModel
import ru.fefu.photo_tests_impl.presentation.last_photo_screen.LastPhotoScreen
import ru.fefu.photo_tests_impl.presentation.photo_test_screen.PhotoTestScreen
import ru.fefu.viewModelCreator
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import javax.inject.Singleton

private const val GRAPH_ROUTE = "photoTestsGraph"
private const val GUIDE_ROUTE = "guideRoute"
private const val TEST_ROUTE = "testRoute"
private const val TEST_RESULT_ROUTE = "resultRoute"
private const val PHOTO_RESULT_ROUTE = "photoResultRoute"
private const val CAMERA_ROUTE = "cameraRoute"

@Singleton
class PhotoTestsImpl @Inject constructor():PhotoTestsApi {
    @Inject
    lateinit var photoTestsViewModelFactory: GuideScreenViewModel.Factory

    override val route: String = GRAPH_ROUTE
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = route,
            startDestination = CAMERA_ROUTE
        ){
            composable(GUIDE_ROUTE) {
                val viewModel = viewModelCreator {
                    photoTestsViewModelFactory.create(PhotoTestType.ClockPhotoTest)
                }
                GuideScreen(
                    modifier = modifier,
                    viewModel = viewModel,
                    onNavigateToTest = {navController.navigate(TEST_ROUTE)}
                )
            }

            composable(TEST_ROUTE) {
                PhotoTestScreen(
                    modifier = modifier,
                    onNavigateToGuide = {navController.navigate(GUIDE_ROUTE)},
                    onNavigateToCamera = {navController.navigate(CAMERA_ROUTE)}
                )
            }

            composable(CAMERA_ROUTE) {
                CameraScreen(
                    modifier = modifier,
                    onNavigateToPhotoResult = { photoPath: String ->
                        val encodedUrl = URLEncoder.encode(photoPath, StandardCharsets.UTF_8.toString())
                        navController.navigate("$PHOTO_RESULT_ROUTE/$encodedUrl")
                    }
                )
            }

            composable(
                route = "$PHOTO_RESULT_ROUTE/{photoPath}",
                arguments = listOf(navArgument("photoPath") { type = NavType.StringType })
            ) { backStackEntry->
                val photoPath = Uri.parse(backStackEntry.arguments?.getString("photoPath")!!)
                LastPhotoScreen(
                    modifier = modifier,
                    photoPath = photoPath,
                    onNavigateToCamera = {navController.navigate(CAMERA_ROUTE)}
                )
            }
        }
    }
}