package ru.fefu.data.written_tests.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.data.written_tests.sources.InMemoryWrittenTestDataSource
import ru.fefu.data.written_tests.sources.WrittenTestDataSource

@Module
@InstallIn(SingletonComponent::class)
interface WrittenTestModule {

    @Binds
    fun bindWrittenTestDataSource(source: InMemoryWrittenTestDataSource): WrittenTestDataSource
}