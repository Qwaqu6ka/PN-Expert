package ru.fefu.written_test_impl.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.fefu.written_test_api.WrittenTestApi
import ru.fefu.written_test_impl.navigation.WrittenTestImpl
import ru.fefu.written_test_impl.presentation.WrittenTestViewModel

val writtenTestModule = module {
    singleOf<WrittenTestApi>(::WrittenTestImpl)
    viewModelOf(::WrittenTestViewModel)
}