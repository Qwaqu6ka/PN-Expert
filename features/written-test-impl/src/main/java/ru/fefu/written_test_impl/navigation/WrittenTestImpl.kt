package ru.fefu.written_test_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.fefu.written_test_api.WrittenTestApi
import ru.fefu.written_test_impl.entities.TestType
import ru.fefu.written_test_impl.presentation.WrittenTest
import javax.inject.Inject
import javax.inject.Singleton

private const val TEST_PAGE_ROUTE = "written_test"

@Singleton
class WrittenTestImpl @Inject constructor() : WrittenTestApi {

    override val testUpdrs1Route = TEST_PAGE_ROUTE + "/" + TestType.Updrs1.name
    override val testUpdrs2Route = TEST_PAGE_ROUTE + "/" + TestType.Updrs2.name
    override val testUpdrs3Route = TEST_PAGE_ROUTE + "/" + TestType.Updrs3.name
    override val testUpdrs4Route = TEST_PAGE_ROUTE + "/" + TestType.Updrs4.name
    override val testPdq39Route = TEST_PAGE_ROUTE + "/" + TestType.Pdq39.name
    override val testHadsRoute = TEST_PAGE_ROUTE + "/" + TestType.Hads.name
    override val testSchwabEnglandRoute = TEST_PAGE_ROUTE + "/" + TestType.SchwabEngland.name
    override val testHoehnYahrRoute = TEST_PAGE_ROUTE + "/" + TestType.HoehnYahr.name
    override val testFabRoute = TEST_PAGE_ROUTE + "/" + TestType.Fab.name
    override val testPsqiRoute = TEST_PAGE_ROUTE + "/" + TestType.Psqi.name

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable("$TEST_PAGE_ROUTE/{testType}") {
            WrittenTest(modifier = modifier)
        }
    }
}