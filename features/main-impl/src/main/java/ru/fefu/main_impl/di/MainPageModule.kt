package ru.fefu.main_impl.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.fefu.main_api.MainPageApi
import ru.fefu.main_impl.navigation.MainPageImpl

val mainPageModule = module {
    singleOf<MainPageApi>(::MainPageImpl)
}