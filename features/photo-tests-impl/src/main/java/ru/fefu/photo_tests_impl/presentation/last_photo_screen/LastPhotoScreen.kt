package ru.fefu.photo_tests_impl.presentation.last_photo_screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import ru.fefu.presentation.components.Toolbar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LastPhotoScreen(
    modifier: Modifier = Modifier,
    photoPath:Uri
) {
    Scaffold(
        modifier = modifier,
        topBar = { Toolbar(title = "Результат")}
    ) {scaffoldPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = scaffoldPadding.calculateTopPadding())
        ) {
            Image(
                painter = rememberImagePainter(photoPath),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}