package ru.fefu.video_tests_impl.di;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0006"}, d2 = {"Lru/fefu/video_tests_impl/di/VideoTestsModule;", "", "bindVideoTests", "Lru/fefu/video_tests_api/VideoTestApi;", "impl", "Lru/fefu/video_tests_impl/navigation/VideoTestsImpl;", "video-tests-impl_debug"})
@dagger.Module
public abstract interface VideoTestsModule {
    
    @org.jetbrains.annotations.NotNull
    @dagger.Binds
    public abstract ru.fefu.video_tests_api.VideoTestApi bindVideoTests(@org.jetbrains.annotations.NotNull
    ru.fefu.video_tests_impl.navigation.VideoTestsImpl impl);
}