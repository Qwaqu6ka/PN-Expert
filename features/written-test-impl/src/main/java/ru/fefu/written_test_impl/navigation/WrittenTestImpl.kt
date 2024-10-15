package ru.fefu.written_test_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.fefu.written_test_api.WrittenTestApi
import ru.fefu.written_test_impl.domain.TestType
import ru.fefu.written_test_impl.presentation.WrittenTest

private const val WRITTEN_TEST_GRAPH_ROUTE = "writtenTest"
internal const val ARG_WRITTEN_TEST_TYPE = "writtenTestType"

class WrittenTestImpl : WrittenTestApi {

    override val testUpdrs1Route = "$WRITTEN_TEST_GRAPH_ROUTE/${TestType.UPDRS1.name}"
    override val testUpdrs2Route = "$WRITTEN_TEST_GRAPH_ROUTE/${TestType.UPDRS2.name}"
    override val testUpdrs3Route = "$WRITTEN_TEST_GRAPH_ROUTE/${TestType.UPDRS3.name}"
    override val testUpdrs4Route = "$WRITTEN_TEST_GRAPH_ROUTE/${TestType.UPDRS4.name}"
    override val testPdq39Route = "$WRITTEN_TEST_GRAPH_ROUTE/${TestType.PDQ39.name}"
    override val testHadsRoute = "$WRITTEN_TEST_GRAPH_ROUTE/${TestType.HADS.name}"
    override val testSchwabEnglandRoute = "$WRITTEN_TEST_GRAPH_ROUTE/${TestType.SchwabEngland.name}"
    override val testHoehnYahrRoute = "$WRITTEN_TEST_GRAPH_ROUTE/${TestType.HoehnYahr.name}"
    override val testFabRoute = "$WRITTEN_TEST_GRAPH_ROUTE/${TestType.FAB.name}"
    override val testPsqiRoute = "$WRITTEN_TEST_GRAPH_ROUTE/${TestType.PSQI.name}"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable("$WRITTEN_TEST_GRAPH_ROUTE/{$ARG_WRITTEN_TEST_TYPE}") {
            WrittenTest(onNavigateBack = navController::popBackStack, modifier = modifier)
        }
    }
}