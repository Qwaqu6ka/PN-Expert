package ru.fefu.photo_tests_impl.presentation.photo_test_screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import ru.fefu.photo_test_impl.R
import ru.fefu.photo_tests_impl.domain.models.TestPhoto
import ru.fefu.presentation.TextCardHolder
import ru.fefu.presentation.components.Toolbar
import ru.fefu.theme.PnExpertTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhotoTestScreen(
    viewModel: PhotoTestScreenViewModel,
    modifier: Modifier,
    onNavigateToGuide: () -> Unit,
    onNavigateToCamera: () -> Unit,
    onNavigateToNextPage: () -> Unit,
    onNavigateToResult: () -> Unit
) {
    val testIsSuccess = {
        viewModel.photoPath.value != Uri.EMPTY
    }
    println(viewModel.testPage)
   Scaffold(
       topBar = { Toolbar(title = "Сделать фото", onBackPressed = {onNavigateToGuide()})},
       containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor,
       modifier = modifier,
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
           if (!testIsSuccess())
               TextCardHolder(modifier = Modifier.fillMaxWidth(), text = viewModel.testData.value!!.testTask.taskName)
           Spacer(modifier = Modifier.weight(1f))
           Spacer(modifier = Modifier.height(16.dp))
           PhotoResult(photoPath = viewModel.photoPath.value, guidePhoto = viewModel.testData.value!!.testGuidePhoto, modifier = Modifier.height(500.dp))
           Spacer(modifier = Modifier.height(16.dp))
           Spacer(modifier = Modifier.weight(1f))
           Row (
               modifier = Modifier.fillMaxWidth()
           ){
               PhotoButton(onNavigateToCamera)
               Spacer(modifier = Modifier.width(16.dp))
               DownloadButton(
                   setUri = {uri: Uri -> viewModel.setPhotoPath(uri) }
               )
           }
           Spacer(modifier = Modifier.height(16.dp))
           NextButton(testIsSuccess(), onNavigateToNextPage,{viewModel.addAnswer()}, onNavigateToResult, viewModel.isLastTest.value)
           Spacer(modifier = Modifier.height(8.dp))
       }
   }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun PhotoResult(
    photoPath: Uri,
    guidePhoto: TestPhoto,
    modifier: Modifier = Modifier
){
    val resultPhoto = rememberImagePainter(photoPath)

    if (resultPhoto.state is ImagePainter.State.Loading){
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            color = PnExpertTheme.colors.mainAppColors.AppBlueColor,
            strokeWidth = 2.dp
        )
    }
    if (resultPhoto.state is ImagePainter.State.Empty){
        GuidePhoto(guidePhoto)
    }
    if (resultPhoto.state is ImagePainter.State.Error){
        GuidePhoto(guidePhoto)
    }
    else{
        Card(
            modifier = modifier,
            shape = PnExpertTheme.shapes.imageShapes.imageClassic15,
            border = BorderStroke(1.dp, PnExpertTheme.colors.mainAppColors.AppBlueColor)
        ){
            Image(
                painter = resultPhoto,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
private fun GuidePhoto(guidePhoto: TestPhoto){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = PnExpertTheme.shapes.imageShapes.imageClassic15,
            border = BorderStroke(1.dp, PnExpertTheme.colors.mainAppColors.AppDarkColor)
        ) {
            Image(
                modifier = Modifier
                    .clip(PnExpertTheme.shapes.imageShapes.imageClassic15),
                painter = painterResource(id = guidePhoto.drawableId),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
        }
        Text(
            text = "Пример выполненного задания",
            style = PnExpertTheme.typography.text.medium_16,
            color = PnExpertTheme.colors.textColors.FontDarkColor
        )
    }
}

@Composable
private fun DownloadButton(setUri: (Uri) -> Unit){
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            setUri(uri!!)
        }

    TextButton(
        onClick = {launcher.launch("image/*")},
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
private fun PhotoButton(onNavigateToCamera: ()->Unit){
    Button(
        onClick = {onNavigateToCamera()},
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
private fun NextButton(
    testIsSuccess: Boolean,
    onNavigateToNextPage: () -> Unit,
    buttonMotion: () -> Unit,
    onNavigateToResult: () -> Unit,
    isLastTest:Boolean
){
    TextButton(
        onClick = {
            buttonMotion()
            if(isLastTest){
                onNavigateToResult()
            }else{
                onNavigateToNextPage()
            }
          },
        modifier = Modifier
            .fillMaxWidth()
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        enabled = testIsSuccess,
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