package ru.fefu.photo_tests_impl.domain.repositories

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LifecycleOwner

interface PhotoTestsCameraRepository {
    suspend fun captureAndSaveImage(
        context:Context,
        photoPath:MutableState<Uri>
    )
    suspend fun showCameraPreview(
        previewView:PreviewView,
        lifecycleOwner: LifecycleOwner
    )
}