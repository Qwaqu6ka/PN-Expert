package ru.fefu.pnexpert.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.fefu.theme.ApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ActivityScopeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            viewModel.isUiReady.value == false
        }

        setContent {
            ApplicationTheme {
                AppContent(viewModel)
            }
        }
    }
}

// TODO: DELETE
@Composable
fun InDevPlug(modifier: Modifier = Modifier, testStr: String = "", onClick: (() -> Unit)? = null) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Раздел в разработке $testStr")
            onClick?.let {
                Button(onClick = it) {
                    Text(text = "Тык")
                }
            }
        }
    }
}