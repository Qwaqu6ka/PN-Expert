package ru.fefu.pnexpert.main.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.fefu.pnexpert.main.presentation.navigation.BottomNavTab
import ru.fefu.pnexpert.main.presentation.navigation.MarkedBottomNabTab
import ru.fefu.pnexpert.presentation.theme.BadgeColor
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun BottomNavBar(navController: NavController, tabItems: List<BottomNavTab>, backgroundColor: Color) {
    BottomNavigation(
        backgroundColor = backgroundColor
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        tabItems.forEach { tab ->

            val isTabSelected = currentDestination?.hierarchy?.any { it.route == tab.route } == true
            val tabButtonContentColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor
            val isTabMarked = tab is MarkedBottomNabTab && tab.isMarked

            BottomNavigationItem(
                icon = {
                    Row {
                        Icon(
                            painter = painterResource(id = tab.iconRes),
                            contentDescription = stringResource(tab.titleRes),
                            tint = tabButtonContentColor,
                            modifier = Modifier.padding(bottom = 3.dp, end = 0.dp)
                        )
                        if (isTabMarked) {
                            Spacer(
                                modifier = Modifier
                                    .drawBehind {
                                        drawCircle(BadgeColor, 8f)
                                    }
                            )
                        }
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
                selectedContentColor = PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor  // color of ripple
            )
        }
    }
}