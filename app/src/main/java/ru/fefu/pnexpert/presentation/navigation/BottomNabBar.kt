package ru.fefu.pnexpert.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun BottomNavBar(navController: NavController, tabItems: List<BottomNavTab>) {
    BottomNavigation(
        modifier = Modifier.background(PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        tabItems.forEach { tab ->

            val isTabSelected = currentDestination?.hierarchy?.any { it.route == tab.route } == true
            val tabButtonContentColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = tab.iconRes),
                        contentDescription = stringResource(tab.titleRes),
                        tint = tabButtonContentColor,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(tab.titleRes),
                        color = tabButtonContentColor,
                        fontSize = 11.sp
                    )
                },
                selected = isTabSelected,
                onClick = {
                    navController.navigate(tab.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                modifier = Modifier.background(
                    if (isTabSelected)
                        PnExpertTheme.colors.buttonColors.ButtonPressedBlueColor
                    else PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor
                )
            )
        }
    }
}