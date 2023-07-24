package ru.fefu.pnexpert.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.fefu.pnexpert.presentation.navigation.BottomNavBar
import ru.fefu.pnexpert.presentation.navigation.MainScreenNavGraph
import ru.fefu.pnexpert.presentation.navigation.bottomNavTabItems

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavBar(navController, bottomNavTabItems) }
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