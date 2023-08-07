package ru.fefu.navigation.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ru.fefu.navigation.presentation.tabsscreen.MainScreen
import ru.fefu.theme.PnExpertTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            viewModel.isUiReady.value == false
        }

        setContent {
            PnExpertTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ru.fefu.navigation.presentation.AppNavGraph()
                }
            }
        }
    }
}