package ru.fefu.photo_tests_impl.presentation.result_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.fefu.photo_test_impl.R
import ru.fefu.presentation.TextCardHolder
import ru.fefu.presentation.components.Toolbar
import ru.fefu.theme.PnExpertTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ResultScreen(
    modifier: Modifier,
) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.White,
        topBar = { Toolbar(title = "Результат теста", onBackPressed = {}) },
    ) {scaffoldTopPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = scaffoldTopPadding.calculateTopPadding())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            ResultHolder()
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier.weight(1f))
            SendButton()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun ResultHolder(){
    for (i in 0..1){
        ResultItem()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun ResultItem(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextCardHolder(
            modifier = Modifier.fillMaxWidth(),
            text = "Нарислвать часы еще раз и сфотографировать результат",
            titleText = "Задание 1"
        )
        Spacer(modifier = Modifier.height(4.dp))
        Card(
            shape = PnExpertTheme.shapes.imageShapes.imageClassic15,
            border = BorderStroke(1.dp, PnExpertTheme.colors.mainAppColors.AppBlueColor)
        ){
            Image(
                painter = painterResource(id = R.drawable.photo_test_clock),
                contentDescription = null,
            )
        }

    }
}

@Composable
private fun SendButton(){
    TextButton(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        colors = ButtonDefaults.textButtonColors(
            containerColor = PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor,
            disabledContainerColor = PnExpertTheme.colors.buttonColors.ButtonInactiveColor
        )
    ) {
        Text(
            text = "Отправить и завершить тест",
            style = PnExpertTheme.typography.subtitle.medium_18,
            color = PnExpertTheme.colors.textColors.FontWhiteColor
        )
    }
}