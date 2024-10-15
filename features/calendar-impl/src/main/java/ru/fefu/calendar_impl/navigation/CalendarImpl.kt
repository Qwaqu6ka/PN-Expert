package ru.fefu.calendar_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.fefu.calendar_api.CalendarApi
import ru.fefu.calendar_impl.presentation.CalendarScreen

private const val CALENDAR_PAGE_ROUTE = "calendarPage"

class CalendarImpl : CalendarApi {

    override val route: String = CALENDAR_PAGE_ROUTE

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            CalendarScreen(
                modifier = modifier,
                onBackNavigate = { navController.popBackStack() },
                onTestNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(0)     // todo нужно ли чистить стек?
                    }
                }
            )
        }
    }
}