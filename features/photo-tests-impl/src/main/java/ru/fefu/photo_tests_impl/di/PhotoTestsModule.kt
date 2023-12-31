package ru.fefu.photo_tests_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.photo_tests_api.PhotoTestsApi
import ru.fefu.photo_tests_impl.navigation.PhotoTestsImpl

@Module
@InstallIn(SingletonComponent::class)
interface PhotoTestsModule {
    @Binds
    fun bindPhotoTestsImpl(photoTestsImpl: PhotoTestsImpl): PhotoTestsApi
}