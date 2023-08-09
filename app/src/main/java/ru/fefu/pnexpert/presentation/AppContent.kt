package ru.fefu.pnexpert.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.fefu.pnexpert.navigation.AppNavGraph
import ru.fefu.theme.PnExpertTheme

@Composable
fun AppContent() {
    val tabs = remember { BottomTabs.values() }
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController, tabItems = tabs) }
    ) { innerPaddingModifier ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPaddingModifier)
        )
    }
}

@Composable
fun BottomNavBar(navController: NavController, tabItems: Array<BottomTabs>) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomTabsRoutes = remember { BottomTabs.values().map { it.route } }

    val showBottomTabs = currentDestination?.hierarchy?.any { destination ->
        destination.route in bottomTabsRoutes
    } == true

    if (showBottomTabs) {
        BottomNavigation {
            tabItems.forEach { tab ->
                val isTabSelected =
                    currentDestination?.hierarchy?.any { it.route == tab.route } == true
                val tabButtonContentColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor

                BottomNavigationItem(
                    icon = {
                        Row {
                            Icon(
                                painter = painterResource(id = tab.iconRes),
                                contentDescription = stringResource(tab.titleRes),
                                tint = tabButtonContentColor,
                                modifier = Modifier.padding(bottom = 3.dp, end = 0.dp)
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(tab.titleRes),
                            color = tabButtonContentColor,
                            fontSize = 9.sp
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
                        if (isTabSelected) PnExpertTheme.colors.buttonColors.ButtonPressedBlueColor
                        else PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor
                    ),
                    // color of ripple
                    selectedContentColor = PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor
                )
            }
        }
    }
}