package ru.fefu.photo_tests_impl.domain.repositories

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.flow.MutableStateFlow

interface PhotoTestsCameraRepository {
    suspend fun captureAndSaveImage(
        context: Context,
        photoPath: MutableStateFlow<Uri>
    )

    suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        onBarcodeScanner: Boolean,
        barcodeSuccessListener: (List<Barcode>) -> Unit,
    )

    suspend fun getImageBarcodes(image: InputImage): List<Barcode>?
}