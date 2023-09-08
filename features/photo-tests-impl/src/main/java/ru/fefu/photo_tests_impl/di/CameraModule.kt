package ru.fefu.photo_tests_impl.di

import android.app.Application
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.FLASH_MODE_ON
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CameraModule {

    @Provides
    @Singleton
    fun provideCameraSelector():CameraSelector{
        //for camera selection front or back
        return CameraSelector.Builder()
            .requireLensFacing((CameraSelector.LENS_FACING_BACK))
            .build()
    }

    @Provides
    @Singleton
    fun provideCameraProvider(application: Application)
    : ProcessCameraProvider {
        //for providing camera instance
        return ProcessCameraProvider.getInstance(application).get()
    }

    @Provides
    @Singleton
    fun provideCameraPreview():Preview{
        //for previewing whatever behind the camera
        return Preview.Builder().build()
    }

    @Provides
    @Singleton
    fun provideImageCapture(): ImageCapture{
        //for capturing image you can select aspect ratio
        //either 16_9 or 4_3
        //you can set flash type if its a back camera some devices
        return ImageCapture.Builder()
            .setFlashMode(FLASH_MODE_ON)
            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
            .build()
    }

    @Provides
    @Singleton
    fun provideImageAnalysis():ImageAnalysis{
        //this is for analyzing the image before capturing
        return ImageAnalysis.Builder()
            .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
            .build()
    }
}