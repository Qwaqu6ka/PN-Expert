package ru.fefu.photo_tests_impl.domain.repositories

import android.content.Context
import android.net.Uri
import androidx.camera.core.ImageProxy
import androidx.camera.view.PreviewView
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScanner

interface PhotoTestsCameraRepository {
    suspend fun captureAndSaveImage(
        context:Context,
        photoPath:MutableState<Uri>
    )
    suspend fun showCameraPreview(
        previewView:PreviewView,
        lifecycleOwner: LifecycleOwner,
        onBarcodeScanner: Boolean,
        barcodeScannerListener: (barcodeScanner: BarcodeScanner, imageProxy: ImageProxy) -> Unit
    )
}