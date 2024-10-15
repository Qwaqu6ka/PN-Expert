package ru.fefu.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val KOIN_DEFAULT_DISPATCHER = "default_dispatcher"
const val KOIN_IO_DISPATCHER = "io_dispatcher"

val coroutinesModule = module {
    single<CoroutineDispatcher>(named(KOIN_DEFAULT_DISPATCHER)) { Dispatchers.Default }
    single<CoroutineDispatcher>(named(KOIN_IO_DISPATCHER)) { Dispatchers.IO }
}