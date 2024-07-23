package ru.fefu.main_impl.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.fefu.main_impl.R
import ru.fefu.components.PNExpertToolbar
import ru.fefu.theme.PnExpertTheme

internal data class LongCardData(val image: Int, val title: String, val subtitle: String)
internal data class BoxCardData(val image: Int, val title: String)

@Composable
internal fun MainTab(modifier: Modifier) {

    //variables
    val longCardsData = listOf(
        LongCardData(
            R.drawable.event_one_updrs,
            "Оценка текущего состояния по UPDRS",
            "8 из 16 заданий"
        ),
        LongCardData(R.drawable.event_two_warning, "Строка предупреждений", "Смотреть"),
        LongCardData(R.drawable.event_three_chat, "Чат с врачем", "Смотреть"),
        LongCardData(
            R.drawable.event_four_records,
            "Ваши показатели выше чем у 75% пользователей!",
            "Смотреть"
        ),
        LongCardData(R.drawable.event_five_pils, "Лекартсвенная терапия", "Смотреть"),
    )

    val boxCardsData = listOf(
        BoxCardData(R.drawable.event_box_columns, "Диагностическая программа мероприятий"),
        BoxCardData(R.drawable.event_box_graph, "Мониторинг"),
    )

    Scaffold(
        topBar = { PNExpertToolbar(title = stringResource(id = R.string.main)) },
        containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
        contentWindowInsets = WindowInsets(0.dp),
        modifier = modifier,
    ) { scaffoldPadding ->
        Surface(modifier = Modifier.padding(scaffoldPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                for (cardData in longCardsData) {
                    LongActionCard(cardData)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BoxCardDiagnostic(boxCardsData[0])
                    Spacer(modifier = Modifier.width(8.dp))
                    BoxCardMonitoring(boxCardsData[1])
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
internal fun BoxCardMonitoring(data: BoxCardData) {

    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .size(165.dp)
            .shadow(1.dp, PnExpertTheme.shapes.imageShapes.imageClassic15)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(radius = 400.dp),
                onClick = {

                },
            ),
        shape = PnExpertTheme.shapes.imageShapes.imageClassic15,
        colors = CardDefaults.cardColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = data.title,
                style = PnExpertTheme.typography.text.medium_14,
                color = PnExpertTheme.colors.textColors.FontDarkColor,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = data.image),
                contentDescription = null
            )
        }
    }
}

@Composable
internal fun BoxCardDiagnostic(data: BoxCardData) {
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .size(165.dp)
            .shadow(1.dp, PnExpertTheme.shapes.imageShapes.imageClassic15)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(radius = 400.dp),
                onClick = {

                },
            ),
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
                text = data.title,
                style = PnExpertTheme.typography.text.medium_14,
                color = PnExpertTheme.colors.textColors.FontDarkColor,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = data.image),
                contentDescription = null
            )
        }
    }
}


@Composable
private fun LongActionCard(data: LongCardData) {
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .shadow(6.dp, PnExpertTheme.shapes.imageShapes.imageClassic15)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(radius = 400.dp),
                onClick = {

                },
            ),
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
                contentScale = ContentScale.FillBounds,
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