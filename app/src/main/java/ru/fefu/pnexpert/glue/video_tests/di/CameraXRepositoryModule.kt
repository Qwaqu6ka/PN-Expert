package ru.fefu.pnexpert.glue.video_tests.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.pnexpert.glue.video_tests.AdapterCameraXRepository
import ru.fefu.video_tests_impl.domain.CameraXRepository

@Module
@InstallIn(SingletonComponent::class)
interface CameraXRepositoryModule {
    @Binds
    fun bindCameraXRepository(impl: AdapterCameraXRepository): CameraXRepository
}