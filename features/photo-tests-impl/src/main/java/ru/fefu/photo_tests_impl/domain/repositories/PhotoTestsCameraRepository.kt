package ru.fefu.photo_tests_impl.domain.repositories

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface PhotoTestsCameraRepository {
    suspend fun captureAndSaveImage(context:Context)
    suspend fun showCameraPreview(
        previewView:PreviewView,
        lifecycleOwner: LifecycleOwner
    )
}