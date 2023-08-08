package ru.fefu.pnexpert.presentation.main.screens.events_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.fefu.pnexpert.R
import ru.fefu.pnexpert.presentation.main.components.Toolbar
import ru.fefu.theme.PnExpertTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainTab() {
    Scaffold(
        topBar = {Toolbar(title = stringResource(id = R.string.events))},
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor
    ) {scaffoldPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            Spacer(modifier = Modifier.height(scaffoldPadding.calculateTopPadding()))
            for (i in 1..4){
                LongActionCard()
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun LongActionCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        colors = CardDefaults.cardColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppBlueColor
        ),
        shape = PnExpertTheme.shapes.imageShapes.imageClassic15
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.6f)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Оценка текущего состояния по UPDRS",
                style = PnExpertTheme.typography.subtitle.medium_18,
                color = PnExpertTheme.colors.textColors.FontWhiteColor
            )

            Text(
                text = "8 из 16 заданий",
                style = PnExpertTheme.typography.text.regular_14,
                color = PnExpertTheme.colors.textColors.FontWhiteColor
            )
        }
    }
}