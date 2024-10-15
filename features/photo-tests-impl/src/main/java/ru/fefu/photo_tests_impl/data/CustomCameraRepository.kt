package ru.fefu.photo_tests_impl.data

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import ru.fefu.photo_tests_impl.domain.repositories.CameraRepository
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executors

class CustomCameraRepository(
    private val cameraProvider: ProcessCameraProvider,
    private val selector: CameraSelector,
    private val preview: Preview,
    private val imageAnalysis: ImageAnalysis,
    private val imageCapture: ImageCapture,
    private val barcodeScanner: BarcodeScanner,
    private val defaultDispatcher: CoroutineDispatcher
) : CameraRepository {
    override suspend fun captureAndSaveImage(context: Context, photoPath: MutableStateFlow<Uri>) {
        val name = SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss-SSS",
            Locale.ENGLISH
        ).format(System.currentTimeMillis())

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > 28) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Photo-Tests-Pictures")
            }
        }

        //for capture output
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    photoPath.value = outputFileResults.savedUri!!
                }

                override fun onError(exception: ImageCaptureException) {
                    photoPath.value = Uri.EMPTY
                    Toast.makeText(
                        context,
                        "Some error occurred ${exception.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )
    }

    @OptIn(ExperimentalGetImage::class)
    override suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        onBarcodeScanner: Boolean,
        barcodeSuccessListener: (List<Barcode>) -> Unit,
    ) {
        val cameraExecutor = Executors.newSingleThreadExecutor()

        preview.setSurfaceProvider(previewView.surfaceProvider)
        imageAnalysis.setAnalyzer(
            cameraExecutor
        ) { imageProxy ->

            val inputImage =
                InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)

            barcodeScanner.process(inputImage)
                .addOnSuccessListener(barcodeSuccessListener)
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                selector,
                preview,
                imageAnalysis,
                imageCapture
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getImageBarcodes(image: InputImage): List<Barcode>? =
        withContext(defaultDispatcher) {
            val result = async {
                val result = barcodeScanner.process(image)
                while (!result.isComplete) {
                }
                result.result
            }
            return@withContext result.await()
        }
}