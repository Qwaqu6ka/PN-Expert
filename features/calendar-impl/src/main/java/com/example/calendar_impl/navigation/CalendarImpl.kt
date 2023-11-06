package com.example.calendar_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.calendar_api.CalendarApi
import com.example.calendar_impl.presentation.CalendarApp
import javax.inject.Inject
import javax.inject.Singleton


private const val CALENDAR_PAGE_ROUTE = "calendarPageRoute"
@Singleton
class CalendarImpl @Inject constructor() : CalendarApi {
    override val route: String = CALENDAR_PAGE_ROUTE
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route){
            CalendarApp(modifier = modifier, onBackNavigate = {
                navController.popBackStack()
            },onTaskNavigate = {
                navController.navigate(it){
                    popUpTo(0)
                }

            })
        }
    }
}