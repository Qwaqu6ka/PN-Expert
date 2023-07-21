package ru.fefu.pnexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import ru.fefu.pnexpert.ui.theme.PnExpertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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