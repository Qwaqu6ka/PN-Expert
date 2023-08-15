package ru.fefu.written_test_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.written_test_api.WrittenTestApi
import ru.fefu.written_test_impl.navigation.WrittenTestImpl

@Module
@InstallIn(SingletonComponent::class)
interface WrittenTestModule {

    @Binds
    fun bindWrittenTest(testImpl: WrittenTestImpl): WrittenTestApi
}