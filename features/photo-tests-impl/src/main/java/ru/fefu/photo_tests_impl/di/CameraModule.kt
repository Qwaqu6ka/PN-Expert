package ru.fefu.photo_tests_impl.di

import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.fefu.common.di.KOIN_DEFAULT_DISPATCHER
import ru.fefu.photo_tests_api.PhotoTestsApi
import ru.fefu.photo_tests_impl.data.CustomCameraRepository
import ru.fefu.photo_tests_impl.data.InMemoryPhotoTestRepository
import ru.fefu.photo_tests_impl.domain.repositories.CameraRepository
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsRepository
import ru.fefu.photo_tests_impl.navigation.PhotoTestsImpl
import ru.fefu.photo_tests_impl.presentation.PhotoTestsViewModel

val photoTestsModule = module {
    singleOf<PhotoTestsApi>(::PhotoTestsImpl)
    singleOf<PhotoTestsRepository>(::InMemoryPhotoTestRepository)
    single<CameraRepository> {
        CustomCameraRepository(
            cameraProvider = ProcessCameraProvider.getInstance(androidApplication()).get(),
            selector = CameraSelector.Builder()
                .requireLensFacing((CameraSelector.LENS_FACING_BACK))
                .build(),
            preview = Preview.Builder().build(),
            imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
                .build(),
            imageCapture = ImageCapture.Builder()
//            .setFlashMode(FLASH_MODE_ON)
                .setTargetAspectRatio(AspectRatio.RATIO_16_9)
                .build(),
            barcodeScanner = BarcodeScanning.getClient(
                BarcodeScannerOptions.Builder()
                    .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                    .build()
            ),
            defaultDispatcher = get(qualifier = named(KOIN_DEFAULT_DISPATCHER))
        )
    }
    viewModelOf(::PhotoTestsViewModel)
}