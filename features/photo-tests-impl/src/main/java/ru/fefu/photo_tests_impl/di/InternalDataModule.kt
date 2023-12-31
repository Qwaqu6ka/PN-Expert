package ru.fefu.photo_tests_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.photo_tests_impl.data.CustomCameraRepository
import ru.fefu.photo_tests_impl.data.InMemoryPhotoTestRepository
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsCameraRepository
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalDataModule {

    @Binds
    fun bindPhotoTestRepository(repository: InMemoryPhotoTestRepository): PhotoTestsRepository

    @Binds
    fun bindCameraRepository(repository: CustomCameraRepository):PhotoTestsCameraRepository
}