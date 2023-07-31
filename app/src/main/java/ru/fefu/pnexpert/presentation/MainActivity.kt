package ru.fefu.pnexpert.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.fefu.pnexpert.main.presentation.screens.MainScreen
import ru.fefu.theme.PnExpertTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var isUiReady: Boolean by mutableStateOf(false)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                delay(2000)
                isUiReady = true
            }
        }

        splashScreen.setKeepOnScreenCondition {
            !isUiReady
        }

        setContent {
            PnExpertTheme {

                //painted system controllers
                val systemUiController = rememberSystemUiController()
                val barBackground = PnExpertTheme.colors.mainAppColors.AppWhiteColor

                //painted system upp & bottom panels
                SideEffect {
                    systemUiController.setStatusBarColor(color = barBackground)
                    systemUiController.setNavigationBarColor(color = barBackground)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainScreen()
//                    InitializationScreens()
                }
            }
        }
    }
}