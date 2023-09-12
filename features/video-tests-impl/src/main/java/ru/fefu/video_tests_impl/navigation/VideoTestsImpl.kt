package ru.fefu.video_tests_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import ru.fefu.video_tests_api.VideoTestApi
import javax.inject.Inject
import javax.inject.Singleton

private const val VIDEO_TESTS_ROUTE = "video_test"

@Singleton
class VideoTestsImpl @Inject constructor() : VideoTestApi {

    override val standUpTestRoute = "$VIDEO_TESTS_ROUTE/"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        TODO("Not yet implemented")
    }
}