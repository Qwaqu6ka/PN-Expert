package ru.fefu.pnexpert.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.fefu.pnexpert.presentation.main.screens.MainScreen
import ru.fefu.pnexpert.presentation.initialization.InitializationScreens
import ru.fefu.pnexpert.presentation.initialization.registration.RegistrationViewModel
import ru.fefu.pnexpert.presentation.initialization.registration.manual.ManualScreen
import ru.fefu.theme.PnExpertTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        //splash screen
        splashScreen.setKeepOnScreenCondition {
            !viewModel.isUiReady.value
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
                    InitializationScreens()
                }
            }
        }
    }
}