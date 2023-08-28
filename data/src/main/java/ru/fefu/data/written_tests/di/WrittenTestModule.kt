package ru.fefu.data.written_tests.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.data.WrittenTestDataRepository
import ru.fefu.data.written_tests.RealWrittenTestDataRepository
import ru.fefu.data.written_tests.sources.DatabaseWrittenTestDataSource
import ru.fefu.data.written_tests.sources.WrittenTestDataSource

@Module
@InstallIn(SingletonComponent::class)
interface WrittenTestModule {

    @Binds
    fun bindWrittenTestDataSource(source: DatabaseWrittenTestDataSource): WrittenTestDataSource

    @Binds
    fun bindWrittenTestDataRepository(repo: RealWrittenTestDataRepository): WrittenTestDataRepository
}