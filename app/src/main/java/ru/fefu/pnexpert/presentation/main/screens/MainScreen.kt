package ru.fefu.pnexpert.presentation.main.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.fefu.pnexpert.presentation.main.navigation.MainScreenNavGraph
import ru.fefu.pnexpert.presentation.main.navigation.bottomNavTabItems
import ru.fefu.theme.PnExpertTheme

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val bottomBarBackgroundColor = PnExpertTheme.colors.mainAppColors.AppBlueColor
    val uppBarBackgroundColor = PnExpertTheme.colors.mainAppColors.AppGreyLightColor

    //painted system upp & bottom panels
    SideEffect {
        systemUiController.setNavigationBarColor(color = bottomBarBackgroundColor)
        systemUiController.setStatusBarColor(color = uppBarBackgroundColor)
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                tabItems = bottomNavTabItems,
                backgroundColor = bottomBarBackgroundColor
            )
        }
    ) { innerPadding ->
        MainScreenNavGraph(navController, innerPadding)
    }
}

// TODO: DELETE
@Composable
fun InDevPlug(testStr: String = "") {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Раздел в разработке $testStr")
    }
}