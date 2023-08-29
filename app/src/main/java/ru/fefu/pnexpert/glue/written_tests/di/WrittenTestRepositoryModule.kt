package ru.fefu.pnexpert.glue.written_tests.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.pnexpert.glue.written_tests.repositories.AdapterWrittenTestRepository
import ru.fefu.written_test_impl.domain.WrittenTestRepository

@Module
@InstallIn(SingletonComponent::class)
interface WrittenTestRepositoryModule {

    @Binds
    fun bindWrittenTestRepo(repo: AdapterWrittenTestRepository): WrittenTestRepository
}