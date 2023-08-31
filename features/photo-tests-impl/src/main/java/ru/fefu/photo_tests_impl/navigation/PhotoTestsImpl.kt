package ru.fefu.photo_tests_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.fefu.photo_tests_api.PhotoTestsApi
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.presentation.guide_screen.GuideScreen
import ru.fefu.photo_tests_impl.presentation.guide_screen.GuideScreenViewModel
import ru.fefu.photo_tests_impl.presentation.photo_test.PhotoTestScreen
import ru.fefu.viewModelCreator
import javax.inject.Inject
import javax.inject.Singleton

private const val GRAPH_ROUTE = "photoTestsGraph"
private const val GUIDE_ROUTE = "guideRoute"
private const val TEST_ROUTE = "testRoute"
private const val RESULT_ROUTE = "resultRoute"
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
            startDestination = GUIDE_ROUTE
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
                    onNavigateToGuide = {navController.navigate(GUIDE_ROUTE)}
                )
            }
        }
    }
}