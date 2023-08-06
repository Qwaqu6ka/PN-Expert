package ru.fefu.pnexpert.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import ru.fefu.pnexpert.PnExpertApp
import ru.fefu.pnexpert.presentation.initialization.InitializationScreens
import ru.fefu.pnexpert.presentation.main.screens.MainScreen
import ru.fefu.theme.PnExpertTheme
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var application: PnExpertApp

    @SuppressLint("StateFlowValueCalledInComposition")
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
                    if (!application.isInitializationReady.value){
                        InitializationScreens()
                    }
                    else{
                       MainScreen()
                    }
                }
            }
        }
    }
}