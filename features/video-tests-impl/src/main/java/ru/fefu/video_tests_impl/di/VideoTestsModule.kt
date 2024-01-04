package ru.fefu.video_tests_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.video_tests_api.VideoTestApi
import ru.fefu.video_tests_impl.navigation.VideoTestsImpl

@Module
@InstallIn(SingletonComponent::class)
interface VideoTestsModule {

    @Binds
    fun bindVideoTests(impl: VideoTestsImpl): VideoTestApi
}