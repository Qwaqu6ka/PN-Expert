package ru.fefu.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.common.MlKitException
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.fefu.camera.VisionImageProcessor
import ru.fefu.camera.presentation.GraphicOverlay
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CameraXDataRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val cameraProvider: ProcessCameraProvider =
        ProcessCameraProvider.getInstance(context).get()

    private val preview: Preview = Preview.Builder().build()
    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK
    private val cameraSelector: CameraSelector
        get() = CameraSelector.Builder()
            .requireLensFacing(lensFacing)
            .build()

    private var lifecycleOwner: LifecycleOwner? = null
    private var camera: Camera? = null

    private val imageAnalysis = ImageAnalysis.Builder().build() // todo надо re-bind?
    private var graphicOverlay: GraphicOverlay? = null

    private fun initCamera() {
        cameraProvider.unbindAll()
        camera = lifecycleOwner?.let { lifecycleOwner ->
            cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageAnalysis)
        }
    }

    fun bindPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner) {
        preview.setSurfaceProvider(previewView.surfaceProvider)
        this.lifecycleOwner = lifecycleOwner
        Log.d("rome4", "bindPreview")
        initCamera()
    }

    fun setGraphicOverlay(graphicOverlay: GraphicOverlay) {
        this.graphicOverlay = graphicOverlay
        Log.d("rome4", "setGraphicOverlay")
    }

    fun changeCameraFacing() {
        lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK) {
            CameraSelector.LENS_FACING_FRONT
        } else {
            CameraSelector.LENS_FACING_BACK
        }
        initCamera()
    }

    fun clearCamera() {
        cameraProvider.unbindAll()
    }

    fun clearAnalyzer() = imageAnalysis.clearAnalyzer()

    fun addPoseAnalyzer(imageProcessor: VisionImageProcessor) {
        if (graphicOverlay != null) {
            Log.d("rome4", "add pose analyzek")
            imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(context)) { imageProxy ->
                val isImageFlipped = lensFacing == CameraSelector.LENS_FACING_FRONT
                val rotationDegrees = imageProxy.imageInfo.rotationDegrees
                if (rotationDegrees == 0 || rotationDegrees == 180) {
                    graphicOverlay?.setImageSourceInfo(
                        imageProxy.width,
                        imageProxy.height,
                        isImageFlipped
                    )
                } else {
                    graphicOverlay?.setImageSourceInfo(
                        imageProxy.height,
                        imageProxy.width,
                        isImageFlipped
                    )
                }
                try {
                    imageProcessor.processImageProxy(
                        imageProxy,
                        graphicOverlay
                    )
                } catch (e: MlKitException) {
                    Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @OptIn(ExperimentalGetImage::class)
    fun addBarcodeAnalyzer(barcodeSuccessListener: (List<Barcode>) -> Unit) {
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
        val barcodeScanner = BarcodeScanning.getClient(options)

        imageAnalysis.setAnalyzer(Executors.newSingleThreadExecutor()) { imageProxy ->
            val inputImage =
                InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)

            barcodeScanner.process(inputImage)
                .addOnSuccessListener(barcodeSuccessListener)
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }
}