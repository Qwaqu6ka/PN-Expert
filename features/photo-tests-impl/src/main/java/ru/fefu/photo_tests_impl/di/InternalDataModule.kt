package ru.fefu.photo_tests_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.fefu.photo_tests_impl.data.InternalPhotoTestsRepository
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsRepository

@Module
@InstallIn(ViewModelComponent::class)
internal interface InternalDataModule {

    @Binds
    fun bindPhotoTestRepository(repository: InternalPhotoTestsRepository): PhotoTestsRepository
}