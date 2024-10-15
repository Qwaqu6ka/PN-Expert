package ru.fefu.video_tests_impl.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.fefu.video_tests_api.VideoTestApi
import ru.fefu.video_tests_impl.navigation.VideoTestsImpl
import ru.fefu.video_tests_impl.presentation.VideoTestViewModel

val videoTestsModule = module {
    singleOf<VideoTestApi>(::VideoTestsImpl)
    viewModelOf(::VideoTestViewModel)
}