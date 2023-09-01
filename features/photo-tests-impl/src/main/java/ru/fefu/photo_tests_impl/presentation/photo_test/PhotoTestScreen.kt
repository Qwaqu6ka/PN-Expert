package ru.fefu.photo_tests_impl.presentation.photo_test

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.fefu.photo_test_impl.R
import ru.fefu.photo_tests_impl.domain.models.TestPhoto
import ru.fefu.photo_tests_impl.presentation.guide_screen.elements.GuidePhotosHolder
import ru.fefu.presentation.TextCardHolder
import ru.fefu.presentation.components.Toolbar
import ru.fefu.theme.PnExpertTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhotoTestScreen(
    modifier: Modifier,
    onNavigateToGuide: () -> Unit
) {
   Scaffold(
       topBar = { Toolbar(title = "Сделать фото", onBackPressed = {onNavigateToGuide()})},
       modifier = modifier,
       containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor
   ) {scaffoldTopPadding->
       Column(
           modifier = Modifier
               .fillMaxSize()
               .verticalScroll(rememberScrollState())
               .padding(horizontal = 16.dp)
               .padding(scaffoldTopPadding)
       ) {
           Spacer(modifier = Modifier.height(8.dp))
           TextCardHolder(
               modifier = Modifier.fillMaxWidth(),
               text = "Выполните задание и сфотографируйте или загрузите результат."
           )
           Spacer(modifier = Modifier.weight(1f))
           Column(
               modifier = Modifier.fillMaxWidth(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               GuidePhoto()
               Text(
                   text = "Пример выполненного задания",
                   style = PnExpertTheme.typography.text.medium_16,
                   color = PnExpertTheme.colors.textColors.FontDarkColor
               )
           }
           Spacer(modifier = Modifier.weight(1f))
           Row (
               modifier = Modifier.fillMaxWidth()
           ){
               PhotoButton()
               Spacer(modifier = Modifier.width(16.dp))
               DownloadButton()
           }
           Spacer(modifier = Modifier.height(16.dp))
           NextButton()
       }
   }
}

@Composable
private fun GuidePhoto(){
    Card(
        modifier = Modifier
            .fillMaxHeight()
            .height(200.dp),
        shape = PnExpertTheme.shapes.imageShapes.imageClassic15,
        border = BorderStroke(1.dp, PnExpertTheme.colors.mainAppColors.AppDarkColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(PnExpertTheme.shapes.imageShapes.imageClassic15),
            painter = painterResource(id = R.drawable.photo_test_clock),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
    }
}

@Composable
private fun DownloadButton(){
    TextButton(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        border = BorderStroke(2.dp, PnExpertTheme.colors.mainAppColors.AppBlueColor),
        colors = ButtonDefaults.textButtonColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
            contentColor = PnExpertTheme.colors.mainAppColors.AppBlueColor
        )
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = "Загрузить фото",
                style = PnExpertTheme.typography.subtitle.medium_18
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                painter = painterResource(id = R.drawable.download_icon),
                contentDescription = "download_icon"
            )
        }
    }
}

@Composable
private fun PhotoButton(){
    Button(
        onClick = {},
        modifier = Modifier
            .size(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        colors = ButtonDefaults.buttonColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppBlueColor
        ),
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        border = BorderStroke(2.dp, PnExpertTheme.colors.mainAppColors.AppBlueColor),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add_photo_icon),
            contentDescription = "add_photo_icon",
            tint = PnExpertTheme.colors.mainAppColors.AppWhiteColor
        )
    }
}

@Composable
private fun NextButton(){
    TextButton(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        enabled = false,
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        colors = ButtonDefaults.textButtonColors(
            containerColor = PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor,
            disabledContainerColor = PnExpertTheme.colors.buttonColors.ButtonInactiveColor
        )
    ) {
        Text(
            text = "Продолжить",
            style = PnExpertTheme.typography.subtitle.medium_18,
            color = PnExpertTheme.colors.textColors.FontWhiteColor
        )
    }
}