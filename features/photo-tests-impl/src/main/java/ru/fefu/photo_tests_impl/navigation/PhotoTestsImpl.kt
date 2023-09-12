package ru.fefu.photo_tests_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.fefu.photo_tests_api.PhotoTestsApi
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.presentation.guide_screen.GuideScreen
import ru.fefu.photo_tests_impl.presentation.guide_screen.GuideScreenViewModel
import ru.fefu.presentation.viewModelCreator
import javax.inject.Inject
import javax.inject.Singleton

private const val GRAPH_ROUTE = "photoTestsGraph"

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
        navGraphBuilder.composable(route){
            val viewModel = viewModelCreator {
                photoTestsViewModelFactory.create(PhotoTestType.ClockPhotoTest)
            }
            GuideScreen(
                modifier = modifier,
                viewModel = viewModel,
            )
        }
    }
}