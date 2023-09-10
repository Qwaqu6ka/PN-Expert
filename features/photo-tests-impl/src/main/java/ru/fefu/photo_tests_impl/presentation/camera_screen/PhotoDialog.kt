package ru.fefu.photo_tests_impl.presentation.camera_screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import coil.compose.rememberImagePainter

@Composable
fun PhotoDialog(
    openDialog: MutableState<Boolean>,
    uri: Uri
) {
    Dialog(onDismissRequest = {openDialog.value = false}) {
        Image(
            painter = rememberImagePainter(uri),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}