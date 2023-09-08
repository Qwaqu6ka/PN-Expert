package ru.fefu.photo_tests_impl.data

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsCameraRepository

class CustomCameraRepository:PhotoTestsCameraRepository {
    override suspend fun captureAndSaveImage(context: Context) {
        TODO("Not yet implemented")
    }

    override suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ) {
        TODO("Not yet implemented")
    }
}