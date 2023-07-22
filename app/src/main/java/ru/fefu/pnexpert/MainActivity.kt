package ru.fefu.pnexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme


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
            PnExpertTheme() {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = PnExpertTheme.colors.mainAppColors.AppBlueColor
                ) {
                    Column() {
                        Text(
                            text = "Дарова",
                            style = PnExpertTheme.typography.title.medium_32
                        )
                        Text(
                            text = "Дарова",
                            style = PnExpertTheme.typography.title.bold_32
                        )
                    }
                }
            }
        }
    }
}