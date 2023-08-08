package ru.fefu.pnexpert.presentation.main.screens.events_screen

import android.annotation.SuppressLint
import android.icu.number.Scale
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.fefu.pnexpert.R
import ru.fefu.pnexpert.presentation.main.components.Toolbar
import ru.fefu.theme.PnExpertTheme

data class LongCardData(val image: Int, val title: String, val subtitle: String)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainTab() {

    //variables
    val longCardsData = listOf(
        LongCardData(R.drawable.event_one_updrs, "Оценка текущего состояния по UPDRS", "8 из 16 заданий"),
        LongCardData(R.drawable.event_two_warning, "Строка предупреждений", "Смотреть"),
        LongCardData(R.drawable.event_three_chat, "Чат с врачем", "Смотреть"),
        LongCardData(R.drawable.event_four_records, "Ваши показатели выше чем у 75% пользователей!", "Смотреть"),
        LongCardData(R.drawable.event_five_pils, "Лекартсвенная терапия", "Смотреть"),
    )

    Scaffold(
        topBar = {Toolbar(title = stringResource(id = R.string.events))},
        containerColor = PnExpertTheme.colors.mainAppColors.AppGreyLightColor
    ) {scaffoldPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            Spacer(modifier = Modifier.height(scaffoldPadding.calculateTopPadding()))
            for (cardData in longCardsData){
                LongActionCard(cardData)
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                BoxCard()
                Spacer(modifier = Modifier.width(8.dp))
                BoxCard()
            }
        }
    }
}

@Composable
fun BoxCard() {
    Card(
        modifier = Modifier
            .size(165.dp)
            .shadow(1.dp, PnExpertTheme.shapes.imageShapes.imageClassic15),
        shape = PnExpertTheme.shapes.imageShapes.imageClassic15,
        colors = CardDefaults.cardColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Диагностическая программа мероприятий",
                style = PnExpertTheme.typography.text.medium_14,
                color = PnExpertTheme.colors.textColors.FontDarkColor,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.event_box_columns),
                contentDescription = null
            )
        }
    }
}


@Composable
private fun LongActionCard(data: LongCardData){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .shadow(6.dp, PnExpertTheme.shapes.imageShapes.imageClassic15),
        shape = PnExpertTheme.shapes.imageShapes.imageClassic15
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(PnExpertTheme.shapes.imageShapes.imageClassic15),
                painter = painterResource(id = data.image),
                contentScale = ContentScale.FillHeight,
                contentDescription = null,
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.6f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = data.title,
                    style = PnExpertTheme.typography.subtitle.medium_18,
                    color = PnExpertTheme.colors.textColors.FontWhiteColor
                )

                Text(
                    text = data.subtitle,
                    style = PnExpertTheme.typography.text.regular_14,
                    color = PnExpertTheme.colors.textColors.FontWhiteColor
                )
            }
        }
    }
}