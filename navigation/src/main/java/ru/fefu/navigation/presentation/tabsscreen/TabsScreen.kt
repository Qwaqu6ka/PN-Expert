package ru.fefu.navigation.presentation.tabsscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.fefu.navigation.presentation.navigation.bottomNavTabItems
import ru.fefu.theme.PnExpertTheme

@Composable
fun TabsScreen() {
    val systemUiController = rememberSystemUiController()
    val bottomBarBackgroundColor = PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor
    SideEffect {
        systemUiController.setNavigationBarColor(color = bottomBarBackgroundColor)
    }

    BottomAppBar {

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

@Preview
@Composable
fun TabsScreenPreview() {
    TabsScreen()
}

// TODO: DELETE
@Composable
fun InDevPlug(testStr: String = "") {
    rememberCompositionContext()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Раздел в разработке $testStr")
    }
}