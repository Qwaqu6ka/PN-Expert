package ru.fefu.photo_tests_impl.navigation

import android.net.Uri
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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
import ru.fefu.photo_tests_impl.presentation.last_photo_screen.LastPhotoScreenViewModel
import ru.fefu.photo_tests_impl.presentation.photo_test_screen.PhotoTestScreen
import ru.fefu.photo_tests_impl.presentation.photo_test_screen.PhotoTestScreenViewModel
import ru.fefu.photo_tests_impl.presentation.result_screen.ResultScreen
import ru.fefu.viewModelCreator
import java.lang.NumberFormatException
import java.net.URLDecoder
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
    lateinit var guideScreenViewModelFactory: GuideScreenViewModel.Factory

    @Inject
    lateinit var lastPhotoViewModelFactory: LastPhotoScreenViewModel.Factory

    @Inject
    lateinit var photoTestViewModelFactory: PhotoTestScreenViewModel.Factory

    override val route: String = GRAPH_ROUTE

    private var testType = PhotoTestType.ClockPhotoTest

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = route,
            startDestination = TEST_RESULT_ROUTE
        ){
            composable(GUIDE_ROUTE) {
                val viewModel = viewModelCreator {
                    guideScreenViewModelFactory.create(testType)
                }
                GuideScreen(
                    modifier = modifier,
                    viewModel = viewModel,
                    onNavigateToTest = {navController.navigate("$TEST_ROUTE/1/ ")}
                )
            }

            composable(
                "$TEST_ROUTE/{testNumber}/{photoPath}",
                arguments = listOf(
                    navArgument("photoPath") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("testNumber") {
                        type = NavType.IntType
                    }
                ),
            ) { backStackEntry ->
                val viewModel = viewModelCreator {
                    photoTestViewModelFactory.create(testType)
                }
                val argumentPhoto = backStackEntry.arguments?.getString("photoPath")!!
                val argumentNumber = backStackEntry.arguments?.getInt("testNumber")!!

                viewModel.setTestPage(argumentNumber)

                if (argumentPhoto.isNotBlank()) {
                    val photoPath = Uri.parse(URLDecoder.decode(argumentPhoto))
                    viewModel.setPhotoPath(photoPath)
                }

                PhotoTestScreen(
                    viewModel = viewModel,
                    modifier = modifier,
                    onNavigateToGuide = {
                        navController.navigate(GUIDE_ROUTE) {
                            popUpTo(GUIDE_ROUTE) {
                                inclusive = false
                            }
                        }
                    },
                    onNavigateToCamera = { navController.navigate("$CAMERA_ROUTE/$argumentNumber") },
                    onNavigateToNextPage = {
                        val nextTestPage = argumentNumber + 1
                        navController.navigate("${TEST_ROUTE}/$nextTestPage/ ")
                    },
                    onNavigateToResult = {navController.navigate(TEST_RESULT_ROUTE)}
                )
            }

            composable(
                "$CAMERA_ROUTE/{testNumber}",
                arguments = listOf(
                    navArgument("testNumber"){
                        type = NavType.IntType
                    }
                )
            ) {backStackEntry->

                val argumentNumber = backStackEntry.arguments?.getInt("testNumber")!!

                CameraScreen(
                    modifier = modifier,
                    onNavigateToPhotoResult = { photoPath: String ->
                        val encodedUrl = URLEncoder.encode(photoPath, StandardCharsets.UTF_8.toString())
                        navController.navigate("$PHOTO_RESULT_ROUTE/$argumentNumber/$encodedUrl")
                    }
                )
            }

            composable(
                route = "$PHOTO_RESULT_ROUTE/{testNumber}/{photoPath}",
                arguments = listOf(
                    navArgument("photoPath") {
                        type = NavType.StringType
                    },
                    navArgument("testNumber") {
                        type = NavType.IntType
                    }
                ),
            ) { backStackEntry->
                val uri= Uri.parse(backStackEntry.arguments?.getString("photoPath")!!)
                val argumentNumber = backStackEntry.arguments?.getInt("testNumber")!!

                val viewModel = viewModelCreator {
                    lastPhotoViewModelFactory.create(uri)
                }
                LastPhotoScreen(
                    viewModel = viewModel,
                    modifier = modifier,
                    onNavigateToCamera = {navController.navigate(CAMERA_ROUTE)},
                    onNavigateToTest = {photoPath: String ->
                        val encodedUrl = URLEncoder.encode(photoPath, StandardCharsets.UTF_8.toString())
                        navController.navigate("$TEST_ROUTE/$argumentNumber/$encodedUrl"){
                            navController.popBackStack()
                            navController.popBackStack()
                            navController.popBackStack()
                        }
                    }
                )
            }

            composable(
                route = TEST_RESULT_ROUTE,
            ){
                ResultScreen(modifier = modifier)
            }
        }
    }
}